package org.openchat.domain.users;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openchat.infrastructure.builders.UserBuilder.aUser;

public class UserRepositoryShould {
    private static final User JOHN = aUser().withUsername("john").build();
    private static final User CHARLIE = aUser().withUsername("charlie").build();
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    public void inform_when_username_is_already_taken() {
        userRepository.add(JOHN);

        assertThat(userRepository.isUsernameTaken(JOHN.username())).isTrue();
        assertThat(userRepository.isUsernameTaken(CHARLIE.username())).isFalse();
    }
}