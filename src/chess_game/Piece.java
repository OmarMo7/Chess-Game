package chess_game;

import static chess_game.Board.board;
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
public class Piece {

    String name;
    String color;

    int x_axis, y_axis;
    boolean isThreatened = false;
    boolean isEaten = false;

    public Piece(String material_name, String material_color, int x_axis, int y_axis) {
        this.name = material_name;
        this.color = material_color;
        this.x_axis = x_axis;
        this.y_axis = y_axis;
    }

    ArrayList<Cell> cellsAllowed = new ArrayList();

    ArrayList calculateCells() {
        switch (this.name) {
        case "Pawn":
            if (this.y_axis >= 7) // Precation condition
                break;
            if (this.y_axis < 6) {
                // Classic move of a pawn
                if (!board[this.x_axis][this.y_axis + 1].isOccupied
                        && !board[this.x_axis][this.y_axis + 2].isOccupied) {
                    cellsAllowed.add(board[this.x_axis][this.y_axis + 1]);
                    cellsAllowed.add(board[this.x_axis][this.y_axis + 2]);
                } else if (!board[this.x_axis][this.y_axis + 1].isOccupied) {
                    cellsAllowed.add(board[this.x_axis][this.y_axis + 1]);
                }

            } else {
                if (!board[this.x_axis][this.y_axis + 1].isOccupied) {
                    cellsAllowed.add(board[this.x_axis][this.y_axis + 1]);
                }
            }

            // Checking if the cells at right-left of a pawn is occupied - Eat move
            for (int i = 3; i > 0; i--) {
                int b = i - 2;
                if (b == 2) // The Cell ahead is Already checked above
                    continue;
                // If there is a material at either left or right up ahead you can go either
                if (board[this.x_axis - b][this.y_axis + 1].isOccupied) {
                    cellsAllowed.add(board[this.x_axis - b][this.y_axis + 1]);
                }
            }

        case "Knight":
            //
        case "Bishop":
            //
        case "Rook":
            //
        case "Queen":
            //
        case "King":
            //
        }
        return cellsAllowed;
    }

    void move(int x_axisAmount, int y_axisAmount) {
        this.x_axis += x_axisAmount;
        this.y_axis += y_axisAmount;
    }

    void take(int x_axisAmount, int y_axisAmount, Piece toBeTaken) {
        this.x_axis += x_axisAmount;
        this.y_axis += y_axisAmount;
        toBeTaken.isEaten = true;
    }
}
