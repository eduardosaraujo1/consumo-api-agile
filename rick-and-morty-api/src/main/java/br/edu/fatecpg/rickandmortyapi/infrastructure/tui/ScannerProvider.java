package br.edu.fatecpg.rickandmortyapi.infrastructure.tui;

import java.util.Scanner;

public class ScannerProvider {

    private static Scanner _s;

    private ScannerProvider() {}

    public static void setScanner(Scanner s) {
        _s = s;
    }

    public static Scanner scanner() {
        if (_s == null) {
            throw new RuntimeException("Scanner was never initialized.");
        }

        return _s;
    }
}
