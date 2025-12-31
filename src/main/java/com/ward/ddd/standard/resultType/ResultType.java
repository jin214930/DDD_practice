package com.ward.ddd.standard.resultType;

public interface ResultType {
    int code();

    String message();

    default <T> T data() {
        return null;
    }
}
