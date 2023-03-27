import vehicles.Vehicle;
import customer.Customer;
import game.Data;
import game.Menu;
import player.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("*** AAA AUTOHANDEL ***");

        /** GAME SETUP **/
        // Generate available cars
        Vehicle.generateVehicle();

        // Generate customers
        Customer.generateCustomer(5);

        // Generate players
        Data.getNumPlayers();
        System.out.println("Liczba graczy: " + Data.numPlayers);

        //// Create players
        for (int i = 1; i <= Data.numPlayers; i++) {
            Scanner newPlayerInput = new Scanner(System.in);
            System.out.println("Podaj imię gracza " + i + ":");
            String newPlayerName = newPlayerInput.nextLine();
            Player newPlayer = new Player(newPlayerName);
            Data.players.add(newPlayer);
            System.out.println("Dodano gracza " + newPlayer.name);
        }
        Data.currentPlayer = Data.players.get(0);

        /** GAME START **/
        Menu.main();
    }
}
