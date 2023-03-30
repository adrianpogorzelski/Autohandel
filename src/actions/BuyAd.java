package actions;

import customer.Customer;
import game.Menu;

import static actions.EndTurn.endTurn;
import static game.Data.currentPlayer;

public abstract class BuyAd {
    public static void newspaperAd() {
        if (currentPlayer.money < 1000) {
            System.out.println("Brak środków na reklamę w gazecie");
            Menu.buyAd();
        }
        currentPlayer.money -= 1000;
        int newCustomers = (int) (Math.random() * 5);
        Customer.generateCustomer(newCustomers);
        System.out.println("Reklama przyciągnęła " + newCustomers + " nowych klientów");
        currentPlayer.transactionHistory.add("Wydano 1000zł na reklamę w lokalnej gazecie");
        endTurn();
    }

    public static void internetAd() {
        if (currentPlayer.money < 200) {
            System.out.println("Brak środków na reklamę w internecie");
            Menu.buyAd();
            endTurn();
        }
        currentPlayer.money -= 200;
        Customer.generateCustomer(1);
        System.out.println("Reklama przyciągnęła nowego klienta");
        currentPlayer.transactionHistory.add("Wydano 200 na reklamę w Internecie");
        endTurn();
    }

}
