package br.edu.fatecpg.rickandmortyapi.domain.exceptions;

public class InvalidCharacterListResponseException
    extends CharacterListException
{

    public int statusCode;

    public InvalidCharacterListResponseException(int statusCode) {
        super();
        this.statusCode = statusCode;
    }

    public InvalidCharacterListResponseException(
        int statusCode,
        String message
    ) {
        super(message);
        this.statusCode = statusCode;
    }

    public InvalidCharacterListResponseException(
        int statusCode,
        String message,
        Throwable cause
    ) {
        super(message, cause);
        this.statusCode = statusCode;
    }
}
