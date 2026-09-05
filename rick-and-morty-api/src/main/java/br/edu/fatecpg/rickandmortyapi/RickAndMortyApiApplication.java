package br.edu.fatecpg.rickandmortyapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RickAndMortyApiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RickAndMortyApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello, world!");
    }
}
