package org.openchat.api;

import com.eclipsesource.json.JsonObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openchat.domain.users.RegistrationData;
import org.openchat.domain.users.UserService;
import spark.Request;
import spark.Response;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UsersAPIShould {

    private static final String USERNAME = "John Doe";
    private static final String PASSWORD = "1234";
    private static final String ABOUT = "About John";
    private static final RegistrationData REGISTRATION_DATA = new RegistrationData(USERNAME, PASSWORD, ABOUT);

    @Mock
    private Request request = mock(Request.class);
    @Mock
    private Response response = mock(Response.class);
    @Mock
    private UserService userService;

    private UsersAPI usersAPI;

    @Before
    public void setUp() {
        usersAPI = new UsersAPI(userService);
    }

    @Test
    public void create_a_new_user() {
        given(request.body()).willReturn(jsonContaining(REGISTRATION_DATA));

        usersAPI.createUser(request, response);

        verify(userService).createUser(REGISTRATION_DATA);
    }

    private String jsonContaining(RegistrationData registrationData) {
        return new JsonObject()
                .add("username", registrationData.username())
                .add("password", registrationData.password())
                .add("about", registrationData.about())
                .toString();
    }
}