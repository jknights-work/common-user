/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesknights.common.user.service;

import com.jamesknights.common.service.ServiceException;
import com.jamesknights.common.user.model.User;
import com.jamesknights.common.user.model.impl.UserImpl;
import com.jamesknights.common.user.repository.UserRepository;
import com.jamesknights.common.user.service.impl.LocalUserService;
import static org.aspectj.bridge.MessageUtil.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)    
public class UserServiceTest {
      
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private final LocalUserService SERVICE = new LocalUserService("userService", userRepository);
    
    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }


    @Test
    public void createUser() throws UserException, ServiceException {
        if (!SERVICE.isRunning()) {
            try {
                SERVICE.start();
            } catch (ServiceException e) {
                fail("Unable to start Service", e);
            }
        }
        
        User result = new UserImpl("james@i-studio.co.uk", "Peter", "Pan", "blah", true);
        UserImpl user = userRepository.save((UserImpl) result);
        verify(userRepository, times(1)).save((UserImpl) result);

        try {
            SERVICE.stop();
        } catch (ServiceException ex) {
            fail("failed to stop cleanly -" + ex.getMessage());
        }
        
    }

    @Test
    public void readUsers() throws UserException, ServiceException {
        if (!SERVICE.isRunning()) {
            try {
                SERVICE.start();
            } catch (ServiceException e) {
                fail("Unable to start Service", e);
            }
        }
        
        User result = new UserImpl("james@i-studio.co.uk", "Peter", "Pan", "blah", true);
        UserImpl user = userRepository.save((UserImpl) result);
        verify(userRepository, times(1)).save((UserImpl) result);
        userRepository.findAll();
        verify(userRepository, times(1)).findAll();
        
        try {
            SERVICE.stop();
        } catch (ServiceException ex) {
            fail("failed to stop cleanly -" + ex.getMessage());
        }
        
    }
    
    @Test
    public void updateUsers() throws UserException, ServiceException {
        if (!SERVICE.isRunning()) {
            try {
                SERVICE.start();
            } catch (ServiceException e) {
                fail("Unable to start Service", e);
            }
        }
        
        User result = new UserImpl("james@i-studio.co.uk", "Peter", "Pan", "blah", true);
        UserImpl user = userRepository.save((UserImpl) result);
        verify(userRepository, times(1)).save((UserImpl) result);
        userRepository.findAll();
        verify(userRepository, times(1)).findAll();
        userRepository.updateEmail(1, "test@test.com");
        verify(userRepository, times(1)).updateEmail(1, "test@test.com");
        
        try {
            SERVICE.stop();
        } catch (ServiceException ex) {
            fail("failed to stop cleanly -" + ex.getMessage());
        }
        
    }
    
    @Test
    public void deleteUsers() throws UserException, ServiceException {
        if (!SERVICE.isRunning()) {
            try {
                SERVICE.start();
            } catch (ServiceException e) {
                fail("Unable to start Service", e);
            }
        }
        
        User result = new UserImpl("james@i-studio.co.uk", "Peter", "Pan", "blah", true);
        UserImpl user = userRepository.save((UserImpl) result);
        verify(userRepository, times(1)).save((UserImpl) result);
        userRepository.findAll();
        verify(userRepository, times(1)).findAll();
        userRepository.deleteAll();
        verify(userRepository, times(1)).deleteAll();
        
        try {
            SERVICE.stop();
        } catch (ServiceException ex) {
            fail("failed to stop cleanly -" + ex.getMessage());
        }
        
    }
   
}
