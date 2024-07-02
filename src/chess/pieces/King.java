package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

    private ChessMatch cheesMatch;

    public King(Board board, Color color, ChessMatch cheesMatch) {
        super(board, color);
        this.cheesMatch = cheesMatch;
    }
    
    @Override
    public String toString(){
        return "K";
    }

    private boolean canMove(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position); //downcasting
        return p == null || p.getColor() != this.getColor();    // if, i can capture
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        //above
        p.setValues(this.position.getRow()-1, this.position.getColumn());
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //below
        p.setValues(this.position.getRow()+1, this.position.getColumn());
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //right
        p.setValues(this.position.getRow(), this.position.getColumn()+1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //left
        p.setValues(this.position.getRow(), this.position.getColumn()-1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //northwest
        p.setValues(this.position.getRow()-1, this.position.getColumn()-1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //north east
        p.setValues(this.position.getRow()-1, this.position.getColumn()+1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //southeast
        p.setValues(this.position.getRow()+1, this.position.getColumn()+1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //south-west
        p.setValues(this.position.getRow()+1, this.position.getColumn()-1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }


        //special move castling
        if(this.getMoveCount() == 0 && !cheesMatch.getCheck()){
            //kingside
            Position posR1 = new Position(this.position.getRow(), this.position.getColumn()+3);
            if(testRookCastling(posR1)){
                Position p1 = new Position(this.position.getRow(), this.position.getColumn()+1);
                Position p2 = new Position(this.position.getRow(), this.position.getColumn()+2); 
                if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
                    mat[this.position.getRow()][this.position.getColumn()+2] = true;
                }
            } 
            //queenside
            Position posR2 = new Position(this.position.getRow(), this.position.getColumn()-4);
            if(testRookCastling(posR2)){
                Position p1 = new Position(this.position.getRow(), this.position.getColumn()-1);
                Position p2 = new Position(this.position.getRow(), this.position.getColumn()-2); 
                Position p3 = new Position(this.position.getRow(), this.position.getColumn()-1);
                
                if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
                    mat[this.position.getRow()][this.position.getColumn()-2] = true;
                }
            } 
        }

        return mat;
    }
        
    

    private boolean testRookCastling(Position position) {
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p != null && p instanceof Rook && p.getColor() == this.getColor() && p.getMoveCount() == 0;
    }
}
