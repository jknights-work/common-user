/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesknights.common.user.service.impl;

import com.jamesknights.common.user.repository.UserRepository;
import com.jamesknights.common.user.service.UserService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author James Knights <james@i-studio.co.uk>
 */
@Transactional
public class LocalUserService extends AbstractUserServiceImpl implements UserService  {
    
    public LocalUserService(String name) {
        super(name);
    }
    
    @Autowired
    public LocalUserService(String name, final UserRepository userRepository) {
        super(name, userRepository);
    }

}
