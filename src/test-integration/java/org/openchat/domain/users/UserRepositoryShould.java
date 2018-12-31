package org.openchat.domain.users;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryShould {
    @Test
    public void inform_when_username_is_already_taken() {
        UserRepository userRepository = new UserRepository();
        String username = "john";

        boolean isUsernameTaken = userRepository.isUsernameTaken(username);

        assertThat(isUsernameTaken).isTrue();
    }
}