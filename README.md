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

# Definição de backlog

## Feature: Colocação de projeto em repositório próprio

**Story: Como desenvolvedor do software e PO, pretendo colocar este projeto em um repositório próprio a fim de apresentar minha habilidade atual na definição de tarefas na metodologia _Agile_**

## Epic: Iniciação de ambiente

### Feature: Iniciação do projeto java

**Story: Como desenvolvedor, espero ter um projeto java para um projeto, a fim de ter onde implementar os requisitos de negócio.**

- Task: inicializar projeto Java e instalar dependências

### Feature: Implementação de auxiliares para requisições em REST

**Story: Como desenvolvedor, espero ter uma abstração para consumo de API Rest pelo método Get, a fim de implementar a camada de dados de forma mais fácil.**

- Task: estruturar ApiClient com interface prática
- Task: implementar método HttpResponse get(String endpoint)

**Story: Como desenvolvedor, espero ter uma abstração para deserialização de uma string JSON em qualquer classe implementada, a fim de implementar a camada de dados de forma mais fácil.**

- Task: adicionar Jackson como dependência Maven
- Task: estruturar JsonParser com interface prática
- Task: implementar método genérico parseObject para serialização

(dúvida sobre agile: muitas vezes o desenvolvedor na hora percebe que existe uma task faltando, ou uma task desnecessária, ou que uma API poderia ser melhor feita. Isso é comentado na Daily ou o dev pode resolver na hora? Nesse caso a task de incluir Jackson só foi pensada de ultima hora)

### Feature: Implementação de logging

**Story: Como desenvolvedor, espero ter uma abstração para escrita de logs, a fim de cumprir com as práticas de logging estabelecidas pelo PO.**

- Task: estruturar Logger com contratos necessários
- Task: implementar método para operação _append_ em um arquivo arbitrário
- Task: implementar método para escrita de um log com texto passado como parâmetro

**Story: Como PO, espero ter timestamps em cada log, a fim de ter mais informações para debugging.**

- Task: implementar método privado para obtenção de timestamp
- Task: modificar método de escrita de log para incluir timestamp como prefixo

### Feature: Definição de modelos de domínio

**Story: Como desenvolvedor, espero ter uma classe de domínio representativa do...**

## Epic: Lista de Personagens

## Epic: Consulta de Personagens
