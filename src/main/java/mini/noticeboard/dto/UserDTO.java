package mini.noticeboard.dto;

import lombok.*;
import mini.noticeboard.entity.UserEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private Long id;
    private String userName;
    private String userPw;
    private String userPwCheck;
    private String userEmail;

    public static UserDTO toUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setUserName(userEntity.getUserName());
        userDTO.setUserPw(userEntity.getUserPw());
        userDTO.setUserEmail(userEntity.getUserEmail());
        return userDTO;
    }
}
