import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void chooseOp(int op) throws Exception {
		UserInterface.printMenuOp(op-1);
		
		switch(op) {
			// ADICIONAR NOVO JOGO
			case 1: {
				Archive<Game> gameFile;

				gameFile = new Archive<>(Game.class.getConstructor(), "./db/game.db", "./db/index.db");
				gameFile.include(UserInterface.readGame());

				break;
			}

			// REMOVER JOGO
			case 2: {				
				Archive<Game> gameFile;
				int id = UserInterface.readID();

				gameFile = new Archive<>(Game.class.getConstructor(), "./db/game.db", "./db/index.db");
				if (gameFile.exclude(id))
					System.out.println("\n-> Jogo Removido!\n");					
				else
					System.out.println("\n-> ID Não Encontrado!\n");	

				break;
			}

			// EDITAR DADOS
			case 3: {
				Archive<Game> gameFile;
				int id = UserInterface.readID();

				gameFile = new Archive<>(Game.class.getConstructor(), "./db/game.db", "./db/index.db");
				if (gameFile.exclude(id)){
					gameFile.include(UserInterface.readGame());
					System.out.println("\n-> Jogo Alterado com Sucesso!\n");				
				}
				else
					System.out.println("\n-> ID Não Encontrado!\n");	

				break;
			}

			// BUSCAR JOGO
			case 4: {
				Archive<Game> gameFile;
				int id = UserInterface.readID();

				gameFile = new Archive<>(Game.class.getConstructor(), "./db/game.db", "./db/index.db");
				Game game = gameFile.read(id);
				if ( game != null ){
					System.out.println("\n-> Jogo Encontrado!\n");
					System.out.println(game.toString());
				}
				else
					System.out.println("\n-> ID Não Encontrado!\n");

				break;
			}

			// LISTAR JOGOS
			case 5: {
				Archive<Game> gameFile;

				gameFile = new Archive<>(Game.class.getConstructor(), "./db/game.db", "./db/index.db");
				
				System.out.println("");
				gameFile.list().forEach((game) -> 
					System.out.println(game.toString())
				);

				break;
			}

			// MOSTRAR METADADOS DO DB
			case 6: {
				Archive<Game> gameFile;
				gameFile = new Archive<>(Game.class.getConstructor(), "./db/game.db", "./db/index.db");

				RandomAccessFile f1 = new RandomAccessFile("./db/game.db", "rw");
				RandomAccessFile f2 = new RandomAccessFile("./db/index.db", "rw");

				System.out.println("Jogos cadastrados -> " + gameFile.list().size());

				System.out.println("Espaço em disco (game.db) -> " + f1.length() + " bytes");
				System.out.println("Espaço em disco (index.db) -> " + f2.length() + " bytes\n");

				break;
			}

			// FECHAR SISTEMA
			case 7: {
			    System.exit(0);
			}
		}
	}

	public static void main(String[] args) {
		int op = 0;
		Scanner in = new Scanner(System.in);

		do {
			UserInterface.printMainMenu();
			
			try {
				op = Integer.parseInt(in.nextLine());
				if(op >= 1 && op <= 7) {
					try {
						chooseOp(op);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
				else
					System.out.println("Opção inválida!");
			} catch (NumberFormatException e) {
				System.out.println("Opção inválida!");
			}
		} while (op != 0);
	}
}