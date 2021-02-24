package com.github.davdavtyan;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        UrlShorter urlShorter =new UrlShorter();
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
                        System.out.println(urlShorter.loadUrl(originUrl, keyword));
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
                    System.out.println(urlShorter.loadUrl(originUrl));
                }
            } else if (i == 3) {
                System.out.println(Utils.ENTER_SHORT_ADDRESS);
                String shortUrl = sc.next();
                if (!urlShorter.getUrls().containsKey(shortUrl)) {
                    System.err.println(Utils.BEAD_ADDRESS);
                } else {
                    System.out.println(urlShorter.getOriginUrl(shortUrl));
                }
            } else if (i == 0) {
                sc.close();
                break;
            }
        }
    }
}
