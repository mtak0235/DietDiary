package seoul.DietDiary;

import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class PersonMongoDBRepositoryTest {
    @Autowired()
    private PersonMongoDBRepository projectMongoDBRepository;

    @Test
    public void printProjectData() {
        System.out.println(projectMongoDBRepository.findAll());
        System.out.println(projectMongoDBRepository.findByName("최훈진"));
        System.out.println(projectMongoDBRepository.findByAge(16));
    }
}


