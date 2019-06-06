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

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    void Given_NbPlayer2levelMax100_When_Create_Then_TotalOfPlayerEqual2() {
        System.setIn(new ByteArrayInputStream("2\n50\n1\n50\n".getBytes()));
        Arena arena = new Arena(2,100);
        arena.addPlayerInArena();
        assertEquals(2,arena.getPersonages().size());
    }

    @Test
    public void Given_ThreePlayer_Mage_Prowler_Warrior_When_ArenaIsCreated_Then_DisplayCorrectClassOfPersonage() {
        System.setIn(new ByteArrayInputStream("3\n50\n2\n50\n1\n50\n".getBytes()));
        Arena arena = new Arena(3, 100);
        arena.addPlayerInArena();
        arena.getPersonages();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("class com.lorenzato.personnage.Mage", output[output.length-3]);
        assertEquals("class com.lorenzato.personnage.Prowler", output[output.length-2]);
        assertEquals("class com.lorenzato.personnage.Warrior", output[output.length-1]);
    }

}