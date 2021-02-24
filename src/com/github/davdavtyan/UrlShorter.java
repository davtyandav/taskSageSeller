package com.github.davdavtyan;

import java.util.HashMap;
import java.util.Map;

public class UrlShorter {

    public Map<String, String> getUrls() {
        return urls;
    }

    public final Map<String, String> urls = new HashMap<>();

    public String loadUrl(String originUrl, String keyword) {
        return createUrl(originUrl, keyword);
    }

    public String loadUrl(String originUrl) {
        return createUrl(originUrl, loadRandomTextForUrl());
    }

    private String loadRandomTextForUrl() {
        StringBuilder sb = new StringBuilder(5);

        for (int i = 0; i < 5; i++) {
            int index = (int) (Utils.ALPHA_NUMERIC_TEXT.length() * Math.random());
            sb.append(Utils.ALPHA_NUMERIC_TEXT.charAt(index));
        }
        return sb.toString();
    }

    private String createUrl(String originUrl, String shortText) {
        int index = originUrl.indexOf("//");
        String textHttp = originUrl.substring(0, index + 2);
        String newUrl = textHttp + "short.en/" + shortText;

        urls.put(newUrl, originUrl);
        return newUrl;
    }

    public String getOriginUrl(String shortUrl) {
        return urls.get(shortUrl);
    }
}
