package chess_game;

import java.util.Scanner;
import static chess_game.Board.board;
import static chess_game.Board.piecesOfPlayer1;
import static chess_game.Board.cellLettersNumbers;

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

    public Game() {

    }

    public void start() {
        Board b = new Board();
        b.construct(b);
        while (true) {
            b.lookup();
            System.out.println("Hello, please choose an option!");
            System.out.println("1- dsiplay board");
            System.out.println("2- move");
            System.out.println("3- see cells allowed");
            Scanner input = new Scanner(System.in);
            Scanner input2 = new Scanner(System.in);
            int x = input.nextInt();
            switch (x) {
            case 1:
                displayBoard();
                break;
            case 2:
                move();
                displayBoard();
                break;
            case 3:
                // System.out.println("Enter the cell: ");
                // String xx = input2.next();
                // for (int i = 0; i < board[Integer.parseInt(xx.substring(0, 1))][Integer
                // .parseInt(xx.substring(1, 2))].takenBy.cellsAllowed.size(); i++) {
                // System.out.println(board[Integer.parseInt(xx.substring(0, 1))][Integer
                // .parseInt(xx.substring(1, 2))].takenBy.cellsAllowed.get(i).x_axis + " "
                // + board[Integer.parseInt(xx.substring(0, 1))][Integer
                // .parseInt(xx.substring(1, 2))].takenBy.cellsAllowed.get(i).y_axis);
                // }
                // System.out.println(
                // board[Integer.parseInt(xx.substring(0, 1))][Integer.parseInt(xx.substring(1,
                // 2))].takenBy.name);
                // break;
            default:
                break;
            }
        }

        // TODO: display options
    }

    // TODO: optimization to be performed - to check both first and second
    // letter together instead of checking each one at once

    public void move() {
        String x = new String();
        int pawnCell = 0;
        int indexOfX = 0;
        String name = new String();
        Piece pieceToBeMoved = new Piece("", "", "", 0, 0);
        Piece pieceToBeMoved2 = new Piece("", "", "", 0, 0);
        Scanner input = new Scanner(System.in);
        boolean existPiece = false;
        do {
            System.out.println("Enter the piece name");
            name = input.next();
            if (name.length() == 1) {
                for (int i = 0; i < cellLettersNumbers.length; i++) {
                    if (cellLettersNumbers[i].equals(name)) {
                        pawnCell = i;
                        break;
                    }
                }
            }
            for (int i = 0; i < piecesOfPlayer1.size(); i++) {
                if (piecesOfPlayer1.get(i).name.equals(name)) {
                    for (int k = i + 1; k < piecesOfPlayer1.size(); k++) {
                        if (piecesOfPlayer1.get(k).name.equals(name)) {
                            pieceToBeMoved2 = piecesOfPlayer1.get(k);
                        }
                    }
                    existPiece = true;
                    pieceToBeMoved = piecesOfPlayer1.get(i);
                    break;
                } else if (i == piecesOfPlayer1.size() - 1) {
                    existPiece = false;
                    break;
                }
            }
        } while (!existPiece);
        boolean existX = false;
        boolean existY = false;
        System.out.println("Enter where do you want to move your piece as x * y");
        do {
            x = input.next();
            for (int i = 0; i < cellLettersNumbers.length; i++) {
                if (x.length() > 2)// Another message to be displayed here
                    break;
                if (cellLettersNumbers[i].equals(x.substring(0, 1))) {
                    existX = true;
                    indexOfX = i;
                    i += (8 - i); // To jump over the rest of letters
                } else if (cellLettersNumbers[i].equals(x.substring(1, 2))) {
                    existY = true;
                    break;

                }
                if (i == cellLettersNumbers.length - 1) {
                    existX = false;
                    System.out.println("Not in-board cell");
                }
            }
        } while (!existX || !existY);
        if (pieceToBeMoved.cellsAllowed.contains(
                board[Integer.parseInt(cellLettersNumbers[indexOfX + 8]) - 1][Integer.parseInt(x.substring(1, 2)) - 1])
                && pieceToBeMoved2.cellsAllowed.contains(board[Integer.parseInt(cellLettersNumbers[indexOfX + 8])
                        - 1][Integer.parseInt(x.substring(1, 2)) - 1])) {
            System.out.println("Common cell!!");
            System.out.println("Please, specify which piece to move");
        } else if (pieceToBeMoved.cellsAllowed.contains(
                board[Integer.parseInt(cellLettersNumbers[indexOfX + 8]) - 1][Integer.parseInt(x.substring(1, 2))
                        - 1])) {
            board[pieceToBeMoved.x_axis][pieceToBeMoved.y_axis].isOccupied = false;
            board[pieceToBeMoved.x_axis][pieceToBeMoved.y_axis].takenBy = null;
            pieceToBeMoved.setPosition(Integer.parseInt(cellLettersNumbers[indexOfX + 8]) - 1,
                    Integer.parseInt(x.substring(1, 2)) - 1);
            board[Integer.parseInt(cellLettersNumbers[indexOfX + 8]) - 1][Integer.parseInt(x.substring(1, 2))
                    - 1].isOccupied = true;
            board[Integer.parseInt(cellLettersNumbers[indexOfX + 8]) - 1][Integer.parseInt(x.substring(1, 2))
                    - 1].takenBy = pieceToBeMoved;

        } else {
            System.out.println("Not available cell for such a piece!");
        }
    }

    public void displayBoard() {

        // White perspective
        System.out.println("White To Move...");
        for (int i = 7; i >= 0; i--) {
            for (int j = 7; j >= 0; j--) {
                if (j == 7)
                    System.out.print("|");
                if (board[j][i].isOccupied) {
                    if (board[j][i].takenBy.name == cellLettersNumbers[j]) {
                        System.out.print("  " + cellLettersNumbers[j] + "  |");
                    } else {
                        System.out.print("  " + board[j][i].takenBy.name + "  |");
                    }
                } else {
                    System.out.print("-----|");
                }
                if (j == 0)
                    System.out.print("\n");
            }
        }
        System.out.print("\n");
        // Black perspective
        System.out.println("Black To Move...");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == 0)
                    System.out.print("|");
                if (board[j][i].isOccupied) {
                    if (board[j][i].takenBy.name == cellLettersNumbers[j]) {
                        System.out.print("  " + cellLettersNumbers[j] + "  |");
                    } else {
                        System.out.print("  " + board[j][i].takenBy.name + "  |");
                    }
                } else {
                    System.out.print("-----|");
                }
                if (j == 7)
                    System.out.print("\n");
            }
        }
    }
}
