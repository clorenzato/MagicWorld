package com.lorenzato.arena;

import com.lorenzato.exeptions.NoPersonnageException;
import com.lorenzato.personnage.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Arena {

    private Scanner sc = new Scanner(System.in);

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

    /**
     * Ask the user to give a number in the console. Check if the result is a number.
     * @return the number that have been chosen.
     */
    int chooseNumber(){

        int numberChosen = 0;
        boolean responseIsANumber;
        do {
            try {
                numberChosen = sc.nextInt();
                responseIsANumber = true;
            } catch (InputMismatchException e) {
                sc.next();
                responseIsANumber = false;
                System.err.println("Veuillez saisir un nombre, svp");
            }
        } while(!responseIsANumber);

        return numberChosen;
    }

    /**
     * Add as many Personage in the arena as the number of player (nbPlayer).
     * Call all function needed to construct a Player and
     * add it to the list.
     */
    public void addPlayersInArena() {

        for (int i = 1; i < this.nbPlayer+1; i++) {

            int personageClass = choosePersonageClass(i);
            int choiceLevel = choosePersonageLevel();
            int strength = chooseAttribute("Force");
            int agility = chooseAttribute("Agilité");
            int intelligence = chooseAttribute("Intelligence");


            while (strength + agility + intelligence != choiceLevel) {
                System.out.println("Il vous reste "+ this.attributesAvailable + " pts à attribuer");
                strength = increaseAttribute("Force",strength);
                agility = increaseAttribute("Agilité",agility);
                intelligence = increaseAttribute("Intelligence", intelligence);
            }

            addPersonnageToList(i, personageClass, choiceLevel, strength, agility, intelligence);
        }
    }

    /**
     * Create and add a Personage to the list of personages.
     * @param indexPlayer he number of the player e.g. "Joueur indexPlayer" == "Joueur 1" if indexPlayer = 1.
     * @param personageClass The personage Class e.g. "Mage" if personageClass == 3.
     * @param choiceLevel The level of the personage
     * @param strength The strength of the personage
     * @param agility The agility of the personage
     * @param intelligence The intelligence of the personage
     */
    private void addPersonnageToList(int indexPlayer, int personageClass, int choiceLevel, int strength, int agility, int intelligence){
        switch (personageClass){
            case 1 :
                Warrior warrior = new Warrior(choiceLevel,strength,agility,intelligence);
                warrior.personageDescription(indexPlayer);
                personages.add(warrior);
                break;
            case 2 :
                Prowler prowler = new Prowler(choiceLevel,strength,agility,intelligence);
                prowler.personageDescription(indexPlayer);
                personages.add(prowler);
                break;
            case 3 :
                Mage mage = new Mage(choiceLevel,strength,agility,intelligence);
                mage.personageDescription(indexPlayer);
                personages.add(mage);
                break;
        }
    }

    /**
     * Allow for choosing the the class of the personage of the players.
     * @param indexPlayer The number of the player e.g. "Joueur indexPlayer" == "Joueur 1" if indexPlayer = 1.
     * @return The chosen Class (e.g. 1 -> Guerrier).
     */
    int choosePersonageClass(int indexPlayer) {

        int personageClass;
        boolean choiceGood;

        do {
            System.out.println("-------------------------------------");
            System.out.println("Joueur "+ (indexPlayer) + ": Veuillez choisir la classe de votre personnage :\n");
            System.out.println("1 - Guerrier");
            System.out.println("2 - Rôdeur");
            System.out.println("3 - Mage");

            personageClass = chooseNumber();
            choiceGood = ((personageClass >= 1) && (personageClass <= 3));

            if (!choiceGood) {
                System.err.println("Vous n'avez pas choisi de class parmi les choix proposés");
            }
        } while(!choiceGood);

        return personageClass;
    }

    /**
     * Allow for choosing the the level of the personage of the different players.
     * @return The chosen Level (from 1 to 100).
     */
    int choosePersonageLevel() {

        int playerLevel;
        boolean levelGood;
        do {

            System.out.println("et choisir son niveau : ");
            playerLevel = chooseNumber();

            levelGood = ((playerLevel >= 1) && (playerLevel <= this.levelMax));
            if (!levelGood) {
                if (playerLevel <= 0)
                    System.err.println("Veuillez choisir un niveau > 0");
                else
                    System.err.println("Veuillez choisir un niveau < "+ this.levelMax);
            }

        } while(!levelGood);
        this.attributesAvailable = playerLevel;

        return playerLevel;
    }

    /**
     * Allow for select the value of a given (attributeName) attribute
     * @param attributeName The Name of the attribute to display to the user
     * @return the value of the attribute (0-100).
     */
    int chooseAttribute(String attributeName) {

        if (attributesAvailable != 0) {

            int attribute;
            boolean attributeGood;

            do {
                boolean isVowel = "aeiouyAEIOUY".contains(Character.toString(attributeName.charAt(0)));

                if (isVowel)
                    System.out.println("Veuillez choisir l'"+ attributeName +" du personnage (0-"+ attributesAvailable + "; MAX = 100):");
                else
                    System.out.println("Veuillez choisir la "+ attributeName +" du personnage (0-"+ attributesAvailable + "; MAX = 100):");

                attribute = chooseNumber();
                attributeGood = ((attribute >= 0) && (attribute <= attributesAvailable));

                if (attribute < 0) {
                    if (isVowel)
                        System.err.println("L'"+ attributeName +" doit être >= 0");
                    else
                        System.err.println("La "+ attributeName +" doit être >= 0");

                } else if (attribute > 100) {
                    if (isVowel)
                        System.err.println("L'"+ attributeName +" doit être <= 100");
                    else
                        System.err.println("La "+ attributeName +" doit être <= 100");
                } else if ((attribute > attributesAvailable))
                    System.err.println("Pas assez de point d'atribut! ");
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
            System.out.println("Plus de point d'attribut; " + attributeName + " = 0");
            return 0;
        }
    }

    /**
     * Allow for select the value of a given (attributeName) attribute
     * @param attributeName The Name of the attribute to display to the user
     * @return the value of the attribute (0-100).
     */
    int increaseAttribute(String attributeName, int currentAttributeValue) {

        if (attributesAvailable != 0) {

            int attribute;
            boolean attributeGood;

            do {
                boolean isVowel = "aeiouyAEIOUY".contains(Character.toString(attributeName.charAt(0)));

                if (isVowel)
                    System.out.println("Veuillez choisir l'"+ attributeName +" à ajouter (0-"+ attributesAvailable +";  "+ attributeName + " actuelle = " + currentAttributeValue + " pts; MAX = 100):");
                else
                    System.out.println("Veuillez choisir la "+ attributeName +" à ajouter (0-"+ attributesAvailable +";  "+ attributeName + " actuelle = " + currentAttributeValue + " pts; MAX = 100):");

                attribute = chooseNumber();
                attributeGood = ((attribute >= 0) && (attribute <= attributesAvailable));

                if ((attribute + currentAttributeValue) < 0) {
                    if (isVowel)
                        System.err.println("L'"+ attributeName +" totale doit être >= 0");
                    else
                        System.err.println("La "+ attributeName +" totale doit être >= 0");

                } else if ((attribute + currentAttributeValue) > 100) {
                    if (isVowel)
                        System.err.println("L'"+ attributeName +" totale doit être <= 100");
                    else
                        System.err.println("La "+ attributeName +" total doit être <= 100");
                } else if ((attribute > attributesAvailable))
                    System.err.println("Pas assez de point d'atribut restant! ");
            } while(!attributeGood);

            if ((attribute + currentAttributeValue) <= 100) {
                attributesAvailable -= attribute;
                return (attribute + currentAttributeValue);
            }
            else {
                attributesAvailable -= 100;
                return 100;
            }
        } else {
            System.out.println("Plus de point d'attribut; " + attributeName + " = " + currentAttributeValue);
            return currentAttributeValue;
        }
    }

    /**
     * @param indexPlayer The number of the player e.g. "Joueur indexPlayer" == "Joueur 1" if indexPlayer = 1.
     * @return return the name of personnage of the player indexPlayer.
     */
    private String getPersonageNameFromList(int indexPlayer){
        return this.personages.get(indexPlayer-1).getPersonageName();
    }


     /**
     * @param indexPlayer The number of the player e.g. "Joueur indexPlayer" == "Joueur 1" if indexPlayer = 1.
     * @return return the Personage of the player indexPLayer.
     */
     Personage getPersonageList(int indexPlayer){
        return this.personages.get(indexPlayer-1);
    }

    /**
     * Allow for the personage of each player to fight turn by turn.
     */
    public void letsFight(){

        if (this.personages.isEmpty()) throw new NoPersonnageException();

        System.out.println("\n*************************************");
        System.out.println("      Que le combat commence !");
        System.out.println("*************************************");

        boolean fightIsOver;
        int fightTurn = 0;

        do {
            Personage assailant = getPersonageList(getAssailantIndex(fightTurn));
            Personage target = getPersonageList(getTargetIndex(fightTurn));

            chooseAttack(getAssailantIndex(fightTurn));
            int attackType = chooseNumber();

            switch (attackType){
                case 1 :
                    assailant.basicAttack(target);
                    break;
                case 2 :
                    assailant.specialAttack(target);
                    break;
            }

            fightIsOver = target.isDead();
            if (fightIsOver) System.out.println("Le Joueur " + getAssailantIndex(fightTurn) + " à gagné !! ");

            if (fightTurn > 50) fightIsOver = true; // Limitation of the number of turn to 50.
            fightTurn += 1;
        } while (!fightIsOver);

        System.out.println("\n** Le combat est terminé ! **");
    }

    /**
     * Display the possible attacks depending of the personage class.
     * @param indexPlayer The number of the player e.g. "Joueur indexPlayer" == "Joueur 1" if indexPlayer = 1.
     */
    private void chooseAttack(int indexPlayer){

        System.out.println("-------------------------------------");
        System.out.println("\nJoueur "+ indexPlayer +" - " + getPersonageNameFromList(indexPlayer) +", Veuillez choisir votre attaque :");
        System.out.println("-------------------------------------");
        System.out.println("1 : "+ getPersonageList(indexPlayer).getAttacksType().basicAttack() + " ("+ getPersonageList(indexPlayer).getBasicDamageToString() + ")");
        System.out.println("2 : "+ getPersonageList(indexPlayer).getAttacksType().specialAttack() + " (" + getPersonageList(indexPlayer).getSpecialDamageToString() + ")");
    }

    /**
     * @param fightTurn the turn number (Only one player attack during each turn)
     * @return the index of the Player that will attack.
     */
    private int getAssailantIndex(int fightTurn){
        return fightTurn % this.nbPlayer + 1;
    }

    /**
     * @param fightTurn the turn number (One player is attacked during each turn)
     * @return the index of the Player that will be attacked.
     */
    private int getTargetIndex(int fightTurn) {
        int target = fightTurn % this.nbPlayer + 2;
        if (target <= this.nbPlayer)
            return target;
        else
            return 1;
    }
}
