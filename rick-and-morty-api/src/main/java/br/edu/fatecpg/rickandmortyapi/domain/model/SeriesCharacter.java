package br.edu.fatecpg.rickandmortyapi.domain.model;

public record SeriesCharacter(
    int id,
    String name,
    String status,
    String species,
    String gender
) {}
