package com.github.davtyandav;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlShorter {

    public static final String ALPHA_NUMERIC_TEXT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";

    private static final Pattern URL_PATTERN
            = Pattern.compile("^https?://(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&/=]*)$");

    private final Map<String, String> urls = new HashMap<>();

    public String addShrinkUrl(String originUrl) {
        return addShrinkUrl(originUrl, generateRandomTextForUrl());
    }

    public String addShrinkUrl(String originUrl, String keyword) {
        String shortenUrl = createShortenUrlFromOriginal(originUrl, keyword);
        urls.put(shortenUrl, originUrl);
        return shortenUrl;
    }

    public String getOriginUrl(String shortUrl) {
        return urls.get(shortUrl);
    }

    private String generateRandomTextForUrl() {
        char[] chars = new char[5];

        int templateLength = ALPHA_NUMERIC_TEXT.length();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(templateLength);
            chars[i] = ALPHA_NUMERIC_TEXT.charAt(index);
        }

        return new String(chars);
    }

    private String createShortenUrlFromOriginal(String originUrl, String shortText) {
        int index = originUrl.indexOf("//");
        String textHttp = originUrl.substring(0, index + 2);

        return textHttp + "short.en/" + shortText;
    }

    public void validateUrl(String url) throws InvalidUrlException {
        if (url == null) {
            throw new InvalidUrlException("Url not provided");
        }

        Matcher matcher = URL_PATTERN.matcher(url);
        if (!matcher.matches()) {
            throw new InvalidUrlException("Invalid url: " + url);
        }
    }

    public void validateKeyword(String keyword) throws InvalidKeywordException {
        if (keyword.length() > 20) {
            throw new InvalidKeywordException("Too large keyword: " + keyword);
        }
    }
}
