package com.lorenzato.personnage;

public class Warrior extends Personage {

    /** CONSTRUCTOR
     * @param level Le niveau du personnage
     * @param strength Sa force
     * @param agility Son agilité
     * @param intelligence Son intelligence
     */
    public Warrior(int level, int strength, int agility, int intelligence) {
        super(level, strength, agility, intelligence);
        this.personageName = "Brutus";
        this.attacksType = AttacksType.valueOf("WARRIOR");
    }

    /**
     * Attaque Basique - Coup d’Épée :
     * Effectue des dommages égaux à la force du joueur sur l’adversaire.
     * @param target La cible visée par l'attaque.
     */
    @Override
    public void basicAttack(Personage target) {
        target.receivedDamage(getBasicDamage());
    }

    /**
     * Attaque Spéciale - Coup de Rage :
     * Effectue des dommages égaux à la force du joueur fois deux sur l’adversaire.
     * Le joueur lançant l'attaque perd de la vitalité : la valeur de sa force divisée par 2.
     * @param target La cible visée par l'attaque.
     */
    @Override
    public void specialAttack(Personage target) {
        target.receivedDamage(getSpecialDamage());
        this.strength /= 2;
        System.out.println(this.personageName + " perd la moitié de sa force, force = " + this.strength);
    }

    /**
     * Description du personnage
     * @param indexPlayer L'index du joueur dont le personnage est décrit.
     */
    @Override
    public void personageDescription(int indexPlayer) {
        System.out.println(this.personageName + " je suis le Guerrier du Joueur " + indexPlayer + ", de niveau " + this.level +
                ". Je possède " + this.life + " de vitalité, " + this.strength + " de force, " + this.agility +
                " d'agilité et " + this.intelligence + " d'intelligence !");
    }

    /**
     * Affiche le nom du personnage mort
     * @return true si le personage est mort, vie  = 0.
     */
    @Override
    public boolean isDead() {
        if (this.life == 0) {
            System.out.println("Le Guerrier " + this.personageName + " est mort !");
            return true;
        } else
            return false;
    }

    /**
     * @return la description de l'attaque cf. l'énumérationAttacksType.
     */
    @Override
    public AttacksType getAttacksType() {
        return this.attacksType;
    }

    @Override
    public int getSpecialDamage() {
        return this.strength * 2;
    }

    @Override
    public int getBasicDamage() {
        return this.strength;
    }

    @Override
    public String getSpecialDamageToString() {
        return ((2*this.strength) + " pts de dommages, -" + (this.strength / 2) + " de Force)");
    }

    @Override
    public String getBasicDamageToString() {
        return (this.strength + " pts de dommages");
    }
}
