package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

    private int turn;
    private Color currentPlayer;
    private Board board;
    private boolean check;
    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch() {
        this.board = new Board(8, 8);
        this.turn = 1;
        this.currentPlayer = Color.WHITE;
        initialSetup();
    }

    public int getTurn() {
        return this.turn;
    }

    public Color getCurrentPlayer() {
        return this.currentPlayer;
    }

    public List<Piece> getCapturedPieces() {
        return this.capturedPieces;
    }

    // returns an array[][] of pieces from my chess game- downcasting Piece -
    // ChessPiece
    public ChessPiece[][] getPieces() {
        ChessPiece[][] matrix = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                matrix[i][j] = (ChessPiece) board.piece(i, j); // downcasting
            }
        }
        return matrix;
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();

        validateSource(source);
        validadeTarget(source, target);

        Piece capturedPiece = makeMove(source, target);
        nextTurn();
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);

        if (capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
    }

    private void undoMove(Position source, Position target, Piece captured) {

        Piece p = board.removePiece(target);
        board.placePiece(p, source);

        if (captured != null) {
            board.placePiece(captured, target);
            capturedPieces.remove(captured);
            piecesOnTheBoard.add(captured);
        }
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSource(position);
        return board.piece(position).possibleMoves();
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        this.piecesOnTheBoard.add(piece);
    }

    public void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE; // ternary operation
    }

    private Color opponent(Color color) {
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private ChessPiece king(Color color) {
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for(Piece p : list) {
            if(p instanceof King) {
                return (ChessPiece) p;
            }
        }
        //my chess system are with problem - inicialSetup() maybe ...
        throw new IllegalStateException("There is no " + color + " king on the board");
    }

    public void initialSetup() {
        this.placeNewPiece('b', 7, new Rook(board, Color.WHITE));
        this.placeNewPiece('b', 6, new Rook(board, Color.BLACK));
        this.placeNewPiece('a', 7, new King(board, Color.WHITE));
        this.placeNewPiece('c', 1, new King(board, Color.BLACK));
    }

    private void validateSource(Position source) {
        if (!board.thereIsAPiece(source)) {
            throw new ChessException("There is no piece in source position");
        }
        if (currentPlayer != ((ChessPiece) board.piece(source)).getColor()) {
            throw new ChessException("The chosen piece isn't yours");
        }
        if (!board.piece(source).isThereAnyPossibleMove()) {
            throw new ChessException("There is any possible move for the chosen piece");
        }
    }

    private void validadeTarget(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("The chosen piece can't move to target position");
        }
    }

}
