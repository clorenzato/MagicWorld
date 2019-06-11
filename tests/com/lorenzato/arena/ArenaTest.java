package com.lorenzato.arena;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ArenaTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }


    @AfterEach
    void restoreStreams() {
        //System.setOut(System.out);
        //System.setErr(System.err);
    }


    @Test
    void Given_GoodEntries_When_SelectPersoClass_Then_ProposeClassinGoodOrder() {
        System.setIn(new ByteArrayInputStream(String.format("2%n100%n20%n20%n20%n").getBytes()));
        Arena arena = new Arena(1,100);
        arena.choosePersoClass(1);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("1 - Guerrier", output[11]);
        assertEquals("2 - Rôdeur", output[12]);
        assertEquals("3 - Mage", output[13]);
    }

    @Test
    void Given_BadEntries_When_SelectPersoClass_Then_CatchErrs() {
        System.setIn(new ByteArrayInputStream(String.format("a%n25%n2%n100%n20%n20%n20%n").getBytes()));
        Arena arena = new Arena(1,100);
        arena.choosePersoClass(1);
        String[] errput = errContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Veulliez saisir un chiffre, svp", errput[0]);
        assertEquals("Vous n'avez pas choisi de class parmi les choix proposés", errput[1]);
    }

    @Test
    void Given_GoodEntries_When_NoAtributesAvailable_Then_ProposeClassinGoodOrder() {
        System.setIn(new ByteArrayInputStream(String.format("20%n").getBytes()));
        Arena arena = new Arena(1,100);
        int valueAtribute = arena.chooseAttribute("Attribute");
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Plus de points d'attributs; Attribute = 0", output[8]);
        assertEquals(0, valueAtribute);
    }

    @Test
    void letsFighttest() {

    }
}
