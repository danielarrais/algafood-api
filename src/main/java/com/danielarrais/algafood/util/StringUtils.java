package com.danielarrais.algafood.util;

import java.util.Objects;

public class StringUtils {
    public static String camelToKebab(String s) {
        if (Objects.nonNull(s)){
            return s.replaceAll("([a-z0-9]|(?=[A-Z]))([A-Z])", "$1-$2").toLowerCase();
        } else {
            return null;
        }
    }
}
