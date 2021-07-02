package chess_game;

import java.util.ArrayList;
import static chess_game.Board.board;

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

    String name = " ";
    String color = " ";
    String type = " ";

    int x_axis = 0, y_axis = 0;
    boolean isThreatened = false;
    boolean isEaten = false;

    public Piece(String material_name, String type, String material_color, int x_axis, int y_axis) {
        this.name = material_name;
        this.type = type;
        this.color = material_color;
        this.x_axis = x_axis;
        this.y_axis = y_axis;
    }

    public ArrayList<Cell> cellsAllowed = new ArrayList();

    ArrayList calculateCells() {
        this.cellsAllowed.clear();
        switch (this.type) {
            case "Pawn":
                if (this.y_axis > 7) // Precaution condition
                    break;
                Cell classic_move_cell_1 = null;
                Cell classic_move_cell_2 = null;
                Cell cell_ahead = null;
                Cell two_cells_ahead = null;
                if (this.color == "White") {
                    classic_move_cell_1 = board[this.x_axis][2];
                    classic_move_cell_2 = board[this.x_axis][3];
                    cell_ahead = board[this.x_axis][this.y_axis + 1];
                    two_cells_ahead = board[this.x_axis][this.y_axis + 2];
                } else {
                    classic_move_cell_1 = board[this.x_axis][5];
                    classic_move_cell_2 = board[this.x_axis][4];
                    cell_ahead = board[this.x_axis][this.y_axis - 1];
                    two_cells_ahead = board[this.x_axis][this.y_axis - 2];
                }
                // Classic move of a pawn at the beginning of a game
                if (!classic_move_cell_1.isOccupied && !classic_move_cell_2.isOccupied
                        && ((this.color == "White" && this.y_axis == 1)
                                || (this.color == "Black" && this.y_axis == 6))) {
                    cellsAllowed.add(cell_ahead);
                    cellsAllowed.add(two_cells_ahead);
                } else if (this.color == "White" && this.y_axis <= 6 && !cell_ahead.isOccupied) {
                    // Classic move of a pawn at the mid game
                    cellsAllowed.add(cell_ahead);
                } else if (this.color == "Black" && this.y_axis >= 1 && !cell_ahead.isOccupied) {
                    // Classic move of a pawn at the mid game
                    cellsAllowed.add(cell_ahead);
                }
                // When a pawn is about to be promoted
                else {
                    // pawn here is to be replaced by an another material
                }

                // Checking if the cells at right-left of a pawn is occupied - Eat move

                if (!(this.x_axis == 0 || this.x_axis == 7)) {
                    Cell one_cell_up_left = null;
                    if (this.color == "White") {
                        one_cell_up_left = board[this.x_axis - 1][this.y_axis + 1];
                    } else {
                        one_cell_up_left = board[this.x_axis + 1][this.y_axis - 1];
                    }
                    if (one_cell_up_left.isOccupied && one_cell_up_left.takenBy.color != this.color) {
                        cellsAllowed.add(one_cell_up_left);
                    }
                    Cell one_cell_up_right = null;
                    if (this.color == "White") {
                        one_cell_up_right = board[this.x_axis + 1][this.y_axis + 1];
                    } else {
                        one_cell_up_right = board[this.x_axis - 1][this.y_axis - 1];
                    }
                    if (one_cell_up_right.isOccupied && one_cell_up_right.takenBy.color != this.color) {
                        cellsAllowed.add(one_cell_up_right);
                    }
                } else if (this.x_axis == 0) {
                    Cell one_cell_up_right = null;
                    if (this.color == "White") {
                        one_cell_up_right = board[this.x_axis + 1][this.y_axis + 1];
                    } else {
                        one_cell_up_right = board[this.x_axis + 1][this.y_axis - 1];
                    }
                    if (one_cell_up_right.isOccupied && one_cell_up_right.takenBy.color != this.color) {
                        cellsAllowed.add(one_cell_up_right);
                    }
                } else if (this.x_axis == 7) {
                    Cell one_cell_up_left = null;
                    if (this.color == "White") {
                        one_cell_up_left = board[this.x_axis - 1][this.y_axis + 1];
                    } else {
                        one_cell_up_left = board[this.x_axis - 1][this.y_axis - 1];
                    }
                    if (one_cell_up_left.isOccupied && one_cell_up_left.takenBy.color != this.color) {
                        cellsAllowed.add(one_cell_up_left);
                    }
                }
                break;
            case "Knight":
                if (this.x_axis < 8 && this.y_axis < 8) {
                    // level 4
                    if (this.y_axis >= 2 && this.y_axis <= 7) { // x >> [1-6] ----- y >> [2-7]
                        // left
                        if (this.x_axis >= 1) {
                            Cell cell_lvl_4_left = board[this.x_axis - 1][this.y_axis - 2];
                            if (!cell_lvl_4_left.isOccupied
                                    || (cell_lvl_4_left.isOccupied && cell_lvl_4_left.takenBy.color != this.color)) {
                                cellsAllowed.add(cell_lvl_4_left);
                            }
                        }
                        // Right
                        if (this.x_axis <= 6) {
                            Cell cell_lvl_4_right = board[this.x_axis + 1][this.y_axis - 2];
                            if (!cell_lvl_4_right.isOccupied
                                    || (cell_lvl_4_right.isOccupied && cell_lvl_4_right.takenBy.color != this.color)) {
                                cellsAllowed.add(cell_lvl_4_right);
                            }
                        }
                    }
                    // Level 3
                    if (this.y_axis >= 1 && this.y_axis <= 7) { // x >> [2-5] ----- y >> [1-7]
                        // Left
                        if (this.x_axis >= 2) {
                            Cell cell_lvl_3_left = board[this.x_axis - 2][this.y_axis - 1];
                            if (!cell_lvl_3_left.isOccupied
                                    || (cell_lvl_3_left.isOccupied && cell_lvl_3_left.takenBy.color != this.color)) {
                                cellsAllowed.add(cell_lvl_3_left);
                            }
                        }
                        // Right
                        if (this.x_axis <= 5) {
                            Cell cell_lvl_3_right = board[this.x_axis + 2][this.y_axis - 1];
                            if (!cell_lvl_3_right.isOccupied
                                    || (cell_lvl_3_right.isOccupied && cell_lvl_3_right.takenBy.color != this.color)) {
                                cellsAllowed.add(cell_lvl_3_right);
                            }
                        }
                    }
                    // Level 2
                    if (this.y_axis >= 0 && this.y_axis <= 6) { // x >> [2-5] ----- y >> [0-6]
                        // Left
                        if (this.x_axis >= 2) {
                            Cell cell_lvl_2_left = board[this.x_axis - 2][this.y_axis + 1];
                            if (!cell_lvl_2_left.isOccupied
                                    || (cell_lvl_2_left.isOccupied && cell_lvl_2_left.takenBy.color != this.color)) {
                                cellsAllowed.add(cell_lvl_2_left);
                            }
                        }
                        // Right
                        if (this.x_axis <= 5) {
                            Cell cell_lvl_2_right = board[this.x_axis + 2][this.y_axis + 1];
                            if (!cell_lvl_2_right.isOccupied
                                    || (cell_lvl_2_right.isOccupied && cell_lvl_2_right.takenBy.color != this.color)) {
                                cellsAllowed.add(cell_lvl_2_right);
                            }
                        }
                    }
                    // Level 1
                    if (this.y_axis >= 0 && this.y_axis <= 5) { // x >> [1-6] ----- y >> [0-5]
                        // Left
                        if (this.x_axis >= 1) {
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
                break;
            case "Bishop":
                int b = 1;
                while (this.x_axis + b <= 7 && this.y_axis + b <= 7) {
                    Cell cell_up_left = board[this.x_axis + b][this.y_axis + b];
                    if (!cell_up_left.isOccupied) {
                        cellsAllowed.add(cell_up_left);
                    } else if (cell_up_left.isOccupied && cell_up_left.takenBy.color != this.color) {
                        cellsAllowed.add(cell_up_left);
                        break;
                    } else
                        break;
                    b++;
                }
                b = 1;
                while (this.x_axis - b >= 0 && this.y_axis - b >= 0) {
                    Cell cell_down_right = board[this.x_axis - b][this.y_axis - b];
                    if (!cell_down_right.isOccupied) {
                        cellsAllowed.add(cell_down_right);
                    } else if (cell_down_right.isOccupied && cell_down_right.takenBy.color != this.color) {
                        cellsAllowed.add(cell_down_right);
                        break;
                    } else
                        break;
                    b++;
                }
                b = 1;
                while (this.x_axis - b >= 0 && this.y_axis + b <= 7) {
                    Cell cell_up_right = board[this.x_axis - b][this.y_axis + b];
                    if (!cell_up_right.isOccupied) {
                        cellsAllowed.add(cell_up_right);
                    } else if (cell_up_right.isOccupied && cell_up_right.takenBy.color != this.color) {
                        cellsAllowed.add(cell_up_right);
                        break;
                    } else
                        break;
                    b++;
                }
                b = 1;
                while (this.x_axis + b <= 7 && this.y_axis - b >= 0) {
                    Cell cell_down_left = board[this.x_axis + b][this.y_axis - b];
                    if (!cell_down_left.isOccupied) {
                        cellsAllowed.add(cell_down_left);
                    } else if (cell_down_left.isOccupied && cell_down_left.takenBy.color != this.color) {
                        cellsAllowed.add(cell_down_left);
                        break;
                    } else
                        break;
                    b++;
                }

                break;
            case "Rook":
                //
                int r = 1;
                while (this.y_axis + r < 8) {
                    Cell cell_column = board[this.x_axis][this.y_axis + r];
                    if (!cell_column.isOccupied) {
                        cellsAllowed.add(cell_column);
                    } else if (cell_column.isOccupied && cell_column.takenBy.color != this.color) {
                        cellsAllowed.add(cell_column);
                        break;
                    }
                    // If a block found.. Cells allowed to move-at stops there
                    else
                        break;
                    r++;
                }
                r = 1;
                while (this.y_axis - r >= 0) {
                    Cell cell_column = board[this.x_axis][this.y_axis - r];
                    if (!cell_column.isOccupied) {
                        cellsAllowed.add(cell_column);
                    } else if (cell_column.isOccupied && cell_column.takenBy.color != this.color) {
                        cellsAllowed.add(cell_column);
                        break;
                    }
                    // If a block found.. Cells allowed to move-at stops there
                    else
                        break;
                    r++;
                }
                r = 1;
                while (this.x_axis + r < 8) {
                    Cell cell_row = board[this.x_axis + r][this.y_axis];
                    if (!cell_row.isOccupied) {
                        cellsAllowed.add(cell_row);
                    } else if (cell_row.isOccupied && cell_row.takenBy.color != this.color) {
                        cellsAllowed.add(cell_row);
                        break;
                    }
                    // If a block found.. Cells allowed to move-at stops there
                    else
                        break;
                    r++;
                }
                r = 1;
                while (this.x_axis - r >= 0) {
                    Cell cell_row = board[this.x_axis - r][this.y_axis];
                    if (!cell_row.isOccupied) {
                        cellsAllowed.add(cell_row);
                    } else if (cell_row.isOccupied && cell_row.takenBy.color != this.color) {
                        cellsAllowed.add(cell_row);
                        break;
                    }
                    // If a block found.. Cells allowed to move-at stops there
                    else
                        break;
                    r++;
                }
                break;
            case "Queen":
                //
                int q = 1;
                while (this.x_axis + q <= 7 && this.y_axis + q <= 7) {
                    Cell cell_up_left = board[this.x_axis + q][this.y_axis + q];
                    if (!cell_up_left.isOccupied) {
                        cellsAllowed.add(cell_up_left);
                    } else if (cell_up_left.isOccupied && cell_up_left.takenBy.color != this.color) {
                        cellsAllowed.add(cell_up_left);
                        break;
                    } else
                        break;
                    q++;
                }
                q = 1;
                while (this.x_axis - q >= 0 && this.y_axis - q >= 0) {
                    Cell cell_down_right = board[this.x_axis - q][this.y_axis - q];
                    if (!cell_down_right.isOccupied) {
                        cellsAllowed.add(cell_down_right);
                    } else if (cell_down_right.isOccupied && cell_down_right.takenBy.color != this.color) {
                        cellsAllowed.add(cell_down_right);
                        break;
                    } else
                        break;
                    q++;
                }
                q = 1;
                while (this.x_axis - q >= 0 && this.y_axis + q <= 7) {
                    Cell cell_up_right = board[this.x_axis - q][this.y_axis + q];
                    if (!cell_up_right.isOccupied) {
                        cellsAllowed.add(cell_up_right);
                    } else if (cell_up_right.isOccupied && cell_up_right.takenBy.color != this.color) {
                        cellsAllowed.add(cell_up_right);
                        break;
                    } else
                        break;
                    q++;
                }
                q = 1;
                while (this.x_axis + q <= 7 && this.y_axis - q >= 0) {
                    Cell cell_down_left = board[this.x_axis + q][this.y_axis - q];
                    if (!cell_down_left.isOccupied) {
                        cellsAllowed.add(cell_down_left);
                    } else if (cell_down_left.isOccupied && cell_down_left.takenBy.color != this.color) {
                        cellsAllowed.add(cell_down_left);
                        break;
                    } else
                        break;
                    q++;
                }
                //////////////////
                q = 1;
                while (this.y_axis + q < 8) {
                    Cell cell_column = board[this.x_axis][this.y_axis + q];
                    if (!cell_column.isOccupied) {
                        cellsAllowed.add(cell_column);
                    } else if (cell_column.isOccupied && cell_column.takenBy.color != this.color) {
                        cellsAllowed.add(cell_column);
                        break;
                    }
                    // If a block found.. Cells allowed to move-at stops there
                    else
                        break;
                    q++;
                }
                q = 1;
                while (this.y_axis - q >= 0) {
                    Cell cell_column = board[this.x_axis][this.y_axis - q];
                    if (!cell_column.isOccupied) {
                        cellsAllowed.add(cell_column);
                    } else if (cell_column.isOccupied && cell_column.takenBy.color != this.color) {
                        cellsAllowed.add(cell_column);
                        break;
                    }
                    // If a block found.. Cells allowed to move-at stops there
                    else
                        break;
                    q++;
                }
                q = 1;
                while (this.x_axis + q < 8) {
                    Cell cell_row = board[this.x_axis + q][this.y_axis];
                    if (!cell_row.isOccupied) {
                        cellsAllowed.add(cell_row);
                    } else if (cell_row.isOccupied && cell_row.takenBy.color != this.color) {
                        cellsAllowed.add(cell_row);
                        break;
                    }
                    // If a block found.. Cells allowed to move-at stops there
                    else
                        break;
                    q++;
                }
                q = 1;
                while (this.x_axis - q >= 0) {
                    Cell cell_row = board[this.x_axis - q][this.y_axis];
                    if (!cell_row.isOccupied) {
                        cellsAllowed.add(cell_row);
                    } else if (cell_row.isOccupied && cell_row.takenBy.color != this.color) {
                        cellsAllowed.add(cell_row);
                        break;
                    }
                    // If a block found.. Cells allowed to move-at stops there
                    else
                        break;
                    q++;
                }
                break;
            case "King":

                if (this.x_axis <= 6) {

                    if (!(board[this.x_axis + 1][this.y_axis].isOccupied)
                            || board[this.x_axis + 1][this.y_axis].takenBy.color != this.color) {
                        cellsAllowed.add(board[this.x_axis + 1][this.y_axis]); // middle right
                    }

                }
                if (this.x_axis >= 1) {
                    if (!(board[this.x_axis - 1][this.y_axis].isOccupied)
                            || board[this.x_axis - 1][this.y_axis].takenBy.color != this.color) {
                        cellsAllowed.add(board[this.x_axis - 1][this.y_axis]); // middle left
                    }
                }

                if ((this.y_axis <= 6)) {

                    if (!(board[this.x_axis][this.y_axis + 1].isOccupied)
                            || board[this.x_axis][this.y_axis + 1].takenBy.color != this.color) {
                        cellsAllowed.add(board[this.x_axis][this.y_axis + 1]); // top
                    }
                }
                if (this.y_axis >= 1) {
                    if (!(board[this.x_axis][this.y_axis - 1].isOccupied)
                            || board[this.x_axis][this.y_axis - 1].takenBy.color != this.color) {
                        cellsAllowed.add(board[this.x_axis][this.y_axis - 1]); // down
                    }
                }
                ////

                if ((this.x_axis >= 1 && this.y_axis <= 6)) {

                    if (!(board[this.x_axis - 1][this.y_axis + 1].isOccupied)
                            || board[this.x_axis - 1][this.y_axis + 1].takenBy.color != this.color) {
                        cellsAllowed.add(board[this.x_axis - 1][this.y_axis + 1]); // top left
                    }
                }
                if ((this.x_axis >= 1 && this.y_axis >= 1)) {

                    if (!(board[this.x_axis - 1][this.y_axis - 1].isOccupied)
                            || board[this.x_axis - 1][this.y_axis - 1].takenBy.color != this.color) {
                        cellsAllowed.add(board[this.x_axis - 1][this.y_axis - 1]);// down left
                    }
                }
                if ((this.x_axis <= 6 && this.y_axis <= 6)) {

                    if (!(board[this.x_axis + 1][this.y_axis + 1].isOccupied)
                            || board[this.x_axis + 1][this.y_axis + 1].takenBy.color != this.color) {
                        cellsAllowed.add(board[this.x_axis + 1][this.y_axis + 1]); // top right
                    }
                }
                if ((this.x_axis <= 6 && this.y_axis >= 1)) {

                    if (!(board[this.x_axis + 1][this.y_axis - 1].isOccupied)
                            || board[this.x_axis + 1][this.y_axis - 1].takenBy.color != this.color) {
                        cellsAllowed.add(board[this.x_axis + 1][this.y_axis - 1]);// down right
                    }
                }

        }
        return cellsAllowed;

    }

    void setPosition(int x_axisAmount, int y_axisAmount) {
        this.x_axis = x_axisAmount;
        this.y_axis = y_axisAmount;
    }

    // void take(int x_axisAmount, int y_axisAmount, Piece toBeTaken) {
    // this.x_axis += x_axisAmount;
    // this.y_axis += y_axisAmount;
    // toBeTaken.isEaten = true;
    // }
}
