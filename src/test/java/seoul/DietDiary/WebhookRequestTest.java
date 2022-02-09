package seoul.DietDiary;

import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2QueryResult;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class WebhookRequestTest {
    @Autowired()
    private WebhookRequestRepository webhookRequestRepository;

    @Test
    public void printProjectData() {
        System.out.println(webhookRequestRepository);
        WebhookRequest dto = new WebhookRequest();
        dto.setResponseId("huchoi2");
        GoogleCloudDialogflowV2QueryResult queryResult = new GoogleCloudDialogflowV2QueryResult();
        queryResult.setAction("huchoi huchoi huchoi action");
        dto.setQueryResult(queryResult);
        webhookRequestRepository.save(dto);

        String test = "huchoi huchoi huchoi action";
        System.out.println("출력테스트 시작");
        System.out.println(webhookRequestRepository.findByResponseId("huchoi2"));
        System.out.println(webhookRequestRepository.findAll());
        System.out.println("출력 테스트 끝");
    }

    /**
     * "토마토 2개를 먹었다" 라는 문자열에서
     * "토마토"라는 문자열 오른쪽에 오는 문자열 "2개를 먹었다" 를 얻어내기위해
     * "토마토"와 "2개를" 사이의 개행은 뛰어넘자.
     */
    @Test
    public void getRightString(){
        String shorter = "hello";
        String longer = "hi hello      new world";

        int index = longer.indexOf(shorter);
        int new_index = index + shorter.length();//String에는 operator[] 메서드가 없음
        while (Character.isSpaceChar(longer.charAt(new_index))){
            new_index++;
        }
        System.out.println(longer.substring(new_index));
    }

    @Test
    public void isMatchWithParameter(){

        //given
        String userText = "토마토 2개와 샐러드를 그리고 바나나를 3개, 그리고 양배추를 먹었다";
        ArrayList<Integer> numberParameters = new ArrayList<>();
        ArrayList<String> foodParameters = new ArrayList<>();

        numberParameters.add(2);
        numberParameters.add(3);
        foodParameters.add("토마토");
        foodParameters.add("샐러드");
        foodParameters.add("바나나");
        foodParameters.add("양배추");

        //when
        int foodIdx = 0;
        int number = 0;
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
                number = number * 10;
                number = longer.charAt(tempIdx) - '0';
                tempIdx++;
                System.out.println("개수가 존재합니다 그 개수는: " + number);
            }
            if(number != 0 && number != numberParameters.get(foodIdx)){
                System.out.println(number + " vs " + numberParameters.get(foodIdx) + "&& foodIndex: " + foodIdx);
                throw new RuntimeException("개수가 일치하지 않습니다");
            }
            else if (number != 0){
                //nothing to do
            }
            else{
//                System.out.println("지금 단계는 " + foodParameters.get(foodIdx).toString() + "그리고 rightString은" + longer.substring(tempIdx));
                numberParameters.add(foodIdx, 1);
            }
            number = 0;
            foodIdx++;
        }
        //then
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(0, 2);
        arr.add(1, 1);
        arr.add(2, 3);
        arr.add(3, 1);

        System.out.println(arr);
        System.out.println(numberParameters);
    }

    @Test
    public void bigDecimalTest(){
        BigDecimal dec = new BigDecimal(3);
        System.out.println(dec.intValue());
    }
}