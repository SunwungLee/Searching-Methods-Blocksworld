package sl7a19_SunwungLee_31240232;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Main 함수
 * 
 * 1. start와 goal state는 2차원 배열로 설정 
 * 2. A,B,C는 편의상 1,2,3으로 설정
 * 3. Agent는 4로 표현
 * 
 * @author Sunwung Lee
 *
 */
public class PuzzleMain {
	// set the board size
	public static int _boardSize = 4;
	public static int _A = 1;
	public static int _B = 2;
	public static int _C = 3;
	public static int _Agent = 4;
//	public Vertex _pathToGoal;
	
	public static void main(String args[]){
		/**
		 * Set start and goal state
		 */
//		File file = new File("output");
//		FileOutputStream fos = null;
//		try {
//			fos = new FileOutputStream(file+"_initial_pos"+".txt");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		PrintStream ps = new PrintStream(fos);
//		System.setOut(ps);

		int start_state[][] = {
				{0,		0,		0,		0},
				{0,		0,		0,		0},
				{0,		0,		0,		0},
				{_A,	_B,		_C,		_Agent}
		};
//		int goal_state[][] = {
//				{0,		0,		0,		0},
//				{0,		0,		0,		0},
//				{0,		_B,		0,		0},
//				{0,		_A,		0,		_C}
//		};

		// original
		int goal_state[][] = {
				{0,		0,		0,		0},
				{0,		_A,		0,		0},
				{0,		_B,		0,		0},
				{0,		_C,		0,		0}
		};

		/**
		 * Print
		 */
		Vertex start = new Vertex(start_state);
		Vertex goal = new Vertex(goal_state);
		System.out.println("Start State:");
		System.out.println(start.showState());
		System.out.println("Goal State:");
		System.out.println(goal.showState());
		/**
		 * Main loop
		 */
		// for calculating time3
		//long start_time = System.currentTimeMillis();
		int iter_num = 1;
		for (int i=0; i<iter_num; i++) {
//			try {
//				fos = new FileOutputStream(file+"_"+i+".txt");
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			ps = new PrintStream(fos);
//			System.setOut(ps);
			
			UninformedBFS bfs = new UninformedBFS(start_state, goal_state, _boardSize);
			//bfs.methodBreathFirstSearch();
			//bfs.methodBreathFirstSearchGraph();

			UninformedDFS dfs = new UninformedDFS(start_state, goal_state, _boardSize);
			//dfs.methodDepthFirstSearch();
			//dfs.methodDepthFirstSearchGraph();
			
			UninformedIDS ids = new UninformedIDS(start_state, goal_state, _boardSize);
			//ids.methodIterativeDeepeningSearch();
			//ids.methodIterativeDeepeningSearchGraph();
			
			HeuristicAstar as = new HeuristicAstar(start_state, goal_state, _boardSize);
			as.methodAStarSearch();
			
		}
	}
}
