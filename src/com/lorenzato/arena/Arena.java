package com.lorenzato.arena;

import com.lorenzato.personnage.Mage;
import com.lorenzato.personnage.Personage;
import com.lorenzato.personnage.Prowler;
import com.lorenzato.personnage.Warrior;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Arena {

    Scanner sc = new Scanner(System.in);

    // Arena parameters :

    private int nbPlayer;
    private int levelMax;
    private List<Personage> personages = new ArrayList<>();
    private int attributesAvailable;

    public Arena(int nbPlayer, int levelMax) {
        this.nbPlayer = nbPlayer;
        this.levelMax = levelMax;
        this.attributesAvailable = 0;
        this.arenaDescription();
    }

    private void arenaDescription() {
        System.out.println("\n=====================================");
        System.out.println("    !!Bienvenu dans MagicWorld !!    ");
        System.out.println("-------------------------------------");
        System.out.println("    Ce jeux se joue à " + this.nbPlayer + " joueurs.");
        System.out.println("         Niveau max = " + this.levelMax + ".");
        System.out.println("=====================================\n");
    }

    public void addPlayersInArena() {

        for (int i = 0; i < this.nbPlayer; i++) {

            int playerClass = choosePlayerClass(i);
            int choiceLevel = choosePlayerLevel(playerClass);
            this.attributesAvailable = choiceLevel;
            int strength = chooseAttribute("Force");
            int agility = chooseAttribute("Agilité");
            int intelligence = chooseAttribute("Intelligence");

            switch (playerClass){
                case 1 :
                    personages.add(new Warrior(choiceLevel,strength,agility,intelligence));
                    break;
                case 2 :
                    personages.add(new Prowler(choiceLevel,strength,agility,intelligence));
                    break;
                case 3 :
                    personages.add(new Mage(choiceLevel,strength,agility,intelligence));
                    break;
            }
        }
    }


    public int choosePlayerClass(int index) {

        int playerClass = 0;
        boolean choiceGood;

        do {
            System.out.println("-------------------------------------");
            System.out.println("Joueur "+ (index+1) + ": Veuillez choisir la classe de votre personnage :\n");
            System.out.println("1 - Guerrier");
            System.out.println("2 - Rôdeur");
            System.out.println("3 - Mage");

            boolean responseIsANumber;
            do {
                try {
                    playerClass = sc.nextInt();
                    responseIsANumber = true;

                } catch (InputMismatchException e) {
                    sc.next();
                    responseIsANumber = false;
                    System.err.println("Veulliez saisir un chiffre, svp");
                }

            } while (!responseIsANumber);

            choiceGood = ((playerClass >= 1) && (playerClass <= 3));

            if (!choiceGood) {
                System.out.println("Vous n'avez pas choisi de class parmi les choix proposés");
            }
        } while(!choiceGood);


    return playerClass;
    }

    private int choosePlayerLevel(int playerClass) {

        int playerLevel = 0;
        boolean levelGood;

        do {
            System.out.println("et choisir son niveau : ");

            boolean responseIsANumber;
            do {
                try {
                    playerLevel = sc.nextInt();
                    responseIsANumber = true;

                } catch (InputMismatchException e) {
                    sc.next();
                    responseIsANumber = false;
                    System.err.println("Veulliez saisir un nombre, svp");
                }

            } while (!responseIsANumber);

            levelGood = ((playerLevel >= 1) && (playerClass <= this.levelMax));
            if (!levelGood) {
                if (playerLevel < 0)
                    System.out.println("Veuillez choisir un niveau > 0");
                else if (playerLevel > this.levelMax)
                    playerLevel = this.levelMax;
            }

        } while(!levelGood);

        return playerLevel;
    }


    public int chooseAttribute(String attributeName) {

        if (attributesAvailable != 0) {

            int attribute = 0;
            boolean attributeGood;

            do {

                boolean responseIsANumber;

                do {
                    try {
                        boolean isVowel = "aeiouyAEIOUY".contains(Character.toString(attributeName.charAt(0)));

                        if (isVowel)
                            System.out.println("Veuillez choisir l'"+ attributeName +" du personnage (0-"+ attributesAvailable +"; MAX = 100):");
                        else
                            System.out.println("Veuillez choisir la "+ attributeName +" du personnage (0-"+ attributesAvailable +"; MAX = 100):");

                        attribute = this.sc.nextInt();
                        responseIsANumber = true;
                        attributeGood = ((attribute >= 0) && (attribute <= attributesAvailable));

                        if (attribute < 0) {
                            if (isVowel)
                                System.out.println("L'"+ attributeName +" doit être >= 0");
                            else
                                System.out.println("La "+ attributeName +" doit être >= 0");

                        } else if (attribute > 100) {
                            if (isVowel)
                                System.out.println("L'"+ attributeName +" doit être <= 100");
                            else
                                System.out.println("La "+ attributeName +" doit être <= 100");
                        } else if ((attribute >= attributesAvailable) && (attributesAvailable != 0))
                            System.out.println("Pas assez de point d'atribut! ");
                        // TODO: 07/06/19 Check the condition 

                    } catch (InputMismatchException e) {
                        sc.next();
                        responseIsANumber = false;
                        attributeGood = false;
                        System.out.println("Veulliez saisir un nombre, svp");
                    }

                }while (!responseIsANumber);

            } while(!attributeGood);

            if (attribute <= 100) {
                attributesAvailable -= attribute;
                return attribute;
            }
            else {
                attributesAvailable -= 100;
                return 100;
            }
        } else {
            System.out.println("Plus de points d'attributs; " + attributeName + " = 0");
            return 0;
        }
    }
}
