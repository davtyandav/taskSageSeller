package com.github.davdavtyan;

import java.util.*;

public class Main {

    public static final Map<String, String> urls = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(Utils.SELECT_OPTION_DOWNLOAD_ADDRESS);
        System.out.println(Utils.FOR_LOAD_YOUR_SELECTED_TEXT);
        System.out.println(Utils.FOR_LOAD_RANDOM_TEXT);
        System.out.println(Utils.FOR_GET_ORIGINAL_ADDRESS);
        System.out.println(Utils.EXIT);
        boolean isStart = true;
        while (isStart) {
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

                System.out.println(Utils.ENTER_TEXT_FOR_SHORT_ADDRESS);
                String next = sc.next();

                String loadUrl = loadUrl(originUrl, next);
                if (loadUrl.equals(Utils.BEAD_ADDRESS)) {
                    System.err.println(Utils.BEAD_ADDRESS);
                } else {
                    System.out.println(loadUrl);
                }
                i = -1;

            } else if (i == 2) {
                System.out.println(Utils.ENTER_ORIGINAL_ADDRESS);
                String originUrl = sc.next();

                System.out.println(loadRandomUrl(originUrl));
                i = -1;
            } else if (i == 3) {
                System.out.println(Utils.ENTER_SHORT_ADDRESS);
                String shortUrl = sc.next();
                String url = getUrl(shortUrl);
                if (url.equals(Utils.BEAD_ADDRESS)) {
                    System.err.println(Utils.BEAD_ADDRESS);
                } else {
                    System.out.println(url);
                }
                i = -1;
            } else if (i == 0) {
                sc.close();
                isStart = false;
            }
            if (i == -1) {
                System.out.println(Utils.SELECT_OPTION_DOWNLOAD_ADDRESS);
            }
        }

    }

    public static String loadUrl(String originUrl, String keyword) {
        if (keyword.length() > 20 || !URLValidator.isValid(originUrl)) {
            return Utils.BEAD_DATA;
        }
        return createUrl(originUrl, keyword);
    }

    public static String loadRandomUrl(String originUrl) {
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

    private static String createUrl(String originUrl, String sb) {
        int index = originUrl.indexOf("//");
        String substring = originUrl.substring(0, index + 2);
        String url = substring + "short.en/" + sb;

        urls.put(originUrl, url);
        return url;
    }

    public static String getUrl(String shortUrl) {
        Map.Entry<String, String> entryUrl = urls.entrySet().stream().filter(entry -> entry.getValue().equals(shortUrl)).findFirst().orElse(null);
        return entryUrl != null ? entryUrl.getKey() : Utils.BEAD_ADDRESS;
    }

}
