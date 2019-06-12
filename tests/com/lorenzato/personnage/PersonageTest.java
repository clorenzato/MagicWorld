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

    @Test
    void Given_Warrior_When_SpecialAttack_Then_TargetLoseTwiceWarriorStrength() {
        Mage target = new Mage(100,0,0,0);
        Warrior warrior = new Warrior(100, 50,0,0);
        assertEquals(500 ,target.getLife());
        warrior.specialAttack(target);
        assertEquals(500-100 ,target.getLife(),1);
    }

    @Test
    void Given_Warrior_When_SpecialAttack_Then_WarriorLoseHalfOfHisStrength() {
        Mage target = new Mage(100,0,0,0);
        Warrior warrior = new Warrior(100, 50,0,0);
        assertEquals( 50 ,warrior.getStrength(), 1);
        warrior.specialAttack(target);
        assertEquals( 25 ,warrior.getStrength(), 1);
        warrior.specialAttack(target);
        assertEquals( 12 ,warrior.getStrength(),1);
        warrior.specialAttack(target);
        assertEquals( 6 ,warrior.getStrength(),1);
    }

    @Test
    void Given_ProwlerLevel100_When_SpecialAttack_Then_TargetDoNotLoseLifeAndProwlerWin50OfAgility() {
        Mage target = new Mage(100,0,0,0);
        Prowler prowler = new Prowler(100, 0,50,0);
        assertEquals(500 ,target.getLife());
        prowler.specialAttack(target);
        assertEquals(500 ,target.getLife(),1); // The target should not lose Life
        assertEquals(50+(100/2) ,prowler.getAgility(),1); // Agility += Level/2
    }


    @Test
    void Given_Mage_When_SpecialAttack_Then_MageWin100OfLife() {
        Warrior target = new Warrior(100,100,0,0);
        Mage mage = new Mage(100, 0,0,50);
        assertEquals(500 ,mage.getLife());
        target.basicAttack(mage);
        assertEquals(400 ,mage.getLife(),1);
        mage.specialAttack(target);
        assertEquals(500 ,target.getLife(),1); //Target do not lose life
        assertEquals(500 ,mage.getLife(),1);
    }

    @Test
    void Given_Mage_When_SpecialAttack_Then_MageWin100OfLifeAnd0OfLife() {
        Warrior target = new Warrior(100,100,0,0);
        Mage mage = new Mage(100, 0,0,50);
        assertEquals(500 ,mage.getLife());
        target.basicAttack(mage);
        assertEquals(400 ,mage.getLife(),1);
        mage.specialAttack(target);
        assertEquals(500 ,mage.getLife(),1);
        mage.specialAttack(target);
        assertEquals(500 ,mage.getLife(),1);
    }
}

