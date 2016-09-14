import java.util.Scanner;


public class Player {

	// methods
//		public static int[] playerGo(int[] coord){
//			Scanner input = new Scanner(System.in);
//			System.out.printf("Enter row: ");
//			coord[0] = input.nextInt();
//			System.out.printf("Enter column: ");
//			coord[1] = input.nextInt();
//			return coord;
//		}
	
	public void playerGo(Board board) throws InterruptedException{
		Scanner input = new Scanner(System.in);
		board.currentBoard();
		System.out.printf("%nPlayer, select your coordinates");
		System.out.printf("%nEnter row: ");
		int x = input.nextInt();
		System.out.printf("Enter column: ");
		int y = input.nextInt();
		board.playerMove(x,y);
	}
	
	public static void main(String[] args) {
		
	}

}
