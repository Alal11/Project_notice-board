package mini.noticeboard.service;

import lombok.RequiredArgsConstructor;
import mini.noticeboard.dto.UserDTO;
import mini.noticeboard.entity.UserEntity;
import mini.noticeboard.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public void save(UserDTO userDTO) {
        // 매개변수로 받은 dto 객체를 repository에 entity 객체로 넘겨줘야 함
        // 1. dto -> entity로 변환  2. repository의 save 메서드 호출
        UserEntity userEntity = UserEntity.toUserEntity(userDTO);
        userRepository.save(userEntity);

    }
}
