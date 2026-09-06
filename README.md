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
  - Exemplo de infraestrutura para dados: interação com uma API Rest pode ser feita usando uma classe auxiliar (ApiClient)
  - Exemplo de infraestrutura para Ui: uma classe para definir a cor de um texto mediante sua escrita (ConsoleWrapper)
  - Exemplo de infraestrutura geral: _logs_ podem ser utilizados em qualquer camada da aplicação (Logger) e geralmente não são uma funcionalidade de domínio

# Agile: Definição de backlog

Para finalidade de praticar Gestão Ágil antes da matéria constar em meu currículo da facultade, decidi utilizar esse projeto como oportunidade para escrever um roadmap/backlog no formato comum de metodologias ágeis (epic -> feature -> story -> task), obtendo assim um pouco de experiência neste quesito.

Em seguida, utilizei uma ferramenta de IA (ChatGPT) para me trazer um feedback e, por fim, essa mesma ferramenta reescrever o backlog, a fim de aprender por meio de exemplos e não gerar _burnout_ ao reescrever este mesmo backlog por muitas horas.

<details>

<summary>Definição de backlog original</summary>

## Epic: Iniciação de ambiente

### Feature: Iniciação do projeto java

**Story: Como desenvolvedor, espero ter um projeto java para um projeto, a fim de ter onde implementar os requisitos de negócio.**

- [ ] Task: inicializar projeto Java e instalar dependências

### Feature: Implementação de auxiliares para requisições em REST

**Story: Como desenvolvedor, espero ter uma abstração para consumo de API Rest pelo método Get, a fim de implementar a camada de dados de forma mais fácil.**

- [ ] Task: estruturar ApiClient com interface prática
- [ ] Task: implementar método HttpResponse get(String endpoint)

**Story: Como desenvolvedor, espero ter uma abstração para deserialização de uma string JSON em qualquer classe implementada, a fim de implementar a camada de dados de forma mais fácil.**

- [ ] Task: adicionar Jackson como dependência Maven
- [ ] Task: estruturar JsonParser com interface prática
- [ ] Task: implementar método genérico parseObject para serialização

(dúvida sobre agile: muitas vezes o desenvolvedor na hora percebe que existe uma task faltando, ou uma task desnecessária, ou que uma API poderia ser melhor feita. Isso é comentado na Daily ou o dev pode resolver na hora? Por exemplo, nesse caso a task de incluir Jackson só foi pensada de ultima hora. Se o dev começasse a fazer o projeto e percebesse "preciso de Jackson pois isso foi falado durante a daily" ele pode colocar, ou precisa esperar até a próxima daily para adicionar uma task e só depois resolver o problema.)
(resposta: o backlog não precisa ser tão específico. O desenvolvedor pode fazer alterações na hora e anotar essa mudança para ser abordada na próxima daily).

### Feature: Implementação de logging

**Story: Como desenvolvedor, espero ter uma abstração para escrita de logs, a fim de cumprir com as práticas de logging estabelecidas pelo PO.**

- [ ] Task: estruturar Logger com contratos necessários
- [ ] Task: implementar método para operação _append_ em um arquivo arbitrário
- [ ] Task: implementar método para escrita de um log com texto passado como parâmetro

**Story: Como operador do sistema, espero ter timestamps e timezone em cada log, a fim de ter mais informações para debugging.**

- [ ] Task: implementar especificação de timestamp e timezone nos logs
- [ ] Task: modificar método de escrita de log para incluir timestamp como prefixo

### Feature: Definição de modelos de domínio

**Story: Como desenvolvedor, espero ter uma classe de domínio representativa de personagens a fim de manipular os dados da API de forma fácil e segura**

- [ ] Task: criar _record_ `Character` com campos especificados no documento de domínio

## Epic: Gestão de Personagens

### Feature: Listar personagens

**Story: Como desenvolvedor da camada UI, espero ter um método que me retorne uma lista de personagens, com suporte a paginação, a fim de exibi-los para o usuário**

- [ ] Task: Definir interface `CharacterRepository` para incluir método de obter lista de personagens, suportando paginação em seu método.
- [ ] Task: Implementar classe `CharacterRepositoryImpl` para interagir com a API e retornar a lista especificada em sua interface.

