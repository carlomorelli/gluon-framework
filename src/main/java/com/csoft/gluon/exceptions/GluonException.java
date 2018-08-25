package com.csoft.gluon.exceptions;

public class GluonException extends RuntimeException {

    public GluonException(final String message) {
        super(message);
    }

    public GluonException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
