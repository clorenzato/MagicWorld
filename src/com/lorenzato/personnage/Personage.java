package com.lorenzato.personnage;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Personage implements Attacks{

    Scanner sc = new Scanner(System.in);

    //Player attributes
    protected int level;
    protected int life;
    protected int strength;
    protected int agility;
    protected int intelligence;


    public Personage(int level) {
        this.level = level;
        this.life = level*5;
        this.strength = setAttribute("Force");
        this.agility = setAttribute("Agilité");
        this.intelligence = setAttribute("Intelligence");
    }


    public int getLife() {
        return this.life;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int NbAttributeAvailable() {
        return this.level - (this.strength + this.agility + this.intelligence);
    }

    public int setAttribute(String attributeName) {

        if (NbAttributeAvailable() != 0) {

            int attribute = 0;
            boolean attributeGood;

            do {

                boolean responseIsANumber;

                do {
                    try {
                        boolean isVowel = "aeiouyAEIOUY".contains(Character.toString(attributeName.charAt(0)));

                        if (isVowel)
                            System.out.println("Veuillez re-choisir l'"+ attributeName +" du personnage (0-"+ NbAttributeAvailable() +"; MAX = 100):");
                        else
                            System.out.println("Veuillez re-choisir la "+ attributeName +" du personnage (0-"+ NbAttributeAvailable() +"; MAX = 100):");

                        attribute = this.sc.nextInt();
                        responseIsANumber = true;
                        attributeGood = ((attribute >= 0) && (attribute <= NbAttributeAvailable()));



                    } catch (InputMismatchException e) {
                        sc.next();
                        responseIsANumber = false;
                        attributeGood = false;
                        System.out.println("Veulliez saisir un nombre, svp");
                    }

                }while (!responseIsANumber);

            } while(!attributeGood);

            if (attribute <= 100) {
                System.out.println("Return "+ attributeName + " = " + attribute);
                return attribute;
            }
            else {
                System.out.println("Retourne 100");
                return 100;
            }
        } else {
            System.out.println("Plus de points d'attributs; " + attributeName + " = 0");
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Personage{" +
                "level=" + level +
                ", life=" + life +
                ", strength=" + strength +
                ", agility=" + agility +
                ", intelligence=" + intelligence +
                '}';
    }
}
