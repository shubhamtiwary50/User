package com.niit.service;

import com.niit.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    public Map<String, String> tokenGenerator(User user);

}
