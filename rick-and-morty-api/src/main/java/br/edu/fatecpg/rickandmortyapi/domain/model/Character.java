package br.edu.fatecpg.rickandmortyapi.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Character(
    int id,
    @JsonProperty("name") String nome,
    String status,
    @JsonProperty("species") String especie,
    @JsonProperty("gender") String genero
) {}
