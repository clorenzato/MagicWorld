package com.lorenzato.personnage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonageTest {

    @Test
    void Given_levelOf10_When_GetLife_Then_LifeIs50() {
        Personage personage = new Mage(10);
        assertEquals(50,personage.getLife());
    }

}

