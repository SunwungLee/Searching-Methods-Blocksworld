package sl7a19_SunwungLee_31240232;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class UninformedBFS {
	private int _boardSize;
	private int _movingCnt = 0;
    private Vertex _startState = new Vertex();
    private Vertex _goalState = new Vertex();
    private Vertex _currentState = new Vertex();
    private Queue<Vertex> _queue = new LinkedList<Vertex>();
    private ArrayList<Vertex> _visitedVertex = new ArrayList<>();
    private int _vertexNum = 0;	// 
    BlocksworldTile _b = new BlocksworldTile(_boardSize);
        
	public UninformedBFS(int _start[][], int _goal[][], int _boardSize) {
        this._startState = new Vertex(_start);
        this._goalState = new Vertex(_goal);
        this._boardSize = _boardSize;
        _queue.offer(_startState);
    }

	private void moveAgent(Vertex _currentState) {
        Vertex new_state;
        BlocksworldTile b = new BlocksworldTile(_boardSize);
        
        new_state = b.moveAgentLeft(_currentState);
        if (new_state != null) {
            _queue.offer(new_state);
            _vertexNum ++;
            new_state.setDepthLevel(_currentState.getDepthLevel() + 1);
        }
        new_state = b.moveAgentUp(_currentState);
        if (new_state != null) {
            _queue.offer(new_state);
            _vertexNum ++;          
            new_state.setDepthLevel(_currentState.getDepthLevel() + 1);
        }
        new_state = b.moveAgentRight(_currentState);
        if (new_state != null) {
            _queue.offer(new_state);
            _vertexNum ++;     
            new_state.setDepthLevel(_currentState.getDepthLevel() + 1);
        }
        new_state = b.moveAgentDown(_currentState);
        if (new_state != null) {
            _queue.offer(new_state);
            _vertexNum ++;
            new_state.setDepthLevel(_currentState.getDepthLevel() + 1);
        }
    }
	    
	public void methodBreathFirstSearch() {
        boolean isGoalState = false;
        System.out.println(" < Breath-First-Search >");
        _currentState = _queue.poll();
        moveAgent(_currentState);
        while (!isGoalState) {
            try {
                _movingCnt++;
                _currentState = _queue.poll();
                //System.out.println(">> Depth: " + _currentState.getDepthLevel());
                //System.out.println(_currentState.showState() + "-------------------------");
                if (_b.checkFinalState(_currentState, _goalState)) {
                	// 
                	isGoalState = true;
                	System.out.print("       < Success >\n" + _currentState.showState());
                    break;
                } else {
                    moveAgent(_currentState);
                }
            } catch (Exception e) {
            	System.out.print("< Failed to find solution >\n");
                break;
            }
        }
        System.out.println(">> Total steps: " + _movingCnt);
        System.out.println(">> Expanded vertexes: " + _vertexNum);
    }

    public void methodBreathFirstSearchGraph() {
        boolean isGoalState = false;
        System.out.println("< Breath-First-Search + Graph >");
        boolean visited;
        Vertex tmp_vertex;
        _currentState = _queue.remove();
        _visitedVertex.add(_currentState);
        moveAgent(_currentState);
        while (!isGoalState) {
            try {
                visited = false;
                _currentState = _queue.remove();
                for (int i = 0; i < _visitedVertex.size(); i++) {
                	tmp_vertex = _visitedVertex.get(i);
                    if (_currentState.showState().equals(tmp_vertex.showState())) {
                        visited = true;                        
                        break;
                    }
                }
                if (!visited) {
                    _movingCnt++;                 
                    //System.out.println(">> Depth: " + _currentState.getDepthLevel());
                    //System.out.println(_currentState.showState() + "-------------------------");
                    if (_b.checkFinalState(_currentState, _goalState)) {
                    	isGoalState = true;
                    	System.out.print("       < Success >\n" + _currentState.showState());
                        break;
                    } else {
                        moveAgent(_currentState);
                        _visitedVertex.add(_currentState);                        
                    }
                }

            } catch (Exception e) {
            	System.out.print("< Failed to find solution >\n");
                break;
            }
        }
        System.out.println(">> Total steps: " + _movingCnt);
        System.out.println(">> Expanded vertexes: " + _vertexNum);
    }
	
	
}
