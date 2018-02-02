/*
 *  File: UserService.java
    Author: James Knights
    Date: 15th January 2018

    The initial version
 */
package com.jamesknights.common.user.service;

import com.jamesknights.common.service.Service;
import com.jamesknights.common.user.model.User;
import com.jamesknights.common.util.ServiceMessage;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author James Knights <james@i-studio.co.uk>
 * @param <T>
 */
public interface UserService <T extends User> extends Service {
    
    
    User createUser (String forename, String lastname, String emailAddress, String password, boolean isEnabled);
    
    List<User> getAll();
    
    User findUser (long uId);
    
    User findUserByEmailAddress (String email);
    
    boolean updateUser (long uId, HashMap<String, Object> data);

    boolean deleteUser (long uId);
    
    User userLogin (String password, String emailAddress);
    
    boolean changePassword (String oldPassword, String newPassword, String emailAddress);
    

}
