package com.lorenzato.personnage;

public interface Attacks {
    void basicAttack(Personage target);
    void specialAttack(Personage target);
    void receivedDamage(int damage);
    boolean isDead();
    AttacksType getAttacksType();
    String  getSpecialDamageToString();
    String getBasicDamageToString();
}
