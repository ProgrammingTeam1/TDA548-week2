package assignments.product;

import java.util.Random;
import java.util.Scanner;

import static java.lang.System.*;
import static java.lang.Math.*;

/*
 * Simplified text based (i.e. non graphical) version of the Dice Wars game
 * For a full graphical version, see http://www.gamedesign.jp/flash/dice/dice.html (or other)
 *
 * Some rule simplification
 * - No limit of dices in a country
 * - Distribution of earned dices may be "somewhat" random.
 * - Game over when a player lost all countries
 * - Player with most countries wins (or dices? or a combination? You find a measure)
 *
 *  Plan and Process: Compare Tic Tac Toe from exercises
 */
public class DiceWars {

    public static void main(String[] args) {
        new DiceWars().program();
    }

    void program() {
        Random random = new Random();
        Scanner scan = new Scanner(in);
        String input;
        boolean inGame = true;

        // The players of the game (mostly referenced by index)
        String[] players = {"pil", "katten", "jazz"};
        // A map with nine countries, named by their index (0-8)
        // Leading 1:s because can't have leading 0 (just skip the ones when processing)
        // 114 says: The country 0 has border to countries 1 and 4.
        // Number of countries is a multiple of players, they all get the same numbers of countries
        int[] map = {114, 1024, 115, 146, 110357, 12487, 137, 14568, 157};

        // This is the owners of the countries. Country 0 is owned by player pil (players[0])
        int[] owners = {0, 1, 2, 1, 2, 0, 0, 1, 2};
        // This is the number of dices for a country. Country 1 has 3 dices.
        int[] dices = {2, 3, 2, 2, 1, 3, 1, 1, 3};

        out.println("Welcome to Dice Wars \"lite!\"");
        out.println();

        int attacker = random.nextInt(players.length);
        plotMap(map, owners, dices, players);

        // ****** Write out code here  ******
        // You should use functional decomposition i.e. find a top level
        // method. Then, which methods should be used by the top level method?
        // Then, which methods should be used by methods used by top level etc.
        // Draw a sketch on paper!


        while (inGame){
            //Skriv ut vilken spelare som kör
            out.println("It is "+players[attacker]+"s turn");
            //Välja a eller x för attack respektive gå vidare
            while (true){

                // ---------------------- Get Inputs

                do {
                   out.println("Choose a for attack or x to continue");
                    input = scan.next();
                } while (!(input.equals("a") || input.equals("x")));

                if (input.equals("x")){
                    break;
                }

                int from, to;
                boolean proceed = true;

                while (true) {
                   out.println("From where do you want to attack? To go back, input -1.");
                   from = scan.nextInt();
                    if (from == -1){
                        proceed = false;
                        break;
                    }
                   if (from >= 0 && from <= 8 && owners[from] == attacker && dices[from] > 1){
                       break;
                   }
                }

                if (!proceed){
                    break;
                }

                while (true){
                    out.println("To where do you want to attack?");
                    to = scan.nextInt();
                    if (to >= 0 && to <= 8 && owners[to] != attacker && hasBorder(from, to, map)){ // Det går att fastna i en loop här
                        break;
                    }
                }

                // --------------------- Do the attack


                int attackerSum = sumDices(dices[from], random);
                int defenderSum = sumDices(dices[to], random);
                out.println(players[attacker] +" got " + attackerSum + " and "+ players[owners[to]]+" got "+defenderSum);



                if (attackerSum > defenderSum){
                    owners[to] = attacker;
                    dices[to] = dices[from] - 1;
                }
                dices[from] = 1;
                plotMap(map, owners, dices, players);
                if (endGame(owners)){
                    inGame = false;
                    break;
                }

            }
            putOutDices(dices, owners, attacker, random);
            plotMap(map, owners, dices, players);

            attacker++;
            if (attacker == 3){
                attacker = 0;
            }
        }

        out.println("Game over");
    }


    // ---- Write your methods below this -----------------------------------

    // Start with this one! Is will say "true" f two countries have a border
    boolean hasBorder(int i, int j, int[] map){
        int[] dissected = new int[0];
        for (int p = 8; p > 0; p--){ // 8 is the maximum amount of possible border relations per tile
            if (Math.pow(10,p) < map[i]){
                dissected = new int[p];
                break;
            }
        }

        int value = map[i];
        value -= Math.pow(10, dissected.length);

        for (int k = dissected.length-1; k >= 0; k--){
            dissected[k] = (int)(value / Math.pow(10, k));
            value -= dissected[k]*Math.pow(10, k);
        }

        for (int a = 0; a < dissected.length; a++){
            if (dissected[a] == j){
                return true;
            }
        }
        return false;   // TODO
    }
    void putOutDices(int[] dices, int[] owners, int player, Random random){
        int howManyDices = playerLand(player, owners);

        for (int i = 0; i < howManyDices; i++){
            while (true){
                int r = random.nextInt(9);
                if (owners[r] == player){
                    dices[r]++;
                    break;
                }
            }
        }
    }
    int sumDices(int n, Random random){
        int sum = 0;
        for (int i = 0; i < n; i++){
          sum += random.nextInt(6)+1;
        }
        return sum;
    }
    int playerLand(int player, int[] owners){
        int sum = 0;
        for (int i = 0; i < owners.length; i++){
            if (owners[i] == player){
                sum++;
            }
        }
        return sum;
    }
    boolean endGame(int[] owners){
        for (int i = 0; i < 3; i++){
            if (playerLand(i, owners) == 0) {
                return true;
            }
        }
        return false;
    }

    // ----  Nothing to do for you below this line  -----

    // Plot map (as a graph) using ASCII chars
    void plotMap(int[] map, int[] owners, int[] dices, String[] players) {
        int n = (int) sqrt(map.length);
        for (int row = 0; row < 2 * n - 1; row += 2) {
            // One row with horizontal connections
            for (int col = 0; col < n; col++) {
                int i = 3 * row / 2 + col;
                out.print(players[owners[i]] + ":" + dices[i]);
                if (hasBorder(i, i + 1, map)) {
                    out.print("--");
                } else {
                    out.print("  ");
                }
            }
            out.println();
            // Another row with vertical connections
            for (int col = 0; col < n; col++) {
                int i = 3 * row / 2 + col;
                if (hasBorder(i, i + n, map)
                        && hasBorder(i, i + n - 1, map)
                        && hasBorder(i, i + n + 1, map)) {
                    out.print("  / | \\ ");
                } else if (hasBorder(i, i + n, map)
                        && hasBorder(i, i + n - 1, map)) {
                    out.print("/  |     ");
                } else if (hasBorder(i, i + n, map)
                        && hasBorder(i, i + n + 1, map)) {
                    out.print("   | \\  ");
                } else if (hasBorder(i, i + n - 1, map)) {
                    out.print(" /      ");
                } else if (hasBorder(i, i + n + 1, map)) {
                    out.print("     \\  ");
                } else if (hasBorder(i, i + n, map)) {
                    out.print("   |    ");
                } else {
                    out.print("         ");
                }
            }
            out.println();
        }
        out.println("-----------------------------------------");
    }
}
