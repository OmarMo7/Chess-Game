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
            if (this.y_axis > 7) // Precaution condition
                break;
            boolean begin = true;
            Cell classic_move_cell_1 = board[this.x_axis][2];
            Cell classic_move_cell_2 = board[this.x_axis][3];
            Cell cell_ahead = board[this.x_axis][this.y_axis + 1];
            Cell two_cells_ahead = board[this.x_axis][this.y_axis + 2];
            // Classic move of a pawn at the beginning of a game
            if (!classic_move_cell_1.isOccupied && !classic_move_cell_2.isOccupied && begin) {
                cellsAllowed.add(cell_ahead);
                cellsAllowed.add(two_cells_ahead);
                begin = false;
            }
            if (this.y_axis <= 6 && !cell_ahead.isOccupied) {
                // Classic move of a pawn at the mid game
                cellsAllowed.add(cell_ahead);
            }
            // When a pawn is about to be promoted
            else {
                // pawn here is to be replaced by an another material
            }

            // Checking if the cells at right-left of a pawn is occupied - Eat move
            Cell one_cell_up_left = board[this.x_axis - 1][this.y_axis + 1];
            Cell one_cell_up_right = board[this.x_axis + 1][this.y_axis + 1];
            if (!(this.x_axis == 0 || this.x_axis == 7)) {
                if (one_cell_up_left.isOccupied && one_cell_up_left.takenBy.color != this.color) {
                    cellsAllowed.add(one_cell_up_left);
                }

                if (one_cell_up_right.isOccupied && one_cell_up_right.takenBy.color != this.color) {
                    cellsAllowed.add(one_cell_up_right);
                }
            } else if (this.x_axis == 0) {
                if (one_cell_up_right.isOccupied && one_cell_up_right.takenBy.color != this.color) {
                    cellsAllowed.add(one_cell_up_right);
                }
            } else if (this.x_axis == 7) {
                if (one_cell_up_left.isOccupied && one_cell_up_left.takenBy.color != this.color) {
                    cellsAllowed.add(one_cell_up_left);
                }
            }
        case "Knight":

            if (this.x_axis > 8 && this.y_axis > 8) {
                // level 4
                if (this.y_axis >= 2 && this.x_axis >= 1) { // x >> [1-6] ----- y >> [2-7]
                    // left
                    if (this.y_axis <= 7) {
                        Cell cell_lvl_4_left = board[this.x_axis - 1][this.y_axis - 2];
                        if (!cell_lvl_4_left.isOccupied
                                || (cell_lvl_4_left.isOccupied && cell_lvl_4_left.color != this.color)) {
                            cellsAllowed.add(cell_lvl_4_left);
                        }
                    }
                    // Right
                    if (this.x_axis <= 6) {
                        Cell cell_lvl_4_right = board[this.x_axis + 1][this.y_axis - 2];
                        if (!cell_lvl_4_right.isOccupied
                                || (cell_lvl_4_right.isOccupied && cell_lvl_4_right.color != this.color)) {
                            cellsAllowed.add(cell_lvl_4_right);
                        }
                    }
                }
                // Level 3
                if (this.y_axis >= 1 && this.x_axis >= 2) { // x >> [2-5] ----- y >> [1-7]
                    // Left
                    if (this.y_axis <= 7) {
                        Cell cell_lvl_3_left = board[this.x_axis - 2][this.y_axis - 1];
                        if (!cell_lvl_3_left.isOccupied
                                || (cell_lvl_3_left.isOccupied && cell_lvl_3_left.color != this.color)) {
                            cellsAllowed.add(cell_lvl_3_left);
                        }
                    }
                    // Right
                    if (this.x_axis <= 5) {
                        Cell cell_lvl_3_right = board[this.x_axis + 2][this.y_axis - 1];
                        if (!cell_lvl_3_right.isOccupied
                                || (cell_lvl_3_right.isOccupied && cell_lvl_3_right.color != this.color)) {
                            cellsAllowed.add(cell_lvl_3_right);
                        }
                    }
                }
                // Level 2
                if (this.x_axis >= 2 && this.y_axis >= 0) { // x >> [2-5] ----- y >> [0-6]
                    // Left
                    if (this.y_axis <= 6) {
                        Cell cell_lvl_2_left = board[this.x_axis - 2][this.y_axis + 1];
                        if (!cell_lvl_2_left.isOccupied
                                || (cell_lvl_2_left.isOccupied && cell_lvl_2_left.color != this.color)) {
                            cellsAllowed.add(cell_lvl_2_left);
                        }
                    }
                    // Right
                    if (this.x_axis <= 5) {
                        Cell cell_lvl_2_right = board[this.x_axis + 2][this.y_axis + 1];
                        if (!cell_lvl_2_right.isOccupied
                                || (cell_lvl_2_right.isOccupied && cell_lvl_2_right.color != this.color)) {
                            cellsAllowed.add(cell_lvl_2_right);
                        }
                    }
                }
                // Level 1
                if (this.x_axis >= 1 && this.y_axis >= 0) { // x >> [1-6] ----- y >> [0-5]
                    // Left
                    if (this.y_axis <= 5) {
                        Cell cell_lvl_1_left = board[this.x_axis - 1][this.y_axis + 2];
                        if (!cell_lvl_1_left.isOccupied
                                || (cell_lvl_1_left.isOccupied && cell_lvl_1_left.takenBy.color != this.color)) {
                            cellsAllowed.add(cell_lvl_1_left);
                        }
                    }
                    // Right
                    if (this.x_axis <= 6) {
                        Cell cell_lvl_1_right = board[this.x_axis + 1][this.y_axis + 2];
                        if (!cell_lvl_1_right.isOccupied
                                || (cell_lvl_1_right.isOccupied && cell_lvl_1_right.takenBy.color != this.color)) {
                            cellsAllowed.add(cell_lvl_1_right);
                        }
                    }
                }
            }
        case "Bishop":
            int upLeft = 7 - this.y_axis;
            int downRight = this.x_axis;
            for (int k = 0; k < upLeft; k++) {// up left tendon
                Cell cell_up_left = board[this.x_axis + (k + 1)][this.y_axis + (k + 1)];
                if (!cell_up_left.isOccupied) {
                    cellsAllowed.add(cell_up_left);
                } else if (cell_up_left.isOccupied && cell_up_left.takenBy.color != this.color) {
                    cellsAllowed.add(cell_up_left);
                    break;
                } else
                    break;
            }
            for (int k = 0; k < downRight; k++) {// down right tendon
                Cell cell_down_right = board[this.x_axis - (k + 1)][this.y_axis - (k + 1)];
                if (!cell_down_right.isOccupied) {
                    cellsAllowed.add(cell_down_right);
                } else if (cell_down_right.isOccupied && cell_down_right.takenBy.color != this.color) {
                    cellsAllowed.add(cell_down_right);
                    break;
                } else
                    break;
            }

            int upRight = this.y_axis - 1;
            int downLeft = this.x_axis - 1;
            for (int k = 0; k < upRight; k++) { // up right tendon
                Cell cell_up_right = board[this.x_axis - (k + 1)][this.y_axis + (k + 1)];
                if (!cell_up_right.isOccupied) {
                    cellsAllowed.add(cell_up_right);
                } else if (cell_up_right.isOccupied && cell_up_right.takenBy.color != this.color) {
                    cellsAllowed.add(cell_up_right);
                    break;
                } else
                    break;
            }
            for (int k = 0; k < downLeft; k++) {// down left tendon
                Cell cell_down_left = board[this.x_axis + (k + 1)][this.y_axis - (k + 1)];
                if (!cell_down_left.isOccupied) {
                    cellsAllowed.add(cell_down_left);
                } else if (cell_down_left.isOccupied && cell_down_left.takenBy.color != this.color) {
                    cellsAllowed.add(cell_down_left);
                    break;
                } else
                    break;
            }

        case "Rook":
            //
            if (this.x_axis < 8 && this.y_axis < 8) {
                // Checking for any block in the column where Rook is located
                for (int i = 0; i < 8; i++) {
                    Cell cell_column = board[this.x_axis][this.y_axis + i];
                    if (!cell_column.isOccupied) {
                        cellsAllowed.add(cell_column);
                    } else if (cell_column.isOccupied && cell_column.takenBy.color != this.color) {
                        cellsAllowed.add(cell_column);
                        break;
                    }
                    // If a block found.. Cells allowed to move-at stops there
                    else
                        break;
                }
                // Checking for any block in the row where Rook is located
                for (int i = 0; i < 8; i++) {
                    Cell cell_row = board[this.x_axis + i][this.y_axis];
                    if (!cell_row.isOccupied) {
                        cellsAllowed.add(cell_row);
                    } else if (cell_row.isOccupied && cell_row.takenBy.color != this.color) {
                        cellsAllowed.add(cell_row);
                        break;
                    }
                    // If a block found.. Cells allowed to move-at stops there
                    else
                        break;
                }
            }
        case "Queen":
            //
            int upLeft_queen = 7 - this.y_axis;
            int downRight_queen = this.x_axis;
            for (int k = 0; k < upLeft_queen; k++) {// up left tendon
                Cell cell_up_left_queen = board[this.x_axis + (k + 1)][this.y_axis + (k + 1)];
                if (!cell_up_left_queen.isOccupied) {
                    cellsAllowed.add(cell_up_left_queen);
                } else if (cell_up_left_queen.isOccupied && cell_up_left_queen.takenBy.color != this.color) {
                    cellsAllowed.add(cell_up_left_queen);
                    break;
                } else
                    break;
            }
            for (int k = 0; k < downRight_queen; k++) {// down right tendon
                Cell cell_down_right_queen = board[this.x_axis - (k + 1)][this.y_axis - (k + 1)];
                if (!cell_down_right_queen.isOccupied) {
                    cellsAllowed.add(cell_down_right_queen);
                } else if (cell_down_right_queen.isOccupied && cell_down_right_queen.takenBy.color != this.color) {
                    cellsAllowed.add(cell_down_right_queen);
                    break;
                } else
                    break;
            }

            int upRight_queen = this.y_axis - 1;
            int downLeft_queen = this.x_axis - 1;
            for (int k = 0; k < upRight_queen; k++) { // up right tendon
                Cell cell_up_right_queen = board[this.x_axis - (k + 1)][this.y_axis + (k + 1)];
                if (!cell_up_right_queen.isOccupied) {
                    cellsAllowed.add(cell_up_right_queen);
                } else if (cell_up_right_queen.isOccupied && cell_up_right_queen.takenBy.color != this.color) {
                    cellsAllowed.add(cell_up_right_queen);
                    break;
                } else
                    break;
            }
            for (int k = 0; k < downLeft_queen; k++) {// down left tendon
                Cell cell_down_left_queen = board[this.x_axis + (k + 1)][this.y_axis - (k + 1)];
                if (!cell_down_left_queen.isOccupied) {
                    cellsAllowed.add(cell_down_left_queen);
                } else if (cell_down_left_queen.isOccupied && cell_down_left_queen.takenBy.color != this.color) {
                    cellsAllowed.add(cell_down_left_queen);
                    break;
                } else
                    break;
            }

            if (this.x_axis < 8 && this.y_axis < 8) {
                // Checking for any block in the column where Rook is located
                for (int i = 0; i < 8; i++) {
                    Cell cell_column_queen = board[this.x_axis][this.y_axis + i];
                    if (!cell_column_queen.isOccupied) {
                        cellsAllowed.add(cell_column_queen);
                    } else if (cell_column_queen.isOccupied && cell_column_queen.takenBy.color != this.color) {
                        cellsAllowed.add(cell_column_queen);
                        break;
                    }
                    // If a block found.. Cells allowed to move-at stops there
                    else
                        break;
                }
                // Checking for any block in the row where Rook is located
                for (int i = 0; i < 8; i++) {
                    Cell cell_row_queen = board[this.x_axis + i][this.y_axis];
                    if (!cell_row_queen.isOccupied) {
                        cellsAllowed.add(cell_row_queen);
                    } else if (cell_row_queen.isOccupied && cell_row_queen.takenBy.color != this.color) {
                        cellsAllowed.add(cell_row_queen);
                        break;
                    }
                    // If a block found.. Cells allowed to move-at stops there
                    else
                        break;
                }
            }
        case "King":
            //
            // if ((this.x_axis >= 1 && this.x_axis <= 6 && this.y_axis >= 1 && this.y_axis
            // <= 6)) {
            // if (board[this.x_axis + 1][this.y_axis + 1].takenBy.color != this.color) {
            // cellsAllowed.add(board[this.x_axis + 1][this.y_axis + 1]); // top right
            // }
            // if (board[this.x_axis + 1][this.y_axis].takenBy.color != this.color) {
            // cellsAllowed.add(board[this.x_axis + 1][this.y_axis]); // middle right
            // }
            // if (board[this.x_axis + 1][this.y_axis - 1].takenBy.color != this.color) {
            // cellsAllowed.add(board[this.x_axis + 1][this.y_axis - 1]);// down right
            // }
            // if (board[this.x_axis - 1][this.y_axis + 1].takenBy.color != this.color) {
            // cellsAllowed.add(board[this.x_axis - 1][this.y_axis + 1]); // top left
            // }
            // if (board[this.x_axis - 1][this.y_axis].takenBy.color != this.color) {
            // cellsAllowed.add(board[this.x_axis - 1][this.y_axis]); // middle left
            // }
            // if (board[this.x_axis - 1][this.y_axis - 1].takenBy.color != this.color) {
            // cellsAllowed.add(board[this.x_axis - 1][this.y_axis - 1]);// down left
            // }
            // if (board[this.x_axis][this.y_axis + 1].takenBy.color != this.color) {
            // cellsAllowed.add(board[this.x_axis][this.y_axis + 1]); // top
            // }
            // if (board[this.x_axis][this.y_axis - 1].takenBy.color != this.color) {
            // cellsAllowed.add(board[this.x_axis][this.y_axis - 1]); // down
            // }

            // }
            ////

            if ((this.x_axis >= 1 && this.x_axis <= 6)) {

                if (board[this.x_axis + 1][this.y_axis].takenBy.color != this.color) {
                    cellsAllowed.add(board[this.x_axis + 1][this.y_axis]); // middle right
                }
                if (board[this.x_axis - 1][this.y_axis].takenBy.color != this.color) {
                    cellsAllowed.add(board[this.x_axis - 1][this.y_axis]); // middle left
                }

            }

            if ((this.y_axis >= 1 && this.y_axis <= 6)) {

                if (board[this.x_axis][this.y_axis + 1].takenBy.color != this.color) {
                    cellsAllowed.add(board[this.x_axis][this.y_axis + 1]); // top
                }
                if (board[this.x_axis][this.y_axis - 1].takenBy.color != this.color) {
                    cellsAllowed.add(board[this.x_axis][this.y_axis - 1]); // down
                }
            }
            ////

            if ((this.x_axis >= 1 && this.y_axis <= 6)) {

                if (board[this.x_axis - 1][this.y_axis + 1].takenBy.color != this.color) {
                    cellsAllowed.add(board[this.x_axis - 1][this.y_axis + 1]); // top left
                }
            }
            if ((this.x_axis >= 1 && this.y_axis >= 1)) {

                if (board[this.x_axis - 1][this.y_axis - 1].takenBy.color != this.color) {
                    cellsAllowed.add(board[this.x_axis - 1][this.y_axis - 1]);// down left
                }
            }
            if ((this.x_axis <= 6 && this.y_axis <= 6)) {

                if (board[this.x_axis + 1][this.y_axis + 1].takenBy.color != this.color) {
                    cellsAllowed.add(board[this.x_axis + 1][this.y_axis + 1]); // top right
                }
            }
            if ((this.x_axis <= 6 && this.y_axis >= 1)) {

                if (board[this.x_axis + 1][this.y_axis - 1].takenBy.color != this.color) {
                    cellsAllowed.add(board[this.x_axis + 1][this.y_axis - 1]);// down right
                }
            }

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
