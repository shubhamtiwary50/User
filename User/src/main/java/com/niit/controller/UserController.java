package com.niit.controller;


import com.niit.domain.User;
import com.niit.service.SecurityTokenGenerator;
import com.niit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    UserService userService;
    SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/adduser")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.FOUND);
    }

    @GetMapping("/users/{lastName}")
    public ResponseEntity<?> getAllUsersByLastName(@PathVariable String username) {
        return new ResponseEntity<>(userService.getUserByuserName(username), HttpStatus.FOUND);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCheck(@RequestBody User user){
//        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
        User result= userService.loginCheck(user.getUserName(),user.getUserPassword());
        if(result!=null){
            Map<String, String> map = securityTokenGenerator.tokenGenerator(result);
            return new ResponseEntity<>(map,HttpStatus.OK);
        } else{
            return new ResponseEntity<>("invalid user or user not exist",HttpStatus.NOT_FOUND);
        }
    }

}