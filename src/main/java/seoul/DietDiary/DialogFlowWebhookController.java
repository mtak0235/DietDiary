package seoul.DietDiary;

import com.google.api.client.json.Json;
import com.google.api.client.json.JsonGenerator;
import com.google.api.services.dialogflow.v3.model.GoogleCloudDialogflowV2WebhookRequest;
import com.google.api.services.dialogflow.v3.model.GoogleCloudDialogflowV2WebhookResponse;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DialogFlowWebhookController {
    @RequestMapping(value = "/google/webhook.do", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody JSONObject webhook(HttpServletRequest request, @RequestBody Map<String, Object> map) {
//        System.out.println("webhook=====" + map);

        JSONObject jsonObject = new JSONObject();
        JSONObject payloadObject = new JSONObject();
        JSONObject googleObject = new JSONObject();
        JSONObject eventObject = new JSONObject();

        LinkedHashMap<String, Object> queryResult = getQueryResult(map);
        System.out.println(getResponseId(map));
        System.out.println(getSession(map));
        System.out.println(getQueryText(map));
        System.out.println(getParameters(map));
        System.out.println(getAllRequiredParamsPresent(map));
        System.out.println(getFulfillmentText(map));
        System.out.println(getOutputContexts(map));
        System.out.println(getIntent(map));
        System.out.println(getIntentDetectionConfidence(map));
        System.out.println(getDiagnosticInfo(map));
        System.out.println(getLanguageCode(map));
        System.out.println(getOriginalDetectIntentRequest(map));

        googleObject.put("expectUserResponse", true);
        payloadObject.put("google", googleObject);
        jsonObject.put("fulfillmentText", "webhook service에서 대답을 생성합니다.");
        jsonObject.put("payload", payloadObject);

        return jsonObject;
    }

    String getResponseId(Map<String, Object> map){
        return (String) map.get("responseId");
    }

    String getSession(Map<String, Object> map){
        return (String) map.get("session");
    }

    LinkedHashMap<String, Object> getQueryResult(Map<String, Object> map){
        return (LinkedHashMap<String, Object>) map.get("queryResult");
    }

    String getQueryText(Map<String, Object> map){
        LinkedHashMap<String, Object> queryResult = getQueryResult(map);
        return (String) queryResult.get("queryText");
    }

    LinkedHashMap<String, Object> getParameters(Map<String, Object> map){
        LinkedHashMap<String, Object> queryResult = getQueryResult(map);
        return (LinkedHashMap<String, Object>) queryResult.get("parameters");
    }


    Boolean getAllRequiredParamsPresent(Map<String, Object> map){
        LinkedHashMap<String, Object> queryResult = getQueryResult(map);
        return (Boolean) queryResult.get("allRequiredParamsPresent");
    }

    String getFulfillmentText(Map<String, Object> map){
        LinkedHashMap<String, Object> queryResult = getQueryResult(map);
        return (String) queryResult.get("fulfillmentText");
    }

//    LinkedHashMap<String, Object> getFulfillmentMessages(LinkedHashMap<String, Object> map){
//        LinkedHashMap<String, Object> queryResult = getQueryResult(map);
//        return (LinkedHashMap<String, Object>) queryResult.get("fulfillmentMessages");
//    } 일단 보류

    List<LinkedHashMap<String, Object>> getOutputContexts(Map<String, Object> map){
        LinkedHashMap<String, Object> queryResult = getQueryResult(map);
        return (List<LinkedHashMap<String, Object>>) queryResult.get("outputContexts");
    }

    LinkedHashMap<String, Object> getIntent(Map<String, Object> map){
        LinkedHashMap<String, Object> queryResult = getQueryResult(map);
        return (LinkedHashMap<String, Object>) queryResult.get("intent");
    }

    Double getIntentDetectionConfidence(Map<String, Object> map){
        LinkedHashMap<String, Object> queryResult = getQueryResult(map);
        return (Double) queryResult.get("intentDetectionConfidence");
    }

    LinkedHashMap<String, Object> getDiagnosticInfo(Map<String, Object> map){
        LinkedHashMap<String, Object> queryResult = getQueryResult(map);
        return (LinkedHashMap<String, Object>) queryResult.get("diagnosticInfo");
    }

    String getLanguageCode(Map<String, Object> map){
        LinkedHashMap<String, Object> queryResult = getQueryResult(map);
        return (String) queryResult.get("languageCode");
    }

    LinkedHashMap<String, Object> getOriginalDetectIntentRequest(Map<String, Object> map){
        return (LinkedHashMap<String, Object>) map.get("originalDetectIntentRequest");
    }
}

