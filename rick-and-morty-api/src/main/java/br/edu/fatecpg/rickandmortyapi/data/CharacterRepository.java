package br.edu.fatecpg.rickandmortyapi.data;

import br.edu.fatecpg.rickandmortyapi.domain.model.Character;
import java.util.List;

public class CharacterRepository {

    public List<Character> listCharacters(String nameQuery) {
        throw new RuntimeException();
    }

    public List<Character> listCharacters() {
        return listCharacters("");
    }
}
