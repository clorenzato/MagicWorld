package com.lorenzato.personnage;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Personage implements Attacks{

    Scanner sc = new Scanner(System.in);

    //Player attributes
    private int level;
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


    public void setLife(int level) {
        this.life = level * 5;
    }

    public int getLife() {
        return this.life;
    }


    public int NbAttributeAvailable() {
        return this.level - (this.strength + this.agility + this.intelligence);
    }

    public int setAttribute(String attributeName) {

        if (NbAttributeAvailable() != 0) {

            int attribute = 0;
            boolean attributeGood;

            System.out.println("Veuillez choisir l'"+ attributeName +" du personnage (0-"+ NbAttributeAvailable() +"; MAX = 100):");
            do {
                boolean responseIsANumber;

                do {
                    try {
                        attribute = this.sc.nextInt();
                        responseIsANumber = true;
                        attributeGood = ((attribute >= 0) && (attribute <= NbAttributeAvailable()));

                        if (!attributeGood) {
                        System.out.println("Vous n'avez pas assez de points d'attributs en reserve ("+ NbAttributeAvailable() +" pts restants)");
                        boolean isVoyel = "aeiouyAEIOUY".contains(Character.toString(attributeName.charAt(0)));
                        if (isVoyel)
                            System.out.println("Veuillez re-choisir l'"+ attributeName +" du personnage (0-"+ NbAttributeAvailable() +"; MAX = 100):");
                        else
                            System.out.println("Veuillez re-choisir la'"+ attributeName +" du personnage (0-"+ NbAttributeAvailable() +"; MAX = 100):");
                        }

                    } catch (InputMismatchException e) {
                        sc.next();
                        responseIsANumber = false;
                        attributeGood = false;
                        System.out.println("Veulliez saisir un nombre, svp");
                    }

                }while (!responseIsANumber);
            } while(!attributeGood);

            if (attribute <= 100) {
                System.out.println("Retourne agility = " + attribute);
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
