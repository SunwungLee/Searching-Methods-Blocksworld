package sl7a19_SunwungLee_31240232;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class UninformedDFS {
	private int _boardSize;
	private int _movingCnt = 0;
    private Vertex _startState;
    private Vertex _goalState;
    private Vertex _currentState = new Vertex();
    private int _vertexNum = 0;	// 
    private Stack<Vertex> _nodeStack = new Stack<Vertex>();
    private ArrayList<Vertex> _visitedVertex = new ArrayList<>();
    private BlocksworldTile _b = new BlocksworldTile(_boardSize);
    //private ArrayList<Integer> _pathToGoal = new ArrayList<Integer>();
    
    public UninformedDFS(int _start[][], int _goal[][], int _boardSize) {
    	this._startState = new Vertex(_start);
        this._goalState = new Vertex(_goal);
        this._boardSize = _boardSize;
        _nodeStack.push(_startState);
    }
   
    private void moveAgent(Vertex _currentState) {
        Vertex new_state = new Vertex();
        Random random = new Random();
        BlocksworldTile b = new BlocksworldTile(_boardSize);
        boolean choose = false;
        while (choose == false) {
            int i = random.nextInt(4) + 1; // ·£´ýÀ¸·Î stack¿¡ push
            switch (i) {
                case 1:
                    new_state = b.moveAgentUp(_currentState);
                    if (new_state != null) {
                    	_nodeStack.push(new_state);
                    	_vertexNum ++;
                        choose = true;
                        break;
                    }
                    break;
                case 2:
                    new_state = b.moveAgentRight(_currentState);
                    if (new_state != null) {
                    	_nodeStack.push(new_state);
                    	_vertexNum ++;
                        choose = true;
                        break;
                    }
                    break;
                case 3:
                    new_state = b.moveAgentDown(_currentState);
                    if (new_state != null) {
                    	_nodeStack.push(new_state);
                    	_vertexNum ++;
                        choose = true;
                        break;
                    }
                    break;
                case 4:
                    new_state = b.moveAgentLeft(_currentState);
                    if (new_state != null) {
                    	_nodeStack.push(new_state);
                    	_vertexNum ++;
                        choose = true;
                        break;
                    }
                    break;
            }
        }
    }

    private void moveAgentGraph(Vertex _currentState) {
        Vertex new_state = new Vertex();
        BlocksworldTile b = new BlocksworldTile(_boardSize);

        new_state = b.moveAgentUp(_currentState);
        if (new_state != null) {
            _nodeStack.push(new_state);
            _vertexNum ++;
        }

        new_state = b.moveAgentRight(_currentState);
        if (new_state != null) {
        	_nodeStack.push(new_state);
        	_vertexNum ++;
        }

        new_state = b.moveAgentDown(_currentState);
        if (new_state != null) {
        	_nodeStack.push(new_state);
        	_vertexNum ++;
        }

        new_state = b.moveAgentLeft(_currentState);
        if (new_state != null) {
        	_nodeStack.push(new_state);
        	_vertexNum ++;
        }

    }

    public void methodDepthFirstSearch() {
        boolean findSolution = false;
        System.out.println(" < Depth-First-Search >");
        _currentState = _nodeStack.pop();
        moveAgent(_currentState);
        while (!findSolution) {
            try {               
                _currentState = _nodeStack.pop();
                _movingCnt++;
                //System.out.println(_currentState.showState() + "-------------------------");
                if (_b.checkFinalState(_currentState, _goalState)) {
                    findSolution = true;
                    System.out.print("       < Success >\n" + _currentState.showState());
                    System.out.println("success >> Depth: " + _currentState.getDepthLevel());
                    break;
                } else {
                    moveAgent(_currentState);
                }
            } catch (Exception e) {
                System.out.println("No solutions found!");
                break;
            }
        }
        System.out.println(">> Total steps: " + _movingCnt);
        System.out.println(">> Expanded vertexes: " + _vertexNum);
    }

    public void methodDepthFirstSearchGraph() {
        boolean isGoalState = false;
        System.out.println("< Depth-First-Search + Graph >");
        boolean visited;
        Vertex tempNode;
        _currentState = _nodeStack.pop();
        _visitedVertex.add(_currentState);
        moveAgentGraph(_currentState);
        while (!isGoalState) {
            try {
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
                    _movingCnt++;
                    //System.out.println(_currentState.showState() + "-------------------------");
                    if (_b.checkFinalState(_currentState, _goalState)) {
                    	isGoalState = true;
                        System.out.print("       < Success >\n" + _currentState.showState());
                        break;
                    } else {
                        moveAgentGraph(_currentState);
                        _visitedVertex.add(_currentState);
                    }
                }
            } catch (Exception e) {
                System.out.println("No solutions found!");
                break;
            }
        }
        System.out.println(">> Total steps: " + _movingCnt);
        System.out.println(">> Expanded vertexes: " + _vertexNum);
    }


}
