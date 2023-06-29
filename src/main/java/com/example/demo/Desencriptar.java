package com.example.demo;

public class Desencriptar {
    public static String encrypt(String plaintext, int[] key) {
        int numRows = (int) Math.ceil((double) plaintext.length() / key.length);
        char[][] grid = new char[numRows][key.length];

        int index = 0;
        for (int col : key) {
            for (int row = 0; row < numRows; row++) {
                if (index < plaintext.length()) {
                    grid[row][col] = plaintext.charAt(index);
                    index++;
                }
            }
        }

        StringBuilder encryptedText = new StringBuilder();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < key.length; col++) {
                if (grid[row][col] != '\0') {
                    encryptedText.append(grid[row][col]);
                }
            }
        }

        return encryptedText.toString();
    }
}
