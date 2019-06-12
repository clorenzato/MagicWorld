package com.lorenzato.personnage;

public class Mage extends Personage {

    /** CONSTRUCTORS
     * @param level Le niveau du personnage
     * @param strength Sa force
     * @param agility Son agilité
     * @param intelligence Son intelligence
     */
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

    /**
     * Attaque Basique - Boule de Feu :
     * Effectue des dommages égaux à l’intelligence du joueur sur l’adversaire.
     * @param target La cible visée par l'attaque
     */
    @Override
    public void basicAttack(Personage target) {
        target.receivedDamage(this.intelligence);
    }

    /**
     * Attaque Spéciale - Soin :
     * Le joueur soigne sa vie et regagne sa quantité d’intelligence fois 2 en points de vie.
     * Attention, il ne peut pas avoir plus de vie qu’il n’en avait au départ.
     * @param target La cible visée par l'attaque.
     */
    @Override
    public void specialAttack(Personage target) {
        target.receivedDamage(0);
        this.life += (this.intelligence * 2);
        if (this.life > (this.level*5)) this.life = this.level*5;
        System.out.println(this.personageName + " soinge ses blessures, vie = "+ this.life + " / " + (this.level*5));
    }

    /**
     * Description du personnage
     * @param indexPlayer L'index du joueur dont le personnage est décrit.
     */
    @Override
    public void persoDescription(int indexPlayer) {
        System.out.println(this.personageName + " je suis le Mage Joueur " + indexPlayer + " niveau " + this.level +
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
            System.out.println("Le Mage " + this.personageName + " est mort !");
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

