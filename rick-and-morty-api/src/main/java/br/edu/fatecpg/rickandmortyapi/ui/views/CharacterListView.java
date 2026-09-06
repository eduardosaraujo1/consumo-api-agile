package br.edu.fatecpg.rickandmortyapi.ui.views;

import br.edu.fatecpg.rickandmortyapi.data.CharacterRepository;
import br.edu.fatecpg.rickandmortyapi.domain.exceptions.CharacterListUnreachableException;
import br.edu.fatecpg.rickandmortyapi.domain.exceptions.InvalidCharacterListResponseException;
import br.edu.fatecpg.rickandmortyapi.domain.model.Character;
import br.edu.fatecpg.rickandmortyapi.infrastructure.tui.Ansi;
import br.edu.fatecpg.rickandmortyapi.infrastructure.tui.Console;
import br.edu.fatecpg.rickandmortyapi.infrastructure.tui.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CharacterListView implements View {

    private CharacterRepository repo;
    private Scanner sc;

    public CharacterListView() {
        repo = new CharacterRepository();
        sc = Console.scanner();
    }

    @Override
    public void loop() {
        // Display Parameters
        final int ROWS_PER_PAGE = 20;

        // State
        int currentPage = 1;
        List<Character> currentList = null;
        int cachedPage = -1;
        char option = '-';

        do {
            Console.clear();

            System.out.println(
                "2. Lista de Personagens --- Página " + currentPage
            );
            System.out.println(
                Ansi.colorize("Carregando...", Ansi.Background.CYAN)
            );

            try {
                if (currentList == null || cachedPage != currentPage) {
                    currentList = repo.listCharacters(
                        ROWS_PER_PAGE,
                        ROWS_PER_PAGE * (currentPage - 1)
                    );
                    cachedPage = currentPage;
                }
            } catch (
                CharacterListUnreachableException
                | InvalidCharacterListResponseException e
            ) {
                // It is possible we have just reached the end of the list and no error occurred
                // (I did not realize I'd need  registryCount to validate if the next fetch operation.
                // I'm not changing the repository I've spent enough time on that)
                // Let's set the list to empty, mark the last cached page as "none" and allow the pagination overflow.
                System.out.println(
                    Ansi.colorize(
                        "Não foi possível carregar a lista. O resultado será vazio.",
                        Ansi.Foreground.RED
                    )
                );
                Console.pause("[Enter] Ok");
                currentList = new ArrayList<Character>();
                cachedPage = -1;
            }

            if (currentList.isEmpty()) {
                System.out.println("Nenhum personagem encontrado.");
            } else {
                for (Character c : currentList) {
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
                    if (currentPage > 1) {
                        --currentPage;
                    } else {
                        System.out.println("Você já está na primeira página");
                        Console.pause("[ Enter ] OK");
                    }
                }
                case 'n' -> {
                    // Here is where we would check if ROW_PER_PAGE * page is greater than count.
                    // Since that piece of information is not available yet, just ignore it.
                    // TODO: maybe add a sprint to fix this
                    currentPage++;
                }
                default -> {
                    System.out.println("Opção inválida. Tente novamente.");
                    Console.pause("[ Enter ] OK");
                }
            }
        } while (option != 'q');
    }
}
