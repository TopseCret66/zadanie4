package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {


// X O

    static char[][] field;

    public static void main(String[] args) {
//
        boolean finish;
        initField();
        showField();

        finish = isFinishedGame();
        System.out.println("Игора завершилася? ответ -" + finish);

        while (!finish) {
            movePlayer();
            finish = isFinishedGame();
            showField();
            if (finish) {
                break;
            }
            movePC();
            finish = isFinishedGame();
            showField();
        }
        System.out.println("That's all, folks");
    }


    public static void showField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void initField() {
        field = new char[3][3];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = '.';
            }
        }
    }

    public static void movePlayer() {
        Scanner sc = new Scanner(System.in);

        boolean isNotFinishedMove = true;

        while (isNotFinishedMove) {
            System.out.println("Выберите строку и столбец куда нужно ходить: ");
            try {
                int x = sc.nextInt() - 1;
                int y = sc.nextInt() - 1;
                if (x >= 0 && x < field.length && y >= 0 && y < field.length) {
                    if (!(field[x][y] == 'X') && !(field[x][y] == 'O')) {
                        field[x][y] = 'X';
                        isNotFinishedMove = false;
                    } else {
                        System.out.println("Тут уже сделан ход");
                    }
                } else {
                    System.out.println("Вы ушли за пределеы поля");
                }
            } catch (Exception e) {
                System.out.println("Вы ввели неправильные данные");
            }
        }

    }


//    . . . .
//    . . . .
//    . . . .
//    . . . .

    public static boolean isFinishedGame() {

        int countFreeSpace = 0;

        for (char[] arr : field)
            for (char elem : arr)
                if (elem == '.')
                    countFreeSpace += 1;

        boolean poobeda = pobedil("Игрок", 'X') || pobedil("Камплюхтеръ", '0');

        if (countFreeSpace == 0) {
            System.out.println("Ничья");
            return true;
        } else {
            return poobeda;
        }

    }

    private static boolean pobedil(String who, char symbol) {
        boolean strokaX = true;
        for (int t = 0; t < field.length; t++) {
            int i;
            for (i = 0; i < field[t].length; i++) {
                char p = field[t][i];
                boolean answer = p == symbol;
                strokaX = answer && strokaX;
            }
            if (strokaX) {
                System.out.println(who + " пообедал!");
                return true;
            }
        }

        boolean kolonkaX = true;
        for (int t = 0; t < field.length; t++) {
            int i;
            for (i = 0; i < field[t].length; i++) {
                char p = field[i][t];
                boolean answer = p == symbol;
                kolonkaX = answer && kolonkaX;
            }
            if (kolonkaX) {
                System.out.println(who + "пообедал!");
                return true;
            }
        }
        boolean diagonalX = true;
        for (int i = 0; i < field.length; i++) {
            char p = field[i][i];
            boolean answer = p == symbol;
            diagonalX = answer && diagonalX;
        }
        if (diagonalX) {
            System.out.println(who + "пообедал!");
            return true;
        }
        boolean diagonalX1 = true;
        for (int i = 0; i < field.length; i++) {
            char p = field[field.length - 1 - i][i];
            boolean answer = p == symbol;
            diagonalX1 = answer && diagonalX1;
        }
        if (diagonalX1) {
            System.out.println(who + "пообедал!");
            return true;
        }
        return false;
    }

    public static void movePC() {
        Random random = new Random();

        boolean isNotFinishedMove = true;

        while (isNotFinishedMove) {
            int x = random.nextInt(field.length);
            int y = random.nextInt(field.length);
            if (!(field[x][y] == 'X') && !(field[x][y] == 'O')) {
                field[x][y] = 'O';
                isNotFinishedMove = false;
            }
        }
        System.out.println("Компьютер сделал ход");
    }
}
