package com.example.ceertifications.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErreurEnum implements ErreurBase{


    BAD_CONTEXT(SeveriteEnum.ERROR, HttpStatus.BAD_REQUEST),
    RESOURCE_NOT_FOUND(SeveriteEnum.ERROR,HttpStatus.INTERNAL_SERVER_ERROR),
    USER_N_PAS_ROLE_REQUIS(SeveriteEnum.ERROR, HttpStatus.INTERNAL_SERVER_ERROR),
    DOMES_RESOURCE_NOT_EXIST(SeveriteEnum.ERROR, HttpStatus.INTERNAL_SERVER_ERROR),
    DOMES_GENERIC_ERROR(SeveriteEnum.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);


    private final SeveriteEnum severiteEnum;
    private final HttpStatus status;

    ErreurEnum(SeveriteEnum severiteEnum, HttpStatus status) {

        this.severiteEnum = severiteEnum;
        this.status = status;
    }
}
