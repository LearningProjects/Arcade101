
# Arcade 101
## Por Raul Mansur e Lucas Campregher (PUC MINAS - AEDS III)

# Sistema de gestão de jogos eletrônicos
Este projeto é uma simulação de um sistema de gestão de jogos eletrônicos.
Ele implementa um banco de dados(SGBD), capaz de incluir, alterar, remover, buscar e listar.
O banco de dados é composto por um arquivo sequencial que guarda os registros com tamanho variável, e por um arquivo de índice implementado com árvore B+.


# Classes
* Main.java (Classe principal)
* UserInterface.java (Métodos de input/output para usuário)
* Archive.java (Manipulação de arquivo sequencial)
* Index.java (Manipulação do índice com árvore B+)
* FileObject.java (Interface para Objetos de Classe Game)
* Game.java (Produto manipulado pelo sistema)

### Campos:
* ID_Produto (int)
* Nome (String)
* Descrição (String)
* Tamanho (Float)
* Preço (Float)
* Tamanho (Float)
* Empresa ()
* Ano (Short)

Projeto foi feito com Java 10.0.1, implementado e testado no Windows 10 (64 bits).