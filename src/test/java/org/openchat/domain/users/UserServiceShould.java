package org.openchat.domain.users;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.openchat.infrastructure.builders.UserBuilder.aUser;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceShould {

    private static final String USER_ID = UUID.randomUUID().toString();
    private static final String USERNAME = "John Doe";
    private static final String PASSWORD = "1234";
    private static final String ABOUT = "About John";
    private static final User USER = aUser()
            .withId(USER_ID)
            .withUsername(USERNAME)
            .withPassword(PASSWORD)
            .withAbout(ABOUT)
            .build();
    private static final RegistrationData REGISTRATION_DATA = new RegistrationData(USERNAME, PASSWORD, ABOUT);

    @Mock
    private IdGenerator idGenerator;
    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserService(idGenerator, userRepository);
    }

    @Test
    public void create_a_user() throws UsernameAlreadyInUseException {
        given(idGenerator.next()).willReturn(USER_ID);

        User user = userService.createUser(REGISTRATION_DATA);

        verify(userRepository).add(USER);
        assertThat(user).isEqualTo(USER);
    }
}
