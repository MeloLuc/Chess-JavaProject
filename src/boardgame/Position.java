package boardgame;

public class Position {
    
    private int row;
    private int column;
    
    public Position(int row, int column) {
        if(row < 0 || row >= 8 || column < 0 || column >= 8) {
            throw new BoardException("error instantiating Position board");
        }
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setValues(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public String toString(){
        return row + ", " + column;
    }
    
}
