import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static boolean game = true;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BattleField playerOneBF = new BattleField("Player 1");
        Coordinates playerOneXY = new Coordinates();

        BattleField playerTwoBF = new BattleField("Player 2");
        Coordinates playerTwoXY = new Coordinates();
        //deployment

        System.out.printf("\n%s, place your ships on the game field\n\n", playerOneBF.name);
        Ship [] playerOneShips = deployment(scanner, playerOneBF,playerOneXY );

        System.out.print("\nPress Enter and pass the move to another player\n...");
        scanner.nextLine();


        System.out.printf("\n%s, place your ships on the game field\n\n", playerTwoBF.name);
        Ship [] playerTwoShips = deployment(scanner, playerTwoBF,playerTwoXY );

        System.out.print("\nPress Enter and pass the move to another player\n...");
        scanner.nextLine();

        System.out.println();
        System.out.println("The game starts!\n");

        while(game) {
            battle(scanner, playerOneBF, playerTwoBF, playerTwoXY, playerTwoShips);
            if (game){
            battle(scanner, playerTwoBF, playerOneBF, playerOneXY, playerOneShips);
            }
        }

    }

    static void battle(
            Scanner scanner,
            BattleField playerBF,BattleField enemyBF,
            Coordinates enemyXY, Ship [] enemyShips
    ){
        playerBF.showField();
        System.out.println("---------------------");
        enemyBF.fogOfWar();
        System.out.printf("\n%s, it's your turn:\n> ", playerBF.name);
        String shot = scanner.nextLine();

        if (!Arrays.asList(enemyXY.coordinates).contains(shot)) {
            System.out.println("Error! You entered wrong coordinates! Try again:\n");
            return;
        }

        if (!enemyBF.battleField.get(shot).equals(" O")) {
            System.out.println("You missed!\n");
            enemyBF.battleField.put(shot, " M");
            System.out.println();
        } else if (enemyBF.battleField.get(shot).equals(" O")) {
            enemyBF.battleField.put(shot, " X");
            System.out.println();
            for (Ship target : enemyShips) {
                if (Arrays.asList(target.shipCoordinates).contains(shot)) {
                    target.lives--;
                    if (target.lives == 0) {

                                switch (enemyBF.name){
                                    case "Player 1":
                                        Ship.playerOneShipsAlvie--;
                                        break;
                                    case "Player 2":
                                        Ship.playerTwoShipsAlvie--;
                                         break;
                                }
                        if (Ship.playerOneShipsAlvie == 0 || Ship.playerTwoShipsAlvie == 0) {
                            System.out.println("You sank the last ship. You won. Congratulations!");
                            game = false;
                            return;
                        } else {
                            System.out.println("You sank a ship!");
                        }
                    } else System.out.println("You hit a ship!");
                }
            }
        }
        System.out.print("Press Enter and pass the move to another player\n...");
        scanner.nextLine();
    }

    static Ship[] deployment(Scanner scanner, BattleField bf, Coordinates xy) {

        bf.showField();
        Ship [] player = new Ship[]{
//                new AircraftCarrier(),
//                new Battleship(),
//                new Submarine(),
//                new Cruiser(),
                new Destroyer()
        };

        for (Ship ship : player) {
            System.out.println();
            xy.setUP(scanner, ship);
            bf.setShipOnField(ship.getCoordinates());
            System.out.println();
            bf.showField();
        }
        return player;
    }

}



class BattleField {
    String name;
    Map<String, String> battleField = new LinkedHashMap<>();

    BattleField(String name) {
        this.name = name;
        for (char c = '@'; c <= 'J'; c++) {
            for (int i = 0; i <= 11; i++) {
                String key = "" + c + i;
                String value = (i == 0 && c != '@') ? "" + c : (i == 11) ? "\n" : c != '@' ? " ~" : (i == 0) ? " " : " " + i;
                battleField.put(key, value);
            }
        }
    }

    void showField() {
        for (String value : battleField.values()) {
            System.out.print(value);
        }
    }

    void fogOfWar() {
        for (String value : battleField.values()) {
            if (value.equals(" O"))
                System.out.print(" ~");
            else
                System.out.print(value);
        }
    }

    void setShipOnField(String[] keys) {
        for (String key : keys) {
            battleField.put(key, " O");
        }
    }
}

class Ship{
    static int playerOneShipsAlvie = 1;
    static int playerTwoShipsAlvie = 1;
    int lives;
    String[]shipCoordinates;
    String[]aroundShip;
    int length;
    String name;

    Ship(int length, String name, int lives) {
        this.length = length;
        this.name = name;
        this.lives = lives;
    }
    int getLength() {
        return length;
    }

    String getName() {
        return name;
    }

    void setCoordinates(String[]coordinates) {
        this.shipCoordinates = coordinates;
    }

    String [] getCoordinates() {
        return this.shipCoordinates;
    }

    void setCoordinatesAround(String[]coordinates) {
        this.aroundShip = coordinates;
    }

    String [] getCoordinatesAround() {
        return this.aroundShip;
    }

    void showInfo () {
        System.out.printf("Enter the coordinates of the %s (%d cells): ", getName(), getLength());
        System.out.println();
        System.out.println();
    }
}

class AircraftCarrier extends Ship {
    AircraftCarrier() {
        super(5, "AircraftCarrier",5);
    }
}

class Battleship extends Ship {
    Battleship() {
        super(4, "Battleship",4);
    }
}

class Submarine extends Ship {
    Submarine() {
        super(3, "Submarine", 3);
    }
}

class Cruiser extends Ship {
    Cruiser() {
        super(3, "Cruiser",3);
    }
}

class Destroyer extends Ship {
    Destroyer() {
        super(2, "Destroyer", 2);
    }
}

class Coordinates {
    String[] coordinates;
    String[][]coordinates2D;
    StringBuilder busyArea;

    Coordinates() {
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

    void setUP(Scanner scanner,Ship ship) {
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


