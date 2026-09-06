package br.edu.fatecpg.rickandmortyapi.domain.exceptions;

public class CharacterListUnreachableException extends CharacterListException {

    public CharacterListUnreachableException() {
        super();
    }

    public CharacterListUnreachableException(String message) {
        super(message);
    }

    public CharacterListUnreachableException(String message, Throwable cause) {
        super(message, cause);
    }
}
