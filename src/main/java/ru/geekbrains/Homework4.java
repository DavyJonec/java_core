package ru.geekbrains;

import java.util.Random;
import java.util.Scanner;

public class Homework4 {
// Переделать проверку победы, чтобы она не была реализована просто набором условий, например, с использованием циклов.

        private static final Random RANDOM = new Random();
        private static final Scanner SCANNER = new Scanner(System.in);
        private static final char DOT_HUMAN = 'X';
        private static final char DOT_AI = '0';
        private static final char DOT_EMPTY = '.';

        private static char[][] field;

        private static int fieldSizeX;
        private static int fieldSizeY;
        private static String playerName = "";

        private static int scoreHuman = 0;
        private static int scoreAI = 0;
        private static String playerOneName = "";
        private static int winLine = 0;

        public static void main(String[] args) {
            fieldSizeY = 10;
            fieldSizeX = 10;
            if(fieldSizeY > 3){
                winLine = fieldSizeY - 1;
            } else {
                winLine = fieldSizeY;
            }

            while (true) {
                if(playerOneName.equals("")){
                    System.out.print("Представьтесь пожалуйста >>> ");
                    playerOneName = SCANNER.nextLine();
                }
                initField();
                printField();

                while (true) {
                    humanTurn();
                    printField();
                    if (gameCheck(DOT_HUMAN, String.format("%s, вы великолепны! Победа!", playerOneName))) break;
                    aiTurn();
                    printField();
                    if (gameCheck(DOT_AI, "Компьютер победил!")) break;
                }//%s - строка, %d - целое число, %f вещественное число, %c символ %b булево
                System.out.printf("SCORE IS:\n%s: %d || AI: %d\n", playerOneName, scoreHuman, scoreAI);
                System.out.println("Wanna play again? >>> Y or N >>");
                if (!SCANNER.next().toLowerCase().equals("y")) break;
            }


        }

        private static boolean gameCheck(char dot, String s) {
            if (checkWin(dot, winLine)) {
                if (dot == DOT_HUMAN) {
                    scoreHuman++;
                } else {
                    scoreAI++;
                }
                System.out.println(s);
                return true;
            }
            if (checkDraw()) {
                System.out.println("DRAW!!!");
                return true;
            }
            return false;
        }

        private static boolean checkWin(char c, int winLine) {
            int countD1 = 0;
            int countD2 = 0;
            for (int y = 0; y < fieldSizeY; y++) {
                for (int x = 0; x < fieldSizeX; x++) {
                    if (x == y && field[y][x] == c){
                        countD1++;
                        if (countD1 == winLine)
                            return true;
                    }
                    if(x + y == fieldSizeY -1 && field[y][x] == c){
                        countD2++;
                        if(countD2 == winLine)
                            return true;
                    }
                }
            }
            countD1 = 0;
            countD2 = 0;
            for (int y = 0; y < fieldSizeY; y++) {
                for (int x = 0; x < fieldSizeX; x++) {
                    if(field[y][x] == c){
                        countD1++;
                        if(countD1 == winLine)
                            return true;
                    }
                    if(field[x][y] == c){
                        countD2++;
                        if(countD2 == winLine)
                            return true;
                    }
                }
                countD1 = 0;
                countD2 = 0;
            }
            return false;
        }

        private static boolean checkDraw() {
            for (int y = 0; y < fieldSizeY; y++) {
                for (int x = 0; x < fieldSizeX; x++) {
                    if (isCellEmpty(x, y)) return false;
                }
            }
            return true;
        }

        private static void humanTurn() {
            int x, y;
            do {
                System.out.printf("%s введите координаты х и у через пробел >>>>>", playerOneName);
                x = SCANNER.nextInt() - 1;
                y = SCANNER.nextInt() - 1;
            } while (!isCellValid(x, y) || !isCellEmpty(x, y));

            field[y][x] = DOT_HUMAN;
        }

        private static void aiTurn() {
            int x, y;
            do {
                x = RANDOM.nextInt(fieldSizeX);
                y = RANDOM.nextInt(fieldSizeY);
            } while (!isCellEmpty(x, y));

            field[y][x] = DOT_AI;
        }

        private static boolean isCellValid(int x, int y) {
            return x >= 0 && y >= 0 && x < fieldSizeX && y < fieldSizeY;
        }

        private static boolean isCellEmpty(int x, int y) {
            return field[y][x] == DOT_EMPTY;
        }


        private static void initField() {
            field = new char[fieldSizeY][fieldSizeX];
            for (int y = 0; y < fieldSizeY; y++) {
                for (int x = 0; x < fieldSizeX; x++) {
                    field[y][x] = DOT_EMPTY;
                }
            }
        }

        private static void printField() {
            System.out.print("+");
            for (int i = 0; i < fieldSizeX * 2 + 1; i++)
                System.out.print((i % 2 == 0) ? "-" : i / 2 + 1);
            System.out.println();

            for (int i = 0; i < fieldSizeY; i++) {
                System.out.print(i + 1 + "|");
                for (int j = 0; j < fieldSizeX; j++)
                    System.out.print(field[i][j] + "|");
                System.out.println();
            }

            for (int i = 0; i <= fieldSizeX * 2 + 1; i++)
                System.out.print("-");
            System.out.println();
//        for (int y = 0; y < fieldSizeY; y++) {
//            for (int x = 0; x < fieldSizeX; x++) {
//                System.out.print(field[y][x] + " ");
//            }
//            System.out.println();
//        }
        }

    }

