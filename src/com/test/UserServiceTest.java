package com.test;

import com.services.UserService;

public class UserServiceTest {
    private UserService userService;

    @BeforeEach
    public void setup() {
        userService = new UserService();
    }

    @Test
    public void testAuthenticateSuccess() {
        // Assuming user exists in DB
        assertTrue(userService.authenticate("admin", "admin123"));
    }

    @Test
    public void testAuthenticateFailure() {
        assertFalse(userService.authenticate("fake", "wrong"));
    }
}