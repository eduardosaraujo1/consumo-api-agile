package br.edu.fatecpg.rickandmortyapi;

import br.edu.fatecpg.rickandmortyapi.infrastructure.tui.Ansi;
import br.edu.fatecpg.rickandmortyapi.infrastructure.tui.Console;
import java.util.Scanner;
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
        Console.setInternalScanner(new Scanner(System.in));

        System.out.println(
            Ansi.colorize("Hello, world!", Ansi.Foreground.YELLOW)
        );
    }
}
