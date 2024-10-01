package mini.noticeboard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mini.noticeboard.dto.UserDTO;

@Entity
@Setter
@Getter
@Table(name = "user_table")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Column
    private String userPw;

    @Column(unique = true)  // unique 제약조건 추가
    private String userEmail;

    public static UserEntity toUserEntity(UserDTO userDTO){  // dto를 entity로 변환
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setUserPw(userDTO.getUserPw());
        userEntity.setUserEmail(userDTO.getUserEmail());
        return userEntity;
    }
}
