package com.lorenzato.arena;

import com.lorenzato.exeptions.NoPersonnageException;

import org.junit.jupiter.api.*;
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
        System.setOut(System.out);
        System.setErr(System.err);
    }

    @Test
    void Given_GoodEntries_When_SelectPersonageClass_Then_ProposeClassInGoodOrder() {
        System.setIn(new ByteArrayInputStream(String.format("2%n100%n20%n20%n20%n").getBytes()));
        Arena arena = new Arena(1,100);
        arena.choosePersonageClass(1);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("1 - Guerrier", output[11]);
        assertEquals("2 - Rôdeur", output[12]);
        assertEquals("3 - Mage", output[13]);
    }

    @Test
    void Given_BadEntries_When_SelectPersonageClass_Then_CatchErrs() {
        System.setIn(new ByteArrayInputStream(String.format("a%n25%n2%n100%n20%n20%n20%n").getBytes()));
        Arena arena = new Arena(1,100);
        arena.choosePersonageClass(1);
        String[] errOutput = errContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Veuillez saisir un nombre, svp", errOutput[0]);
        assertEquals("Vous n'avez pas choisi de class parmi les choix proposés", errOutput[1]);
    }

    @Test
    void Given_GoodEntries_When_ChooseAttributeNoAttributeAvailable_Then_ProposeClassInGoodOrder() {
        System.setIn(new ByteArrayInputStream(String.format("20%n").getBytes()));
        Arena arena = new Arena(1,100);
        int valueAttribute = arena.chooseAttribute("AttributeName");
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Plus de point d'attribut; AttributeName = 0", output[8]);
        assertEquals(0, valueAttribute);
    }

    @Test
    void Given_ALetterAndANumber_When_CallingChooseNumber_Then_ReceiveAnErrorMessageReturnTheGoodNumber() {
        System.setIn(new ByteArrayInputStream(String.format("a%n25%n").getBytes()));
        Arena arena = new Arena(1,100);
        int numberChosen = arena.chooseNumber();
        String[] errOutput = errContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals(25,numberChosen);
        assertEquals("Veuillez saisir un nombre, svp",errOutput[0]);
    }

    @Test
    void Given_NoPersonageSelected_When_CallingLetsFight_Then_ThrowExceptionNoPersonnageException(){
        Arena arena = new Arena(1,100);
        assertThrows(NoPersonnageException.class, arena::letsFight);
    }
}
