package mini.noticeboard.service;

import lombok.RequiredArgsConstructor;
import mini.noticeboard.dto.UserDTO;
import mini.noticeboard.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public void save(UserDTO userDTO) {
    }
}
