package projects.battleship.field;

import projects.battleship.ships.Ship;

import java.util.Arrays;
import java.util.Scanner;

public class Coordinates {
    public String[] coordinates;
    String[][]coordinates2D;
    StringBuilder busyArea;

    public Coordinates() {
        coordinates = new String[100];
        coordinates2D = new String[10][10];
        busyArea = new StringBuilder();
        fillCoordinates();
        fillCoordinates2D();
    }

    private void fillCoordinates() {
        int i = 0;
        for (char c = 'A'; c <= 'J'; c++) {
            for (int j = 1; j <= 10; j++, i++) {
                this.coordinates[i] = "" + c + j;

            }
        }
    }

    private void fillCoordinates2D() {
        int index = 0;
        for (char c = 'A'; c <= 'J'; c++, index++) {
            for (int j = 1, i = 0; j <= 10; j++, i++) {
                this.coordinates2D[index][i] = "" + c + j;

            }
        }
    }

    void showCoordinates() {
        for (int i = 0, ih = 0; i < 10; i++) {
            for (int h = 0; h < 10; h++, ih++) {
                System.out.print(this.coordinates2D[i][h]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void setUP(Scanner scanner, Ship ship) {
        ship.showInfo();
        while(true){

            outer: {
                System.out.print("> ");
                String input = scanner.nextLine();
                int x, y;

                try {
                    x = Arrays.asList(coordinates).indexOf(input.split(" ")[0]);
                    y = Arrays.asList(coordinates).indexOf(input.split(" ")[1]);

                } catch (Exception e) {
                    System.out.println("Error!");
                    continue;
                }

                int innerIncrement = (x / 10 == y / 10 && x % 10 != y % 10) ? ((x % 10 > y % 10) ? -1 : 1)
                        : (x / 10 != y / 10 && x % 10 == y % 10) ? ((x / 10 > y / 10) ? -10 : 10)
                        : 1000;

                int outerIncrement = (x / 10 == y / 10 && x % 10 != y % 10) ? ((x / 10 > y / 10) ? -10 : 10)
                        : (x / 10 != y / 10 && x % 10 == y % 10) ? ((x % 10 > y % 10) ? -1 : 1)
                        : 1000;

                if (innerIncrement == 1000) { // in this case there's are wrong coordinates
                    System.out.println();
                    System.out.println("Error! Wrong ship location! Try again:");
                    System.out.println();
                    continue;
                }
                int inputLength =
                        (Math.abs(innerIncrement) == 1) ? Math.abs(x % 10 - y % 10) + 1 : Math.abs(x / 10 - y / 10) + 1;

                if (ship.getLength() != inputLength) {// if coordinates are longer then the actual ship
                    System.out.println();
                    System.out.printf("Error! Wrong length of the %s! Try again:\n", ship.getName());
                    System.out.println();
                    continue;
                }

                // building coordinates for actual ship
                String[] shipPosition = new String[ship.getLength()];
                for (int i = 0; i < ship.getLength(); i++) {
                    if (busyArea.indexOf(coordinates[x]) != -1) {
                        System.out.println();
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        System.out.println();
                        break outer;
                    }
                    shipPosition[i] = coordinates[x];
                    x += innerIncrement;
                }
                ship.setCoordinates(shipPosition);
                Arrays.sort(ship.getCoordinates());


                // building busy coordinates on table
                for (String value : shipPosition) {
                    for (int i = 0; i < 10; i++) {
                        for (int h = 0; h < 10; h++) {
                            if (coordinates2D[i][h].equals(value)) {
                                --i;
                                --h;
                                for (int j = 0; j < 3; j++) {
                                    for (int l = 0; l < 3; l++) {
                                        if (i + j < 0 || i + j >= 10 || h + l < 0 || h + l >= 10 || busyArea.indexOf(coordinates2D[i + j][h + l]) != -1)
                                            continue;
                                        busyArea.append(coordinates2D[i + j][h + l]).append(" ");
                                    }
                                }
                                ++i;
                                ++h;
                            }
                        }
                    }
                }

                String[] busyAreaArray = busyArea.toString().split(" ");
                Arrays.sort(busyAreaArray);

                break;
            }
        }
    }
}