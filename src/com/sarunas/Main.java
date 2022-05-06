package com.sarunas;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

     String gameFile = "hummingbird_map.txt";

     char[][] board = readFile(gameFile);
     game(board);

//     char[][] arr = readFile(gameFile);
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[i].length; j++) {
//                System.out.print(arr[i][j]);
//            }
//            System.out.println();
//        }
    }


    private static char[][] readFile(String gameFile) throws IOException {            // reading txt file to 2D array
        int row = 0;
        String line = "";
        File file = new File(gameFile);
        Scanner scanner = new Scanner(file);
        List<String> read = new ArrayList<>();
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            read.add(line);
            ++row;
        }

        int column = read.get(0).toCharArray().length;
        char[][] board = new char[row][column];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = read.get(i).charAt(j);
            }
        }
        return board;
    }


    public static void game(char[][] board) {
        int rounds = 0;

        if (board.length == 1) {                                               // bird only moves in a row >
            int startI = 0;
            int startJ = 0;
            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board[x].length; y++) {
                    if (board[x][y] == '>') {
                        startI = x;
                        startJ = y;
                        break;
                    }
                }
            }
            boolean previousRight = true;
            while (previousRight) {
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[i].length; j++) {
                        if (board[i][j] == '>') {
                            if (i == startI && (j + 1) % board[0].length  == startJ) {
                                previousRight = false;
                                break;
                            } else if (board[i][(j + 1) % board[0].length] == '.') {
                                char tempJ = board[i][j];
                                board[i][j] = board[i][(j + 1) % board[0].length];
                                board[i][(j + 1) % board[i].length] = tempJ;
                                rounds++;
                            } else if (board[0][(j + 1) % board[i].length] == 'x') {
                                int p = j;
                                int e = j;
                                while (board[i][(e + 1) % board[i].length] == 'x') {
                                    e++;
                                }
                                char tempJX = board[i][p];
                                board[i][p] = board[i][(e + 1) % board[i].length];
                                board[i][(e + 1) % board[i].length] = tempJX;
                                rounds++;
                            }
                        }
                    }
                }
            }
        } else if (board[0].length == 1) {                                       // bird only moves the column v
            int startI = 0;
            int startJ = 0;
            for (int x = 0; x < board[0].length; x++) {
                for (int y = 0; y < board.length; y++) {
                    if (board[x][y] == 'v') {
                        startI = x;
                        startJ = y;
                        break;
                    }
                }
            }
            boolean previousDown = true;
            while (previousDown) {
                for (int b = 0; b < board[0].length; b++) {
                    for (int a = 0; a < board.length; a++) {
                        if (board[a][b] == 'v') {
                            if ((a + 1) % board.length == startI && b == startJ) {
                                previousDown = false;
                                break;
                            } else if (board[(a + 1) % board.length][b] == '.') {
                                char tempI = board[a][b];
                                board[a][b] = board[(a + 1) % board.length][b];
                                board[(a + 1) % board.length][b] = tempI;
                                rounds++;
                            } else if (board[(a + 1) % board.length][b] == 'x') {
                                int o = a;
                                int p = a;
                                while (board[(p + 1) % board.length][b] == 'x') {
                                    p++;
                                }
                                char tempIX = board[o][b];
                                board[o][b] = board[(p + 1) % board.length][b];
                                board[(p + 1) % board.length][b] = tempIX;
                                rounds++;
                            }
                        }
                    }
                }
            }
        } else {
            int countRight = 0;
            int countRightBreak = 0;
            int countDown = 0;
            int countDownBreak = 0;

            while (true) {
                for (int i = 0; i < board.length; i++) {                                  //birds moving right >
                    for (int j = 0; j < board[i].length; j++) {
                        if (board[i][j] == '>') {
                            countRight++;
                            if (board[i][(j + 1) % board[i].length] == '.') {
                                char tempJ = board[i][j];
                                board[i][j] = board[i][(j + 1) % board[i].length];
                                board[i][(j + 1) % board[i].length] = tempJ;
                                break;
                            } else if (board[i][(j + 1) % board[i].length] == 'x') {
                                int p = j;
                                int e = j;
                                while (board[i][(e + 1) % board[i].length] == 'x') {
                                    e++;
                                }
                                char tempJX = board[i][p];
                                board[i][p] = board[i][(e + 1) % board[i].length];
                                board[i][(e + 1) % board[i].length] = tempJX;
                                break;
                            } else if (board[i][(j + 1) % board[i].length] == 'v' || board[i][(j + 1) % board[i].length] == '>') {
                                countRightBreak++;
                                break;
                            }
                        }
                    }
                }
                if (countRight == countRightBreak) {
                    break;
                } else {
                    countRight = 0;
                    countRightBreak = 0;
                }
                for (int b = 0; b < board[0].length; b++) {                        // birds moving down v
                    for (int a = 0; a < board.length; a++) {
                        if (board[a][b] == 'v') {
                            countDown++;
                            if (board[(a + 1) % board.length][b] == '.') {
                                char tempI = board[a][b];
                                board[a][b] = board[(a + 1) % board.length][b];
                                board[(a + 1) % board.length][b] = tempI;
                                break;
                            } else if (board[(a + 1) % board.length][b] == 'x') {
                                int o = a;
                                int p = a;
                                while (board[(p + 1) % board.length][b] == 'x') {
                                    p++;
                                }
                                char tempIX = board[o][b];
                                board[o][b] = board[(p + 1) % board.length][b];
                                board[(p + 1) % board.length][b] = tempIX;
                                break;
                            } else if (board[(a + 1) % board.length][b] == '>' || board[(a + 1) % board.length][b] == 'v') {
                                countDownBreak++;
                                break;
                            }
                        }
                    }
                }
                if (countDown == countDownBreak) {
                    break;
                } else {
                    countDown = 0;
                    countDownBreak = 0;
                }
            rounds++;
            }
        }



        for (int m = 0; m < board.length; m++) {                         // checking 2D array
            for (int n = 0; n < board[m].length; n++) {
                System.out.print(board[m][n]);
            }
            System.out.println();
        }
        System.out.println(rounds);
    }
}

