/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.excepciones;

import javax.ejb.ApplicationException;

/**
 *
 * @author Camilo Marroquin
 */
@ApplicationException
public class CupoInsuficienteException extends RuntimeException {

    /**
     * Constructor de la clase.
     */
    public CupoInsuficienteException() {
        super();
    }

    /**
     * Constructor de la clase.
     * @param message
     */
    public CupoInsuficienteException(String message) {
        super(message);
    }

    /**
     * Constructor de la clase.
     * @param message
     * @param cause
     */
    public CupoInsuficienteException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor de la clase.
     * @param cause
     */
    public CupoInsuficienteException(Throwable cause) {
        super(cause);
    }
}
