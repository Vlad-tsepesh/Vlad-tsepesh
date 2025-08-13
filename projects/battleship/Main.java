package projects.battleship;

import projects.battleship.field.BattleField;
import projects.battleship.field.Coordinates;
import projects.battleship.ships.Destroyer;
import projects.battleship.ships.Ship;

import java.util.Arrays;
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
        Ship[] playerOneShips = deployment(scanner, playerOneBF,playerOneXY );

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
//                new projects.battleship.AircraftCarrier(),
//                new projects.battleship.Battleship(),
//                new projects.battleship.Submarine(),
//                new projects.battleship.Cruiser(),
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