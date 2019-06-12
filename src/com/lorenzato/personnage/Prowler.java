package com.lorenzato.personnage;

public class Prowler extends Personage {

    /** CONSTRUCTORS
     * @param level Le niveau du personnage
     * @param strength Sa force
     * @param agility Son agilité
     * @param intelligence Son intelligence
     */
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

    /**
     * Attaque Basique - Tir à l’Arc :
     * Effectue des dommages égaux à l’agilité du joueur sur l’adversaire.
     * @param target La cible visée par l'attaque
     */
    @Override
    public void basicAttack(Personage target) {
        target.receivedDamage(this.agility);
    }

    /**
     * Attaque Spéciale - Soin :
     * Attaque Spéciale - Concentration : Le joueur gagne son niveau divisé par 2 en agilité
     * System.out.println("Attaque Spéciale - Concentration");
     * @param target La cible visée par l'attaque.
     */
    @Override
    public void specialAttack(Personage target) {
        target.receivedDamage(0);
        this.agility += (this.level/2);
        System.out.println(this.personageName +" se concentre, sont agilité augmente à "+ this.agility);
    }

    /**
     * Description du personnage
     * @param indexPlayer L'index du joueur dont le personnage est décrit.
     */
    @Override
    public void persoDescription(int indexPlayer) {
        System.out.println(this.personageName + " je suis le Rôdeur Joueur " + indexPlayer + " niveau " + this.level +
                " je possède " + this.life + "de vitalité, " + this.strength + " de force, " + this.agility +
                " d'agilité et " + this.intelligence + " d'intelligence !");
    }

    /**
     * Affiche le nom du personnage mort
     * @return true si le personage est mort, vie  = 0.
     */
    @Override
    public boolean isDead() {
        if (this.life == 0) {
            System.out.println("Le Rôdeur " + this.personageName + " est mort !");
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
}
