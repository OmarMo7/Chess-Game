package chess_game;

import java.util.ArrayList;
import java.util.Scanner;
import static chess_game.Board.board;
import static chess_game.Board.piecesOfPlayer1;
import static chess_game.Board.piecesOfPlayer2;
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

    public Game() {

    }

    public static boolean WhiteToMove = true;
    public static Piece ThreateningPiece = null;

    public void start() {
        Board b = new Board();
        b.construct(b);
        while (true) {
            b.lookup(WhiteToMove);
            if (ThreateningPiece != null) {
                System.out.println("Check!..");
            }
            Scanner input = new Scanner(System.in);
            int x = 2;

            switch (x) {
                case 1:
                    displayBoard(WhiteToMove);
                    break;
                case 2:
                    displayBoard(WhiteToMove);
                    if (move(WhiteToMove)) {
                        WhiteToMove = !WhiteToMove;
                    }
                    break;
                default:
                    break;
            }

        }

        // TODO: display options
    }

    public static int validatePositiveInt(Scanner scanner) {
        int x;
        do {
            System.out.println("Hello, please choose an option!");
            System.out.println("1- dsiplay board");
            System.out.println("2- move");
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.printf("\"%s\" is not a valid number.\n", input);
            }
            x = scanner.nextInt();
        } while (x < 0);
        return x;
    }

    // TODO: optimization to be performed - to check both first and second
    // letter together instead of checking each one at once

    public boolean move(boolean White) {
        String name = new String();
        int indexOfAttackers[] = new int[] { 0, 4, 6, 10, 14 };
        Piece pieceToBeMoved = new Piece("", "", "", 0, 0);
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Enter the move notation: ");
            name = input.next();
            ArrayList<Piece> player = null;
            if (White) {
                player = piecesOfPlayer1;
            } else {
                player = piecesOfPlayer2;
            }
            switch (name.length()) {
                case 2:
                case 3:
                    pieceToBeMoved = detectPiece(name, false, false, player, pieceToBeMoved); // e.g. NF3
                    break;
                case 4:
                    if (name.contains("x")) {
                        pieceToBeMoved = detectPiece(name, false, true, player, pieceToBeMoved); // e.g. NxF3
                    } else {
                        pieceToBeMoved = detectPiece(name, true, false, player, pieceToBeMoved); // e.g. NbF3 or e.g. //
                                                                                                 // N1F3
                    }
                    break;
                case 5:
                    pieceToBeMoved = detectPiece(name, true, true, player, pieceToBeMoved); // e.g. NbxF3 or e.g. N1xF3
                    break;
                default:
                    break;
            }
            // TODO: if user enters notation of a piece that is not existed.. he shall not
            // see the "Invalid move" message if his king was threatend
            for (int i = 0; i < indexOfAttackers.length; i++) {
                piecesOfPlayer1.get(indexOfAttackers[i]).capturePiece();
                piecesOfPlayer2.get(indexOfAttackers[i]).capturePiece();
            }
            if (ThreateningPiece != null && !ThreateningPiece.isEaten && player.get(8).isThreatened) {
                ThreateningPiece.calculateCells();
                // Piece blockingPiece = getTheBlockingPiece(cellsAllowed1, cellsAllowed2);
                if (!ThreateningPiece.cellsAllowed.isEmpty()
                        && ThreateningPiece.cellsAllowed.contains(board[player.get(8).x_axis][player.get(8).y_axis])) {
                    System.out.println("Invalid move.. Your king is checked!");
                    return move(White);
                } else {
                    ThreateningPiece = null;
                }
            }
        } while (pieceToBeMoved.name == "");
        return true;
    }

    public void displayBoard(boolean whiteToMove) {
        // if it White's player turn
        if (whiteToMove) {
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
        } else {
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
        }

    }

    Piece detectPiece(String notation, boolean common, boolean takes, ArrayList<Piece> player, Piece pieceToBeMoved) {
        // Test case 1 >>> Nf3
        // Test case 2 >>> N1f3
        // Test case 3 >>> N1xf3
        // TODO: PieceToBeMoved is to be added as a fourth param
        Piece Other_King = new Piece("", "", "", 0, 0);
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
                if (!player.get((enumerate(notation.charAt(0))) * 2 - 1).isEaten
                        && player.get((enumerate(notation.charAt(0))) * 2 - 1).cellsAllowed
                                .contains(board[file][rank])) {
                    pieceToBeMoved = player.get((enumerate(notation.charAt(0))) * 2 - 1);
                } else {
                    System.out.println("ERROR!.. Unavailable cell");
                }
                break;
            case 'R':
                pieceToBeMoved = check('R', notation, common, file, rank, player, pieceToBeMoved);
                break;
            case 'N':
                pieceToBeMoved = check('N', notation, common, file, rank, player, pieceToBeMoved);
                break;
            case 'B':
                if (!player.get(4).isEaten && player.get(4).cellsAllowed.contains(board[file][rank])) {
                    pieceToBeMoved = player.get(4);
                } else if (!player.get(10).isEaten && player.get(10).cellsAllowed.contains(board[file][rank])) {
                    pieceToBeMoved = player.get(10);
                } else {
                    System.out.println("ERROR!.. Unavailable piece or cell");
                }
                break;
            case 'Q':
                if (!player.get(6).isEaten && player.get(6).cellsAllowed.contains(board[file][rank])) {
                    pieceToBeMoved = player.get(6);
                } else {
                    System.out.println("ERROR!.. Unavailable piece or cell");
                }
                break;
            case 'K':
                if (!player.get(8).isEaten && player.get(8).cellsAllowed.contains(board[file][rank])) {
                    pieceToBeMoved = player.get(8);
                } else {
                    System.out.println("ERROR!.. Unavailable piece or cell");
                }
                break;
            default:
                System.out.println("ERROR!..Invalid piece notation");
                break;
        }
        if (pieceToBeMoved.isBlocking) {
            System.out.println("You cannot move a defending piece!");
            Piece emptyPiece = new Piece("", "", "", 0, 0);
            return emptyPiece;
        }
        if (pieceToBeMoved.name != "") {
            board[pieceToBeMoved.x_axis][pieceToBeMoved.y_axis].takenBy = null;
            board[pieceToBeMoved.x_axis][pieceToBeMoved.y_axis].isOccupied = false;
            pieceToBeMoved.setPosition(file, rank);
            ArrayList<Piece> Other_Player = null;
            if (WhiteToMove) {
                Other_King = piecesOfPlayer2.get(8);
                Other_Player = piecesOfPlayer2;
            } else {
                Other_King = piecesOfPlayer1.get(8);
                Other_Player = piecesOfPlayer1;
            }
            if (board[file][rank].isOccupied && !WhiteToMove) {
                Other_Player = piecesOfPlayer1;
                Other_Player.get(Other_Player.indexOf(board[file][rank].takenBy)).isEaten = true;
                board[file][rank].takenBy = pieceToBeMoved;
            } else if (board[file][rank].isOccupied && WhiteToMove) {
                Other_Player = piecesOfPlayer2;
                Other_Player.get(Other_Player.indexOf(board[file][rank].takenBy)).isEaten = true;
                board[file][rank].takenBy = pieceToBeMoved;
            } else {
                board[file][rank].takenBy = pieceToBeMoved;
                board[file][rank].isOccupied = true;
            }

            for (int i = 0; i < player.size(); i++) {
                Piece CurrentPiece = player.get(i);
                CurrentPiece.calculateCells();
                if (!CurrentPiece.cellsAllowed.isEmpty()
                        && CurrentPiece.cellsAllowed.contains(board[Other_King.x_axis][Other_King.y_axis])) {
                    Other_King.isThreatened = true;
                    ThreateningPiece = CurrentPiece;
                }
            }
        } else {
            System.out.println("Unfound piece!");
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

    Piece check(char material_name, String notation, boolean common, int file, int rank, ArrayList<Piece> player,
            Piece pieceToBeMoved) {
        boolean p1_contains_cell = false;
        boolean p2_contains_cell = false;
        int first_material_index = 0;
        int second_material_index = 0;
        if (material_name == 'R') {
            if (!player.get(0).isEaten) {
                p1_contains_cell = player.get(0).cellsAllowed.contains(board[file][rank]);
            }
            if (!player.get(14).isEaten) {
                p2_contains_cell = player.get(14).cellsAllowed.contains(board[file][rank]);
            }
            first_material_index = 0;
            second_material_index = 14;
        } else if (material_name == 'N') {
            if (!player.get(2).isEaten) {
                p1_contains_cell = player.get(2).cellsAllowed.contains(board[file][rank]);
            }
            if (!player.get(12).isEaten) {
                p2_contains_cell = player.get(12).cellsAllowed.contains(board[file][rank]);
            }
            first_material_index = 2;
            second_material_index = 12;
        }
        if (common) {

            if (p1_contains_cell && p2_contains_cell) {
                int difference = enumerate(notation.charAt(notation.length() - 3)) - 1;
                if (player.get(first_material_index).x_axis == player.get(second_material_index).x_axis) {
                    pieceToBeMoved = board[player.get(first_material_index).x_axis][difference].takenBy;
                } else if (player.get(first_material_index).y_axis == player.get(second_material_index).y_axis) {
                    pieceToBeMoved = board[difference][player.get(first_material_index).y_axis].takenBy;
                } else if (difference == player.get(first_material_index).x_axis) {
                    pieceToBeMoved = board[difference][player.get(first_material_index).y_axis].takenBy;
                } else if (difference == player.get(second_material_index).x_axis) {
                    pieceToBeMoved = board[difference][player.get(second_material_index).y_axis].takenBy;
                }
            } else if (p1_contains_cell && !p2_contains_cell) {
                pieceToBeMoved = player.get(first_material_index);
            } else if (!p1_contains_cell && p2_contains_cell) {
                pieceToBeMoved = player.get(second_material_index);
            }

        } else {
            p1_contains_cell = player.get(first_material_index).cellsAllowed.contains(board[file][rank]);
            p2_contains_cell = player.get(second_material_index).cellsAllowed.contains(board[file][rank]);
            if (p1_contains_cell && !p2_contains_cell) {
                pieceToBeMoved = player.get(first_material_index);
            } else if (!p1_contains_cell && p2_contains_cell) {
                pieceToBeMoved = player.get(second_material_index);
            } else {
                System.out.println("ERROR!.. Unavailable cell");
            }
        }
        return pieceToBeMoved;
    }
}
