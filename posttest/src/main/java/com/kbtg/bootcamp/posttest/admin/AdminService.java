package com.kbtg.bootcamp.posttest.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    public Admin RegisterAdmin(Admin admin) {
        return adminRepository.save(admin);
    }
}
