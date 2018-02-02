/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesknights.common.user.model.impl;

import com.jamesknights.common.model.impl.AbstractModelObject;
import com.jamesknights.common.user.model.User;
import com.jamesknights.common.util.ServiceMessage;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.jboss.logging.Logger;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

/**
 *
 * @author James Knights <james@i-studio.co.uk>
 */
@Entity
@Table(name = "jk_user")
public class UserImpl extends AbstractModelObject implements User {
    
    private static final Logger LOG = Logger.getLogger(UserImpl.class);
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long uId;
    
    @Email
    @Size(min = 3, message = "email address must be at least three characters")
    private String emailAddress;
    
    @NotNull
    @Size(min = 3, message = "email address must be at least three characters")
    private String forename;
    
    @NotNull
    @Size(min = 3, message = "email address must be at least three characters")
    private String lastname;
    
    @NotNull
    @Size(min = 7, message = "email address must be at least three characters")
    private String password;
    
    private LocalDateTime created;
    
    @NotNull
    private boolean isEnabled;
    
    public UserImpl () {}
    
    public UserImpl(String emailAddress, String forename, String lastname, String password, boolean isEnabled) {
        this();
        this.emailAddress = emailAddress;
        this.forename = forename;
        this.lastname = lastname;
        this.created = LocalDateTime.now();
        this.isEnabled = isEnabled;
        this.password = password;
    }
    
    
    @Override
    public long getUid() {
        return uId;
    }
    
    @Override
    public String getEmailAddress() {
        return emailAddress;
    }
    
    @Override
    public String getForename() {
        return forename;
    }
    
    @Override
    public String getLastname() {
        return lastname;
    }
    
    @Override
    public LocalDateTime getCreated() {
        return created;
    }
    
    @Override
    public String getPassword() {
        return password;
    }
    
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
    
    public void setUid(long uId) {
        this.uId = uId; 
    }
    
    @Override
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    @Override
    public void setForename(String forename) {
        this.forename = forename;
    }
    
    @Override
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    @Override
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
    
    @Override
    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
    
    @Override
    public void setPassword(String password) {
        this.password = password;
    }
    
    public ServiceMessage setMap (HashMap<String, Object> data) {
        ServiceMessage errors = new ServiceMessage();
        if (data != null) {
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                if (!entry.getValue().toString().isEmpty()) {
                    switch (entry.getKey()) {
                        case "forename": 
                            this.setForename((String) entry.getValue());
                            break;
                        case "lastname":
                            this.setLastname((String) entry.getValue());
                            break;
                        case "emailAddress":
                            this.setEmailAddress((String) entry.getValue());
                            break;
                        default:
                            LOG.error("User Model" + "Unable to set the value for " + entry.getKey() + ", please check the param name");
                    }
                }
            }
        }
        this.set(data);
        return errors;
    }

    @Override
    public void set(Object propertyName, Object propertyValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
