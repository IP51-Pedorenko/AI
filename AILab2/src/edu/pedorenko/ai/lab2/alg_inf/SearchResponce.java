package edu.pedorenko.ai.lab2.alg_inf;

import edu.pedorenko.ai.lab2.chess.BoardWithQueens;

public class SearchResponce {

    private BoardWithQueens resultedBoard;

    private int generatedStates;


    public SearchResponce(BoardWithQueens resultedBoard, int generatedStates) {
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
