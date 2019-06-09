package com.lorenzato.personnage;

public class Mage extends Personage {

    public Mage(int level, int strength, int agility, int intelligence) {
        super(level, strength, agility, intelligence);
        this.persoName = "Merlin";
    }

    public Mage(int level, int strength, int agility, int intelligence, String persoName) {
        super(level, strength, agility, intelligence);
        this.persoName = persoName;
    }

    @Override
    public void basicAttack(Personage target) {
        //Attaque Basique - Boule de Feu :
        // Effectue des dommages égaux à l’intelligence du joueur sur l’adversaire.
        target.receivedDamage(this.intelligence);
    }

    @Override
    public void specialAttack(Personage target) {
        //Attaque Spéciale - Soin :
        // Le joueur soigne sa vie et regagne sa quantité d’intelligence fois 2 en points de vie.
        // Attention, il ne peut pas avoir plus de vie qu’il n’en avait au départ.
        this.life += (this.intelligence * 2);
        if (this.life > this.maxLife) this.life = this.maxLife;

    }

    @Override
    public void persoDescription(int index) {

    }

}