**Story: Como usuário, espero ter uma interface para visualizar uma lista de todas as entidades disponíveis, utilizando paginação, a fim de ver um panorama de personagens para conhecer a série.**

- [ ] Task: Implementar View para exibir personagens a partir de uma lista
- [ ] Task: Implementar navegação de páginas na View de personagens
- [ ] Task: Implementar definição de título da View para suportar casos de uso genéricos de exibição de personagens

### Feature: Consultar personagens

**Story: como desenvolvedor da camada UI, espero ter um método para consultar um personagem a partir do nome, a fim de fornecer essa funcionalidade para o usuário.**

- [ ] Task: Adicionar contrato na interface `CharacterRepository` para pesquisar uma lista de personagens pelo nome
- [ ] Task: Adicionar funcionalidade na classe `CharacterRepositoryImpl` para interagir com a API e retornar resultados da pesquisa como `List<Character>`.

**Story: Como usuário, espero ter uma interface para consultar personagens por nome a fim de localizar informações deste personagem.**

- [ ] Task: Implementar View para digitação de busca de nome
- [ ] Task: Reutilizar view de exibição de lista de personagens para visualizar resultados da busca

Dúvida Agile: é normal ser tão verboso? Ou fiz algo de errado? É um escopo minúsculo com o que seriam três requisitos funcionais: Listar personagens, Consultar personagem, Armazenar consulta em log. Só esses três itens viraram duas epics e várias Features/Stories

</details>

<details>

<summary>Feedbacks do ChatGPT</summary>

## 1. Primeiro: Epic, Feature, Story e Task não precisam formar uma árvore tão rígida

Epic: uma iniciativa grande demais para ser entregue em uma única Story.
Feature: uma capacidade relevante do produto, normalmente composta por várias Stories.
Story: uma unidade de valor que pode ser planejada, implementada e validada.
Task: trabalho necessário para implementar uma Story.

**Nem todo projeto precisa usar os quatro níveis.**

## 2. Seu maior problema: algumas "Stories" são, na realidade, tarefas técnicas/enablers

Stories que tem como usuário "Desenvolvedor" não são comuns, Stories são sempre "User stories" e estão voltadas para o usuário final.

Isso não significa que não devam estar no backlog. Devem, se forem trabalho necessário. O que eu mudaria é não tentar forçá-las a parecer user stories. Isso é definido pela flexibilidade da hierarquia:

```
Epic: Infraestrutura

  Task/Enabler: Inicializar projeto Java
  Task/Enabler: Implementar cliente HTTP
  Task/Enabler: Implementar desserialização JSON
  Task/Enabler: Implementar logging
```

Nem todo item do backlog precisa ser uma User Story.

## 3. Respondendo à sua pergunta "Se eu perceber que preciso de Jackson durante a implementação, preciso esperar a Daily?"

Não. De forma alguma.

A Daily não é uma reunião para autorizar o desenvolvedor a trabalhar. É uma reunião para sincronização, não autorização. Tasks podem mudar enquanto a Story é implementada, isso é absolutamente esperado

Você pode dizer:

> "Ontem comecei a implementação do parser e descobri que precisamos do Jackson. Adicionei a dependência e continuei."

Se essa descoberta tiver impacto significativo no escopo, arquitetura, prazo ou requisito, aí sim ela merece ser discutida com o time/PO.

## 4. Algumas de suas Tasks estão detalhadas demais

Evite ter tasks que ditam de modo imperativo como algo será implementado. Por exemplo, a task

`Task: modificar método de escrita de log para incluir timestamp como prefixo`

Pode ser simplesmente

> `Task: Incluir timestamp nos logs`

## 5. Eu também não criaria duas Stories para repository e UI nesse caso

Você atualmente tem:

```
Story 1:
Como desenvolvedor da camada UI...
→ repository

Story 2:
Como usuário...
→ interface
```

Isso é possível, mas acho que está artificialmente fragmentado.

A primeira Story existe basicamente para possibilitar a segunda.

Você poderia ter simplesmente:

```
Feature: Listar personagens

Story:
Como usuário, quero visualizar uma lista paginada de personagens,
a fim de explorar os personagens disponíveis.

Tasks:
- Definir CharacterRepository
- Implementar CharacterRepository
- Implementar View de personagens
- Implementar paginação
```

