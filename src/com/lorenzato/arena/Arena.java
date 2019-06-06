package com.lorenzato.arena;

import com.lorenzato.personnage.Mage;
import com.lorenzato.personnage.Personage;
import com.lorenzato.personnage.Prowler;
import com.lorenzato.personnage.Warrior;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Arena {
    // Arena parameters :
    private int nbPlayer;
    private int levelMax;
    private List<Personage> personages = new ArrayList<>();

    public Arena(int nbPlayer, int levelMax) {
        this.nbPlayer = nbPlayer;
        this.levelMax = levelMax;
        this.gameDescription();
    }

    private void gameDescription() {
        System.out.println("\n=====================================");
        System.out.println("    !!Bienvenu dans MagicWorld !!    ");
        System.out.println("-------------------------------------");
        System.out.println("    Ce jeux se joue à " + this.nbPlayer + " joueurs.");
        System.out.println("         Niveau max = " + this.levelMax + ".");
        System.out.println("=====================================\n");
    }

    public void addPlayerInArena() {

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < this.nbPlayer; i++) {

            int level;
            int choice;
            boolean choiceGood;
            boolean levelGood;

            do {
                System.out.println("-------------------------------------");
                System.out.println("Joueur "+ (i+1) + ": Veuillez choisir la classe de votre personnage :\n");
                System.out.println("1 - Guerrier");
                System.out.println("2 - Rôdeur");
                System.out.println("3 - Mage");

                choice = sc.nextInt();
                choiceGood = ((choice >= 1) && (choice <= 3));

                if (!choiceGood) {
                    System.out.println("Vous n'avez pas choisi de class parmi les choix proposés");
                }
            } while(!choiceGood);

            do {
                System.out.println("et choisir son niveau : ");
                level = sc.nextInt();
                levelGood = ((level >= 1) && (choice <= this.levelMax));
                if (!levelGood) {
                    if (level < 0)
                        System.out.println("Veuillez choisir un niveau > 0");
                    else if (level > this.levelMax)
                        level = this.levelMax;
                }

            } while(!levelGood);

            switch (choice){
                case 1 :
                    personages.add(new Warrior(level));
                    break;
                case 2 :
                    personages.add(new Prowler(level));
                    break;
                case 3 :
                    personages.add(new Mage(level));
                    break;
            }
        }
    }
}
