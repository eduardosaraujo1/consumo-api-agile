package br.edu.fatecpg.rickandmortyapi.domain.model;

import java.util.List;

public record CharacterList(
    List<Character> list,
    int limit,
    int offset,
    int totalCount
) {}
