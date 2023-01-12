package com.example.ceertifications.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;


public interface ErreurBase extends Serializable {

    String name();

    /**
     * le Statut de l'erreur.
     * @return statut de l'erreur.
     */
    HttpStatus getStatus();
}
