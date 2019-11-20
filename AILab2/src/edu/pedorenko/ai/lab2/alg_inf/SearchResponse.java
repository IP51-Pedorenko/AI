package edu.pedorenko.ai.lab2.alg_inf;

import edu.pedorenko.ai.lab2.chess.BoardWithQueens;

public class SearchResponse {

    private BoardWithQueens resultedBoard;

    private int generatedStates;


    public SearchResponse(BoardWithQueens resultedBoard, int generatedStates) {
        this.resultedBoard = resultedBoard;
        this.generatedStates = generatedStates;
    }

    public BoardWithQueens getResultedBoard() {
        return resultedBoard;
    }

    public int getGeneratedStates() {
        return generatedStates;
    }
}
