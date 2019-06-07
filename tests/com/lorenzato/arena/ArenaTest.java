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
    void addPlayerInArena() {
        System.setIn(new ByteArrayInputStream(String.format("2%n100%n20%n20%n20%n").getBytes()));
        Arena arena = new Arena(1,100);
        arena.addPlayersInArena();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        // TODO: 07/06/19 Finish the Test!! 
        assertEquals("Vous devez saisir un nombre, correspondant au nombre de menus souhaités", output[15]);
    }
}