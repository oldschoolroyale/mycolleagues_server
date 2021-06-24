package com.brm.mycollegues.controller;

import com.brm.mycollegues.dto.LoginDto;
import com.brm.mycollegues.entity.Register;
import com.brm.mycollegues.repo.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("auth")
@RestController
public class PersonController {

     @Autowired
     private RegistrationRepository registrationRepository;

    @PostMapping("reg")
    public boolean  reg(@RequestBody Register register)
    {
            if(registrationRepository.findByUsername(register.getUsername()).isPresent())
            {
                return true;

            }  else {

                registrationRepository.save(register);
                return false;
            }
    }

    @PostMapping("log")
    public boolean login(@RequestBody LoginDto loginDto)
    {
        if(registrationRepository.findByUsernameAndPassword(loginDto.getUsername(),loginDto.getPassword()).isPresent())
        {
            return true;

        } else {
            return false;
        }
    }

        @GetMapping("allusers")
        public ResponseEntity<List<Register>> getAllUsers() {

          return ResponseEntity.ok().body(registrationRepository.findAll());
        }


}
