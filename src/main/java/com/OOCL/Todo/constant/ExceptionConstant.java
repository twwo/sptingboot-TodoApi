package com.OOCL.Todo.constant;

public enum ExceptionConstant {
    NOT_SUCH_DATA("not such data"),
    ILLEGAL_OPERATION("illegal operation");

    private final String message;

    ExceptionConstant(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
