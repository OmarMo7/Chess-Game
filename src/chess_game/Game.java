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
            Scanner input = new Scanner(System.in);
            int x = input.nextInt();
            switch (x) {
                case 1:
                    displayBoard();
                    break;
                case 2:
                    move();
                    displayBoard();
                    break;
                default:
                    break;
            }
        }

        // TODO: display options
    }

    // TODO: optimization to be performed - to check both first and second
    // letter together instead of checking each one at once

    public void move() {
        String name = new String();
        Piece pieceToBeMoved = new Piece("", "", "", 0, 0);
        Scanner input = new Scanner(System.in);
        boolean existPiece = false;
        do {
            System.out.println("Enter the piece name");
            name = input.next();

            switch (name.length()) {
                case 2:
                case 3:
                    pieceToBeMoved = detectPiece(name, false, false, pieceToBeMoved); // e.g. NF3
                    existPiece = true;
                    break;
                case 4:
                    if (name.contains("x")) {
                        pieceToBeMoved = detectPiece(name, false, true, pieceToBeMoved); // e.g. NxF3
                        existPiece = true;
                    } else {
                        pieceToBeMoved = detectPiece(name, true, false, pieceToBeMoved); // e.g. NbF3 or e.g. N1F3
                        existPiece = true;
                    }
                    break;
                case 5:
                    pieceToBeMoved = detectPiece(name, true, true, pieceToBeMoved); // e.g. NbxF3 or e.g. N1xF3
                    existPiece = true;
                    break;
                default:
                    break;
            }
        } while (!existPiece);

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

    Piece detectPiece(String notation, boolean common, boolean takes, Piece pieceToBeMoved) {
        // Test case 1 >>> Nf3
        // Test case 2 >>> N1f3
        // Test case 3 >>> N1xf3
        // TODO: PieceToBeMoved is to be added as a fourth param
        int file = enumerate(notation.charAt(notation.length() - 2)) - 1; // f --> 1 as index
        int rank = enumerate(notation.charAt(notation.length() - 1)) - 1; // 3 --> 2 as index
        switch (notation.charAt(0)) {
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
            case 'g':
            case 'h':
                pieceToBeMoved = piecesOfPlayer1.get((enumerate(notation.charAt(0))) * 2 - 1);
                break;
            case 'R':
                pieceToBeMoved = check('R', notation, common, file, rank, pieceToBeMoved);
                break;
            case 'N':
                pieceToBeMoved = check('N', notation, common, file, rank, pieceToBeMoved);
                break;
            case 'B':
                if (piecesOfPlayer1.get(4).cellsAllowed.contains(board[file][rank])) {
                    pieceToBeMoved = piecesOfPlayer1.get(4);
                } else if (piecesOfPlayer1.get(10).cellsAllowed.contains(board[file][rank])) {
                    pieceToBeMoved = piecesOfPlayer1.get(10);
                } else {
                    System.out.println("ERROR!.. Unavailable cell");
                }
                break;
            case 'Q':
                if (piecesOfPlayer1.get(6).cellsAllowed.contains(board[file][rank])) {
                    pieceToBeMoved = piecesOfPlayer1.get(6);
                } else {
                    System.out.println("ERROR!.. Unavailable cell");
                }
                break;
            case 'K':
                if (piecesOfPlayer1.get(8).cellsAllowed.contains(board[file][rank])) {
                    pieceToBeMoved = piecesOfPlayer1.get(8);
                } else {
                    System.out.println("ERROR!.. Unavailable cell");
                }
                break;
            default:
                System.out.println("ERROR!..Invalid piece notation");
                break;
        }
        if (pieceToBeMoved.name != "") {
            board[pieceToBeMoved.x_axis][pieceToBeMoved.y_axis].takenBy = null;
            board[pieceToBeMoved.x_axis][pieceToBeMoved.y_axis].isOccupied = false;
            pieceToBeMoved.setPosition(file, rank);
            board[file][rank].takenBy = pieceToBeMoved;
            board[file][rank].isOccupied = true;
        }
        return pieceToBeMoved;
    }

    int enumerate(char c) {
        int index = 0;
        switch (c) {
            case 'a':
            case '1':
                index = 1;
                break;
            case 'b':
            case '2':
                index = 2;
                break;
            case 'c':
            case '3':
                index = 3;
                break;
            case 'd':
            case '4':
                index = 4;
                break;
            case 'e':
            case '5':
                index = 5;
                break;
            case 'f':
            case '6':
                index = 6;
                break;
            case 'g':
            case '7':
                index = 7;
                break;
            case 'h':
            case '8':
                index = 8;
                break;

            default:
                break;
        }
        return index;
    }

    Piece check(char material_name, String notation, boolean common, int file, int rank, Piece pieceToBeMoved) {
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
                int difference = enumerate(notation.charAt(notation.length() - 3)) - 1;
                if (piecesOfPlayer1.get(first_material_index).x_axis == piecesOfPlayer1
                        .get(second_material_index).x_axis) {
                    pieceToBeMoved = board[piecesOfPlayer1.get(first_material_index).x_axis][difference].takenBy;
                } else if (piecesOfPlayer1.get(first_material_index).y_axis == piecesOfPlayer1
                        .get(second_material_index).y_axis) {
                    pieceToBeMoved = board[difference][piecesOfPlayer1.get(first_material_index).y_axis].takenBy;
                } else if (difference == piecesOfPlayer1.get(first_material_index).x_axis) {
                    pieceToBeMoved = board[difference][piecesOfPlayer1.get(first_material_index).y_axis].takenBy;
                } else if (difference == piecesOfPlayer1.get(second_material_index).x_axis) {
                    pieceToBeMoved = board[difference][piecesOfPlayer1.get(second_material_index).y_axis].takenBy;
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
            } else {
                System.out.println("ERROR!.. Unavailable cell");
            }
        }
        return pieceToBeMoved;
    }
}
