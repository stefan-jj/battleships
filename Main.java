import java.util.Scanner;

public class Main {
		
		
	public static void main(String[] args) throws InterruptedException {
		// user input
		Scanner input = new Scanner(System.in);
		int[] coord = new int[2];
		boolean win=false;
		boolean win1=false;
		boolean win2=false;
		
		// create player 1
		Player player1 = new Player();
		Player player2 = new Player();
		
		// create board
		Board board1 = new Board(5,5); // player 1 board
		Board board2 = new Board(5,5); // player 2 board
		System.out.printf("Welcome to battleships!%nWe are playing on a 5x5 board seen below%nThe game will continue until all ships have been sunk%n%n");
		board1.firstPrintBoard();
		System.out.println("Player 1");
		System.out.println("~~~~~~~~~~~~~~");
		System.out.println("Player 2");
		board2.firstPrintBoard();
		
		// create and add ships
		Ship patrol1 = new Ship(2);
		Ship patrol2 = new Ship(2);
		board1.placeShip(patrol1);
		board1.placeShip(patrol2);
		board2.placeShip(patrol1);
		board2.placeShip(patrol2);
				
		// pause for input
		System.out.printf("%nPress enter to start...");
		String x = input.nextLine();
		System.out.printf("%n3...%n");
		Thread.sleep(1000);
		System.out.printf("%n2...%n");
		Thread.sleep(1000);
		System.out.printf("%n1...%n");
		Thread.sleep(1000);
		System.out.printf("%nFight!!%n%n");
		Thread.sleep(1500);
		
		// start the game
		while (win==false){
		// player 1 move
		System.out.printf("%nPlayer 1's go%n");
		player1.playerGo(board1);
		win1 = board1.checkWin();
		if (win1==true){
			System.out.printf("%nPlayer 1 wins battleships!!%n%n");
			win = true;
			return;
		}
		// player 2 move
		System.out.printf("%nPlayer 2's go%n");
		player2.playerGo(board2);
		win2 = board2.checkWin();
		if (win2==true){
			System.out.printf("%nPlayer 2 wins battleships!!%n%n");
			win = true;
			return;
		}
		}
		
		// testing
//		board1.printBoardShips();
//		board2.printBoardShips();
//		System.out.println(board1.rw.size());
//		System.out.println(board1.rw);
//		System.out.println(board1.cl);
		
//		board.playerMove(1,0);
//		board.playerMove(1,3);
//		board.playerMove(0,2);
		
		//board.printBoardShips();
		//board.printBoard();
	
	}

}
