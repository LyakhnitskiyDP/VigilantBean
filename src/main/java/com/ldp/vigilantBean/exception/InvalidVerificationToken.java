package com.ldp.vigilantBean.exception;

public class InvalidVerificationToken extends RuntimeException {

    final Reason reason;

    public InvalidVerificationToken(Reason reason) {
       this.reason = reason;
    }

    public enum Reason {
       NO_SUCH_TOKEN, EXPIRED
    }

    public Reason getReason() {
        return this.reason;
    }

}
