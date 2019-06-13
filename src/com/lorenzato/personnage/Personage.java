package com.lorenzato.personnage;

public abstract class Personage implements Attacks{

    int level;
    int life;
    int strength;
    int agility;
    int intelligence;
    String personageName;
    AttacksType attacksType;

    Personage(int level, int strength, int agility, int intelligence) {
        this.level = level;
        this.life = level * 5;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
    }


    int getLife() {
        return this.life;
    }

    int getStrength() {
        return strength;
    }

    int getAgility() {
        return agility;
    }

    int getIntelligence() {
        return intelligence;
    }


    public String getPersonageName() {
        return this.personageName;
    }

    @Override
    public void receivedDamage(int damage) {
        this.life -= damage;
        if (this.life <= 0) {
            this.life = 0;
        }
        if (damage != 0) {
            System.out.println(this.personageName + " a reçu " + damage +" de dommages.");
            if (this.life != 0)
                System.out.println("Il lui reste : " + this.life + "/" + (this.level*5) + " pts de vie.");
        } else {
            System.out.println(this.personageName + " n'a reçu aucun dommage ");
            System.out.println("Il lui reste : " + this.life + "/" + (this.level*5) + " pts de vie.");
        }
    }

    public abstract void personageDescription(int indexPlayer);
    public abstract int getSpecialDamage();
    public abstract int getBasicDamage();
}
