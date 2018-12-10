package org.openchat.api;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.openchat.domain.users.RegistrationData;
import org.openchat.domain.users.User;
import org.openchat.domain.users.UserService;
import spark.Request;
import spark.Response;

public class UsersAPI {
    private UserService userService;

    public UsersAPI(UserService userService) {
        this.userService = userService;
    }

    public String createUser(Request request, Response response) {
        RegistrationData registration = registrationDataFrom(request);
        User user = userService.createUser(registration);
        return "";
    }

    private RegistrationData registrationDataFrom(Request request) {
        JsonObject json = Json.parse(request.body()).asObject();

        return new RegistrationData(
                json.getString("username", ""),
                json.getString("password", ""),
                json.getString("about", "")
        );
    }
}
