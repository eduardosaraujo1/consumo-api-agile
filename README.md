# Arquitetura Proposta: Camadas

- Ui: Possui Views e Controllers, faz a comunicação com o usuário
- Data: Interage com fontes de dados como APIs, 
- Domain: Possui fluxos de lógica avançados (regras de negócio) e certas classes de suporte (exceções)
- Infrastructure: Classes técnicas que servem como suporte para as ações de domínio que a aplicação deseja realizar.
    - Exemplo: interação com uma API Rest pode ser feita com uma classe auxiliar (infraestrutura para Data Layer)
    - Exemplo: _logs_ são utilizados pela aplicação, mas não fazem parte do seu domínio (infraestrutura geral)
    - Exemplo: uma classe para definir a cor de um texto mediante sua escrita (infraestrutura para UI)

# API Escolhida: Rick and Morty

TODO: escrever restante da introdução
https://rickandmortyapi.com/documentation#introduction
