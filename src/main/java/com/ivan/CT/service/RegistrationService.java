package com.ivan.CT.service;

import com.ivan.CT.entities.MyUser;
import com.ivan.CT.entities.Role;
import com.ivan.CT.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class RegistrationService {

    private final MyUserRepository myUserRepository;

    private final MyMailSender myMailSender;

    @Value("${myCurrentHost}")
    private String myCurrentHost;

    public RegistrationService(MyUserRepository myUserRepository, MyMailSender myMailSender) {
        this.myUserRepository = myUserRepository;
        this.myMailSender = myMailSender;
    }

    public boolean addUserInDB(MyUser myUser) {
        if(myUserRepository.findByLogin(myUser.getLogin()) != null) {
            return false;
        }

        String activationCode = UUID.randomUUID().toString();
        String text = String.format("To activate your Centralized testing account, click here: %s/registration/activate/%s", myCurrentHost, activationCode);
        if(myMailSender.sendMessage(myUser.getEmail(),"Activation code",text)) {
            myUser.setActivationCode(activationCode);
            myUser.setRoles(Collections.singleton(Role.USER));
            myUserRepository.save(myUser);
            return true;
        }
        return false;
    }
}
