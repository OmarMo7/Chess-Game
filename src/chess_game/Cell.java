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
    
    //to be changed to private
    
    public Cell(String name, boolean isOccupied, int x_axis, int y_axis, Piece takenBy){
        this.name = name;
        this.isOccupied = isOccupied;
        this.x_axis = x_axis;
        this.y_axis = y_axis;
        this.takenBy = takenBy;
    }

    void setColor(String color) {
        this.color = color;
    }
    
    String getColor() {
        return this.color;
    }

};
