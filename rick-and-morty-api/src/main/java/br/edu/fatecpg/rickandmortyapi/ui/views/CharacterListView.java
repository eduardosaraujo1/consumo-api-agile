package br.edu.fatecpg.rickandmortyapi.ui.views;

import br.edu.fatecpg.rickandmortyapi.domain.model.SeriesCharacter;
import br.edu.fatecpg.rickandmortyapi.infrastructure.tui.Ansi;
import br.edu.fatecpg.rickandmortyapi.infrastructure.tui.Console;
import br.edu.fatecpg.rickandmortyapi.infrastructure.tui.View;
import br.edu.fatecpg.rickandmortyapi.ui.viewmodel.CharacterPaginationViewModel;
import java.util.List;
import java.util.Scanner;

public class CharacterListView implements View {

    private CharacterPaginationViewModel paginator;
    private Scanner sc;

    public CharacterListView() {
        sc = Console.scanner();
        paginator = new CharacterPaginationViewModel();
    }

    private void writeHeader() {
        System.out.println(
            "2. Lista de Personagens --- Página %d de %d".formatted(
                paginator.getPage(),
                // TODO: arrume isso
                -1
            )
        );
    }

    @Override
    public void loop() {
        char option = '-';

        do {
            Console.clear();
            writeHeader();

            System.out.println(
                Ansi.colorize("Carregando...", Ansi.Background.CYAN)
            );

            List<SeriesCharacter> currentList = paginator.getCharacterList();

            if (paginator.hasFetchError()) {
                System.out.println(
                    Ansi.colorize(
                        "Não foi possível carregar a lista. O resultado será vazio.",
                        Ansi.Foreground.RED
                    )
                );
                Console.pause("[Enter] Ok");
            }

            Console.clear();
            writeHeader();

            if (currentList.isEmpty()) {
                System.out.println("Nenhum personagem encontrado.");
            } else {
                for (SeriesCharacter c : currentList) {
                    // TODO: display character in a prettiter format.
                    System.out.println(c);
                }
            }

            System.out.print(
                """
                [%s] Próximo [%s] Anterior [%s] Sair
                >\s """.formatted(
                    Ansi.colorize("N", Ansi.Foreground.GREEN),
                    Ansi.colorize("P", Ansi.Foreground.GREEN),
                    Ansi.colorize("Q", Ansi.Foreground.RED)
                )
            );
            option = sc.nextLine().toLowerCase().charAt(0);

            switch (option) {
                case 'q' -> System.out.println("Saindo...");
                case 'p' -> {
                    if (!paginator.previousPage()) {
                        Console.clear();
                        System.out.println("Você já está na primeira página");
                        Console.pause("[ Enter ] OK");
                    }
                }
                case 'n' -> {
                    // Here is where we would check if ROW_PER_PAGE * page is greater than count.
                    // Since that piece of information is not available yet, just ignore it.
                    // TODO: maybe add a sprint to fix this
                    if (!paginator.nextPage()) {
                        Console.clear();
                        System.out.println("Você já está na última página");
                        Console.pause("[ Enter ] OK");
                    }
                }
                default -> {
                    System.out.println("Opção inválida. Tente novamente.");
                    Console.pause("[ Enter ] OK");
                }
            }
        } while (option != 'q');
    }
}
