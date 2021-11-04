package com.company;

import java.io.IOException;
import java.util.Scanner;

/**
 * at+cfg=433000000,5,6,12,4,1,0,0,0,0,3000,8,8
 * at+send=8
 * jo jo jo
 */
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        while (true) {
            System.out.println("enter your command: ");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            command = command.toUpperCase();

            CommandHandler commandHandler = new CommandHandler();
            commandHandler.sendRestSignal(command);
        }
    }

}
