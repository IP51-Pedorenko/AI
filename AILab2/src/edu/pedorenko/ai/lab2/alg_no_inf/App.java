package edu.pedorenko.ai.lab2.alg_no_inf;

import edu.pedorenko.ai.lab2.chess.BoardWithQueens;
import edu.pedorenko.ai.lab2.chess.Coordinates;
import java.util.ArrayDeque;
import java.util.List;

public class App {

    public static void main(String[] args) {

        BoardWithQueens boardWithQueens = new BoardWithQueens();
        boardWithQueens.setQueensRandomly();
        System.out.println("Initial board: \n\n" + boardWithQueens);

        BoardWithQueens result = bfs(boardWithQueens);
        System.out.println("Result: \n\n" + result);
    }

    private static BoardWithQueens bfs(BoardWithQueens boardWithQueens) {

        ArrayDeque<BoardWithQueens> queue = new ArrayDeque<>();
        BoardWithQueens currentBoard = boardWithQueens;

        int F2 = currentBoard.F2();
        while (F2 != 0) {

            if (F2 <= 2) {
                System.out.println(F2 + "\n\n" + currentBoard);
                System.out.println(currentBoard.getQueensCoordinates());
            }

            List<Coordinates> queensCoordinates = currentBoard.getQueensCoordinates();

            for (Coordinates queenCoordinates : queensCoordinates) {

                for (int i = 0; i < BoardWithQueens.BOARD_SIDE_SIZE; ++i) {
                    for (int j = 0; j < BoardWithQueens.BOARD_SIDE_SIZE; ++j) {
                        if (!currentBoard.isQueen(i, j)) {
                            BoardWithQueens nextPosition = currentBoard.clone();
                            nextPosition.moveQueen(queenCoordinates, new Coordinates(i, j));
                            queue.addLast(nextPosition);
                        }
                    }
                }
            }

            currentBoard = queue.removeFirst();
            F2 = currentBoard.F2();
        }

        return currentBoard;
    }
}
