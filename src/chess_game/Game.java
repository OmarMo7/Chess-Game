package chess_game;

import java.util.Scanner;
import static chess_game.Board.board;
import static chess_game.Board.piecesOfPlayer1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Omar Mostafa
 */
public class Game {
    // public ArrayList <Piece> piecesOfPlayer1 = new ArrayList();
    // public static ArrayList <Piece> piecesOfPlayer2 = new ArrayList();
    public static String cellLettersNumbers[] = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "1", "2", "3",
            "4", "5", "6", "7", "8" };

    public Game() {

    }

    public void start() {
        Board b = new Board();
        b.construct(b);
        System.out.println("Hello, please choose an option!");
        System.out.println("1- dsiplay board");
        System.out.println("2- move");
        System.out.println("3- see cells allowed");
        Scanner input = new Scanner(System.in);
        int x = 3;
        switch (x) {
        case 1:
            displayBoard();
            break;
        case 2:
            // move();
            break;
        case 3:
            b.lookup();
            break;
        default:
            break;
        }

        // TODO: display options
    }

    // TODO: optimization to be performed - to check both first and second
    // letter together instead of checking each one at once

    public void move(Piece pieceToBeMoved, int x_axis, int y_axis) {
        String x = new String();
        String y = new String();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the cell name as x * y");
        boolean existX = false;
        do {
            System.out.println("Enter x");
            x = input.next();
            for (int i = 0; i < cellLettersNumbers.length; i++) {
                if (x.length() > 1)// Another message to be displayed here
                    break;
                if (cellLettersNumbers[i] == x) {
                    existX = true;
                    break;
                } else if (i == cellLettersNumbers.length - 1) {
                    existX = false;
                    System.out.println("Not in-board cell");
                }
            }
        } while (!existX);
        boolean existY = false;
        do {
            System.out.println("Enter y");
            y = input.next();
            for (int i = 0; i < cellLettersNumbers.length; i++) {
                if (y.length() > 1)// Another message to be displayed here
                    break;
                if (cellLettersNumbers[i] == y) {
                    existY = true;
                    break;
                } else if (i == cellLettersNumbers.length - 1) {
                    existY = false;
                    System.out.println("Not in-board cell");
                }
            }
        } while (!existY);
        input.close();
        if (pieceToBeMoved.cellsAllowed.contains(board[Integer.parseInt(x)][Integer.parseInt(y)])) {
            pieceToBeMoved.setPosition(Integer.parseInt(x), Integer.parseInt(y));
        } else {
            System.out.println("Not available cell for such a piece!");
        }
    }

    public void displayBoard() {

        // White perspective
        for (int i = 7; i >= 0; i--) {
            for (int j = 7; j >= 0; j--) {
                if (board[j][i].isOccupied) {
                    if (board[j][i].takenBy.name == "Pawn") {
                        if (j == 0 || j == 4) {
                            System.out.print("   " + "Pawn");
                        } else
                            System.out.print("  " + "Pawn" + " ");
                    } else
                        System.out.print(" " + board[j][i].takenBy.name + " ");
                } else {
                    System.out.print("------|");
                }
                if (j == 0)
                    System.out.print("\n");
            }
        }

        // Black perspective
        // for (int i = 0; i < 8; i++) {
        // for (int j = 0; j < 8; j++) {
        // if (board[j][i].isOccupied) {
        // if (board[j][i].takenBy.name == "Pawn") {
        // if (j == 3 || j == 7) {
        // System.out.print(" " + "Pawn" + " ");
        // } else
        // System.out.print(" " + "Pawn" + " ");
        // } else
        // System.out.print(" " + board[j][i].takenBy.name + " ");
        // } else {
        // System.out.print("------|");
        // }
        // if (j == 7)
        // System.out.print("\n");
        // }
        // }
    }

}
