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


    private static char[][] readFile(String gameFile) throws IOException {
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

//     boolean flag = true;
//     while (flag) {


// moving right >

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '>') {                                 //goes right
                    if (board[i][(j + 1) % board[i].length] == 'v') {
                        break;
                    } else if (board[i][(j + 1) % board[i].length] != 'x') {

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
                        board[i][(e + 1) % board[i].length] = tempJX;//  to escape 'x'
                        break;
                    }
                }
            }
        }

// moving down v

        for (int a = 0; a < board.length; a++) {
            for (int b = 0; b < board[a].length; b++) {
                if (board[a][b] == 'v') {                                        //goes down
                    if (board[(a + 1) % board.length][b] == '>') {        // stops if >
                        break;
                    } else if (board[(a + 1) % board.length][b] != 'x') {
                        char tempI = board[a][b];
                        board[a][b] = board[(a + 1) % board.length][b];
                        board[(a + 1) % board.length][b] = tempI;
                        break;
                    } else if (board[(a + 1) % board.length][b] == 'x'){
                        int o = a;
                        int p = a;
                        while (board[(p + 1) % board.length][b] == 'x') {
                            p++;
                        }                                                                      //  to escape 'x'
                        char tempIX = board[o][b];
                        board[o][b] = board[(p + 1) % board.length][b];
                        board[(p + 1) % board.length][b] = tempIX;
                        break;
                    }
                }
            }
       }


              //  }




// checking 2D array

        for (int m = 0; m < board.length; m++) {
            for (int n = 0; n < board[m].length; n++) {
                System.out.print(board[m][n]);
            }
            System.out.println();
        }
    }


}

