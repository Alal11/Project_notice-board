package mini.noticeboard.repository;

import mini.noticeboard.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // 아이디로 회원 정보 조회 (select * from user_table where user_name=?)
    Optional<UserEntity> findByUserName(String userName);
}
