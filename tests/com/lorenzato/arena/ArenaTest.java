package com.lorenzato.arena;

import com.lorenzato.exeptions.NoPersonnageException;

import com.lorenzato.personnage.Personage;
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
    void Given_BadEntries_When_SelectPersonageClass_Then_CatchErrMessages() {
        System.setIn(new ByteArrayInputStream(String.format("a%n25%n2%n100%n20%n20%n20%n").getBytes()));
        Arena arena = new Arena(1,100);
        arena.choosePersonageClass(1);
        String[] errOutput = errContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Veuillez saisir un nombre, svp", errOutput[0]);
        assertEquals("Vous n'avez pas choisi de class parmi les choix proposés", errOutput[1]);
    }

    @Test
    void Given_ChoseWarriorValueIs1_When_SelectPersonageClass_Then_GetWarriorClassInList() {
        System.setIn(new ByteArrayInputStream(String.format("1%n100%n20%n20%n30%n30%n").getBytes()));
        Arena arena = new Arena(1,100);
        arena.addPlayersInArena();
        Personage personage = arena.getPersonageList(1);
        String[] classOfPersonage = personage.getClass().toString().split("\\.");
        assertEquals("Warrior", classOfPersonage[classOfPersonage.length-1]);
    }

    @Test
    void Given_ChoseProwlerValueIs2_When_SelectPersonageClass_Then_GetProwlerClassInList() {
        System.setIn(new ByteArrayInputStream(String.format("2%n100%n20%n20%n30%n30%n").getBytes()));
        Arena arena = new Arena(1,100);
        arena.addPlayersInArena();
        Personage personage = arena.getPersonageList(1);
        String[] classOfPersonage = personage.getClass().toString().split("\\.");
        assertEquals("Prowler", classOfPersonage[classOfPersonage.length-1]);
    }
    @Test
    void Given_ChoseMageValueIs3_When_SelectPersonageClass_Then_GetMageClassInList() {
        System.setIn(new ByteArrayInputStream(String.format("3%n100%n20%n20%n30%n30%n").getBytes()));
        Arena arena = new Arena(1,100);
        arena.addPlayersInArena();
        Personage personage = arena.getPersonageList(1);
        String[] classOfPersonage = personage.getClass().toString().split("\\.");
        assertEquals("Mage", classOfPersonage[classOfPersonage.length-1]);
    }

    @Test
    void Given_BadEntries_When_SelectPersonageLevel_Then_CatchErrMessages() {
        System.setIn(new ByteArrayInputStream(String.format("101%n0%n100%n").getBytes()));
        Arena arena = new Arena(1,100);
        int level = arena.choosePersonageLevel();
        String[] errOutput = errContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Veuillez choisir un niveau < 100", errOutput[0]);
        assertEquals("Veuillez choisir un niveau > 0", errOutput[1]);
        assertEquals(100, level);
    }


    @Test
    void Given_GoodEntries202010_When_ChooseAttributeWithAttributeAvailable50_Then_AttributesAre202010() {
        System.setIn(new ByteArrayInputStream(String.format("1%n50%n20%n20%n10%n").getBytes()));
        Arena arena = new Arena(1,100);
        arena.addPlayersInArena();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Brutus je suis le Guerrier du Joueur 1," +
                " de niveau 50. Je possède 250 de vitalité, 20 de force," +
                " 20 d'agilité et 10 d'intelligence !",
                output[18]);
    }

    @Test
    void Given_GoodEntries_When_ChooseAttributeWithNoAttributeAvailable_Then_AttributeIs0() {
        System.setIn(new ByteArrayInputStream(String.format("20%n").getBytes()));
        Arena arena = new Arena(1,100);
        int valueAttribute = arena.chooseAttribute("AttributeName");
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Plus de point d'attribut; AttributeName = 0", output[8]);
        assertEquals(0, valueAttribute);
    }

    @Test
    void Given_GoodEntries_When_IncreaseAttributeWithNoAttributeAvailable_Then_AttributeIsCurrentAttribute() {
        System.setIn(new ByteArrayInputStream(String.format("20%n").getBytes()));
        Arena arena = new Arena(1,100);
        int valueAttribute = arena.increaseAttribute("AttributeName", 15);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Plus de point d'attribut; AttributeName = 15", output[8]);
        assertEquals(15, valueAttribute);
    }

    @Test
    void Given_ALetterAndANumber_When_CallingChooseNumber_Then_ReceiveAnErrorMessageAndReturnTheGoodNumber() {
        System.setIn(new ByteArrayInputStream(String.format("a%n25%n").getBytes()));
        Arena arena = new Arena(1,100);
        int numberChosen = arena.chooseNumber();
        String[] errOutput = errContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Veuillez saisir un nombre, svp",errOutput[0]);
        assertEquals(25, numberChosen);
    }

    @Test
    void Given_NoPersonageSelected_When_CallingLetsFight_Then_ThrowNoPersonnageException(){
        Arena arena = new Arena(1,100);
        assertThrows(NoPersonnageException.class, arena::letsFight);
    }
}
