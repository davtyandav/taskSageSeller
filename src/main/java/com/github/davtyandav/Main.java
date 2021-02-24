package com.github.davtyandav;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UrlShorter urlShorter = new UrlShorter();
        Scanner sc = new Scanner(System.in);
        System.out.println("1: Shrink URL with keyword");
        System.out.println("2: Shrink URL randomly");
        System.out.println("3: Get original url by shorten URL");
        System.out.println("0: exit");
        while (true) {
            System.out.println("Select option from menu");
            int i;
            if (sc.hasNextInt()) {
                i = sc.nextInt();
            } else {
                String next = sc.next();
                System.err.println("Unknown option: " + next);
                continue;
            }

            if (i == 1) {
                System.out.println("Enter the original address");
                String originUrl = sc.next();

                System.out.println("Enter the keyword");
                String keyword = sc.next();

                try {
                    String shortenURL = urlShorter.shrinkUrl(originUrl, keyword);
                    System.out.println("Shorten URL: " + shortenURL);
                } catch (InvalidUrlException | InvalidKeywordException e) {
                    System.err.println(e.getMessage());
                }
            } else if (i == 2) {
                System.out.println("Enter the original address");
                String originUrl = sc.next();

                try {
                    String shortenURL = urlShorter.shrinkUrl(originUrl);
                    System.out.println("Shorten URL: " + shortenURL);
                } catch (InvalidUrlException e) {
                    System.err.println(e.getMessage());
                }
            } else if (i == 3) {
                System.out.println("Enter the short address");
                String shortUrl = sc.next();
                String originUrl = urlShorter.getOriginUrl(shortUrl);

                if (originUrl == null) {
                    System.err.println("Original URL not found by " + shortUrl );
                } else {
                    System.out.println("Original URL: " + originUrl);
                }
            } else if (i == 0) {
                sc.close();
                break;
            } else {
                System.err.println("Unknown option: " + i);
            }
        }
    }
}
