package chess_game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Omar Mostafa
 */
public class Cell {
    
    String name;
    boolean isOccupied;
    int x_axis, y_axis;
    String color;
    Piece takenBy;
    // to set properties as private
    public Cell(String name, int x_axis, int y_axis){
        this.x_axis = x_axis;
        this.y_axis = y_axis;
        this.name = name;
    }

    void setColor(String color) {
        this.color = color;
    }
    
    String getColor() {
        return this.color;
    }

};
