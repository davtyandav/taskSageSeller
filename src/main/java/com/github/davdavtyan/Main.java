package com.github.davdavtyan;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        String EnterOriginalAddress = "Enter original address";
        String EnterShortAddress = "Enter short address";
        String EnterTextForShortAddress = "Enter a string for a short address";
        String beadAddress = "Invalid address";
        String beadData = "Incorrect data";
        String selectOptionDownloadAddress = "Select the option to download the address";
        String forLoadYourSelectedText = "1:  To load with your selected line";
        String forLoadRandomText = "2:  To load from random line";
        String forGetOriginalAddress = "3: To get the original address";
        String exit = "0:  exit";

        UrlShorter urlShorter = new UrlShorter();
        Scanner sc = new Scanner(System.in);
        System.out.println(forLoadYourSelectedText);
        System.out.println(forLoadRandomText);
        System.out.println(forGetOriginalAddress);
        System.out.println(exit);
        while (true) {
            System.out.println(selectOptionDownloadAddress);
            int i;
            if (!sc.hasNextInt()) {
                System.err.println(beadData);
                sc.next();
                i = -1;
            } else {
                i = sc.nextInt();
            }

            if (i == 1) {
                System.out.println(EnterOriginalAddress);
                String originUrl = sc.next();

                if (Validator.validateUrl(originUrl)) {
                    System.out.println(EnterTextForShortAddress);
                    String keyword = sc.next();
                    if (Validator.validateKeyword(keyword)) {
                        System.out.println(urlShorter.loadUrl(originUrl, keyword));
                    } else {
                        System.err.println(beadAddress);
                    }
                } else {
                    System.err.println(beadAddress);
                }

            } else if (i == 2) {
                System.out.println(EnterOriginalAddress);
                String originUrl = sc.next();
                if (!Validator.validateUrl(originUrl)) {
                    System.err.println(beadAddress);
                } else {
                    System.out.println(urlShorter.loadUrl(originUrl));
                }
            } else if (i == 3) {
                System.out.println(EnterShortAddress);
                String shortUrl = sc.next();
                if (!urlShorter.getUrls().containsKey(shortUrl)) {
                    System.err.println(beadAddress);
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
