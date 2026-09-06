package br.edu.fatecpg.rickandmortyapi.domain.model;

public record Character(
    int id,
    String name,
    String status,
    String species,
    String gender
) {}
