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

    public Personage(int level, int strength, int agility, int intelligence) {
        this.level = level;
        this.life = level*5;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
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
