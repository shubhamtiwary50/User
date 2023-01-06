package com.niit.service;


import com.niit.domain.User;

import java.util.List;

public interface UserService
{
    User saveUser(User user) ;
    List<User> getAllUsers();

    List<User> getUserByuserName(String username);

    User loginCheck(String username, String password);
}
