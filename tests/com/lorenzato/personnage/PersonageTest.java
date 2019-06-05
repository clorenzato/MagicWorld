package com.lorenzato.personnage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonageTest {

    @Test
    public void Given_levelOf10_When_GetLife_Then_Get50() {
        int level = 10;
        Personage personage = new Mage(level);
        assertEquals(50,personage.getLife());
    }

}

