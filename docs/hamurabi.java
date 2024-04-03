import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

// this seems to be an example of a solution that mimics the original BASIC code the author was writing from.
//
// it's a great example of Very Bad Java.
// Do not write Java like this. If you do, do NOT tell people you went to Zip Code.
// I'm serious.
// (how the hell would you ever be able to TEST this piece of code?)
//
public class HAMURABI {
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);

    private int population = 100;
    private int grain = 2800;
    private int land = 1000;
    private int landPrice = 0;
    private int year = 1;

    public static void main(String[] args) {
        Hammurabi game = new Hammurabi();
        game.playGame();
    }

    private void playGame() {
        printWelcomeMessage();
        while (year <= 10) {
            performYearlyActivities();
            if (isGameOver()) break;
            year++;
        }
        printGameOverMessage();
    }

    private void performYearlyActivities() {
        updateLandPrice();
        tradeLand();
        feedPopulation();
        plantCrops();
        printAnnualReport();
    }

    private boolean isGameOver() {
        if (population <= 0) {
            System.out.println("All your people have died.");
            return true;
        }
        return false;
    }

    private void updateLandPrice() {
        landPrice = random.nextInt(7) + 17; // Land price between 17 and 23
        System.out.println("Current land price: " + landPrice + " bushels per acre.");
    }

    private void tradeLand() {
        System.out.println("Acres of land to buy (-value to sell): ");
        int acresToTrade = scanner.nextInt();
        if (acresToTrade >= 0) {
            buyLand(acresToTrade);
        } else {
            sellLand(Math.abs(acresToTrade));
        }
    }

    private void buyLand(int acres) {
        int cost = acres * landPrice;
        if (cost <= grain) {
            land += acres;
            grain -= cost;
        } else {
            System.out.println("Not enough grain.");
        }
    }

    private void sellLand(int acres) {
        if (acres <= land) {
            land -= acres;
            grain += acres * landPrice;
        } else {
            System.out.println("You don't own enough land to sell.");
        }
    }

    private void feedPopulation() {
        System.out.println("Bushels of grain to feed your people: ");
        int grainToFeed = scanner.nextInt();
        if (grainToFeed <= grain) {
            grain -= grainToFeed;
        } else {
            System.out.println("Not enough grain.");
        }
    }

    private void plantCrops() {
        System.out.println("Acres of land to plant with seed: ");
        int acresToPlant = scanner.nextInt();
        if (acresToPlant <= land && acresToPlant * 2 <= grain) {
            grain -= acresToPlant * 2;
        } else {
            System.out.println("Not enough land or grain.");
        }
    }

    private void printWelcomeMessage() {
        System.out.println("Welcome to Hammurabi!\nYou are the ruler of a small kingdom. Manage your resources wisely.");
    }

    private void printAnnualReport() {
        System.out.printf("Year %d complete.\nPopulation: %d\nGrain: %d\nLand: %d acres\n", year, population, grain, land);
    }

    private void printGameOverMessage() {
        System.out.println("Game Over. You've ruled for " + year + " years.");
    }
}