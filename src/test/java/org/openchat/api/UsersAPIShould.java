package org.openchat.api;

import com.eclipsesource.json.JsonObject;
import org.junit.Before;
import org.junit.Test;
import org.openchat.domain.users.RegistrationData;
import org.openchat.domain.users.UserService;
import spark.Request;
import spark.Response;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UsersAPIShould {

    private static final String USERNAME = "John Doe";
    private static final String PASSWORD = "1234";
    private static final String ABOUT = "About John";
    private static final RegistrationData REGISTRATION_DATA = new RegistrationData(USERNAME, PASSWORD, ABOUT);

    private UserService userService;
    private UsersAPI usersAPI;

    @Before
    public void setUp() {
        userService = new UserService();
        usersAPI = new UsersAPI(userService);
    }

    @Test
    public void create_a_new_user() {
        Request request = mock(Request.class);
        Response response = mock(Response.class);
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