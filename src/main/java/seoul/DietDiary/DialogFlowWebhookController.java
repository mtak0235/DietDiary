package seoul.DietDiary;

import com.google.api.client.json.JsonFactory;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookRequest;
import com.google.api.services.dialogflow.v2.model.GoogleCloudDialogflowV2WebhookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class DialogFlowWebhookController {
    private final JsonFactory jsonFactory;

    @Override
    public String toString() {
        return "DialogFlowWebhookController{" +
                "jsonFactory=" + jsonFactory +
                '}';
    }

    @RequestMapping(value = "/google/webhook.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> webhook(HttpServletRequest servletRequest, @RequestBody String reqBody) throws IOException {
        try {
            String email = servletRequest.getHeader("username");
            String password = servletRequest.getHeader("password");
//            this.userController.Authenticate(new User(email, password, null, null));
            GoogleCloudDialogflowV2WebhookRequest request = jsonFactory.createJsonParser(reqBody).parse(GoogleCloudDialogflowV2WebhookRequest.class);
            GoogleCloudDialogflowV2WebhookResponse response = new GoogleCloudDialogflowV2WebhookResponse();
            Map<String, Object> params = request.getQueryResult().getParameters();
            System.out.println("====" + request);
            if (params.size() > 0) {
//                response.setFulfillmentText(dialogController.processMessage(params));
            } else {
                response.setFulfillmentText("sorry you didnt send enough to process");
            }
            return new ResponseEntity<GoogleCloudDialogflowV2WebhookResponse>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

