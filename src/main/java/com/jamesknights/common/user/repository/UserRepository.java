/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesknights.common.user.repository;

import com.jamesknights.common.user.model.impl.UserImpl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author James Knights <james@i-studio.co.uk>
 */
@Resource
@Transactional
public interface UserRepository extends JpaRepository <UserImpl, Long> {
    
  //  @Query("SELECT t FROM UserImpl t WHERE t.emailAddress = ?1")
  //  UserImpl findByEmailAddress(String emailAddress);
    @Override
    List<UserImpl> findAll();
    
    @Modifying
    @Query("Update UserImpl u SET u.emailAddress=:emailAddress WHERE u.uId=:uId")
    public void updateEmail (@Param("uId") long uId, @Param("emailAddress") String emailAddress);
    
    @Modifying
    @Query("Update UserImpl u SET u.emailAddress=:emailAddress, u.forename=:forename, u.lastname=:lastname WHERE u.uId=:uId")
    public void updateUser (@Param("uId") long uId, @Param("emailAddress") String emailAddress, @Param("forename") String forename, 
            @Param("lastname") String lastname);
    
    @Modifying
    @Query("Update UserImpl u SET u.password=:password WHERE u.uId=:uId")
    public void updateUserPassword (@Param("uId") long uId, @Param("password") String password);
    
    @Query("Select u FROM UserImpl u  WHERE u.emailAddress=:emailAddress")
    public UserImpl findUserByEmail (@Param("emailAddress") String emailAddress);
}
