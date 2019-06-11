package com.lorenzato.personnage;

public enum AttacksType {
    MAGE ("Boule de Feu", "Soin"),
    WARRIOR ("Coup d’Épée", "Coup de Rage"),
    PROWLER ("Tir à l’Arc", "Concentration");

    private final String  basicAttack;   // in kilograms
    private final String specialAttack; // in meters

    AttacksType(String basicAttack, String specialAttack) {
        this.basicAttack = basicAttack;
        this.specialAttack = specialAttack;
    }
    public String basicAttack() {
        return "Attaque Basique - "+ basicAttack;
    }
    public String specialAttack() {
        return "Attaque Spéciale - " + specialAttack;
    }

}
