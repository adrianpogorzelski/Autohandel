package actions;

import customer.Customer;
import game.Menu;

import static actions.EndTurn.endTurn;
import static game.Data.currentPlayer;

public abstract class BuyAd implements TransactionSettings{

    public static void newspaperAd() {
        if (currentPlayer.money < NEWSPAPER_AD_COST) {
            System.out.println("Brak środków na reklamę w gazecie");
            Menu.buyAd();
        }
        currentPlayer.money -= NEWSPAPER_AD_COST;
        int newCustomers = (int) (Math.random() * 5);
        Customer.generateCustomer(newCustomers);
        String receipt = "Wydano " + NEWSPAPER_AD_COST + "zł na reklamę w lokalnej gazecie";
        System.out.println(">> " + receipt);
        System.out.println("Reklama przyciągnęła " + newCustomers + " nowych klientów");
        currentPlayer.transactionHistory.add(receipt);
        endTurn();
    }

    public static void internetAd() {
        if (currentPlayer.money < INTERNET_AD_COST) {
            System.out.println("Brak środków na reklamę w internecie");
            Menu.buyAd();
            endTurn();
        }
        currentPlayer.money -= INTERNET_AD_COST;
        Customer.generateCustomer(1);
        String receipt = "Wydano " + INTERNET_AD_COST + "zł na reklamę w Internecie";
        System.out.println(">> " + receipt);
        System.out.println("Reklama przyciągnęła nowego klienta");
        currentPlayer.transactionHistory.add(receipt);
        endTurn();
    }

}
