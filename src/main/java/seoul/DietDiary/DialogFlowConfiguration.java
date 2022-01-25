package seoul.DietDiary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.api.client.json.gson.GsonFactory;

@Configuration
public class DialogFlowConfiguration {
        @Bean
        public GsonFactory gsonFactory() {
                return GsonFactory.getDefaultInstance();
        }
}
