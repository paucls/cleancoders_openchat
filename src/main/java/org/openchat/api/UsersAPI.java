package org.openchat.api;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.openchat.domain.users.RegistrationData;
import org.openchat.domain.users.User;
import org.openchat.domain.users.UserService;
import org.openchat.domain.users.UsernameAlreadyInUseException;
import spark.Request;
import spark.Response;

public class UsersAPI {
    private UserService userService;

    public UsersAPI(UserService userService) {
        this.userService = userService;
    }

    public String createUser(Request request, Response response) {
        RegistrationData registration = registrationDataFrom(request);

        try {
            User user = userService.createUser(registration);
            response.status(201);
            response.type("application/json");
            return jsonFor(user);
        } catch (UsernameAlreadyInUseException e) {
            response.status(400);
            return "Username already in use";
        }
    }

    private RegistrationData registrationDataFrom(Request request) {
        JsonObject json = Json.parse(request.body()).asObject();

        return new RegistrationData(
                json.getString("username", ""),
                json.getString("password", ""),
                json.getString("about", "")
        );
    }

    private String jsonFor(User user) {
        return new JsonObject()
                .add("id", user.id())
                .add("username", user.username())
                .add("about", user.about())
                .toString();
    }
}
