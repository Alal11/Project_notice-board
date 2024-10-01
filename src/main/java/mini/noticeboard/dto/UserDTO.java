package mini.noticeboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {
    private Long id;
    private String userName;
    private String userPw;
    private String userPwCheck;
    private String userEmail;
}
