package codesquad.user;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setEmail("brad@brad");
        user.setName("Brad");
        user.setPassword("1111");
    }

    @Test
    public void update() {
        User userUpdated = new User();
        userUpdated.setEmail("aa@aa");
        userUpdated.setName("이정현");
        userUpdated.setPassword("1234");
        user.update(userUpdated);

        assertThat(user.getEmail()).isEqualTo(userUpdated.getEmail());
        assertThat(user.getName()).isEqualTo(userUpdated.getName());
        assertThat(user.getPassword()).isEqualTo(userUpdated.getPassword());
    }
}