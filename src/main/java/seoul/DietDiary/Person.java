package seoul.DietDiary;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("busan")
public class Person {
    private String name;
    private String job;
    private int age;
    private String school;


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", age='" + age + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}
