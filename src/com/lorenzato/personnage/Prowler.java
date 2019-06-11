package com.lorenzato.personnage;

public class Prowler extends Personage {

    public Prowler(int level, int strength, int agility, int intelligence) {
        super(level, strength, agility, intelligence);
        this.personageName = "Robin";
        this.attacksType = AttacksType.valueOf("PROWLER");
    }

    public Prowler(int level, int strength, int agility, int intelligence, String persoName) {
        super(level, strength, agility, intelligence);
        this.personageName = persoName;
        this.attacksType = AttacksType.valueOf("PROWLER");
    }

    @Override
    public void basicAttack(Personage target) {
        // Attaque Basique - Tir à l’Arc :
        // Effectue des dommages égaux à l’agilité du joueur sur l’adversaire.
        target.receivedDamage(this.agility);
    }

    @Override
    public void specialAttack(Personage target) {
        // Attaque Spéciale - Concentration : Le joueur gagne son niveau divisé par 2 en agilité
        // System.out.println("Attaque Spéciale - Concentration");
        target.receivedDamage(0);
        this.agility += (this.level/2);
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
        System.out.println(this.personageName + " je suis le Rôdeur Joueur " + indexPlayer + " niveau " + this.level +
                " je possède " + this.life + "de vitalité, " + this.strength + " de force, " + this.agility +
                " d'agilité et " + this.intelligence + " d'intelligence !");
    }

    @Override
    public boolean isDead() {
        if (this.life == 0) {
            System.out.println("Le Rôdeur " + this.personageName + " est mort !");
            return true;
        } else
            return false;
    }

    @Override
    public AttacksType getAttacksType() {
        return this.attacksType;
    }
}
