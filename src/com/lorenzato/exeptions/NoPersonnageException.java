package com.lorenzato.exeptions;

public class NoPersonnageException extends RuntimeException {
    public NoPersonnageException() {
        System.err.println(" Fatal Error : La Liste des joueurs est vide. Veuillez appeler la méthode addPlayersInArena avant de lancer le combat.");
    }
}
