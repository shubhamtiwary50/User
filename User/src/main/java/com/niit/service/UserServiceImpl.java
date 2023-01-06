package com.niit.service;


import com.niit.domain.User;
import com.niit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user)  {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }


    @Override
    public List<User> getUserByuserName(String username) {
        return userRepository.findByuserName(username);

    }

    @Override
    public User loginCheck(String email, String password) {

        if(userRepository.findById(email).isPresent()){

            User user = userRepository.findById(email).get();

            if(user.getUserPassword().equals(password)){

                return user;

            }else {
                return null;
            }
        } else {
            return null;
        }
    }


}
