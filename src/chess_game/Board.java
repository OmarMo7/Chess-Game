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

    public static String cellLettersNumbers[] = { "a", "b", "c", "d", "e", "f", "g", "h", "1", "2", "3", "4", "5", "6",
            "7", "8" };

    void setPlayersMaterial(ArrayList<Piece> piecesOfPlayer1, ArrayList<Piece> piecesOfPlayer) {

    }

    // Mehtod setPosition to be made
    void construct(Board b) {
        char letter = ' ';
        String material_name = "";
        String material_color = "";
        for (int i = 0; i < 8; i++) {
            if (i == 0) { // Distribute letters over rows' numbers
                letter = 'a';
            } else if (i == 1) {
                letter = 'b';
            } else if (i == 2) {
                letter = 'c';
            } else if (i == 3) {
                letter = 'd';
            } else if (i == 4) {
                letter = 'e';
            } else if (i == 5) {
                letter = 'f';
            } else if (i == 6) {
                letter = 'g';
            } else {
                letter = 'h';
            }
            for (int j = 0; j < 8; j++) {
                board[i][j].name = letter + Integer.toString(j + 1);
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
                    material_name = "Rook";
                } else if ((j == 0 || j == 7) && (i == 1 || i == 6)) { // Knights are positioned at such cells
                    material_name = "Knight";
                } else if ((j == 0 || j == 7) && (i == 2 || i == 5)) { // Bishops are positioned at such cells
                    material_name = "Bishop";
                } else if (i == 1 || i == 6) { // 2nd and 7th row of the board are initially filled with pawns
                    material_name = "Pawn";
                } else if (i == 3 && j == 7) {
                    material_name = "Queen"; // Both queens are positioned at such cells
                } else if (i == 4 && j == 7) {
                    material_name = "King"; // Both kings are positioned at such cells
                }
                if (material_name != "") {
                    Piece piece = new Piece(material_name, material_color, i, j);
                    board[i][j].takenBy = piece;
                    board[i][j].isOccupied = true;
                    if (material_color == "white") {
                        piecesOfPlayer1.add(piece); // White pieces
                    } else {
                        piecesOfPlayer2.add(piece); // Black pieces
                    }

                } else {
                    board[i][j].takenBy = null;
                    board[i][j].isOccupied = false;
                }
            }

        }
    }
}
