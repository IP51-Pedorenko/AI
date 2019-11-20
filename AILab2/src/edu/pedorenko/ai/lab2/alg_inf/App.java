package edu.pedorenko.ai.lab2.alg_inf;

import edu.pedorenko.ai.lab2.chess.BoardWithQueens;
import edu.pedorenko.ai.lab2.chess.Coordinates;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class App {

    public static void main(String[] args) {

        BoardWithQueens boardWithQueens = new BoardWithQueens();
        boardWithQueens.setQueensRandomly();
        System.out.println("Initial board: \n\n" + boardWithQueens);

        BoardWithQueens result = Astar(boardWithQueens).getResultedBoard();
        System.out.println("Result: \n\n" + result);

        // pre test runs
        for (int i = 0; i < 100; ++i) {
            boardWithQueens = new BoardWithQueens();
            boardWithQueens.setQueensRandomly();
            Astar(boardWithQueens);
        }

        //test
        int allGeneratedStates = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 20; ++i) {
            boardWithQueens = new BoardWithQueens();
            boardWithQueens.setQueensRandomly();
            SearchResponse response = Astar(boardWithQueens);
            allGeneratedStates += response.getGeneratedStates();
        }
        long end = System.currentTimeMillis();
        System.out.println("Average time: " + ((end - start) / 20d));
        System.out.println("Average states: " + (allGeneratedStates / 20d));
    }

    private static SearchResponse Astar(BoardWithQueens boardWithQueens) {

        int generatedStates = 0;

        PriorityQueue<BoardWithQueens> queue = new PriorityQueue<>(Comparator.comparingInt(BoardWithQueens::F2));
        BoardWithQueens currentBoard = boardWithQueens;

        int F2 = currentBoard.F2();
        while (F2 != 0) {

            List<Coordinates> queensCoordinates = currentBoard.getQueensCoordinates();

            for (Coordinates queenCoordinates : queensCoordinates) {

                for (int i = 0; i < BoardWithQueens.BOARD_SIDE_SIZE; ++i) {
                    for (int j = 0; j < BoardWithQueens.BOARD_SIDE_SIZE; ++j) {
                        if (!currentBoard.isQueen(i, j)) {
                            BoardWithQueens nextPosition = currentBoard.clone();
                            nextPosition.moveQueen(queenCoordinates, new Coordinates(i, j));
                            queue.add(nextPosition);
                            generatedStates++;
                        }
                    }
                }
            }

            currentBoard = queue.remove();
            F2 = currentBoard.F2();
        }

        return new SearchResponse(currentBoard, generatedStates);
    }
}
