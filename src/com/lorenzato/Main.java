package com.lorenzato;

import com.lorenzato.arena.Arena;
import com.lorenzato.personnage.Personage;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Arena arena = new Arena(2,100);
        arena.addPlayerInArena();
    }
}
