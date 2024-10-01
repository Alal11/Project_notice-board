package mini.noticeboard.service;

import lombok.RequiredArgsConstructor;
import mini.noticeboard.dto.UserDTO;
import mini.noticeboard.entity.UserEntity;
import mini.noticeboard.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public UserDTO login(UserDTO userDTO) {
        // 회원이 입력한 아이디로 DB 조회 -> DB의 비번이랑 회원이 입력한 비번이랑 일치하는지 판단
        Optional<UserEntity> byUserName = userRepository.findByUserName(userDTO.getUserName());

        if (byUserName.isPresent()) {
            // 조회 결과가 있다 (해당 아이디를 가진 회원 정보가 있다)
            UserEntity userEntity = byUserName.get();
            if (userEntity.getUserPw().equals(userDTO.getUserPw())) {
                // 비번 일치
                // entity -> dto 변환 후 반환
                UserDTO dto = UserDTO.toUserDTO(userEntity);
                return dto;
            } else {
                // 비번 불일치 (로그인 실패)
                return null;
            }
        } else {
            // 조회 결과가 없다 (해당 아이디를 가진 회원이 없다)
            return null;
        }
    }

    public UserDTO updateForm(String myName) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUserName(myName);
        if (optionalUserEntity.isPresent()) {
            return UserDTO.toUserDTO(optionalUserEntity.get());
        } else {
            return null;
        }

    }

    public void update(UserDTO userDTO) {
        userRepository.save(UserEntity.toUpdateUserEntity(userDTO));
    }
}
