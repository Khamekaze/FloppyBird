package com.floppy.game;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
public class Menu {
    private static int menuSelection;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
            System.out.println("Welcome to Flappy bird!");
                System.out.println("1. Play");
                System.out.println("2. High Score");
                System.out.println("3. Help");
                menuSelection = sc.nextInt();
             if (menuSelection == 1) {
                //starta spelet
            } else if (menuSelection == 2) {
                highScore();
                System.out.println(" ");
                System.out.println("Press '0' to return to main menu");
                menuSelection = sc.nextInt();
            } else if (menuSelection == 3) {
                System.out.println("Welcome to Flappy Bird KYH edition!");
                System.out.println("To play simply press 'start' in the menu selection. ");
                System.out.println("Press 'space' to jump.");
                System.out.println("Make sure to avid all obstacles.");
                System.out.println("Have fun!");
                System.out.println(" ");
                System.out.println("Press '0' to return to main menu");
                menuSelection = sc.nextInt();
            }
            sc.close();

    }
    public static void highScore() {
        File file = new File("./highscore.txt");
        try{
            Scanner rf = new Scanner(file);
            System.out.println("High Score is: " + rf.nextLine());
            rf.close();
        }catch (Exception e){
            System.out.println("No high Score yet!");
        }
    }
}

