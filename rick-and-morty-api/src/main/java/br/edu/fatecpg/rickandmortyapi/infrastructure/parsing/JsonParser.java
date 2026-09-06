package br.edu.fatecpg.rickandmortyapi.infrastructure.parsing;

import tools.jackson.databind.ObjectMapper;

public class JsonParser {

    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> T parseString(String str, Class<T> clazz) {
        return mapper.readValue(str, clazz);
    }
}
