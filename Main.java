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
				Game game = UserInterface.readGame();
				if(game != null)
					gameFile.include(game, -1);
				else
					System.out.println("\n-> ID Categoria Não Encontrado!\n");	

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

			// EDITAR DADOS DE JOGO
			case 3: {
				Archive<Game> gameFile;
				int id = UserInterface.readID();

				gameFile = new Archive<>(Game.class.getConstructor(), "./db/game.db", "./db/index.db");

				// verifica se jogo existe
				if(gameFile.read(id) != null){
					Game game = UserInterface.readGame();
					// se categoria é valida
					if(game != null){
						gameFile.exclude(id);
						gameFile.include(game, id);
						System.out.println("\n-> Jogo Alterado com Sucesso!\n");				
					}
					else
						System.out.println("\n-> ID CATEGORIA Não Encontrado!\n");	
				}
				else
					System.out.println("\n-> ID Não Encontrado!\n");	

				break;
			}

			// BUSCAR JOGO
			case 4: {
				Archive<Game> gameFile;
				Archive<Category> categoryFile;
				int id = UserInterface.readID();

				gameFile = new Archive<>(Game.class.getConstructor(), "./db/game.db", "./db/index.db");
				categoryFile = new Archive<>(Category.class.getConstructor(), "./db/category.db", "./db/index_category.db");
				
				Category category = categoryFile.read(id);
				Game game = gameFile.read(id);

				if ( game != null ){
					System.out.println("\n-> Jogo Encontrado!\n");

					System.out.println(game.toString(category.name));
				}
				else
					System.out.println("\n-> ID Não Encontrado!\n");

				break;
			}

			// LISTAR JOGOS
			case 5: {
				Archive<Game> gameFile;
				Archive<Category> categoryFile;
				
				gameFile = new Archive<>(Game.class.getConstructor(), "./db/game.db", "./db/index.db");
				categoryFile = new Archive<>(Category.class.getConstructor(), "./db/category.db", "./db/index_category.db");

				System.out.println("");
				gameFile.list().forEach((game) -> {
					try {
				    	Category category = categoryFile.read(game.categoryID);
						System.out.println(game.toString(category.name));
				    } 
				    catch(Exception e) {
						e.printStackTrace();
					}
				});

				break;
			}

			// ADICIONAR NOVA CATEGORIA
			case 6: {
				Archive<Category> categoryFile;

				categoryFile = new Archive<>(Category.class.getConstructor(), "./db/category.db", "./db/index_category.db");
				categoryFile.include(UserInterface.readCategory(), -1);

				break;
			}

			// REMOVER CATEGORIA
			case 7: {	
				Archive<Game> gameFile;
				Archive<Category> categoryFile;
				int id = UserInterface.readID();

				gameFile = new Archive<>(Game.class.getConstructor(), "./db/game.db", "./db/index.db");
				categoryFile = new Archive<>(Category.class.getConstructor(), "./db/category.db", "./db/index_category.db");

				Category category = categoryFile.read(id);
				int cont = 0;
				if( category != null) {
					for(Game game: gameFile.list()){
						if(game.categoryID == id){
							cont++;
						}
					}
					if(cont>0)
						System.out.println("Não foi possível remover.\nHá " + cont + "jogos atribuídos a essa categoria");
					else{
						categoryFile.exclude(id);
						System.out.println("\n-> Categoria Removida!\n");
					}
				}
				else{
					System.out.println("\n-> Categoria Não Encontrada!\n");
				}

				fim:

				break;
			}

			// EDITAR CATEGORIA
			case 8: {
				Archive<Category> categoryFile;
				int id = UserInterface.readID();

				categoryFile = new Archive<>(Category.class.getConstructor(), "./db/category.db", "./db/index_category.db");
				if (categoryFile.exclude(id)){
					categoryFile.include(UserInterface.readCategory(), id);
					System.out.println("\n-> Categoria Alterada com Sucesso!\n");				
				}
				else
					System.out.println("\n-> ID Não Encontrado!\n");	

				break;
			}

			// BUSCAR CATEGORIA
			case 9: {
				Archive<Category> categoryFile;
				int id = UserInterface.readID();

				categoryFile = new Archive<>(Category.class.getConstructor(), "./db/category.db", "./db/index_category.db");
				Category category = categoryFile.read(id);
				if ( category != null ){
					System.out.println("\n-> Categoria Encontrada!\n");
					System.out.println(category.toString());
				}
				else
					System.out.println("\n-> ID Não Encontrado!\n");


				break;
			}

			// LISTAR CATEGORIA
			case 10: {
				Archive<Category> categoryFile;
				Archive<Game> gameFile;

				categoryFile = new Archive<>(Category.class.getConstructor(), "./db/category.db", "./db/index_category.db");
				gameFile = new Archive<>(Game.class.getConstructor(), "./db/game.db", "./db/index.db");
		
				for (Category category: categoryFile.list()) {
					System.out.print(category.toString());		
					System.out.println("Jogos: ");
					for(Game game: gameFile.list()){
						if (game.categoryID == category.id)
							System.out.println(game.name);	
					}
					System.out.println("");								
				}

				break;
			}

			
			// MOSTRAR METADADOS DO DB
			case 11: {
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
			case 12: {
			    System.exit(0);
			}
			default:{
				break;
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
				if(op >= 1 && op <= UserInterface.NUM_OPS) {
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
		} while (true);
	}
}