package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        if (this.getColor() == Color.WHITE) {

            // default
            p.setValues(this.position.getRow() - 1, this.position.getColumn());
            if (this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }

            // First move
            p.setValues(this.position.getRow() - 2, this.position.getColumn());
            Position p2 = new Position(this.position.getRow() - 1, this.position.getColumn());
            if (this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)
                    && !this.getBoard().thereIsAPiece(p2) && this.getMoveCount() == 0) {
                mat[p.getRow()][p.getColumn()] = true;
            }

            // with opponents nw ne
            p.setValues(this.position.getRow() - 1, this.position.getColumn() - 1);
            if (this.getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(this.position.getRow() - 1, this.position.getColumn() + 1);
            if (this.getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }

            // special move en passant white
            if (this.position.getRow() == 3 && this.position.getColumn()-1 >= 0 && this.position.getColumn()+1 <= 7) {
                Position left = new Position(this.position.getRow(), this.position.getColumn() - 1);
                if (getBoard().positionExists(left) && isThereOpponentPiece(left)
                        && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
                    mat[left.getRow() - 1][left.getColumn()] = true;
                }

                Position right = new Position(this.position.getRow(), this.position.getColumn() + 1);
                if (getBoard().positionExists(right) && isThereOpponentPiece(right)
                        && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
                    mat[right.getRow() - 1][right.getColumn()] = true;
                }
            }

        } 
        else {

            // default
            p.setValues(this.position.getRow() + 1, this.position.getColumn());
            if (this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }

            // First move
            p.setValues(this.position.getRow() + 2, this.position.getColumn());
            Position p2 = new Position(this.position.getRow() + 1, this.position.getColumn());
            if (this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)
                    && !this.getBoard().thereIsAPiece(p2) && this.getMoveCount() == 0) {
                mat[p.getRow()][p.getColumn()] = true;
            }

            // with opponents nw ne
            p.setValues(this.position.getRow() + 1, this.position.getColumn() - 1);
            if (this.getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(this.position.getRow() + 1, this.position.getColumn() + 1);
            if (this.getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }

            // special move en passant black
            if (this.position.getRow() == 4 && this.position.getColumn()-1 >= 0 && this.position.getColumn()+1 <= 7) {
                Position left = new Position(this.position.getRow(), this.position.getColumn() - 1);
                if (getBoard().positionExists(left) && isThereOpponentPiece(left)
                        && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
                    mat[left.getRow() + 1][left.getColumn()] = true;
                }

                Position right = new Position(this.position.getRow(), this.position.getColumn() + 1);
                if (getBoard().positionExists(right) && isThereOpponentPiece(right)
                        && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
                    mat[right.getRow() + 1][right.getColumn()] = true;
                }
            }

        }
        return mat;
    }

}
