package seoul.DietDiary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class User {
    private String email;
    private String password;
    private String fullName;
    private String role;

}
