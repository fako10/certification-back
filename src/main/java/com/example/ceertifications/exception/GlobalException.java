package com.example.ceertifications.exception;

import java.io.Serializable;
import java.util.Arrays;

public class GlobalException extends  Exception {
    private final ErreurBase erreurEnum;
    private final String message;
    private final Serializable[] parameters;


    /**
     * Constructeur.
     *
     * @param erreurEnum : erreurs.
     */
    public GlobalException(ErreurBase erreurEnum) {
        super();
        this.erreurEnum = erreurEnum;
        this.message = null;
        this.parameters = null;
    }

    /**
     * Constructeur.
     *
     * @param erreurEnum : erreurs.
     * @param parameters : paramètres à substituer dans le message.
     */
    public GlobalException(ErreurBase erreurEnum, Serializable... parameters) {
        super();
        this.erreurEnum = erreurEnum;
        this.message = null;
        this.parameters = parameters;
    }


    /**
     * Constructeur.
     *
     * @param message : message.
     */
    public GlobalException(String message) {
        super();
        this.erreurEnum = ErreurEnum.DOMES_GENERIC_ERROR;
        this.message = message;
        this.parameters = null;
    }

    /**
     * Constructeur.
     *
     * @param message    : message.
     * @param parameters : paramètres à substituer dans le message.
     */
    public GlobalException(String message, Serializable... parameters) {
        super();
        this.erreurEnum = ErreurEnum.DOMES_GENERIC_ERROR;
        this.message = message;
        this.parameters = parameters;
    }


    /**
     * Constructeur.
     *
     * @param message : message.
     * @param e       : exception parent
     */
    public GlobalException(String message, Exception e) {
        super(e);
        this.erreurEnum = ErreurEnum.DOMES_GENERIC_ERROR;
        this.message = message;
        this.parameters = null;
    }

    /**
     * Constructeur.
     *
     * @param message    : message
     * @param parameters : paramètres à substituer dans le message.
     * @param e          : exception parent
     */
    public GlobalException(String message, Exception e, Serializable... parameters) {
        super(e);
        this.erreurEnum = ErreurEnum.DOMES_GENERIC_ERROR;
        this.message = message;
        this.parameters = parameters;
    }


    /**
     * Constructeur.
     *
     * @param erreurEnum : code de l'erreur
     * @param e          : exception parent
     * @param message    : message.
     */
    public GlobalException(ErreurBase erreurEnum, String message, Exception e) {
        super(e);
        this.erreurEnum = erreurEnum;
        this.message = message;
        this.parameters = null;
    }

    /**
     * Constructeur.
     *
     * @param erreurEnum : type de l'erreur
     * @param e          : exception parent
     * @param message    : message.
     * @param parameters : paramètres à substituer dans le message.
     */
    public GlobalException(ErreurBase erreurEnum, String message, Exception e, Serializable... parameters) {
        super(e);
        this.erreurEnum = erreurEnum;
        this.message = message;
        this.parameters = parameters;
    }

    /**
     * constructeur.
     *
     * @param erreurEnum erreur.
     * @param e          exception parent.
     */
    public GlobalException(ErreurBase erreurEnum, Exception e) {
        super(e);
        this.erreurEnum = erreurEnum;
        this.message = null;
        this.parameters = null;
    }

    /**
     * constructeur.
     *
     * @param erreurEnum erreur.
     * @param e          exception parent.
     * @param parameters : paramètres à substituer dans le message.
     */
    public GlobalException(ErreurBase erreurEnum, Exception e, Serializable... parameters) {
        super(e);
        this.erreurEnum = erreurEnum;
        this.parameters = parameters;
        this.message = null;
    }


    public Serializable[] getParameters() {
        if (this.parameters == null) {
            return new Serializable[0];
        } else {
            return Arrays.copyOf(this.parameters, parameters.length);
        }
    }

}
