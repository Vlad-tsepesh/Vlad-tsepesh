package projects.battleship.ships;

public class Ship{
    public static int playerOneShipsAlvie = 1;
    public static int playerTwoShipsAlvie = 1;
    public int lives;
    public String[]shipCoordinates;
    String[]aroundShip;
    int length;
    String name;

    Ship(int length, String name, int lives) {
        this.length = length;
        this.name = name;
        this.lives = lives;
    }
    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public void setCoordinates(String[] coordinates) {
        this.shipCoordinates = coordinates;
    }

    public String [] getCoordinates() {
        return this.shipCoordinates;
    }

    void setCoordinatesAround(String[]coordinates) {
        this.aroundShip = coordinates;
    }

    String [] getCoordinatesAround() {
        return this.aroundShip;
    }

    public void showInfo() {
        System.out.printf("Enter the coordinates of the %s (%d cells): ", getName(), getLength());
        System.out.println();
        System.out.println();
    }
}