package br.edu.fatecpg.rickandmortyapi.data.dto;

import br.edu.fatecpg.rickandmortyapi.domain.model.SeriesCharacter;
import java.util.List;

public record CharacterListResponse(
    List<SeriesCharacter> list,
    int characterCount
) {}
