# API Escolhida: Rick and Morty

A busca de uma API pública, que não pede chaves, e que retorne recursos compatíveis com uma modelagem de domínio me trouxe à [Rick and Morty API](https://rickandmortyapi.com/documentation#introduction).

Essa API permite busca direta de personagens por `id`, e por parâmetros como nome, status, espécie, tipo e gênero. Além disso, possui paginação para listar todos os personagens.

Nesse contexto, o domínio será centrado na classe (ou record) `Character`, com os atributos:

- `int id;`
- `String nome;`
- `String status;`
- `String tipo`
- `String genero`

# Arquitetura Proposta: Camadas

- Ui: Possui Views e Controllers, faz a comunicação com o usuário
- Data: Interage com fontes de dados, como APIs, bancos de dados, arquivos e afins.
- Domain: Possui fluxos de lógica avançados (regras de negócio), modelos de domínio (para aplicações mais avançadas que um CRUD) e exceções de domínio. Geralmente omitida em pequenos projetos.
- Infrastructure: Classes técnicas que servem como suporte para ações que a aplicação deseja realizar.
    - Exemplo de infraestrutura para dados: interação com uma API Rest pode ser feita com uma classe auxiliar (ApiClient)
    - Exemplo de infraestrutura: _logs_ podem ser utilizados em qualquer camada da aplicação (Logger)
    - Exemplo de infraestrutura para Ui: uma classe para definir a cor de um texto mediante sua escrita (ConsoleWrapper)
