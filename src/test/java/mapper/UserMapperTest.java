package mapper;

import model.User;
import org.junit.jupiter.api.Test;
import persistance.UserMapper;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void getUserFromDb() {
        String email = "fake@email.com";
        String password = "123";
        UserMapper userMapper = new UserMapper();

        User user = userMapper.getUserFromDb(email, password);
        assertEquals(email, user.getEmail());
    }

    @Test
    void getUserRole() {
        UserMapper userMapper = new UserMapper();
        String email = "fake@email.com";
        String password = "123";

        User user = userMapper.getUserFromDb(email, password);
        String sessionId = user.getSessionID();
        assertEquals("Customer", user.getRole());
    }
}