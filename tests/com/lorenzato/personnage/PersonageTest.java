package com.lorenzato.personnage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonageTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(System.out);
    }


    @Test
    void Given_levelOf100_When_GetLife_Then_LifeIs500() {
        Mage mage = new Mage(100,20,20,20);
        assertEquals(500,mage.getLife());
    }

    @Test
    void Given_StrengthOf10_When_GetStrength_Then_StrengthIs10() {
        Mage mage = new Mage(100,10,10,20);
        assertEquals(10,mage.getStrength());

    }

    @Test
    void Given_AgilityOf20_When_GetAgility_Then_AgilityIs20() {
        Mage mage = new Mage(100,20,20,20);
        assertEquals(20,mage.getAgility());

    }

    @Test
    void Given_IntelligenceOf30_When_GetIntelligence_Then_IntelligenceIs30() {
        Mage mage = new Mage(100,20,20,30);
        assertEquals(30 ,mage.getIntelligence());
    }
}

