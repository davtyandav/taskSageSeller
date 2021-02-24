package com.github.davdavtyan;

import java.util.*;

public class Main {

    private static final String ENTER_ORIGINAL_ADDRESS = "Enter original address";
    private static final String ENTER_SHORT_ADDRESS = "Enter short address";
    private static final String ENTER_TEXT_FOR_SHORT_ADDRESS = "Enter a string for a short address";
    private static final String BEAD_ADDRESS = "Invalid address";
    private static final String BEAD_DATA = "Incorrect data";
    private static final String SELECT_OPTION_DOWNLOAD_ADDRESS = "Select the option to download the address";
    private static final String FOR_LOAD_YOUR_SELECTED_TEXT = "1:  To load with your selected line";
    private static final String FOR_LOAD_RANDOM_TEXT = "2:  To load from random line";
    private static final String FOR_GET_ORIGINAL_ADDRESS = "3: To get the original address";
    private static final String EXIT = "0:  exit";

    public static void main(String[] args) {
        UrlShorter urlShorter = new UrlShorter();
        Scanner sc = new Scanner(System.in);
        System.out.println(FOR_LOAD_YOUR_SELECTED_TEXT);
        System.out.println(FOR_LOAD_RANDOM_TEXT);
        System.out.println(FOR_GET_ORIGINAL_ADDRESS);
        System.out.println(EXIT);
        while (true) {
            System.out.println(SELECT_OPTION_DOWNLOAD_ADDRESS);
            int i;
            if (!sc.hasNextInt()) {
                System.err.println(BEAD_DATA);
                sc.next();
                i = -1;
            } else {
                i = sc.nextInt();
            }

            if (i == 1) {
                System.out.println(ENTER_ORIGINAL_ADDRESS);
                String originUrl = sc.next();

                if (Validator.validateUrl(originUrl)) {
                    System.out.println(ENTER_TEXT_FOR_SHORT_ADDRESS);
                    String keyword = sc.next();
                    if (Validator.validateKeyword(keyword)) {
                        System.out.println(urlShorter.loadUrl(originUrl, keyword));
                    } else {
                        System.err.println(BEAD_ADDRESS);
                    }
                } else {
                    System.err.println(BEAD_ADDRESS);
                }

            } else if (i == 2) {
                System.out.println(ENTER_ORIGINAL_ADDRESS);
                String originUrl = sc.next();
                if (!Validator.validateUrl(originUrl)) {
                    System.err.println(BEAD_ADDRESS);
                } else {
                    System.out.println(urlShorter.loadUrl(originUrl));
                }
            } else if (i == 3) {
                System.out.println(ENTER_SHORT_ADDRESS);
                String shortUrl = sc.next();
                if (!urlShorter.getUrls().containsKey(shortUrl)) {
                    System.err.println(BEAD_ADDRESS);
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
