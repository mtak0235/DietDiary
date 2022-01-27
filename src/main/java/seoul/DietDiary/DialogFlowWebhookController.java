package seoul.DietDiary;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2QueryResult;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookRequest;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@RestController
public class DialogFlowWebhookController {

    private static JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();

    @PostMapping("/google/webhook.do")
    public Mono<String> webhook(@RequestBody String rawRequest) throws IOException {

        GoogleCloudDialogflowV2WebhookRequest request = jacksonFactory.createJsonParser(rawRequest)
                .parse(GoogleCloudDialogflowV2WebhookRequest.class);
        System.out.println("=================");
        System.out.println(request.get("queryResult"));
        GoogleCloudDialogflowV2QueryResult queryResult = request.getQueryResult();
        System.out.println(queryResult);
        System.out.println("=================");

        StringWriter stringWriter = new StringWriter();
        JsonGenerator jsonGenerator = jacksonFactory.createJsonGenerator(stringWriter);
        GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();
        response.put("fulfillmentText", "GoogleCloudDialogflowV2WebhookRequest을 사용해서 JSON을 파싱해봆시다.");
        jsonGenerator.serialize(response);
        jsonGenerator.flush();
        return Mono.just(stringWriter.toString());
    }
}


//@Controller
//@RequiredArgsConstructor
//public class DialogFlowWebhookController {
//    private final JsonFactory jsonFactory;
//
//    @Override
//    public String toString() {
//        return "DialogFlowWebhookController{" +
//                "jsonFactory=" + jsonFactory +
//                '}';
//    }
//
//    @RequestMapping(value = "/google/webhook.do", method = {RequestMethod.GET, RequestMethod.POST})
//    public ResponseEntity<?> webhook(HttpServletRequest servletRequest, @RequestBody String reqBody) throws IOException {
//            String email = servletRequest.getHeader("username");
//            String password = servletRequest.getHeader("password");
////            this.userController.Authenticate(new User(email, password, null, null));
//            GoogleCloudDialogflowV2WebhookRequest request = jsonFactory.createJsonParser(reqBody).parse(GoogleCloudDialogflowV2WebhookRequest.class);
//            GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();
//            Map<String, Object> params = request.getQueryResult().getParameters();
//            System.out.println("====" + request);
//            if (params.size() > 0) {
////                response.setFulfillmentText(dialogController.processMessage(params));
//            } else {
//                response.setFulfillmentText("sorry you didnt send enough to process");
//            }
//            return new ResponseEntity<GoogleCloudDialogflowV2WebhookResponse>(response, HttpStatus.OK);
//    }
//}

