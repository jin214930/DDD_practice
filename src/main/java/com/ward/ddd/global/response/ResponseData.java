package com.ward.ddd.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ward.ddd.standard.resultType.ResultType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseData<T>(
        int code,
        String message,
        T data
) implements ResultType {
    public static ResponseData<Void> from(int code, String message) {
        return new ResponseData<>(code, message, null);
    }

    public static <T> ResponseData<T> from(int code, String message, T data) {
        return new ResponseData<>(code, message, data);
    }
}
