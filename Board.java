import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Board {

	// variables
	final int rows;
	final int cols;
//	public int count = 0;
	
	String [] [] board; // real time board
	String [] [] boardShips; // board with ship location before playing
	
	ArrayList<Integer> rw = new ArrayList<Integer>();
	ArrayList<Integer> cl = new ArrayList<Integer>();
	
	// constructor
	public Board(int rows, int cols) {
		this.board = new String [rows] [cols];
		this.rows=rows;
		this.cols=cols;
		this.boardShips = new String [rows] [cols];
		setBoard();
		setPositions();
		
	}
	
	
	
	// Methods
	
	// check in win
	public boolean checkWin(){
		int ships = 0;
		int hits = 0;
		for (int r=0; r<boardShips.length; r++){
			for (int c=0; c<boardShips.length; c++) {
				if (boardShips[r][c]=="s"){
					ships++;
				}
				if (boardShips[r][c]=="s" && board[r][c]=="1"){
					hits++;
				}
			}
		}
		if (hits==ships){
			return true;
		}
		else {
			return false;
		}
	}
	
	// set reference board
	public void setBoard() {
		for (int r=0; r<boardShips.length; r++){
			for (int c=0; c<boardShips.length; c++) {
				boardShips[r][c]="-";
			}
		}
	}
	
	// set arrays of valid ship position values
	public void setPositions(){
		int count=0;
		for (int r=0; r<boardShips.length; r++){
			for (int c=0; c<boardShips.length; c++) {
				count++;
				rw.add(r);
				cl.add(c);
			}
		}
//		System.out.println(rw);
//		System.out.println(cl);
	}
	
	// add ship to board, start with 1x1 ship
	public void addShip(int row, int col, Ship ship) { // try catch if ship out of bounds?
		int count = 0;
		
//		if (boardShips[row][col]=="s"){
//			placeShip(ship);
//		}
		for (int i=1; i<=ship.getSize(); i++) {
			if (count==0) {
				boardShips[row][col] = "s";
				count++;
			}
			else { // add extra ship piece
//				int[] arr = extraPiece(row,col);
//				int r=arr[0];
//				int c=arr[1];
				boolean place = true;
				
				while (place==true){
					int[] arr = extraPiece(row,col);
					int r=arr[0];
					int c=arr[1];
					
					if (boardShips[r][c]=="s") {
						continue;
					}
					else{
						boardShips[r][c] = "s";
						place=false;
						removeCoord(r,c);
					}
				}
			}
		}
		//setDefaultShips(row,col);
	}
	
	public int[] extraPiece(int row, int col){
		int[] arr = new int[2];
		Random r = new Random();
		int idx = r.nextInt(1 - 0 + 1) + 0; // nextInt(max - min +1) + min;
		
		if (idx==0){
		// row placement
		int row2 = row+1;
		if (row2>rows-1){
			row2 = row-1;
		}
		// check if s already there
		
		arr[0]=row2;
		arr[1]=col;
		return arr;
		//boardShips[row2][col] = "s";
		// remove this coordinate from arraylist
		//removeCoord(row2, col);
		}
		
		else if (idx==1){
		// col placement
		int col2 = col+1;
		if (col2>cols-1){
			col2 = col-1;
		}
		// check if s already there
		
		arr[0]=row;
		arr[1]=col2;
		return arr;
		//boardShips[row][col2] = "s";
		// remove this coordinate from arraylist
		//removeCoord(row, col2);
		}
		return arr;
	}
	
	public void removeCoord(int row, int col){
		for (int r=0; r<rw.size()-1; r++){
				if(rw.get(r)==row && cl.get(r)==col){
					rw.remove(r);
					cl.remove(r);
					return;
				}
		}
	}
	
	public void placeShip(Ship ship){
		//int pieces = ship.getSize();
		int ind = rw.size()-1;
		Random rand = new Random();
		int idx = rand.nextInt(ind - 0 + 1) + 0; // nextInt(max - min +1) + min;
		
		int x = rw.get(idx);
		int y = cl.get(idx);
//		addShip(x,y,ship);
//		boardShips[x][y] = "s";
		rw.remove(idx);
		cl.remove(idx);
		addShip(x,y,ship);
		// next chain to another method to place second ship piece.
	}
	
	public void setDefaultShips(int row, int col){ // not needed?
		boardShips[row][col] = "s";
	}
	
	public void firstPrintBoard() {
		// fill grid, print blocks
		//System.out.printf("%nInitial Board%n%n");
			for (int r=0; r<board.length; r++){
				for (int c=0; c<board.length; c++) {
					board[r][c] = "-";
					//System.out.print(" " + board[r][c] + " ");
					System.out.print(" - ");
				}
				System.out.println();
			}
	}
	
	// Player inputs coordinates, adding a character to the grid array. This then chains to
	// updateBoard to display if anything was hit
	public void playerMove(int row, int col) throws InterruptedException { 
		board[row][col] = "1";
		updateBoard(row,col);
	}
	
	public void updateBoard(int x, int y) throws InterruptedException { // need previous board to compare to
		System.out.printf("%nBoard Updated...%n%n");
			for (int r=0; r<board.length; r++){
				for (int c=0; c<board.length; c++) {
					if (board[r][c] == "-") {
						System.out.print(" - ");
					}
					else if (board[r][c] == "s") {
						System.out.print(" - ");
					}
					else if (board[r][c] == "1") { // need to account for player picking same position 
						if (boardShips[r][c] == "s"){
							System.out.print(" o ");
						}
						else if (boardShips[r][c] == "-"){
							System.out.print(" x ");
						}
					}
				}
				System.out.println();
			}
			if (boardShips[x][y] == "s"){
				System.out.printf("%nYou got a hit!%n%n");
			}
			else if (boardShips[x][y] == "-"){
				System.out.printf("%nThat's a miss%n%n");
			}
			Thread.sleep(1500);
			//printBoardShips();
	}
	
	public void currentBoard(){
		System.out.printf("%nCurrent Board...%n%n");
		for (int r=0; r<board.length; r++){
			for (int c=0; c<board.length; c++) {
				if (board[r][c] == "-") {
					System.out.print(" - ");
				}
				else if (board[r][c] == "s") {
					System.out.print(" - ");
				}
				else if (board[r][c] == "1") { // need to account for player picking same position 
					if (boardShips[r][c] == "s"){
						System.out.print(" o ");
					}
					else if (boardShips[r][c] == "-"){
						System.out.print(" x ");
					}
				}
			}
			System.out.println();
		}
	}
	
	// test methods to see boards
	public void printBoardShips(){
		System.out.printf("%nSaved Board%n%n");
		for (int r=0; r<boardShips.length; r++){
			for (int c=0; c<boardShips.length; c++) {
				System.out.print(" " + boardShips[r][c] + " ");
			}
			System.out.println();
		}
		
	}
	
	public void printBoard(){
		System.out.printf("%nPlaying Board%n%n");
		for (int r=0; r<board.length; r++){
			for (int c=0; c<board.length; c++) {
				System.out.print(" " + board[r][c] + " ");
			}
			System.out.println();
		}
		
	}
	
	
	
	
	
	public static void main(String[] args) {

		
	}

}
