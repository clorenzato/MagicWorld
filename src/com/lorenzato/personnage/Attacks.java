package com.lorenzato.personnage;

public interface Attacks {
    AttacksType attacksType = null;
    void basicAttack(Personage target);
    void specialAttack(Personage target);
    AttacksType getAttacksType();
}
