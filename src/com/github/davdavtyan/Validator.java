package com.github.davdavtyan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String URL_REGEX =
            "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))" +
                    "(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)" +
                    "([).!';/?:,][[:blank:]])?$";

    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    public static boolean UrlIsValid(String url) {

        if (url == null) {
            return false;
        }

        Matcher matcher = URL_PATTERN.matcher(url);
        return matcher.matches();
    }

    public static boolean keywordIsValid(String keyword) {
        return keyword.length() <= 20;
    }

}
