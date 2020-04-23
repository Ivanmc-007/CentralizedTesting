package com.ivan.CT.service;

import com.ivan.CT.entities.MyUser;
import com.ivan.CT.repositories.MyUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class MyUserService implements UserDetailsService {

    private final MyUserRepository myUserRepository;

    public MyUserService(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return myUserRepository.findByLogin(username);
    }

    public MyUser save(MyUser myUser) {
        return myUserRepository.save(myUser);
    }

    @Transactional(readOnly = true)
    public MyUser findById(Long id) {
        return myUserRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public MyUser findByActivationCode(String activationCode) {
        return myUserRepository.findByActivationCode(activationCode).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<MyUser> findAll() {
        return myUserRepository.findAll();
    }

    public void removeById(Long id) {
        myUserRepository.deleteById(id);
    }

    private java.sql.Date convertUtilToSql(java.util.Date uDate) {
        return new java.sql.Date(uDate.getTime());
    }

    public List<MyUser> findByChoiceByDateCreation(Date start, Date end) {
        return myUserRepository.findByChoiceByDateCreation(convertUtilToSql(start),convertUtilToSql(end));
    }


}
