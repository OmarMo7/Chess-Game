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

    String name = "";
    boolean isOccupied = false;
    int x_axis = 0, y_axis = 0;
    String color = "";
    Piece takenBy;

    // to set properties as private
    public Cell(String name, int x_axis, int y_axis) {
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
