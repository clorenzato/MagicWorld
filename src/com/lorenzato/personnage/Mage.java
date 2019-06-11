package com.lorenzato.personnage;

public class Mage extends Personage {

    public Mage(int level, int strength, int agility, int intelligence) {
        super(level, strength, agility, intelligence);
        this.personageName = "Merlin";
        this.attacksType = AttacksType.valueOf("MAGE");
    }

    public Mage(int level, int strength, int agility, int intelligence, String persoName) {
        super(level, strength, agility, intelligence);
        this.personageName = persoName;
        this.attacksType = AttacksType.valueOf("MAGE");
    }

    @Override
    public void basicAttack(Personage target) {
        // Attaque Basique - Boule de Feu :
        // Effectue des dommages égaux à l’intelligence du joueur sur l’adversaire.
        target.receivedDamage(this.intelligence);
    }

    @Override
    public void specialAttack(Personage target) {
        // Attaque Spéciale - Soin :
        // Le joueur soigne sa vie et regagne sa quantité d’intelligence fois 2 en points de vie.
        // Attention, il ne peut pas avoir plus de vie qu’il n’en avait au départ.
        // System.out.println("Attaque Spéciale - Soin :");
        target.receivedDamage(0);
        this.life += (this.intelligence * 2);
        if (this.life > (this.level*5)) this.life = this.level*5;
    }

    @Override
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

    @Override
    public void persoDescription(int indexPlayer) {
        System.out.println(this.personageName + " je suis le Mage Joueur " + indexPlayer + " niveau " + this.level +
                " je possède " + this.life + "de vitalité, " + this.strength + " de force, " + this.agility +
                " d'agilité et " + this.intelligence + " d'intelligence !");
    }

    @Override
    public boolean isDead() {
        if (this.life == 0) {
            System.out.println("Le Mage " + this.personageName + " est mort !");
            return true;
        } else
            return false;
    }

    @Override
    public AttacksType getAttacksType() {
        return this.attacksType;
    }


}

