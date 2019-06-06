package com.lorenzato.personnage;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonageTest {

    @Test
    void Given_levelOf10_When_GetLife_Then_LifeIs50() {
        System.setIn(new ByteArrayInputStream("30\n30\n40\n".getBytes()));
        Personage personage = new Mage(100);
        assertEquals(500,personage.getLife());
    }

}

