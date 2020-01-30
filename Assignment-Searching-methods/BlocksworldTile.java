package sl7a19_SunwungLee_31240232;

/**
 * Blocksworld Tile
 * 
 * 1. block들의 움직임들에 대해 정의
 * 2. start와 goal state의 상태 확인
 * 
 * @author Sunwung Lee
 *
 */
public class BlocksworldTile{
    private int _boardSize;
    private int _A = 1;
    private int _B = 2;
    private int _C = 3;
    private int _Agent = 4;
    
    
    
    public BlocksworldTile(int _boardsize){
    	this._boardSize = _boardsize;
    }
    
    // Vertex의 위치가 같은지 다른지 확인, 같으면 1, 다르면 0
    public boolean checkFinalState(Vertex _currentState, Vertex _goalState) {
    	boolean c = false;
    	if(_currentState.posX(_A) == _goalState.posX(_A) && _currentState.posY(_A) == _goalState.posY(_A) 
    			&& _currentState.posX(_B) == _goalState.posX(_B) && _currentState.posY(_B) == _goalState.posY(_B) 
    			&& _currentState.posX(_C) == _goalState.posX(_C) && _currentState.posY(_C) == _goalState.posY(_C))
    	{
    		c = true;
    	}else {
    		c = false;
    	}
		return c; // if the same >> return 1, else >> return 0 
    }
    
    private void swapPosition(Vertex _newState, int _newPosition[]){
        int tmp_tile[] = new int[2];
        int tmp_num;
        _newState.getAxis(tmp_tile, _Agent);
        tmp_num = _newState.getBlockPos(_newPosition[0],_newPosition[1]);
        _newState.setBlock(_Agent, _newPosition[0], _newPosition[1]);
        _newState.setBlock(tmp_num, tmp_tile[0], tmp_tile[1]);
    }

    public Vertex moveAgentDown(Vertex _currentState){
        Vertex downState = new Vertex();
        downState = null;
        int downstate[][] = new int[_boardSize][_boardSize];
        int newPosition[] = new int[2];
        if (_currentState.posX(_Agent) + 1 < _boardSize){
            downstate = _currentState.getState(); // 현재 상태를 받아서
            downState = new Vertex(downstate); // 새로이 vertex를 만듦
            newPosition[0] = downState.posX(_Agent) + 1; // Agent의 x좌표 +1
            newPosition[1] = downState.posY(_Agent); 	// Agent의 y좌표
            swapPosition(downState, newPosition);
        }
        return downState;
    }

    public Vertex moveAgentUp(Vertex _currentState){
        Vertex upState = new Vertex();
        upState = null;
        int upstate[][] = new int[_boardSize][_boardSize];
        int newPosition[] = new int[2];
        if (_currentState.posX(_Agent) - 1 >= 0){
            upstate = _currentState.getState();
            upState = new Vertex(upstate);
            newPosition[0] = upState.posX(_Agent) - 1;
            newPosition[1] = upState.posY(_Agent);
            swapPosition(upState, newPosition);
        }
        return upState;

    }

    public Vertex moveAgentLeft(Vertex _currentState){
        Vertex leftState;
        leftState = null;
        int leftstate [][] = new int[_boardSize][_boardSize];
        int newPosition[] = new int[2];
        if (_currentState.posY(_Agent) - 1 >= 0){
            leftstate = _currentState.getState();
            leftState = new Vertex(leftstate);
            newPosition[0] = leftState.posX(_Agent);
            newPosition[1] = leftState.posY(_Agent) - 1;
            swapPosition(leftState, newPosition);
        }
        return leftState;
    }

    public Vertex moveAgentRight(Vertex _currentState){
        Vertex rightState = new Vertex();
        rightState = null;
        int rightstate[][] = new int[_boardSize][_boardSize];
        int newPosition[] = new int[2];
        if (_currentState.posY(_Agent) + 1 < _boardSize){
            rightstate = _currentState.getState();
            rightState = new Vertex(rightstate);
            newPosition[0] = rightState.posX(_Agent);
            newPosition[1] = rightState.posY(_Agent) + 1;
            swapPosition(rightState, newPosition);
        }
        return rightState;
    }

    
}