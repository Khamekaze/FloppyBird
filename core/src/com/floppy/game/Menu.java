package com.floppy.game;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
public class Menu {
    private static int menuSelection;

    public static void main(String[] args) {
        System.out.println("Hello and welcome to Flappy bird!");
        System.out.println("1. Play");
        System.out.println("2. High Score");
        System.out.println("3. Help");
        Scanner sc = new Scanner(System.in);
        menuSelection = sc.nextInt();
        if(menuSelection == 1){

        } else if(menuSelection == 2){
            highScore();
        } else if(menuSelection == 3){
            System.out.println("Welcome to Flappy Bird KYH edition!");
            System.out.println("To play simply press 'start' in the menu selection. ");
            System.out.println("Press 'space' to jump.");
            System.out.println("Make sure to avid all obstacles.");
            System.out.println("Have fun!");
        }
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

