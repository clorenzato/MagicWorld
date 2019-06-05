package com.lorenzato.personnage;

public abstract class Personage {

    protected int level;
    protected int life;
    protected int strength;
    protected int agility;
    protected int intelligence;

    public Personage(int level) {
        this.level = level;
        this.setLife(level);
    }

    public void setLife(int level) {
        this.life = level * 5;
    }

    public int getLife() {
        return this.life;
    }
}
