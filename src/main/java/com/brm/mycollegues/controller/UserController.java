package com.brm.mycollegues.controller;

import com.brm.mycollegues.entity.Person;
import com.brm.mycollegues.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("users")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping("allusers")
    public ResponseEntity<List<Person>> getAllUsers() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @PutMapping("status/{online}")
    public boolean updateStatus(@PathVariable("online") Boolean is_online,
                                @RequestParam("username") String username){
        if (userRepository.findByUsername(username).isPresent()){
            userRepository.updateStatus(username, is_online);
            return true;
        }
        else{
            return false;
        }


    }

}
