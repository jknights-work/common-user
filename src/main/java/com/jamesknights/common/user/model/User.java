/*
 *  File: User.java
    Author: James Knights
    Date: 15th January 2018

    The initial version
 */
package com.jamesknights.common.user.model;

import com.jamesknights.common.model.ModelObject;
import java.time.LocalDateTime;

/**
 * This class represents the model of User Data
 * @author James Knights <james@i-studio.co.uk>
 */

public interface User extends ModelObject {
    
    long getUid();
    
    String getEmailAddress();
    
    String getForename();
    
    String getLastname();
    
    String getPassword();
    
    LocalDateTime getCreated();
    
    boolean isEnabled();
    
    void setEmailAddress(String emailAddress);
    
    void setForename(String foreName);
    
    void setLastname(String lastName);
    
    void setCreated(LocalDateTime created);
    
    void setEnabled(boolean isEnabled);
    
    void setPassword(String password);
    
}
