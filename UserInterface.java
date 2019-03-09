import java.util.Scanner;

public class UserInterface {
	final static String MENU_OPS[] = {"-------------Adicionar novo jogo--------------", 
							   		  "-----------------Remover jogo-----------------",
							          "-----------------Editar Dados-----------------", 
							          "-----------------Buscar Jogo------------------", 
							          "-----------------Listar Jogos-----------------", 
							          "---------------Dados do Sistema---------------", 
							          "-------------------Até Logo!------------------"};

	public UserInterface(){}

	public static void printMainMenu(){
		System.out.print("------------------Arcade 101------------------" +
						 "\n1. Adicionar novo jogo" +
						 "\n2. Remover jogo" +
						 "\n3. Editar dados" +
						 "\n4. Buscar jogo" +
						 "\n5. Listar jogos" +	
						 "\n----------------------------------------------" +
						 "\n6. Dados do Sistema" +
						 "\n7. Fechar sistema" +
						 "\n----------------------------------------------" +
						 "\nInput: ");
	}

	public static void printMenuOp(int op){
		System.out.println(MENU_OPS[op]+"\n");
	}

	public static Game readGame(){
		Scanner in = new Scanner(System.in);
		Game game;
        String name = "", descr = "", company = "";
        float price = 0.0f, size = 0.0f;
        short year = 0;

		
		System.out.print("Nome: ");
		name = in.nextLine();
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
		game = new Game(name, descr, company, price, size, year);
		return game;
	}

	public static int readID(){
		Scanner in = new Scanner(System.in);
		System.out.print("Insira o ID: ");
		return in.nextInt();
	}

}