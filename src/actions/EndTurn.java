package actions;

import game.Data;
import game.Menu;

import static game.Data.currentPlayer;

public abstract class EndTurn {
    /** END TURN AND CHANGE CURRENT PLAYER **/
    static void endTurn() {
        int playerIndex = Data.players.indexOf(currentPlayer);
        if (Data.players.size() >= 2) {
            // if last player, start again, and make this a new round
            if (playerIndex == Data.players.size() - 1) {
                currentPlayer = Data.players.get(0);
            } else {
                currentPlayer = Data.players.get(playerIndex + 1);
            }
        }
        Data.round++;
        Menu.main();
    }
}
