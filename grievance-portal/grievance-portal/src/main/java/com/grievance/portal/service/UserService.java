package com.grievance.portal.service;

import com.grievance.portal.entity.User;

public interface UserService {
    User register(User user);
    User login(String email, String password);
}
