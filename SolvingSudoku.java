import java.util.*;
public class SolvingSudoku {

	public static int[][] table = new int[9][9];

	public static void fillBoard()
	{
		Scanner in = new Scanner(System.in);
		for(int row = 0; row < table.length; row++)
		{
			for(int col = 0; col < table.length; col++)
			{
				System.out.print("Enter a number: ");
				table[row][col] = in.nextInt();
			}
		}
	}
	public static void printSudoBoard(int[][] table){

		for(int i = 0; i < table.length; i++){

			System.out.println();

			if(i % 3 == 0 && i != 0){

				System.out.println("-----------------------");

			}

			for(int j = 0; j < table[0].length; j++){

				if(j % 3 == 0 && j != 0){

					System.out.print("|| ");

				}
				System.out.print(table[i][j] + " ");

			}
		}
		System.out.println("\n");
	}

	public static int[] findEmpty(int[][] table){
		int[] emptyPos = new int[2];
		for(int i = 0; i < table.length; i++){
			for(int j = 0; j < table[0].length; j++){
				if(table[i][j] == 0){
					emptyPos[0] = i; //this will return the row index
					emptyPos[1] = j; //this will return the col index
					return emptyPos; //return an arr with the row/col index of the next 'open' spot
				}
			}
		}
		return null;
	}

	public static boolean valid(int[][] table, int[] pos, int val){
		int ROW = pos[0];
		int COL = pos[1];

		//This for loop checks for the rows
		for(int i = 0; i < table.length; i++){
			if(table[ROW][i] == val && COL != i){
				return false; 
			}
		}

		//This for loop checks for the column
		for(int j = 0; j < table[0].length; j++){
			if(table[j][COL] == val && ROW != j){
				return false; 
			}
		}

		//check the box (double check this)
		int box_x = COL / 3;  
		int box_y = ROW / 3;  
		for(int i = box_y*3; i < box_y*3+3; i++){
			for(int j = box_x*3; j < box_x*3+3; j++){
				if(table[i][j] == val && ROW != i && COL != j){
					return false;
				}
			}
		}
		return true;
	}

	public static boolean solve(int[][] table){

		int[] openSpot = findEmpty(table);
		int ROW, COL;
		//base case
		if(findEmpty(table) == null){
			return true; //this will only happen when we have solved the entire board
		}else{
			ROW = openSpot[0];
			COL = openSpot[1];
		}

		//here we solve the function 
		//this for loop checks the rows
		for(int i = 1; i <= 9; i++){ 
			if(valid(table, openSpot, i)){
				table[ROW][COL] = i;
				if(solve(table)){
					return true;
				}
				table[ROW][COL] = 0;
			}
		}

		return false; 
	}

	public static void main(String[] args){
		//This is the input for the sudoku board which we need to solve recursively using backtracking
		//declaring a 2D array  
		
		//prints the initial board
		System.out.print("This was the initial input");
		fillBoard();
		printSudoBoard(table);
		solve(table);
		//prints the final board
		System.out.print("This is the answer to the board");
		printSudoBoard(table);



	}
}