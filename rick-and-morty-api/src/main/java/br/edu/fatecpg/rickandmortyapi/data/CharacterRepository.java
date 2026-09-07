package br.edu.fatecpg.rickandmortyapi.data;

import br.edu.fatecpg.rickandmortyapi.data.dto.CharacterListResponse;
import br.edu.fatecpg.rickandmortyapi.domain.exceptions.CharacterListUnreachableException;
import br.edu.fatecpg.rickandmortyapi.domain.exceptions.InvalidCharacterListResponseException;
import br.edu.fatecpg.rickandmortyapi.domain.model.SeriesCharacter;
import br.edu.fatecpg.rickandmortyapi.infrastructure.api.ApiClient;
import br.edu.fatecpg.rickandmortyapi.infrastructure.parsing.JsonParser;
import java.io.IOException;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CharacterRepository {

    private HttpResponse<String> hitCharacterListEndpoint(
        String endpoint,
        int page,
        String nameQuery
    )
        throws InvalidCharacterListResponseException, CharacterListUnreachableException {
        if (page < 1) {
            throw new IllegalArgumentException(
                "Page number must be greater than 0."
            );
        }

        String actualEndpoint = endpoint + "?page=" + page;

        if (nameQuery != null && !nameQuery.isBlank()) {
            actualEndpoint +=
                "&name=" + URLEncoder.encode(nameQuery, StandardCharsets.UTF_8);
        }

        try {
            HttpResponse<String> response = ApiClient.get(actualEndpoint);

            if (response.statusCode() < 200 || response.statusCode() > 299) {
                throw new InvalidCharacterListResponseException(
                    response.statusCode(),
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

    public CharacterListResponse listCharacters(
        Integer limit,
        Integer offset,
        String nameQuery
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
            return new CharacterListResponse(
                new ArrayList<SeriesCharacter>(),
                0
            );
        }

        // Translate the limit..offset format into the API's hardcoded page size approach
        int firstPage = actualOffset / API_PAGE_SIZE + 1;
        int lastRecord = actualOffset + actualLimit;
        int lastPage = (lastRecord - 1) / API_PAGE_SIZE + 1;

        // Populate the initial array with every entry from the API response.
        List<SeriesCharacter> characters = new ArrayList<SeriesCharacter>();
        int characterCount = 0;

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

            characterCount = pageResult.info().count();
            characters.addAll(pageResult.results());
        }

        // Trim the result by removing the entries not encompassed by the limit..offset parameters
        int offsetWithinResult = actualOffset % API_PAGE_SIZE;

        int from = offsetWithinResult;
        int to = Math.min(from + actualLimit, characters.size());

        return new CharacterListResponse(
            characters.subList(from, to),
            characterCount
        );
    }

    public CharacterListResponse listCharacters(int limit, int offset)
        throws CharacterListUnreachableException, InvalidCharacterListResponseException {
        return listCharacters(limit, offset, null);
    }

    public CharacterListResponse listCharacters()
        throws CharacterListUnreachableException, InvalidCharacterListResponseException {
        return listCharacters(20, 0, null);
    }
}

record ApiResultDto(List<SeriesCharacter> results, RequestInfoDto info) {}

record RequestInfoDto(int count) {}
