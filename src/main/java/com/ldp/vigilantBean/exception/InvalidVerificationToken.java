package com.ldp.vigilantBean.exception;

public class InvalidVerificationToken extends RuntimeException {

    final Cause cause;

    public InvalidVerificationToken(Cause cause) {
       this.cause = cause;
    }

    public enum Cause {
       NO_SUCH_TOKEN, EXPIRED
    }



}
