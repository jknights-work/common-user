/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package com.jamesknights.common.user.service.impl;

import com.jamesknights.common.java.configuration.JPAConfiguration;
import com.jamesknights.common.password.service.impl.LocalPasswordService;
import com.jamesknights.common.service.impl.AbstractService;
import com.jamesknights.common.user.model.User;
import com.jamesknights.common.user.model.impl.UserImpl;
import com.jamesknights.common.user.repository.UserRepository;
import com.jamesknights.common.user.service.UserException;
import com.jamesknights.common.user.service.UserService;
import com.jamesknights.common.util.ServiceMessage;
import jamesknights.common.password.service.PasswordService;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author James Knights <james@i-studio.co.uk>
 */
@Service
@RunWith(SpringRunner.class)
@ContextConfiguration(
  classes = { JPAConfiguration.class }, 
  loader = AnnotationConfigContextLoader.class)
@Transactional
public abstract class AbstractUserServiceImpl extends AbstractService implements UserService {
    
    private final static Logger LOG = Logger.getLogger(AbstractUserServiceImpl.class);
    private ServiceMessage messages = new ServiceMessage();
    
    @Resource
    private UserRepository userRepository;
    private PasswordService passwordService;

    @Bean
    private PasswordService passwordService() {
        passwordService = new LocalPasswordService("passwordService");
        passwordService.start();
        return passwordService;
    }
    
    public AbstractUserServiceImpl(String name) {
        super(name);
        passwordService();
    }
    
    @Autowired
    public AbstractUserServiceImpl(String name, final UserRepository userRepository) {
        super(name);
        this.userRepository = userRepository;
        passwordService();
    }

    @Override
    public User createUser (String emailAddress, String forename, String lastname, String password, boolean isEnabled) {
        User result = null;
        String encodedPassword = passwordService.encryptPassword(password);
        try {
             User user = (User) new UserImpl(emailAddress, forename, lastname, encodedPassword, isEnabled);
             if (findUserByEmailAddress(emailAddress) == null) {
                result = userRepository.save((UserImpl) user);
                
                LOG.debug("created User" + result);
             } else {
                 LOG.debug("Duplicate User" + result);
             }
        } catch (Exception e) {
            LOG.error("Unable to set User: " + e);
        }
        
        return result;
    }
    
    
    @Override 
    public User findUser (long uId) {
        User result = null;
        
            try {
                result = (UserImpl) userRepository.findOne((long) uId);
            } catch (Exception e) {
                LOG.error("Unable to find User" + e);
            }
         
        return result;
    }
    
    @Override
    public List<UserImpl> getAll() {
        List<UserImpl> result = (List<UserImpl>) userRepository.findAll();
        return result;
    }
    
    @Override
    public User findUserByEmailAddress (String email) {
        User result = null;
        
        if (!email.isEmpty()) {
            try {
                result = (UserImpl) userRepository.findUserByEmail(email);
            } catch (Exception e) {
                LOG.error("Unable to find User" + e);
            }
        }
         
        return result;
    }
    
    @Override 
    public boolean deleteUser (long uId) {
        boolean result = false;
        if (findUser(uId) != null) {
            userRepository.delete(uId);
            result = true;
        }
        return result;
    }
    
    @Override
    public boolean updateUser(long uId, HashMap data) {
      boolean result = false;
        if (data != null) {
                UserImpl user = userRepository.findOne((long) uId);
                if (user != null) {
                    user.setMap(data);
                    try {
                        userRepository.updateUser(user.getUid(), user.getEmailAddress(), user.getForename(), user.getLastname());
                        result = true;
                    } catch (Exception e) {
                        LOG.error("Unable to update user", e);
                    }
                }
        } 
        return result;
    }
    
    public boolean updateUserPassword (String newPassword, User user) {
      boolean result = false;
        if (user != null) {
            user.setPassword(passwordService.encryptPassword(newPassword));
            try {
                userRepository.updateUserPassword(user.getUid(), user.getPassword());
                result = true;
            } catch (Exception e) {
                LOG.error("Unable to update user", e);
            }
        }
        return result;
    }
    
    @Override
    public User userLogin (String rawPassword, String emailAddress) {
        User result = null;
        if (!rawPassword.isEmpty()&& !emailAddress.isEmpty()) {
            User user = findUserByEmailAddress(emailAddress);
            if (user != null) {
                boolean passwordMatch = passwordService.matches(rawPassword, user.getPassword());
                if (passwordMatch) {
                    result = user;
                }
            }
        }
        return result;
    }
    
    @Override
    public boolean changePassword (String oldPassword, String newPassword, String emailAddress) {
        boolean result = false;
        if (!emailAddress.isEmpty() && !oldPassword.isEmpty() && !newPassword.isEmpty()) {
            User target = userLogin(oldPassword, emailAddress);
            if (target != null) {
                result = updateUserPassword(newPassword, target);
            }
        }
        return result;
    }


  
}
