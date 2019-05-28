import Controller.Game;

import java.util.Scanner;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        int interface_op = 0;
        do {
            try {
                System.out.println("Select an interface:");
                System.out.println("1) Text based");
                System.out.println("2) Graphic");
                System.out.print("Enter option: ");
                String option = myObj.nextLine();
                interface_op = Integer.parseInt(option);
                if (interface_op == 1 || interface_op == 2) {
                    Game game = new Game(interface_op);
                    game.run();
                } else
                    System.out.println("\nInvalid option\n");
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid option\n");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (interface_op != 1 && interface_op != 2);
    }
}