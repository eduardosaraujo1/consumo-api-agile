package br.edu.fatecpg.rickandmortyapi.ui.views;

import br.edu.fatecpg.rickandmortyapi.domain.model.SeriesCharacter;
import br.edu.fatecpg.rickandmortyapi.infrastructure.tui.Ansi;
import br.edu.fatecpg.rickandmortyapi.infrastructure.tui.Console;
import br.edu.fatecpg.rickandmortyapi.infrastructure.tui.View;
import br.edu.fatecpg.rickandmortyapi.ui.viewmodel.CharacterPaginationViewModel;
import java.util.List;
import java.util.Scanner;

public class CharacterQueryView implements View {

    private Scanner sc;
    private CharacterPaginationViewModel paginator;

    public CharacterQueryView() {
        sc = Console.scanner();
        paginator = new CharacterPaginationViewModel();
    }

    private void writeHeader() {
        System.out.println(
            Ansi.colorize(
                "3. Pesquisa de Personagens --- Página " + paginator.getPage(),
                Ansi.Foreground.CYAN
            )
        );
        System.out.println("Busca: '" + paginator.getQuery() + "'");
    }

    @Override
    public void loop() {
        char option = '-';

        do {
            Console.clear();

            // Step: define search query
            while (!paginator.hasQuery()) {
                System.out.println(
                    Ansi.colorize(
                        "3. Pesquisa de Personagens",
                        Ansi.Foreground.CYAN
                    )
                );
                System.out.print(
                    "Digite um termo de pesquisa (ou " +
                        Ansi.colorize("/q", Ansi.Foreground.GREEN) +
                        " para sair)\n> "
                );
                String input = sc.nextLine();
                if (input.equals("/q")) return;
                String sanitized = input
                    .substring(0, Math.min(input.length(), 64))
                    .trim();
                paginator.setQuery(sanitized);
            }

            // Step: Wait for data to be available
            Console.clear();
            writeHeader();
            System.out.println(
                Ansi.colorize("Carregando...", Ansi.Background.CYAN)
            );

            List<SeriesCharacter> currentList = paginator.getCharacterList();

            // Step: display data
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

            // Step: Wait for user input
            System.out.print(
                """
                [%s] Próximo [%s] Anterior [%s] Alterar Pesquisa [%s] Sair
                >\s """.formatted(
                    Ansi.colorize("N", Ansi.Foreground.GREEN),
                    Ansi.colorize("P", Ansi.Foreground.GREEN),
                    Ansi.colorize("C", Ansi.Foreground.CYAN),
                    Ansi.colorize("Q", Ansi.Foreground.RED)
                )
            );
            option = sc.nextLine().toLowerCase().charAt(0);

            switch (option) {
                case 'q' -> System.out.println("Saindo...");
                case 'c' -> {
                    paginator.clearQuery();
                }
                case 'p' -> {
                    if (!paginator.previousPage()) {
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
                    Console.clear();
                    System.out.println("Opção inválida. Tente novamente.");
                    Console.pause("[ Enter ] OK");
                }
            }
        } while (option != 'q');
    }
}

// Testes:
// - Testar saida com /q
// - Pesquisar com um nome normal
// - Pesquisar sem digitar nada
// - Pesquisar com espaços em branco
// - Pesquisar sem resultados
// - Testar Previous na primeira página
// - Testar Next algumas vezes
