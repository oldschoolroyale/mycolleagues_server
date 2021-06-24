package com.brm.mycollegues.controller;

import com.brm.mycollegues.entity.Register;
import com.brm.mycollegues.repo.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("status")
@RestController
public class StatusController {

    @Autowired
    private RegistrationRepository registrationRepository;

    @PutMapping(path = "{online}")
    public boolean updateStatus(@PathVariable("online") Boolean is_online,
                             @RequestParam("username") String username){
        if (registrationRepository.findByUsername(username).isPresent()){
            registrationRepository.updateStatus(username, is_online);
            return true;
        }
        else{
            return false;
        }


    }

}
