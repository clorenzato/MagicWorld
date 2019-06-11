package com.lorenzato.personnage;

public class Warrior extends Personage {


    public Warrior(int level, int strength, int agility, int intelligence) {
        super(level, strength, agility, intelligence);
        this.personageName = "Brutus";
        this.attacksType = AttacksType.valueOf("WARRIOR");
    }

    public Warrior(int level, int strength, int agility, int intelligence, String personageName) {
        super(level, strength, agility, intelligence);
        this.personageName = personageName;
        this.attacksType = AttacksType.valueOf("WARRIOR");
    }

    @Override
    public void basicAttack(Personage target) {
        // Attaque Basique - Coup d’Épée :
        // Effectue des dommages égaux à la force du joueur sur l’adversaire.
        // System.out.print("Attaque Basique - Coup d’Épée : ");
        target.receivedDamage(this.strength);
    }

    @Override
    public void specialAttack(Personage target) {
        // Attaque Spéciale - Coup de Rage :
        // Effectue des dommages égaux à la force du joueur fois deux sur l’adversaire.
        // Le joueur lançant l'attaque perd de la vitalité : la valeur de sa force divisée par 2.
        // System.out.print("Attaque Spéciale - Coup de Rage : ");
        target.receivedDamage(this.strength * 2);
        this.strength /= 2;
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
        System.out.println(this.personageName + " je suis le Guerrier Joueur " + indexPlayer + " niveau " + this.level +
                " je possède " + this.life + "de vitalité, " + this.strength + " de force, " + this.agility +
                " d'agilité et " + this.intelligence + " d'intelligence !");
    }

    @Override
    public boolean isDead() {
        if (this.life == 0) {
            System.out.println("Le Guerrier " + this.personageName + " est mort !");
            return true;
        } else
            return false;
    }

    @Override
    public AttacksType getAttacksType() {
        return this.attacksType;
    }
}
