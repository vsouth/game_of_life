package main.java.ru.vsouth;
import java.awt.Point;
import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    Grid grid;
    int ticks_amount;

    public void setGrid() {

        System.out.print("Размер поля (ширина, высота): ");
        int rows_amount = this.scanner.nextInt();
        int cols_amount = this.scanner.nextInt();
        this.grid = new Grid(rows_amount, cols_amount);
        System.out.print("Количество точек с жизнью: ");
        int life_amount = this.scanner.nextInt();
        System.out.println();
        System.out.println("Координаты точек с жизнью (х, у): ");
        for (int i=0; i<life_amount; i++) {
            Point cell = new Point(this.scanner.nextInt(), this.scanner.nextInt());
            this.grid.changeCellState(cell);
        }
        this.grid.printGrid();
    }
    public void setTicks() {
        System.out.print("Количество тиков: ");
        this.ticks_amount = this.scanner.nextInt();
    }
    public void start() throws InterruptedException {

        for (int i = 0; i < this.ticks_amount; i++) {
            System.out.println();
            this.grid.makeChanges();
            this.grid.printGrid();
            Thread.sleep(1000);

        }
    }
}
