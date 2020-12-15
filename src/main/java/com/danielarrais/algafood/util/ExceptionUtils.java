package com.danielarrais.algafood.util;

import java.util.Objects;

public class ExceptionUtils {
    public static void throwIsNull(Object object, Exception exception) throws Exception {
        if(Objects.isNull(object)) {
            throw exception;
        }
    }
}
