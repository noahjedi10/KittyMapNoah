import java.util.Random;
import java.util.Scanner;
import static java.lang.System.*;

public class KittyMap {
	private boolean[][] kittyGrid;
	int rows, cols;

	public KittyMap(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		Random rand = new Random();
		kittyGrid = new boolean[rows][cols];
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				boolean toAdd = (int) (rand.nextDouble() + .5) == 1;
				kittyGrid[i][j] = toAdd;
			}
		}
	}

	public boolean[][] getKittyGridLol() {
		return kittyGrid;
	}

	public void printKittyCount() {
		int sum = 0;
		for(boolean[] row: kittyGrid) {
			for(boolean v: row) {
				if(v) {sum ++;}
			}
		}
		System.out.println(sum);
	}
	
	private boolean areCoordsInBounds(int x, int y) {
	
		return (x >= 0) && (y >= 0) && (x < rows) && (y < cols);
	}

	private int getKittyCountSurroundingCell(int x, int y) {
		if(kittyGrid[y][x]) {return 9;}
		int sum = 0;
		for(int xOff = -1; xOff <= 1; xOff++) {
			for(int yOff = -1; yOff <= 1; yOff++) {
				int newX = x + xOff;
				int newY = y + yOff;
				if(areCoordsInBounds(newX, newY))  {
					boolean possibleKitty = kittyGrid[newY][newX];
					sum += (possibleKitty) ? 1: 0;
				}
			}
		}
		
		return sum;
	}
	

	
	/*
	 *this method will calculate the kitty counts for each cell
	 *if cell is true set int value to 9
	 *if cell is not true set int value to cnt of kitties in adjacent cells
	 */
	public int[][] getKittyCountsGrid() {
		int[][] kittyMinesweeper = new int[rows][cols];
		for(int y = 0; y < rows; y++) {
			for(int x = 0; x < cols; x++) {
				kittyMinesweeper[y][x] = getKittyCountSurroundingCell(x, y);
			}
		}
		return kittyMinesweeper;
	}
	
	private int getKittyCountSurroundingCell(int x, int y, int radius) {
		int sum = 0;
		for(int xOff = -radius; xOff <= radius; xOff++) {
			for(int yOff = -radius; yOff <= radius; yOff++) {
				int newX = x + xOff;
				int newY = y + yOff;
				if(areCoordsInBounds(newX, newY))  {
					boolean possibleKitty = kittyGrid[newY][newX];
					sum += (possibleKitty) ? 1: 0;
				}
			}
		}
		
		return sum;
	}
	
	public int[] getDensestArea() {
		int maxDensity = Integer.MIN_VALUE;
		int[] position = new int[2];
		
		for(int y = 0; y < rows; y++) {
			for(int x = 0; x < cols; x++) {
				int localDensity = getKittyCountSurroundingCell(x, y, 3);
				if(localDensity > maxDensity) {
					maxDensity = localDensity;
					position = new int[] {x, y};
				}
			}
		}
		return position;
	}

	public String toString() {
		String returnMe = "";
		for(boolean[] row: kittyGrid) {
			for(boolean v: row) {
				returnMe += (v) ? 1: 0;
			}
			returnMe += "\n";
		}
		return returnMe;
	}
}