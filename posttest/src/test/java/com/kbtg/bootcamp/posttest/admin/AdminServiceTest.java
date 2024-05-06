package com.kbtg.bootcamp.posttest.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AdminServiceTest {
    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAdmin() {
        Admin admin1 = new Admin();
        admin1.setUsername("admin01");
        Admin admin2 = new Admin();
        admin2.setUsername("admin245");
        List<Admin> mockAdmins = Arrays.asList(admin1, admin2);
        when(adminRepository.findAll()).thenReturn(mockAdmins);

        List<Admin> returnedAdmins = adminService.getAllAdmin();
        assertNotNull(returnedAdmins);
        assertEquals(2, returnedAdmins.size());
        assertEquals("admin01", returnedAdmins.get(0).getUsername());
        assertEquals("admin245", returnedAdmins.get(1).getUsername());
    }

    @Test
    void registerAdmin() {
        Admin adminToRegister = new Admin();
        adminToRegister.setUsername("Admin01");
        when(adminRepository.save(any(Admin.class))).thenReturn(adminToRegister);
        Admin registeredAdmin = adminService.RegisterAdmin(adminToRegister);
        assertNotNull(registeredAdmin);
        assertEquals("Admin01", registeredAdmin.getUsername());
    }
}