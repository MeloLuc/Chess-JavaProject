package boardgame;

public abstract class Piece {
    
    protected Position position;
    private Board board;

    public Piece(Board board){
        this.board = board;
        this.position = null; //piece not placed in board yet
    }

    protected Board getBoard() {
        return board;
    }

    public abstract boolean[][] possibleMoves();

    public boolean possibleMove(Position position) {
        //estou chamando esse método de uma subclasse-gancho, posso fazer isso
        return possibleMoves()[position.getRow()][position.getColumn()];
    }
    
    public boolean isThereAnyPossibleMove() {
        boolean[][] mat = this.possibleMoves(); //gancho
        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat.length; j++) {
                if(mat[i][j]){
                    return true;
                }
            }
        }
        return false;
    }
}
