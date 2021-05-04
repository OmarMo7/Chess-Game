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
            

            switch (name.length()) {
                case 3:
                    pieceToBeMoved = detectPiece(name, false, false); // e.g. NF3
                    existPiece = true;
                    break;
                case 4:
                    if (name.contains("x")) {
                        pieceToBeMoved = detectPiece(name, false, true); // e.g. NxF3
                        existPiece = true;
                    } else {
                        pieceToBeMoved = detectPiece(name, true, false); // e.g. NbF3 or e.g. N1F3
                        existPiece = true;
                    }
                    break;
                case 5:
                    pieceToBeMoved = detectPiece(name, true, true); // e.g. NbxF3 or e.g. N1xF3
                    existPiece = true;
                    break;
                default:
                    break;
            }
        } while(!existPiece);
        
        //     for (int i = 0; i < piecesOfPlayer1.size(); i++) {
        //         if (piecesOfPlayer1.get(i).name.equals(name)) {
        //             for (int k = i + 1; k < piecesOfPlayer1.size(); k++) {
        //                 if (piecesOfPlayer1.get(k).name.equals(name)) {
        //                     pieceToBeMoved2 = piecesOfPlayer1.get(k);
        //                 }
        //             }
        //             existPiece = true;
        //             pieceToBeMoved = piecesOfPlayer1.get(i);
        //             break;
        //         } else if (i == piecesOfPlayer1.size() - 1) {
        //             existPiece = false;
        //             break;
        //         }
        //     }
        // } while (!existPiece);
        // boolean existX = false;
        // boolean existY = false;
        // System.out.println("Enter where do you want to move your piece as x * y");
        // do {
        //     x = input.next();
        //     for (int i = 0; i < cellLettersNumbers.length; i++) {
        //         if (x.length() > 2)// Another message to be displayed here
        //             break;
        //         if (cellLettersNumbers[i].equals(x.substring(0, 1))) {
        //             existX = true;
        //             indexOfX = i;
        //             i += (8 - i); // To jump over the rest of letters
        //         } else if (cellLettersNumbers[i].equals(x.substring(1, 2))) {
        //             existY = true;
        //             break;

        //         }
        //         if (i == cellLettersNumbers.length - 1) {
        //             existX = false;
        //             System.out.println("Not in-board cell");
        //         }
        //     }
        // } while (!existX || !existY);
        // if (pieceToBeMoved.cellsAllowed.contains(
        //         board[Integer.parseInt(cellLettersNumbers[indexOfX + 8]) - 1][Integer.parseInt(x.substring(1, 2)) - 1])
        //         && pieceToBeMoved2.cellsAllowed.contains(board[Integer.parseInt(cellLettersNumbers[indexOfX + 8])
        //                 - 1][Integer.parseInt(x.substring(1, 2)) - 1])) {
        //     System.out.println("Common cell!!");
        //     System.out.println("Please, specify which piece to move");
        // } else if (pieceToBeMoved.cellsAllowed.contains(
        //         board[Integer.parseInt(cellLettersNumbers[indexOfX + 8]) - 1][Integer.parseInt(x.substring(1, 2))
        //                 - 1])) {
        //     board[pieceToBeMoved.x_axis][pieceToBeMoved.y_axis].isOccupied = false;
        //     board[pieceToBeMoved.x_axis][pieceToBeMoved.y_axis].takenBy = null;
        //     pieceToBeMoved.setPosition(Integer.parseInt(cellLettersNumbers[indexOfX + 8]) - 1,
        //             Integer.parseInt(x.substring(1, 2)) - 1);
        //     board[Integer.parseInt(cellLettersNumbers[indexOfX + 8]) - 1][Integer.parseInt(x.substring(1, 2))
        //             - 1].isOccupied = true;
        //     board[Integer.parseInt(cellLettersNumbers[indexOfX + 8]) - 1][Integer.parseInt(x.substring(1, 2))
        //             - 1].takenBy = pieceToBeMoved;

        // } else {
        //     System.out.println("Not available cell for such a piece!");
        // }
    
    }
    public void displayBoard() {

        // White perspective
        System.out.println("Black To Move...");
        for (int i = 0; i < 8; i++) {
            for (int j = 7; j >= 0; j--) {
                if (j == 7)
                    System.out.print("|");
                if (board[j][i].isOccupied) {
                    System.out.print("  " + board[j][i].takenBy.name + "  |");
                } else {
                    System.out.print("-----|");
                }
                if (j == 0)
                    System.out.print("\n");
            }
        }
        System.out.print("\n");
        // Black perspective
        System.out.println("White To Move...");
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if (j == 0)
                    System.out.print("|");
                if (board[j][i].isOccupied) {
                    System.out.print("  " + board[j][i].takenBy.name + "  |");
                } else {
                    System.out.print("-----|");
                }
                if (j == 7)
                    System.out.print("\n");

            }
        }
    }

    Piece detectPiece(String notation, boolean common, boolean takes) {
        // Test case 1 >>> Nf3
        // Test case 2 >>> N1f3
        // Test case 3 >>> N1xf3
        // TODO: PieceToBeMoved is to be added as a fourth param
        int file = enumerate(notation.charAt(notation.length() - 2)) - 1; // f --> 1 as index
        int rank = enumerate(notation.charAt(notation.length() - 1)) - 1; // 3 --> 2 as index
        Piece pieceToBeMoved = null;
        switch (notation.charAt(0)) {
            case 'R':
                check('R', notation, common, file, rank, pieceToBeMoved);
                break;
            case 'N':
                check('N', notation, common, file, rank, pieceToBeMoved);
                break;
            default:
                break;
        }

        return pieceToBeMoved;
    }

    int enumerate(char c) {
        int rank = 0;
        switch (c) {
            case 'a':
            case '1':
                rank = 1;
                break;
            case 'b':
            case '2':
                rank = 2;
                break;
            case 'c':
            case '3':
                rank = 3;
                break;
            case 'd':
            case '4':
                rank = 4;
                break;
            case 'e':
            case '5':
                rank = 5;
                break;
            case 'f':
            case '6':
                rank = 6;
                break;
            case 'g':
            case '7':
                rank = 7;
                break;
            case 'h':
            case '8':
                rank = 8;
                break;

            default:
                break;
        }
        return rank;
    }

    void check(char material_name, String notation, boolean common, int file, int rank, Piece pieceToBeMoved) {
        boolean p1_contains_cell = false;
        boolean p2_contains_cell = false;
        int first_material_index = 0;
        int second_material_index = 0;
        if (material_name == 'R') {
            p1_contains_cell = piecesOfPlayer1.get(0).cellsAllowed.contains(board[file][rank]);
            p2_contains_cell = piecesOfPlayer1.get(14).cellsAllowed.contains(board[file][rank]);
            first_material_index = 0;
            second_material_index = 14;
        } else if (material_name == 'N') {
            p1_contains_cell = piecesOfPlayer1.get(2).cellsAllowed.contains(board[file][rank]);
            p2_contains_cell = piecesOfPlayer1.get(12).cellsAllowed.contains(board[file][rank]);
            first_material_index = 2;
            second_material_index = 12;
        }
        if (common) {

            if (p1_contains_cell && p2_contains_cell) {
                // System.out.println("Please, specify piece");
                // System.out.println("ERROR!.. Invalid Notation");
                int difference = enumerate(notation.charAt(notation.length() - 3)) - 1;
                if (piecesOfPlayer1.get(first_material_index).x_axis == piecesOfPlayer1
                        .get(second_material_index).x_axis) {
                    pieceToBeMoved = board[piecesOfPlayer1.get(first_material_index).x_axis][difference].takenBy;
                } else if (piecesOfPlayer1.get(first_material_index).y_axis == piecesOfPlayer1
                        .get(second_material_index).y_axis) {
                    pieceToBeMoved = board[difference][piecesOfPlayer1.get(first_material_index).y_axis].takenBy;
                }
            } else if (p1_contains_cell && !p2_contains_cell) {
                pieceToBeMoved = piecesOfPlayer1.get(first_material_index);
            } else if (!p1_contains_cell && p2_contains_cell) {
                pieceToBeMoved = piecesOfPlayer1.get(second_material_index);
            }

        } else {
            p1_contains_cell = piecesOfPlayer1.get(first_material_index).cellsAllowed.contains(board[file][rank]);
            p2_contains_cell = piecesOfPlayer1.get(second_material_index).cellsAllowed.contains(board[file][rank]);
            if (p1_contains_cell && !p2_contains_cell) {
                pieceToBeMoved = piecesOfPlayer1.get(first_material_index);
            } else if (!p1_contains_cell && p2_contains_cell) {
                pieceToBeMoved = piecesOfPlayer1.get(second_material_index);
            }
        }
        board[pieceToBeMoved.x_axis][pieceToBeMoved.y_axis].takenBy = null;
        board[pieceToBeMoved.x_axis][pieceToBeMoved.y_axis].isOccupied = false;
        pieceToBeMoved.setPosition(file, rank);
        board[file][rank].takenBy = pieceToBeMoved;
        board[file][rank].isOccupied = true;
    }
}
