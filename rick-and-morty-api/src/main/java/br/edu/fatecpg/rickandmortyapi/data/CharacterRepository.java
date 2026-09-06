package br.edu.fatecpg.rickandmortyapi.data;

import br.edu.fatecpg.rickandmortyapi.domain.exceptions.CharacterListUnreachableException;
import br.edu.fatecpg.rickandmortyapi.domain.exceptions.InvalidCharacterListResponseException;
import br.edu.fatecpg.rickandmortyapi.domain.model.Character;
import br.edu.fatecpg.rickandmortyapi.infrastructure.api.ApiClient;
import br.edu.fatecpg.rickandmortyapi.infrastructure.parsing.JsonParser;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class CharacterRepository {

    private HttpResponse<String> hitCharacterListEndpoint(
        String endpoint,
        int page,
        String nameQuery
    )
        throws InvalidCharacterListResponseException, CharacterListUnreachableException {
        if (page < 1) page = 1;

        String actualEndpoint = endpoint + "?page=" + page;

        if (nameQuery != null && !nameQuery.isBlank()) {
            actualEndpoint += "&name=" + nameQuery;
        }

        try {
            HttpResponse<String> response = ApiClient.get(actualEndpoint);

            if (response.statusCode() < 200 || response.statusCode() > 299) {
                throw new InvalidCharacterListResponseException(
                    "Expected 2xx response from API. Received " +
                        response.statusCode() +
                        "."
                );
            }

            return response;
        } catch (IOException | InterruptedException e) {
            throw new CharacterListUnreachableException(e.getMessage(), e);
        }
    }

    public List<Character> listCharacters(
        String nameQuery,
        Integer limit,
        Integer offset
    )
        throws CharacterListUnreachableException, InvalidCharacterListResponseException {
        // Repository needs to transparently translate the `page=N` API into a proper `limit` offset.
        final int API_PAGE_SIZE = 20;

        // Input sanitization
        int actualOffset = offset != null ? offset : 0;
        int actualLimit = limit != null ? limit : API_PAGE_SIZE;

        if (actualOffset < 0) {
            throw new IllegalArgumentException(
                "Parameter offset cannot be negative"
            );
        }

        if (actualLimit < 0) {
            throw new IllegalArgumentException(
                "Parameter limit cannot be negative"
            );
        }

        if (actualLimit == 0) {
            return new ArrayList<Character>();
        }

        // Translate the limit..offset format into the API's hardcoded page size approach
        int firstPage = actualOffset / API_PAGE_SIZE + 1;
        int lastRecord = actualOffset + actualLimit;
        int lastPage = (lastRecord - 1) / API_PAGE_SIZE + 1;

        // Populate the initial array with every entry from the API response.
        List<Character> characters = new ArrayList<Character>();

        for (int page = firstPage; page <= lastPage; page++) {
            HttpResponse<String> response = hitCharacterListEndpoint(
                "https://rickandmortyapi.com/api/character",
                page,
                nameQuery
            );
            ApiResultDto pageResult = JsonParser.parseString(
                response.body(),
                ApiResultDto.class
            );

            characters.addAll(pageResult.results());
        }

        // Trim the result by removing the entries not encompassed by the limit..offset parameters
        int offsetWithinResult = actualOffset % API_PAGE_SIZE;

        int from = offsetWithinResult;
        int to = Math.min(from + actualLimit, characters.size());

        return characters.subList(from, to);
    }
}

record ApiResultDto(List<Character> results) {}
