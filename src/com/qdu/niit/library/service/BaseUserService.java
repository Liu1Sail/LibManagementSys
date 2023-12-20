package com.qdu.niit.library.service;

import com.qdu.niit.library.entity.User;

public interface BaseUserService {
    User getLocalUser();
    void logout();

    boolean modifyLocalUserPassword(String newPassword);

    boolean isLocalUserPasswordCorrect();

    boolean isLocalUserSuperUser();
}
