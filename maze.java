//Thanks shlomibabluki
public class Maze_Best {

public int counter = 0;
private final static int MAX_VALUE = 1000;	
	
	int best_solution = MAX_VALUE;
	
	public char[][] maze =
		   {{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
			{'#', ' ', ' ', ' ', '#', ' ', '#', ' ', ' ', '#'},
			{'#', ' ', ' ', ' ', '#', ' ', '#', ' ', '#', '#'},
			{'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
			{'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
			{'#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
			{'#', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#'},
			{'#', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#', '#'},
			{'#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
			{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},};
	
	public char[][] output_maze = null;
	
	private void cloneMaze() {
		output_maze = new char[10][10];
		for (int x=0; x<10; x++) {
			for (int y=0; y<10; y++) {
				output_maze[x][y] = maze[x][y];
			}
		}
	}

	// Get the start location (x,y) and try to solve the maze
	public void solve(int x, int y) {
		best_solution = MAX_VALUE;
		
		if (step(x,y, 0) != MAX_VALUE) {
			output_maze[x][y] = 'S';
		}
	}
	
	// Backtracking method
	public int step (int x, int y, int count) {
		
		counter++;
		//System.out.println(this.toString());
		
		/** Accept case - we found the exit **/
		if (maze[x][y] == 'X') {
			best_solution = count;
			this.cloneMaze();
			return count;
		}
		
		/** Reject case - we are hit a wall or our path **/
		if (maze[x][y] == '#' || maze[x][y] == '*') {
			return MAX_VALUE;
		}
		/** Reject case - we already have a better solution! **/
		if (count == best_solution) {
			return MAX_VALUE;
		}
		
		/** Backtracking Step **/
		
		// Mark this location as part of out path
		maze[x][y] = '*';
		int result = MAX_VALUE;	
		
		int new_result = MAX_VALUE;
		
		// Try to go Right
		new_result = step(x, y+1, count+1);
		if (new_result < result) { result = new_result;}
		
		// Try to go Up
		new_result = step(x-1, y, count+1);
		if (new_result < result) { result = new_result;}
		
		// Try to go Left
		new_result = step(x, y-1, count+1);
		if (new_result < result) { result = new_result;}	
		
		// Try to go Down
		new_result = step(x+1, y, count+1);
		if (new_result < result) { result = new_result;}	
		
		// Unmark this location
		maze[x][y] = ' ';
		
		if (result < MAX_VALUE) {
			return result;
		}
		
		/** Deadend - this location can't be part of the solution **/
		
		// Go back
		return MAX_VALUE;
	}
	
	public String toString() {
		String output = "";
		for (int x=0; x<10; x++) {
			for (int y=0; y<10; y++) {
				output += maze[x][y] + " ";
			}
			output += "\n";
		}
		return output;
	}
	
	public String toStringSolution() {
		if (output_maze == null) {
			return "No Solution!";
		}		
		String output = "";
		for (int x=0; x<10; x++) {
			for (int y=0; y<10; y++) {
				output += output_maze[x][y] + " ";
			}
			output += "\n";
		}
		return output;
	}	
	
	public static void main(String[] args) {
		Maze_Best m = new Maze_Best();
		// Locate the exit
		m.maze[1][1] = 'X';
		
		// Start solving the maze from (8, 1)
		m.solve(8, 1);
		System.out.println(m.toStringSolution());
		System.out.println("Total calls: " + m.counter);
	}

}
