package com.lorenzato.personnage;

public class Warrior extends Personage {


    public Warrior(int level, int strength, int agility, int intelligence) {
        super(level, strength, agility, intelligence);
        this.persoName = "Brutus";
    }

    public Warrior(int level, int strength, int agility, int intelligence, String persoName) {
        super(level, strength, agility, intelligence);
        this.persoName = persoName;
    }

    @Override
    public void basicAttack(Personage target) {
        // Attaque Basique - Coup d’Épée :
        // Effectue des dommages égaux à la force du joueur sur l’adversaire.
        System.out.print("Attaque Basique - Coup d’Épée : ");
        target.receivedDamage(this.strength);
    }


    @Override
    public void specialAttack(Personage target) {
        // Attaque Spéciale - Coup de Rage :
        // Effectue des dommages égaux à la force du joueur fois deux sur l’adversaire.
        // Le joueur lançant l'attaque perd de la vitalité : la valeur de sa force divisée par 2.
        System.out.print("Attaque Spéciale - Coup de Rage : ");
        target.receivedDamage(this.strength * 2);
        this.strength /= 2;

    }


    @Override
    public void persoDescription(int indexPlayer) {

    }


}
