package com.lorenzato.personnage;

public abstract class Personage {

    protected int level;
    protected int life;
    protected int strength;
    protected int agility;
    protected int intelligence;

    public Personage(int level) {
        this.level = level;
    }
}
