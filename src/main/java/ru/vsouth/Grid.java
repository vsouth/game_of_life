package main.java.ru.vsouth;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.abs;

public class Grid {
    boolean[][] grid;
    int rows_amount;
    int cols_amount;

    public Grid(int rows_amount, int cols_amount) {
        this.grid = new boolean[rows_amount][cols_amount];
        this.rows_amount = rows_amount;
        this.cols_amount = cols_amount;
    }


    public void printGrid() {
        for (int x = 0; x < this.rows_amount; x++) {
            for (int y = 0; y < this.cols_amount; y++) {
                System.out.print(((this.grid[x][y])? "o": ".") + " ");
            }
            System.out.println();
        }
    }

    public void makeChanges() {
        /**
        чтобы можно было хранить только поле и его менять,
        все необходимые изменения состояния ячеек сначала записываются - getCellsToChange,
        а потом все подряд производятся.
         */
        List<Point> cells = getCellsToChange();
        for (Point cell : cells) {
            changeCellState(cell);
        }
    }

    java.util.List<Point> getCellsToChange() {
        java.util.List<Point> cells = new LinkedList<>();
        for (int x = 0; x < this.rows_amount; x++) {
            for (int y = 0; y < this.cols_amount; y++) {
                Point cell = new Point(x,y);
                int neighbours_amount = countCellNeighbours(cell);
                boolean cell_state = this.grid[x][y];
                /**
                проверка, нужно ли менять состояние ячейки.

                состояние нужно менять:
                если в ячейке нет жизни и у нее 3 соседа;
                если в ячейке есть жизнь и у нее слишком мало
                или слишком много соседей.

                в остальных случаях менять состояние не нужно.
                 */
                boolean cell_needs_to_be_changed = (!(cell_state) && neighbours_amount == 3) ||
                        (cell_state && ((neighbours_amount < 2) || (neighbours_amount > 3)));
                if (cell_needs_to_be_changed) {
                    cells.add(cell);
                }
            }
        }
        return cells;
    }

    Integer countCellNeighbours(Point cell) {
        int count = 0;
        /**
         * перебор 9 ячеек, включая проверяемую.
         * если слева нет ячейки на поле, она находится справа - abs(j-this.cols_amount+1)-1
         * аналогично для др сторон
         */

        for (int i = (cell.x-1); i <= (cell.x+1); i++) {
            int x = i < this.rows_amount && i >= 0 ? i: abs(i-this.rows_amount+1)-1;
            for (int j = (cell.y-1); j <= (cell.y+1); j++) {
                int y = j < this.cols_amount && j >= 0 ? j: abs(j-this.cols_amount+1)-1;
                if (i == cell.x && j == cell.y) {
                    continue;
                }
                if (this.grid[x][y]) {
                    count++;

                }
            }
        }
        return count;
    }

    public void changeCellState(Point cell) {
        this.grid[cell.x][cell.y] = !this.grid[cell.x][cell.y];
    }
}
