import java.util.Scanner;

public class UserInterface {
	final static int NUM_OPS = 12;
	final static String MENU_OPS[] = {"-------------Adicionar novo jogo--------------", 
							   		  "-----------------Remover jogo-----------------",
							          "-------------Editar dados de jogo-------------", 
									  "-----------------Buscar Jogo------------------", 
									  "-----------------Listar Jogo------------------", 
									  "-----------Adicionar nova categoria-----------", 
							   		  "---------------Remover categoria--------------",
							          "---------------Editar categoria---------------", 
							          "---------------Buscar categoria---------------", 
							          "---------------Listar categorias--------------", 
							          "---------------Dados do Sistema---------------", 
							          "-------------------Até Logo!------------------"};

	public UserInterface(){}

	public static void printMainMenu(){
		System.out.print("_________________Arcade 101___________________\n" +
						 "\n----------------MENU DE JOGOS-----------------\n" +
						 "\n1.  Adicionar novo jogo" +
						 "\n2.  Remover jogo" +
						 "\n3.  Editar dados de jogo" +
						 "\n4.  Buscar jogo" +
						 "\n5.  Listar jogos\n" +	
						 "\n--------------MENU DE CATEGORIAS--------------\n" +
						 "\n6.  Adicionar nova categoria" +
						 "\n7.  Remover categoria" +
						 "\n8.  Editar categoria" +
						 "\n9.  Buscar categoria" +
						 "\n10. Listar categorias\n" +	
						 "\n---------------MENU DO SISTEMA----------------\n" +
						 "\n11. Dados do Sistema" +
						 "\n12. Fechar sistema\n" +
						 "\n----------------------------------------------" +
						 "\nInput: ");
	}

	public static void printMenuOp(int op){
		System.out.println(MENU_OPS[op]+"\n");
	}

	public static Game readGame() throws Exception{
        Archive<Category> categoryFile;
		Scanner in = new Scanner(System.in);
		Game game;
        String name = "", descr = "", company = "";
		int categoryID = 0;
		float price = 0.0f, size = 0.0f;
        short year = 0;
        int op;

        categoryFile = new Archive<>(Category.class.getConstructor(), "./db/category.db", "./db/index_category.db");

		System.out.print("Nome: ");
		name = in.nextLine();
		Category category;
		do{
			System.out.print("ID Categoria: ");
			categoryID = Integer.parseInt(in.nextLine());
			
			category = categoryFile.read(categoryID);
			if(category == null){
				System.out.println("\nID Inválido!");
				System.out.println("1.  Digitar novamente.");
				System.out.println("2.  Sair.\n");
				op = Integer.parseInt(in.nextLine());
				if(op != 1)
					return null;
			}
			else
				break;
		}while(true);

		System.out.println( "Categoria encontrada: " + category.name);

		System.out.print( "Descrição: ");
		descr = in.nextLine();
		System.out.print( "Empresa: ");
		company = in.nextLine();
		System.out.print( "Preço: ");
		price = in.nextFloat();
		System.out.print( "Tamanho em disco(GBs): ");
		size = in.nextFloat();
		System.out.print( "Ano de Lançamento: ");
		year = in.nextShort();
		System.out.println("");
		game = new Game(name, categoryID, descr, company, price, size, year);
		return game;
	}

	public static Category readCategory(){
		Scanner in = new Scanner(System.in);
		Category category;
        String name = "";
		
		System.out.print("Nome: ");
		name = in.nextLine();
		category = new Category(name);
		return category;
	}

	public static int readID(){
		Scanner in = new Scanner(System.in);
		System.out.print("Insira o ID: ");
		return in.nextInt();
	}
}