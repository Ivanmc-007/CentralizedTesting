package com.ivan.CT.service;

import com.ivan.CT.entities.Passport;
import com.ivan.CT.repositories.PassportRepository;
import org.springframework.stereotype.Service;

@Service
public class PassportService {

    private final PassportRepository passportRepository;

    public PassportService(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    public Passport save(Passport passport) {
        return passportRepository.save(passport);
    }
}
