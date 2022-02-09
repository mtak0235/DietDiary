package seoul.DietDiary;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface PersonMongoDBRepository extends MongoRepository<Person, String>{
    public Person findByName(String name);
    public Person findByAge(int age);//"age":18 인 상태인데, 여기서 18은 숫자로 하고 싶다면 어떻게 해야...? <- DTO 에서 자료형을 바꿔주면 되겠다.
}
