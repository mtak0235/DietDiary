package seoul.DietDiary;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class DialogFlowWebhookController {
    @RequestMapping(value = "/google/webhook.do", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody JSONObject webhook(HttpServletRequest request, @RequestBody Map<String, Object> map) {
        System.out.println("webhook=====" + map);

        JSONObject jsonObject = new JSONObject();
        JSONObject payloadObject = new JSONObject();
        JSONObject googleObject = new JSONObject();
        googleObject.put("expectUserResponse", false);
        payloadObject.put("google", googleObject);
        jsonObject.put("fulfillmentText", "블라인드를 켰습니다.");
        jsonObject.put("payload", payloadObject);

        return jsonObject;
    }

}
