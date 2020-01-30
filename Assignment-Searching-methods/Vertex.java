package sl7a19_SunwungLee_31240232;

/**
 * Node���� Vertex�� �����Ѵ�.
 * 
 * 1. ���� board�� ��Ȳ�� 2���� �迭�� ����
 * 2. board ���� tile�� ��ǥ ���� �� Ȯ��
 * 3. board�� �� ��Ȳ ��� �� Ȯ��
 * 4. depth�� ���� ���� �Ϻ� ����
 * 
 * @author Sunwung Lee
 *
 */
public class Vertex {

    private int _boardSize = PuzzleMain._boardSize;
    int _boardState[][] = new int[_boardSize][_boardSize]; // ���� board�� ����
    private int _depthLevel;
    private int _g;

    // ������ 1
    public Vertex() {
    }
    // ������ 2 - �޾ƿ� �Ķ���� state��  ����.
    public Vertex(int _state[][]) {
        this._boardState = _state;
    }
    // 
    public int getBlockPos(int posX, int posY) {
        return _boardState[posX][posY];
    }
    // block�� ��ȣ �ο��ؼ� tile�� ����
    public void setBlock(int _tile, int x, int y) {
    	_boardState[x][y] = _tile;
    }
    // tile�� X ��ǥ�� ������
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
    // tile�� Y ��ǥ�� ������
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
    // tile��ȣ�� ��ġ�� ���忡�� ������
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
    // ���� board ���� print
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
    // ���� board ���¸� return
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
