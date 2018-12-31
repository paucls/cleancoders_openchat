package org.openchat.domain.users;

import java.util.UUID;

public class IdGenerator {
    String next() {
        return UUID.randomUUID().toString();
    }
}
