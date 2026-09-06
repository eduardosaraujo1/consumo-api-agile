package br.edu.fatecpg.rickandmortyapi.infrastructure.tui;

import java.util.Scanner;

public class Console {

    private static Scanner _s;

    private Console() {}

    public static void setInternalScanner(Scanner s) {
        _s = s;
    }

    public static Scanner scanner() {
        if (_s == null) {
            throw new RuntimeException("Scanner was never initialized.");
        }

        return _s;
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
