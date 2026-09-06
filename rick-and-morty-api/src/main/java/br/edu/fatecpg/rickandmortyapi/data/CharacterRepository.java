package br.edu.fatecpg.rickandmortyapi.data;

import br.edu.fatecpg.rickandmortyapi.domain.model.CharacterList;

public class CharacterRepository {

    public CharacterList listCharacters(
        String nameQuery,
        int limit,
        int offset
    ) {
        throw new RuntimeException();
    }

    public CharacterList listCharacters(String nameQuery, int limit) {
        return listCharacters(nameQuery, limit, 0);
    }

    public CharacterList listCharacters(String nameQuery) {
        return listCharacters(nameQuery, 0, 0);
    }

    public CharacterList listCharacters() {
        return listCharacters("");
    }
}
