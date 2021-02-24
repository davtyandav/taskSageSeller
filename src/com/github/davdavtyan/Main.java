package com.github.davdavtyan;

import java.util.*;

public class Main {

    public static final Map<String, String> urls = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(Utils.FOR_LOAD_YOUR_SELECTED_TEXT);
        System.out.println(Utils.FOR_LOAD_RANDOM_TEXT);
        System.out.println(Utils.FOR_GET_ORIGINAL_ADDRESS);
        System.out.println(Utils.EXIT);
        while (true) {
            System.out.println(Utils.SELECT_OPTION_DOWNLOAD_ADDRESS);
            int i;
            if (!sc.hasNextInt()) {
                System.err.println(Utils.BEAD_DATA);
                sc.next();
                i = -1;
            } else {
                i = sc.nextInt();
            }

            if (i == 1) {
                System.out.println(Utils.ENTER_ORIGINAL_ADDRESS);
                String originUrl = sc.next();

                if (Validator.validateUrl(originUrl)) {
                    System.out.println(Utils.ENTER_TEXT_FOR_SHORT_ADDRESS);
                    String keyword = sc.next();
                    if (Validator.validateKeyword(keyword)) {
                        System.out.println(loadUrl(originUrl, keyword));
                    } else {
                        System.err.println(Utils.BEAD_ADDRESS);
                    }
                } else {
                    System.err.println(Utils.BEAD_ADDRESS);
                }

            } else if (i == 2) {
                System.out.println(Utils.ENTER_ORIGINAL_ADDRESS);
                String originUrl = sc.next();
                if (!Validator.validateUrl(originUrl)) {
                    System.err.println(Utils.BEAD_ADDRESS);
                } else {
                    System.out.println(loadUrl(originUrl));
                }
            } else if (i == 3) {
                System.out.println(Utils.ENTER_SHORT_ADDRESS);
                String shortUrl = sc.next();
                if (!urls.containsKey(shortUrl)) {
                    System.err.println(Utils.BEAD_ADDRESS);
                } else {
                    System.out.println(getOriginUrl(shortUrl));
                }
            } else if (i == 0) {
                sc.close();
                break;
            }
        }
    }

    public static String loadUrl(String originUrl, String keyword) {
        return createUrl(originUrl, keyword);
    }

    public static String loadUrl(String originUrl) {
        return createUrl(originUrl, loadRandomTextForUrl());
    }

    private static String loadRandomTextForUrl() {
        StringBuilder sb = new StringBuilder(5);

        for (int i = 0; i < 5; i++) {
            int index = (int) (Utils.ALPHA_NUMERIC_TEXT.length() * Math.random());
            sb.append(Utils.ALPHA_NUMERIC_TEXT.charAt(index));
        }
        return sb.toString();
    }

    private static String createUrl(String originUrl, String shortText) {
        int index = originUrl.indexOf("//");
        String textHttp = originUrl.substring(0, index + 2);
        String newUrl = textHttp + "short.en/" + shortText;

        urls.put(newUrl, originUrl);
        return newUrl;
    }

    public static String getOriginUrl(String shortUrl) {
        return urls.get(shortUrl);
    }
}
