package org.openchat.domain.users;

import java.util.HashMap;

public class UserRepository {

    private final HashMap<String, User> users = new HashMap();

    public void add(User user) {
        users.put(user.username(), user);
    }

    public boolean isUsernameTaken(String username) {
        return users.containsKey(username);
    }
}
