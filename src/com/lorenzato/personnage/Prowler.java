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


    }

    @Override
    public void specialAttack(Personage target) {
        // Attaque Spéciale - Concentration : Le joueur gagne son niveau divisé par 2 en agilité

    }

    @Override
    public void persoDescription(int index) {

    }

}
