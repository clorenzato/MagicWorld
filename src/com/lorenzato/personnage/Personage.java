package com.lorenzato.personnage;

import java.util.Scanner;

public abstract class Personage implements Attacks{

    //Player attributes
    protected int level;
    protected int life;
    protected int strength;
    protected int agility;
    protected int intelligence;
    protected String personageName;
    protected AttacksType attacksType;

    public Personage(int level, int strength, int agility, int intelligence) {
        this.level = level;
        this.life = level * 5;
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

    public void receivedDamage(int damage) {
        this.life -= damage;
        if (this.life <= 0) {
            this.life = 0;
        }
        if (damage != 0) {
            System.out.println(getPersoName() + " à reçu " + damage +" de dommages ");
            System.out.println("Il lui reste : " + this.life + " / " + (this.level*5) + " pts de vie");
        }
    }

    public String getPersoName() {
        return this.personageName;
    }

    public abstract boolean isDead();
    public abstract void persoDescription(int indexPlayer);

}