Agora existe uma única unidade de valor:

> usuário consegue visualizar personagens paginados.

E dentro dela você tem trabalho técnico.

Isso é uma distinção muito útil:

**Story:** O que precisa ser possível fazer?

**Task:** Que trabalho precisamos executar para tornar isso possível?

##

Sua hierarquia está maior que seu domínio porque você está tentando documentar tudo

Essa é provavelmente a principal resposta à sua última dúvida.

Você disse:

> "É um escopo minúsculo com três requisitos funcionais e isso virou duas Epics e várias Features/Stories."

Sim — e isso não significa necessariamente que você fez algo errado.

Você está misturando duas coisas:

**Requisitos do produto:**

```
1. Listar personagens
2. Consultar personagens
3. Registrar logs
```

**Trabalho necessário para construir o produto:**

```
Inicializar Java
Configurar Maven
Adicionar Jackson
Criar HTTP client
Criar parser
Criar logger
Criar Character
Criar repository
Criar UI
Implementar paginação
...
```

O segundo conjunto naturalmente será muito maior que o primeiro.

Mas isso não significa que cada pedaço do segundo conjunto precise virar uma Story.

Esse é o ponto que eu mudaria bastante no seu backlog.

</details>

<details>

<summary>Formato reescrito pelo ChatGPT</summary>

# Agile: Definição de backlog

## Epic: Infraestrutura técnica

### Enabler: Inicialização do projeto

**Objetivo:** Disponibilizar a estrutura básica do projeto Java para permitir a implementação das funcionalidades.

**Tasks iniciais:**

- Inicializar projeto Java
- Configurar Maven
- Configurar dependências iniciais

---

### Enabler: Cliente HTTP

**Objetivo:** Disponibilizar uma abstração para consumo dos endpoints REST utilizados pela aplicação.

**Tasks iniciais:**

- Definir contrato do cliente HTTP
- Implementar operação GET
- Adicionar tratamento necessário para respostas HTTP

---

### Enabler: Desserialização JSON

**Objetivo:** Disponibilizar uma abstração para converter respostas JSON em objetos utilizados pela aplicação.

**Tasks iniciais:**

- Avaliar biblioteca de desserialização
- Adicionar a biblioteca escolhida como dependência
- Definir contrato do parser
- Implementar desserialização genérica

---

## Epic: Observabilidade e manutenção

### Enabler: Logging

**Objetivo:** Disponibilizar uma abstração para facilitar escrita de _logs_ de qualquer camada da aplicação.

**Tasks iniciais:**

- Definir interface de logging
- Implementar escrita de arquivo log
- Adicionar timestamp aos registros

---

## Epic: Gestão de personagens

### Feature: Listagem de personagens

**Story:** Como usuário, quero visualizar uma lista de personagens, com suporte a paginação, a fim de explorar os personagens disponíveis.

**Tasks iniciais:**

- Definir contrato do `CharacterRepository`
- Implementar acesso à API de personagens
- Implementar modelo de domínio `Character`
- Implementar View de listagem
- Implementar navegação entre páginas

> As Tasks são um plano inicial de implementação. Novas Tasks podem ser adicionadas ou removidas conforme o desenvolvimento revelar novas necessidades.

---

### Feature: Consulta de personagens

**Story:** Como usuário, quero pesquisar personagens pelo nome, a fim de localizar informações sobre um personagem específico.

**Tasks iniciais:**

- Adicionar pesquisa por nome ao `CharacterRepository`
- Implementar campo de busca
- Reutilizar a View de listagem para exibir os resultados

## Observações sobre o backlog

Os itens acima não representam necessariamente uma sequência fixa de implementação.

Durante o desenvolvimento, o time pode:

- adicionar Tasks que não estavam previstas;
- remover Tasks que deixaram de ser necessárias;
- dividir uma Task em várias;
- combinar Tasks;
- alterar uma decisão de implementação;
- identificar a necessidade de um novo Enabler.

O backlog descreve principalmente **o trabalho e os resultados esperados**, e não cada ação que um desenvolvedor deverá executar.

As decisões sobre classes, métodos, bibliotecas, estrutura interna e demais detalhes de implementação devem permanecer flexíveis enquanto não houver uma razão para fixá-las.

</details>
