package com.lorenzato.personnage;

public class Prowler extends Personage {

    public Prowler(int level, int strength, int agility, int intelligence) {
        super(level, strength, agility, intelligence);
        this.persoName = "Robin";
    }

    public Prowler(int level, int strength, int agility, int intelligence, String persoName) {
        super(level, strength, agility, intelligence);
        this.persoName = persoName;
    }

    @Override
    public void basicAttack(Personage target) {
        //Attaque Basique - Tir à l’Arc :
        // Effectue des dommages égaux à l’agilité du joueur sur l’adversaire.
        System.out.println("Attaque Basique - Tir à l’Arc");
        target.receivedDamage(this.agility);
    }

    @Override
    public void specialAttack(Personage target) {
        // Attaque Spéciale - Concentration : Le joueur gagne son niveau divisé par 2 en agilité
        System.out.println("Attaque Spéciale - Concentration");
        target.receivedDamage(0);
        this.agility += (this.level/2);
    }

    @Override
    public void persoDescription(int indexPlayer) {

    }

}
