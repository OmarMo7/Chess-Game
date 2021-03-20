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
public class Piece {

    String name;
    String color;

    int x_axis, y_axis;
    boolean isThreatened = false;
    boolean isEaten = false;
    
    public Piece(String material_name, String material_color, int x_axis, int y_axis){
        this.name = material_name;
        this.color = material_color;
        this.x_axis = x_axis;
        this.y_axis = y_axis;
    }

    ArrayList<Cell> cellsAllowed = new ArrayList();

    ArrayList calculateCells() {
        switch (this.name) {
            case "Pawn":
            //do something
                
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
