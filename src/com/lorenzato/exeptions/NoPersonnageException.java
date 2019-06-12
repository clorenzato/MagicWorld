package com.lorenzato.exeptions;

public class NoPersonnageException extends RuntimeException {
    public NoPersonnageException() {
        System.err.println("Erreur Fatale : La Liste des joueurs est vide. Veuillez appeler la methode addPlayersInArena avant de lancer le combat.");
    }
}
