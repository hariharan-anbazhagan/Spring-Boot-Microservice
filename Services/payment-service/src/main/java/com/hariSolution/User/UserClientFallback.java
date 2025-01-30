package com.hariSolution.User;

import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {

    @Override
    public UserDetails getUserDetailsByUserId(Integer userId) {
        return new UserDetails(userId, "Unknown", "Unknown", "unknown@example.com");
    }
}
