package chess_game;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Omar Mostafa
 */
public class Board {

    public static ArrayList<Piece> piecesOfPlayer1 = new ArrayList();
    public static ArrayList<Piece> piecesOfPlayer2 = new ArrayList();
    public static Cell[][] board = new Cell[8][8];
    public static String cellLettersNumbers[] = new String[] { "h", "g", "f", "e", "d", "c", "b", "a", "1", "2", "3",
            "4", "5", "6", "7", "8" };

    void setPlayersMaterial(ArrayList<Piece> piecesOfPlayer1, ArrayList<Piece> piecesOfPlayer) {

    }

    // Mehtod setPosition to be made
    public void construct(Board b) {
        String letter = "";
        String material_name = "";
        String material_type = "";
        String material_color = "";
        for (int i = 0; i < 8; i++) {
            // Distribute letters over rows' numbers
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Cell(" ", 0, 0);
                board[i][j].name = cellLettersNumbers[i] + cellLettersNumbers[j + 8];

                // Even cells are black
                // Odd cells are white
                if ((i + j) % 2 == 0) {
                    board[i][j].color = "Black";
                } else {
                    board[i][j].color = "White";
                }
                // Colorizing white & black
                if (j == 0 || j == 1) {
                    material_color = "White";
                } else if (j == 6 || j == 7) {
                    material_color = "Black";
                }

                if (((j == 0 || j == 7) && (i == 0 || i == 7))) { // Rooks are positioned at such cells
                    material_name = "R";
                    material_type = "Rook";

                } else if ((j == 0 || j == 7) && (i == 1 || i == 6)) { // Knights are positioned at such cells
                    material_name = "N";
                    material_type = "Knight";

                } else if ((j == 0 || j == 7) && (i == 2 || i == 5)) { // Bishops are positioned at such cells
                    material_name = "B";
                    material_type = "Bishop";
                } else if (j == 1 || j == 6) { // 2nd and 7th row of the board are initially filled with pawns
                    material_name = cellLettersNumbers[i];
                    material_type = "Pawn";
                } else if (i == 3 && (j == 7 || j == 0)) {
                    material_name = "K";
                    material_type = "King";
                    // Both kings are positioned at such cells
                } else if (i == 4 && (j == 7 || j == 0)) {
                    material_name = "Q";
                    material_type = "Queen";
                    // Both queens are positioned at such cells
                } else {
                    material_name = "";
                    material_type = "";
                }
                if (material_name != "") {
                    Piece piece = new Piece(material_name, material_type, material_color, i, j);
                    board[i][j].takenBy = piece;
                    board[i][j].isOccupied = true;
                    if (board[i][j].takenBy.color == "White") {
                        piecesOfPlayer1.add(board[i][j].takenBy); // White pieces
                    } else {
                        piecesOfPlayer2.add(board[i][j].takenBy); // Black pieces
                    }
                } else {
                    // Piece piece = new Piece("", "", i, j);
                    board[i][j].takenBy = null;
                    board[i][j].isOccupied = false;
                }

            }

        }
    }

    void lookup() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].isOccupied) {
                    board[i][j].takenBy.calculateCells();
                }
            }
        }
    }
}
