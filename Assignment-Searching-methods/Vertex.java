package sl7a19_SunwungLee_31240232;

/**
 * Node들을 Vertex로 정의한다.
 * 
 * 1. 현재 board의 상황은 2차원 배열로 구현
 * 2. board 내의 tile의 좌표 설정 및 확인
 * 3. board의 현 상황 출력 및 확인
 * 4. depth에 관한 내용 일부 포함
 * 
 * @author Sunwung Lee
 *
 */
public class Vertex {

    private int _boardSize = PuzzleMain._boardSize;
    int _boardState[][] = new int[_boardSize][_boardSize]; // 현재 board의 상태
    private int _depthLevel;
    private int _g;

    // 생성자 1
    public Vertex() {
    }
    // 생성자 2 - 받아온 파라미터 state를  저장.
    public Vertex(int _state[][]) {
        this._boardState = _state;
    }
    // 
    public int getBlockPos(int posX, int posY) {
        return _boardState[posX][posY];
    }
    // block에 번호 부여해서 tile로 설정
    public void setBlock(int _tile, int x, int y) {
    	_boardState[x][y] = _tile;
    }
    // tile의 X 좌표를 가져옴
    public int posX(int _tile) {
        int px = 0;
        for (int x = 0; x < _boardSize; x++) {
            for (int y = 0; y < _boardSize; y++) {
                if (_boardState[x][y] == _tile) {
                	px = x;
                    break;
                }
            }
        }
        return px;
    }
    // tile의 Y 좌표를 가져옴
    public int posY(int _tile) {
        int py = 0;
        for (int x=0; x<_boardSize; x++) {
            for (int y=0; y<_boardSize; y++) {
                if (_boardState[x][y] == _tile) {
                	py = y;
                    break;
                }
            }
        }
        return py;
    }
    // tile번호의 위치를 보드에서 가져옴
    public void getAxis(int _axis[], int _tile) {
        for (int x=0; x<_boardSize; x++) {
            for (int y=0; y<_boardSize; y++) {
                if (_boardState[x][y] == _tile) {
                    _axis[0] = x;
                    _axis[1] = y;
                    break;
                }
            }
        }
    }
    // 현재 board 상태 print
    public String showState() {
        String result = "";
        for (int x=0; x<_boardSize; x++) {
        	result += "\t|";
        	for (int y=0; y<_boardSize; y++) {
        		if (_boardState[x][y] == 0) {
        			result += " ";
        		} else if(_boardState[x][y] == 1) {
        			result += "A";
        		} else if(_boardState[x][y] == 2) {
        			result += "B";
        		} else if(_boardState[x][y] == 3) {
        			result += "C";
        		} else if(_boardState[x][y] == 4) {
        			result += "#";
        		}
                result += "|";
            }
            result += "\n";
        }
        return result;
    }
    // 현재 board 상태를 return
    public int[][] getState() {
        int newstate[][] = new int[_boardSize][_boardSize];
        for (int x=0; x<_boardSize; x++){
            for (int y=0; y<_boardSize; y++){
                newstate[x][y] = _boardState[x][y];
            }
        }
        return newstate;
    }
    
    public void setDepthLevel(int _depthLevel){
        this._depthLevel = _depthLevel;
    }

    public int getDepthLevel(){
        return _depthLevel;
    }

    public void setG(int g){this._g = g;}
    
    public int getG(){return _g;}



}
