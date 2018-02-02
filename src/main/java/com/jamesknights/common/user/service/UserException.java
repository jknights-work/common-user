/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesknights.common.user.service;

import java.io.Serializable;

/**
 *
 * @author James Knights <james@i-studio.co.uk>
 */
public class UserException extends Exception implements Serializable {

    public UserException(String message) {
        super(message);
    }

}
