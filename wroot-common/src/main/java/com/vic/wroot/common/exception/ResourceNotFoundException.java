/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vic.wroot.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String string) {
        super(string);
    }

    public ResourceNotFoundException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public ResourceNotFoundException(Throwable thrwbl) {
        super(thrwbl);
    }

}
