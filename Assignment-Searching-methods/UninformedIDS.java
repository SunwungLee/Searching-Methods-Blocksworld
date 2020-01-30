package sl7a19_SunwungLee_31240232;

import java.util.ArrayList;
import java.util.Stack;

public class UninformedIDS {
	private int _boardSize;
	private int _movingCnt = 0;
	private Vertex _startState;
	private Vertex _goalState;
	private Vertex _currentState = new Vertex();
	private Stack<Vertex> _nodeStack = new Stack<Vertex>();
	private ArrayList<Vertex> _visitedVertex = new ArrayList<>();
	private int _vertexNum = 0;	// 
	private int _limit = -1;
	private BlocksworldTile _b = new BlocksworldTile(_boardSize);
    	
	public UninformedIDS(int _start[][], int _goal[][], int _boardSize) {
		this._startState = new Vertex(_start);
		this._goalState = new Vertex(_goal);
		this._boardSize = _boardSize;
	}

	private void moveAgent(Vertex _currentState) {
		Vertex new_state = new Vertex();
		BlocksworldTile b = new BlocksworldTile(_boardSize);
		
		new_state = b.moveAgentLeft(_currentState);
		if (new_state != null) {
			_nodeStack.push(new_state);
			_vertexNum++;
			new_state.setDepthLevel(_currentState.getDepthLevel() + 1);           
		}
		
		new_state = b.moveAgentUp(_currentState);
		if (new_state != null) {
			_nodeStack.push(new_state);
			_vertexNum++;
			new_state.setDepthLevel(_currentState.getDepthLevel() + 1);            
		}

		new_state = b.moveAgentRight(_currentState);
		if (new_state != null) {
			_nodeStack.push(new_state);
			_vertexNum++;
			new_state.setDepthLevel(_currentState.getDepthLevel() + 1);            
		}

		new_state = b.moveAgentDown(_currentState);
		if (new_state != null) {
			_nodeStack.push(new_state);
			_vertexNum++;
			new_state.setDepthLevel(_currentState.getDepthLevel() + 1);            
		}

	}

	public void methodIterativeDeepeningSearch() {
		boolean findSolution = false;
		System.out.println("< Iterative-Deepening-Search >");
		while (!findSolution) {
			try {
				_nodeStack.push(_startState);
				_limit++;                
				_startState.setDepthLevel(0);
				while (!_nodeStack.isEmpty()) {
					_movingCnt++;                   
					_currentState = _nodeStack.pop();
					//System.out.println(">> Depth: " + _currentState.getDepthLevel());
					//System.out.println(_currentState.showState() + "-------------------------");
					if (_b.checkFinalState(_currentState, _goalState)) {
						findSolution = true;
						System.out.print("       < Success >\n" + _currentState.showState());
						System.out.println("success >> Depth: " + _currentState.getDepthLevel());
						break;
					} else if (_currentState.getDepthLevel() < _limit) {
						moveAgent(_currentState);                        
					}
				}
			} catch (Exception e) {
				System.out.print("< Failed to find solution >\n");
				break;
			}
		}
		System.out.println(">> Total steps: " + _movingCnt);
		System.out.println(">> Expanded nodes: " + _vertexNum);
	}

	public void  methodIterativeDeepeningSearchGraph() {
		boolean isGoalState = false;
		System.out.println("< Iterative-Deepening-Search + Graph >");
		boolean visited;
		Vertex tempNode;
		while (!isGoalState) {
			try {                
				_nodeStack.push(_startState);
				_visitedVertex.clear();
				_limit++;                
				_startState.setDepthLevel(0);
				while (!_nodeStack.isEmpty()) {
					_movingCnt++;
					visited = false;                    
					_currentState = _nodeStack.pop();
					for (int i = 0; i < _visitedVertex.size(); i++) {
						tempNode = _visitedVertex.get(i);
						if (_currentState.showState().equals(tempNode.showState())) {
							visited = true;                           
							break;
						}
					}
					if (!visited) {
						//System.out.println(">> Depth: " + _currentState.getDepthLevel());
						//System.out.println(_currentState.showState() + "-------------------------");                      
						if (_b.checkFinalState(_currentState, _goalState)) {
							isGoalState = true;
							System.out.print("       < Success >\n" + _currentState.showState());
							System.out.println("success >> Depth: " + _currentState.getDepthLevel());
							break;
						} else if (_currentState.getDepthLevel() < _limit) {
							moveAgent(_currentState);
							_visitedVertex.add(_currentState);
						}
					}
				}
			} catch (Exception e) {
				System.out.print("< Failed to find solution >\n");
				break;
			}
		}
	}


}


