package sl7a19_SunwungLee_31240232;
import java.util.ArrayList;
import java.util.Collections;

public class HeuristicAstar {
	private int _boardSize;
	private int _movingCnt = 0;
    private Vertex _startState;
    private Vertex _goalState;
    private Vertex _currentState = new Vertex();
    private ArrayList<Vertex> _openList = new ArrayList<>();
    private ArrayList<Vertex> _minListState = new ArrayList<>();
    private ArrayList<Integer> _stateValue = new ArrayList<>();
    private ArrayList<Integer> _minListValue = new ArrayList<>();
    private int _vertexNum = 0;	// 
    private int _Fvalue;
    
    private int _A = 1;
    private int _B = 2;
    private int _C = 3;
    private int _Agent = 4;
    BlocksworldTile _b = new BlocksworldTile(_boardSize);
    public HeuristicAstar(int _start[][], int _goal[][], int _boardSize) {
    	this._startState = new Vertex(_start);
        this._goalState = new Vertex(_goal);
        this._boardSize = _boardSize;
    }

    private void moveAgent(Vertex _currentState) {
        Vertex newState = new Vertex();
        BlocksworldTile b = new BlocksworldTile(_boardSize);
        int f;
        int g;
        
        newState = b.moveAgentLeft(_currentState);
        if (newState != null) {
            g = calG(newState, _currentState);
            f = calF(newState, g);
            _openList.add(newState);
            _stateValue.add(f);
            _vertexNum++;
        }
        
        newState = b.moveAgentUp(_currentState);
        if (newState != null) {
            g = calG(newState, _currentState);
            f = calF(newState, g);
            _openList.add(newState);
            _stateValue.add(f);
            _vertexNum++;
        }

        newState = b.moveAgentRight(_currentState);
        if (newState != null) {
            g = calG(newState, _currentState);
            f = calF(newState, g);
            _openList.add(newState);
            _stateValue.add(f);
            _vertexNum++;
        }

        newState = b.moveAgentDown(_currentState);
        if (newState != null) {
            g = calG(newState, _currentState);
            f = calF(newState, g);
            _openList.add(newState);
            _stateValue.add(f);
            _vertexNum++;
        }

    }

    private int manH(Vertex _currentState) {
        int h = 0;
        int goalX;
        int goalY;
        for (int i = 1; i < 4; i++) {
            goalX = _goalState.posX(i);
            goalY = _goalState.posY(i);
            h += Math.abs(_currentState.posX(i) - goalX) + Math.abs(_currentState.posY(i) - goalY);
        }
        return h;
    }

    private int calF(Vertex _currentState, int g) {
        int h = manH(_currentState);
        int f;
        f = g + h;        
        return f;
    }

    private int calG(Vertex _newState, Vertex _currentState) {
        if (_newState.posX(_A) != _currentState.posX(_A) || _newState.posY(_A) != _currentState.posY(_A)) {
        	_newState.setG(_currentState.getG() + 1);
        }
        if (_newState.posX(_B) != _currentState.posX(_B) || _newState.posY(_B) != _currentState.posY(_B)) {
        	_newState.setG(_currentState.getG() + 1);
        }
        if (_newState.posX(_C) != _currentState.posX(_C) || _newState.posY(_C) != _currentState.posY(_C)) {
        	_newState.setG(_currentState.getG() + 1);
        }       
        return _newState.getG();
    }

    public void methodAStarSearch() {
        int minFValueIndex;
        System.out.println("< A-Star-Heuristic-Search >");
        boolean isGoalState = false;
        _startState.setG(0);
        moveAgent(_startState);
        while (!isGoalState) {
            try {
                for (int i = 0; i < _stateValue.size(); i++) {
                    if (_stateValue.get(i) == Collections.min(_stateValue)) {
                        _minListValue.add(_stateValue.get(i));
                        _minListState.add(_openList.get(i));
                    }
                }
                if (_minListValue.size() > 1) {
                    _openList.clear();
                    _stateValue.clear();                    
                    _stateValue.add(_minListValue.get(_minListValue.size() - 1));
                    Collections.shuffle(_minListState);
                    _openList.add(_minListState.get(0));
                    _minListValue.clear();
                    _minListState.clear();
                }
                minFValueIndex = _stateValue.indexOf(Collections.min(_stateValue));
                _currentState = _openList.get(minFValueIndex);
                _Fvalue = _stateValue.get(minFValueIndex);
                _stateValue.remove(minFValueIndex);
                _openList.remove(minFValueIndex);
                //System.out.println(">> Minimum F:" + _Fvalue);
                //System.out.println(_currentState.showState() + "-------------------------");
                _movingCnt++;               
                if (_b.checkFinalState(_currentState, _goalState)) {
                	isGoalState = true;
                    System.out.print("       < Success >\n" + _currentState.showState());
                    System.out.println("success >> Depth: " + _currentState.getDepthLevel());
                    break;
                } else {
                	moveAgent(_currentState);
                }
            } catch (Exception e) {
            	System.out.print("< Failed to find solution >\n");
                break;
            }
        }
    }


}
