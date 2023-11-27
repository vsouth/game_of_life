package main.java.ru.vsouth;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        game.setGrid();
        game.setTicks();
        game.start();
    }
}