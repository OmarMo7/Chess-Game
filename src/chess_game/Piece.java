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
            if (this.y_axis >= 7) // Precaution condition
                break;
            if (this.y_axis < 6) {
                // Classic move of a pawn at the beginning of a game
                if (!board[this.x_axis][3].isOccupied && !board[this.x_axis][4].isOccupied) {
                    cellsAllowed.add(board[this.x_axis][this.y_axis + 1]);
                    cellsAllowed.add(board[this.x_axis][this.y_axis + 2]);
                }

                // Classic move of a pawn at the mid game
                else if (!board[this.x_axis][this.y_axis + 1].isOccupied) {
                    cellsAllowed.add(board[this.x_axis][this.y_axis + 1]);
                }
            }
            // When a pawn is about to be promoted
            else {
                if (!board[this.x_axis][this.y_axis + 1].isOccupied) {
                    cellsAllowed.add(board[this.x_axis][this.y_axis + 1]);
                }
            }

            // Checking if the cells at right-left of a pawn is occupied - Eat move
            if (!(this.x_axis == 0 || this.x_axis == 7)) {
                if (board[this.x_axis - 1][this.y_axis + 1].isOccupied
                        && board[this.x_axis - 1][this.y_axis + 1].takenBy.color != this.color) {
                    cellsAllowed.add(board[this.x_axis - 1][this.y_axis + 1]);
                }

                if (board[this.x_axis + 1][this.y_axis + 1].isOccupied
                        && board[this.x_axis + 1][this.y_axis + 1].takenBy.color != this.color) {
                    cellsAllowed.add(board[this.x_axis + 1][this.y_axis + 1]);
                }
            } else if (this.x_axis == 0) {
                if (board[this.x_axis + 1][this.y_axis + 1].isOccupied
                        && board[this.x_axis + 1][this.y_axis + 1].takenBy.color != this.color) {
                    cellsAllowed.add(board[this.x_axis + 1][this.y_axis + 1]);
                }
            } else if (this.x_axis == 7) {
                if (board[this.x_axis - 1][this.y_axis + 1].isOccupied
                        && board[this.x_axis - 1][this.y_axis + 1].takenBy.color != this.color) {
                    cellsAllowed.add(board[this.x_axis - 1][this.y_axis + 1]);
                }
            }

            // for (int i = 3; i > 0; i--) {
            // int b = i - 2;
            // // The Cell ahead is Already checked above
            // if (b == 2)
            // continue;
            // if (board[this.x_axis - 1][this.y_axis + 1].isOccupied
            // && board[this.x_axis - b][this.y_axis + 1].takenBy.color != this.color) {
            // cellsAllowed.add(board[this.x_axis - b][this.y_axis + 1]);
            // }
            // }

        case "Knight":
            if (this.x_axis > 8 && this.y_axis > 8) {
                // level 4
                if (this.y_axis >= 2 && this.x_axis >= 1) { // x >> [1-6] ----- y >> [2-7]
                    // left
                    if (this.y_axis <= 7) {
                        if (!board[this.x_axis - 1][this.y_axis - 2].isOccupied
                                || (board[this.x_axis - 1][this.y_axis - 2].isOccupied
                                        && board[this.x_axis - 1][this.y_axis - 2].color != this.color)) {
                            cellsAllowed.add(board[this.x_axis - 1][this.y_axis - 2]);
                        }
                    }
                    // Right
                    if (this.x_axis <= 6) {
                        if (!board[this.x_axis + 1][this.y_axis - 2].isOccupied
                                || (board[this.x_axis + 1][this.y_axis - 2].isOccupied
                                        && board[this.x_axis + 1][this.y_axis - 2].color != this.color)) {
                            cellsAllowed.add(board[this.x_axis + 1][this.y_axis - 2]);
                        }
                    }
                }
                // Level 3
                if (this.y_axis >= 1 && this.x_axis >= 2) { // x >> [2-5] ----- y >> [1-7]
                    // Left
                    if (this.y_axis <= 7) {
                        if (!board[this.x_axis - 2][this.y_axis - 1].isOccupied
                                || (board[this.x_axis - 2][this.y_axis - 1].isOccupied
                                        && board[this.x_axis - 2][this.y_axis - 1].color != this.color)) {
                            cellsAllowed.add(board[this.x_axis - 2][this.y_axis - 2]);
                        }
                    }
                    // Right
                    if (this.x_axis <= 5) {
                        if (!board[this.x_axis + 2][this.y_axis - 1].isOccupied
                                || (board[this.x_axis + 2][this.y_axis - 1].isOccupied
                                        && board[this.x_axis + 2][this.y_axis - 1].color != this.color)) {
                            cellsAllowed.add(board[this.x_axis + 2][this.y_axis - 1]);
                        }
                    }
                }
                // Level 2
                if (this.x_axis >= 2 && this.y_axis >= 0) { // x >> [2-5] ----- y >> [0-6]
                    // Left
                    if (this.y_axis <= 6) {
                        if (!board[this.x_axis - 2][this.y_axis + 1].isOccupied
                                || (board[this.x_axis - 2][this.y_axis + 1].isOccupied
                                        && board[this.x_axis - 2][this.y_axis + 1].color != this.color)) {
                            cellsAllowed.add(board[this.x_axis - 2][this.y_axis + 1]);
                        }
                    }
                    // Right
                    if (this.x_axis <= 5) {
                        if (!board[this.x_axis + 2][this.y_axis + 1].isOccupied
                                || (board[this.x_axis + 2][this.y_axis + 1].isOccupied
                                        && board[this.x_axis + 2][this.y_axis + 1].color != this.color)) {
                            cellsAllowed.add(board[this.x_axis + 2][this.y_axis + 1]);
                        }
                    }
                }
                // Level 1
                if (this.x_axis >= 1 && this.y_axis >= 0) { // x >> [1-6] ----- y >> [0-5]
                    // Left
                    if (this.y_axis <= 5) {
                        if (!board[this.x_axis - 1][this.y_axis + 2].isOccupied
                                || (board[this.x_axis - 1][this.y_axis + 2].isOccupied
                                        && board[this.x_axis - 1][this.y_axis + 2].takenBy.color != this.color)) {
                            cellsAllowed.add(board[this.x_axis - 1][this.y_axis + 2]);
                        }
                    }
                    // Right
                    if (this.x_axis <= 6) {
                        if (!board[this.x_axis + 1][this.y_axis + 2].isOccupied
                                || (board[this.x_axis + 1][this.y_axis + 2].isOccupied
                                        && board[this.x_axis + 1][this.y_axis + 2].takenBy.color != this.color)) {
                            cellsAllowed.add(board[this.x_axis + 1][this.y_axis + 2]);
                        }
                    }
                }
            }
        case "Bishop":
            int up = 7 - this.y_axis;
            int down = this.x_axis;
            for (int k = 0; k < up; k++) {// up left tendon
                if (!board[this.x_axis + (k + 1)][this.y_axis + (k + 1)].isOccupied) {
                    cellsAllowed.add(board[this.x_axis + (k + 1)][this.y_axis + (k + 1)]);
                } else if (board[this.x_axis + (k + 1)][this.y_axis + (k + 1)].isOccupied
                        && board[this.x_axis + (k + 1)][this.y_axis + (k + 1)].takenBy.color != this.color) {
                    cellsAllowed.add(board[this.x_axis + (k + 1)][this.y_axis + (k + 1)]);
                    break;
                } else
                    break;
            }
            for (int k = 0; k < down; k++) {// down right tendon
                if (!board[this.x_axis - (k + 1)][this.y_axis - (k + 1)].isOccupied) {
                    cellsAllowed.add(board[this.x_axis - (k + 1)][this.y_axis - (k + 1)]);
                } else if (board[this.x_axis - (k + 1)][this.y_axis - (k + 1)].isOccupied
                        && board[this.x_axis - (k + 1)][this.y_axis - (k + 1)].takenBy.color != this.color) {
                    cellsAllowed.add(board[this.x_axis - (k + 1)][this.y_axis - (k + 1)]);
                    break;
                } else
                    break;
            }

            int up2 = this.y_axis - 1;
            int down2 = this.x_axis - 1;
            for (int k = 0; k < up2; k++) { // up right tendon
                if (!board[this.x_axis - (k + 1)][this.y_axis + (k + 1)].isOccupied) {
                    cellsAllowed.add(board[this.x_axis - (k + 1)][this.y_axis + (k + 1)]);
                } else if (board[this.x_axis - (k + 1)][this.y_axis + (k + 1)].isOccupied
                        && board[this.x_axis - (k + 1)][this.y_axis + (k + 1)].takenBy.color != this.color) {
                    cellsAllowed.add(board[this.x_axis - (k + 1)][this.y_axis + (k + 1)]);
                    break;
                } else
                    break;
            }
            for (int k = 0; k < down2; k++) {// down left tendon
                if (!board[this.x_axis + (k + 1)][this.y_axis - (k + 1)].isOccupied) {
                    cellsAllowed.add(board[this.x_axis + (k + 1)][this.y_axis - (k + 1)]);
                } else if (board[this.x_axis + (k + 1)][this.y_axis - (k + 1)].isOccupied
                        && board[this.x_axis + (k + 1)][this.y_axis - (k + 1)].takenBy.color != this.color) {
                    cellsAllowed.add(board[this.x_axis + (k + 1)][this.y_axis - (k + 1)]);
                    break;
                } else
                    break;
            }

        case "Rook":
            //
            if (this.x_axis < 8 && this.y_axis < 8) { // b = 8 - this.x_axis //for (int i = b; i <
                // Checking for any block in the column where Rook is located
                for (int i = 0; i < 8; i++) {
                    if (!board[this.x_axis][this.y_axis + i].isOccupied) {
                        cellsAllowed.add(board[this.x_axis][this.y_axis + i]);
                    } else if (board[this.x_axis][this.y_axis + i].isOccupied
                            && board[this.x_axis][this.y_axis + i].takenBy.color != this.color) {
                        cellsAllowed.add(board[this.x_axis][this.y_axis + i]);
                        break;
                    } else
                        break;
                }
                // Checking for any block in the row where Rook is located
                for (int i = 0; i < 8; i++) {
                    if (!board[this.x_axis + i][this.y_axis].isOccupied) {
                        cellsAllowed.add(board[this.x_axis + i][this.y_axis]);

                    } else if (board[this.x_axis + i][this.y_axis].isOccupied
                            && board[this.x_axis + i][this.y_axis].takenBy.color != this.color) {
                        cellsAllowed.add(board[this.x_axis + i][this.y_axis]);
                        break;
                    } else // If a block found.. Cells allowed to move-at stops there
                        break;
                }
            }
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
