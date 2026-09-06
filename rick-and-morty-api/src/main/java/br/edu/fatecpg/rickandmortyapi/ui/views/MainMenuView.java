package br.edu.fatecpg.rickandmortyapi.ui.views;

import br.edu.fatecpg.rickandmortyapi.infrastructure.tui.Ansi;
import br.edu.fatecpg.rickandmortyapi.infrastructure.tui.Console;
import br.edu.fatecpg.rickandmortyapi.infrastructure.tui.View;
import java.util.Scanner;

public class MainMenuView implements View {

    private Scanner sc;

    public MainMenuView() {
        sc = Console.scanner();
    }

    @Override
    public void loop() {
        int option = 0;

        do {
            Console.clear();

            // ========================
            // == Rick and Morty TUI ==
            // ========================
            System.out.println("========================");
            System.out.print("== ");
            System.out.print(Ansi.colorize("Rick ", Ansi.Foreground.CYAN));
            System.out.print("and ");
            System.out.print(Ansi.colorize("Morty ", Ansi.Foreground.GREEN));
            System.out.print("TUI");
            System.out.println(" ==");
            System.out.println("========================");

            System.out.print(
                """
                [%s] Consultar
                [%s] Listar
                [%s] Sair
                >\s """.formatted(
                    Ansi.colorize("1", Ansi.Foreground.GREEN),
                    Ansi.colorize("2", Ansi.Foreground.GREEN),
                    Ansi.colorize("3", Ansi.Foreground.GREEN)
                )
            );
            option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1 -> renderConsultarScreen();
                case 2 -> renderListarScreen();
                case 3 -> System.out.println("Saindo...");
                default -> {
                    System.out.println("Opção inválida. Tente novamente.");
                    Console.pause("[ Enter ] OK");
                }
            }
        } while (option != 3);
    }

    private void renderListarScreen() {
        new CharacterListView().loop();
    }

    private void renderConsultarScreen() {
        return;
    }
}
