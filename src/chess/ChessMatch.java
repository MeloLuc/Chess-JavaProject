package chess;

import boardgame.Board;
import chess.pieces.Rook;

public class ChessMatch {
    
    private Board board;

    public ChessMatch(){
        this.board = new Board(8, 8);
        initialSetup();
    }

    //returns an array[][] of pieces from my chess game- downcasting Piece - ChessPiece
    public ChessPiece[][] getPieces() {
        ChessPiece[][] matrix = new ChessPiece[board.getRows()][board.getColumns()];
        for(int i=0; i < board.getRows(); i++){
            for(int j=0; j < board.getColumns(); j++){
                matrix[i][j] =(ChessPiece) board.piece(i, j); //downcasting
            }
        }
        return matrix;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    public void initialSetup() {
        this.placeNewPiece('b', 7, new Rook(board, Color.BLACK));
        this.placeNewPiece('b', 6, new Rook(board, Color.BLACK));
    }
}
