import player.Player;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static int numPlayers;
    final public String[] brands = {"Alfa Romeo", "BMW", "Citroen", "Dacia", "Ford", "Honda", "Infiniti", "Jaguar", "Kia", "Lexus", "Mazda", "Nissan", "Opel", "Porsche", "Renault", "Skoda", "Toyota", "Volvo"};
    // Number of players
    public static void getNumPlayers() {
        Scanner playerScanner = new Scanner(System.in);
        System.out.println("Podaj liczbę graczy (conajmniej 1): ");
        try {
            numPlayers = Math.abs(playerScanner.nextByte()); // negative values are converted to positives
        } catch (Exception error) {
            getNumPlayers();
        }
    }

    public static void printMenu() {
        System.out.println("1. Przejrzyj pojazdy do kupienia");
        System.out.println("2. Przejrzyj posiadane pojazdy");
        System.out.println("3. Klienci");
        System.out.println("4. Wykup reklamę");
        System.out.println("5. Stan konta");
        System.out.println("6. Historia transakcji");
    }

    public static void main(String[] args) {
        Integer initialMoney;
        ArrayList<Player> players = new ArrayList<Player>();
        Object[] availableVehicles;
        Integer numMoves = 1;
        LinkedList<Object> transactionHistory;
        Player currentPlayer;

        System.out.println("*** AAA AUTOHANDEL AAA ***");
        // Get players' number
        getNumPlayers();
        System.out.println("Liczba graczy: " + numPlayers);
        // Create players
        for (int i = 1; i <= numPlayers; i++ ) {
            Scanner newPlayerInput = new Scanner(System.in);
            System.out.println("Podaj imię gracza " + i + ":");
            String newPlayerName = newPlayerInput.nextLine();
            Player newPlayer = new Player(newPlayerName);
            players.add(newPlayer);
            System.out.println("Dodano gracza " + newPlayer.name);
        }
        printMenu();
    }
}