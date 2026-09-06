package br.edu.fatecpg.rickandmortyapi.domain.exceptions;

public class CharacterListException extends Exception {

    public CharacterListException() {
        super();
    }

    public CharacterListException(String message) {
        super(message);
    }

    public CharacterListException(String message, Throwable cause) {
        super(message, cause);
    }
}
