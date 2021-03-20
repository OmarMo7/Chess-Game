package chess_game;

import static chess_game.Board.piecesOfPlayer1;
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

    public Piece(String name, String color, int x_axis, int y_axis) {
        this.name = name;
//        this.color = color;
        this.x_axis = x_axis;
        this.y_axis = y_axis;
    }
    String name;
    String color;
    // To set properties as private
    int x_axis, y_axis;
    boolean isThreatened = false;
    boolean isEaten = false;

    ArrayList<Cell> cellsAllowed = new ArrayList();

    ArrayList calculateCells(Board b) {
        switch (this.name) {
            case "Pawn":
                //do something
                for (int i = 0; i < 3; i++) {
                    if (this.x_axis < 6 && this.y_axis < 6) { // checking if the pawn would go till the 7th row

                        if (b.board[this.x_axis][this.y_axis].isOccupied) { //If it's not blocked by another material, move it

                        } else {
                            System.out.println("error");
                        }
                    }
                }
            case "Knight":
            //ToDo
            case "Bishop":
            //ToDo
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
        piecesOfPlayer1.add(this);
    }
}
