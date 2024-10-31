package carrotmoa.carrotmoa.repository;

import carrotmoa.carrotmoa.entity.ChatRoomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUser, Long> {
    List<ChatRoomUser> findByUserId(long userId);
}
