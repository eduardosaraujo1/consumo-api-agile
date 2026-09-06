package br.edu.fatecpg.rickandmortyapi.domain.exceptions;

public class InvalidCharacterListResponseException
    extends CharacterListException
{

    public InvalidCharacterListResponseException() {
        super();
    }

    public InvalidCharacterListResponseException(String message) {
        super(message);
    }

    public InvalidCharacterListResponseException(
        String message,
        Throwable cause
    ) {
        super(message, cause);
    }
}
