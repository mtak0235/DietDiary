package seoul.DietDiary;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface WebhookRequestRepository extends MongoRepository<WebhookRequest, String> {
    public WebhookRequest findByResponseId(String name);
}