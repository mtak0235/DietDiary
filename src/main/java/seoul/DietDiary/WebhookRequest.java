package seoul.DietDiary;

import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2OriginalDetectIntentRequest;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2QueryResult;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("webhookRequest")
public class WebhookRequest {

    private String responseId;
    private GoogleCloudDialogflowV2QueryResult queryResult;
    private GoogleCloudDialogflowV2OriginalDetectIntentRequest originalDetectIntentRequest;

    public WebhookRequest(String responseId, GoogleCloudDialogflowV2QueryResult queryResult, GoogleCloudDialogflowV2OriginalDetectIntentRequest originalDetectIntentRequest) {
        this.responseId = responseId;
        this.queryResult = queryResult;
        this.originalDetectIntentRequest = originalDetectIntentRequest;
    }

    public WebhookRequest() {
    }


    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public GoogleCloudDialogflowV2QueryResult getQueryResult() {
        return queryResult;
    }

    public void setQueryResult(GoogleCloudDialogflowV2QueryResult queryResult) {
        this.queryResult = queryResult;
    }

    public GoogleCloudDialogflowV2OriginalDetectIntentRequest getOriginalDetectIntentRequest() {
        return originalDetectIntentRequest;
    }

    public void setOriginalDetectIntentRequest(GoogleCloudDialogflowV2OriginalDetectIntentRequest originalDetectIntentRequest) {
        this.originalDetectIntentRequest = originalDetectIntentRequest;
    }

    @Override
    public String toString() {
        return "WebhookRequest{" +
                "responseId='" + responseId + '\'' +
                ", queryResult=" + queryResult +
                ", originalDetectIntentRequest=" + originalDetectIntentRequest +
                '}';
    }
}
