package seoul.DietDiary;

import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.dialogflow.v2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Array;
import java.util.*;

@RestController
public class DialogFlowWebhookController {

    private static JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
    @Autowired()
    WebhookRequestRepository webhookRequestRepository;

    @PostMapping("/google/webhook.do")
    public Mono<String> webhook(@RequestBody String rawRequest) throws IOException {
        GoogleCloudDialogflowV2WebhookRequest request = jacksonFactory.createJsonParser(rawRequest)
                .parse(GoogleCloudDialogflowV2WebhookRequest.class);

//        WebhookRequest dto = new WebhookRequest(request.clone().getResponseId(), request.clone().getQueryResult(), request.clone().getOriginalDetectIntentRequest());
//        webhookRequestRepository.save(dto);//mongodb 에 저장해보려고 했는데, 여기서 에러 떠서 막힘...


        StringWriter stringWriter = new StringWriter();
        JsonGenerator jsonGenerator = jacksonFactory.createJsonGenerator(stringWriter);
        GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();

        GoogleCloudDialogflowV2QueryResult queryResult = request.getQueryResult();
        GoogleCloudDialogflowV2Intent intent = queryResult.getIntent();
        String displayName = intent.getDisplayName();
        String fulfillmentText = queryResult.getFulfillmentText();
        String userText = queryResult.getQueryText();
        Map<String, Object> parameters = queryResult.getParameters();
        List<GoogleCloudDialogflowV2Context> outputContexts = queryResult.getOutputContexts();
        GoogleCloudDialogflowV2Context googleCloudDialogflowV2Context = outputContexts.get(1);
        Map<String, Object> parameters1 = googleCloudDialogflowV2Context.getParameters();
        if (displayName.equals("식단 기록 받기?")){
            List<String> list = new ArrayList<>();
            String confirm = "더미 스트링입니다";
            list = parse_text_and_confirm(parameters, userText);
            List<GoogleCloudDialogflowV2Context> outputContext = new ArrayList<>();
            GoogleCloudDialogflowV2Context contextElement = new GoogleCloudDialogflowV2Context();
            String name = request.getSession() + "/contexts/-followup2";
            contextElement.setName(name);
            contextElement.setLifespanCount(2);
            Map<String, Object> obj = new LinkedHashMap<>();
            obj.put("food-number", list);
            contextElement.setParameters(obj);
            outputContext.add(contextElement);
            response.put("outputContexts", outputContext);//꼭 이런 형식으로 해줘야함. response.setOutputContext(~~~) 메서드 쓰지말자!!!!!!!!!**

            confirm = "";
            int tempIdx = 0;
            while(tempIdx < list.size()){
                confirm += "[";
                confirm += list.get(tempIdx++);
                confirm += "의 개수는 ";
                confirm += list.get(tempIdx++);
                confirm += "개] ";
            }
            confirm += "수량이 맞습니까?";
            response.put("fulfillmentText", confirm);
        }
        else if (displayName.equals("식단 기록 받기? - custom")){
            //DB에 저장하기 위해선 아까 parameters가 필요한데... 어떻게 들고올까?? -> 해결!
            GoogleCloudDialogflowV2Context givenContext1 = request.getQueryResult().getOutputContexts().get(0);//0번 index 에 들어가 있음
            GoogleCloudDialogflowV2Context givenContext2 = request.getQueryResult().getOutputContexts().get(1);//
            System.out.println("테스트 출력입니다0  " + givenContext1);
            System.out.println("테스트 출력입니다1  " + givenContext2);
            response.put("fulfillmentText", "예 알겠습니다! 저장 완료!!");
        }
        else if (displayName.equals("식단 기록 받기? - custom-2")){
            Map<String, String> event = new HashMap<String, String>();
            GoogleCloudDialogflowV2EventInput eventInput = new GoogleCloudDialogflowV2EventInput();
            event.put("name", "ask-food");
            eventInput.put("name", "retry");//여기로 보내는 방법 밖에 없지 않나..? 좀 더 세련되게 할수 있으까?
            //ask-food로 보내면 큰 문제이고, 인사말쪽으로 보내면 그나마 덜 문제가 될듯?
            response.put("followupEventInput", eventInput);
        }
        else{
            response.put("fulfillmentText", "어디에도 해당하지 않는 intent입니다!");
        }
        jsonGenerator.serialize(response);
        jsonGenerator.flush();
        return Mono.just(stringWriter.toString());
    }

    private List<String> parse_text_and_confirm(Map<String, Object> parameters, String fulfillmentText) {
        String ret2 = new String();
        List<String> ret = new ArrayList<>();
        String userText = fulfillmentText;
        List<String> foodParameters = (List<String>) parameters.get("food");
        List<BigDecimal> numberParameters = (List<BigDecimal>) parameters.get("number");


        int foodIdx = 0;
        BigDecimal number = new BigDecimal(0);
        String longer = userText;
        String shorter;
        int tempIdx;
        while (foodIdx < foodParameters.size()){
            //getRightString
            shorter = foodParameters.get(foodIdx);
            tempIdx = longer.indexOf(shorter);
            tempIdx += shorter.length();
            while (!Character.isSpaceChar(longer.charAt(tempIdx))){
                tempIdx++;
            }
            while (Character.isSpaceChar(longer.charAt(tempIdx))){
                tempIdx++;
            }
//            longer = longer.substring(tempIdx);//혹시나 해서...

            //getFirstNumberOfString
            while (Character.isDigit(longer.charAt(tempIdx))){
                number = number.multiply(new BigDecimal(10));
                number = new BigDecimal(longer.charAt(tempIdx) - '0');
                tempIdx++;
//                System.out.println("지금 단계는 " + foodParameters.get(foodIdx).toString() + "그리고 rightString은" + longer.substring(tempIdx));
//                System.out.println("개수가 존재합니다 그 개수는: " + number);
            }
            if(number.intValue() != 0 && number.intValue() != numberParameters.get(foodIdx).intValue()){
//                System.out.println("지금 단계는 " + foodParameters.get(foodIdx).toString() + "그리고 rightString은" + longer.substring(tempIdx));
//                System.out.println(number + " vs " + numberParameters.get(foodIdx) + "&& foodIndex: " + foodIdx);
                throw new RuntimeException("개수가 일치하지 않습니다");
            }
            else if (number.intValue() != 0){
                //nothing to do
            }
            else{
//                System.out.println("지금 단계는 " + foodParameters.get(foodIdx).toString() + "그리고 rightString은" + longer.substring(tempIdx));
                numberParameters.add(foodIdx, new BigDecimal(1));
            }
            number = new BigDecimal(0);
            foodIdx++;
        }
        ArrayList<Integer> arr = new ArrayList<>();
        for (BigDecimal numberParameter : numberParameters) {
            System.out.println(numberParameter);
        }
        int iter = 0;
        while(iter < foodParameters.size()){
            ret.add(foodParameters.get(iter));
            ret.add(numberParameters.get(iter).toString());
            iter++;
        }
        return ret;
    }
}
