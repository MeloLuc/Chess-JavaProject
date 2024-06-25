package boardgame;

public class Piece {
    
    protected Position position;
    private Board board;

    public Piece(Board board){
        this.board = board;
        this.position = null; //piece not placed in board yet
    }

    protected Board getBoard() {
        return board;
    }

    
}
