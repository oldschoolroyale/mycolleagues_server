package com.brm.mycollegues.controller;

import com.brm.mycollegues.entity.Login;
import com.brm.mycollegues.entity.Person;
import com.brm.mycollegues.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("reg")
@RestController
public class RegistrationController {

     @Autowired
     private UserRepository userRepository;

    @PostMapping("signup")
    public boolean  reg(@RequestBody Person person)
    {
            if(userRepository.findByUsername(person.getUsername()).isPresent())
            {
                return true;

            }  else {

                userRepository.save(person);
                return false;
            }
    }
    @PostMapping("check")
    public boolean checkUser(@RequestBody Login login){
        if (userRepository.findByUsernameAndPassword(login.username, login.password).isPresent()){
            return true;
        }
        else {
            return false;
        }
    }

    @GetMapping("signin")
    public ResponseEntity<Person> login(@RequestParam("username") String username)
    {
            return ResponseEntity.ok().body(userRepository.findByUsername(username).get());
    }
}
