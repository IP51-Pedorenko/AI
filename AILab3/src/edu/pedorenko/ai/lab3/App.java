package edu.pedorenko.ai.lab3;

import edu.pedorenko.ai.lab3.chess.BoardWithQueens;
import edu.pedorenko.ai.lab3.chess.Coordinates;
import java.util.List;
import java.util.Random;

public class App {

    public static void main(String[] args) {

        BoardWithQueens boardWithQueens = new BoardWithQueens();
        boardWithQueens.setQueensRandomly();
        System.out.println("Initial board: \n\n" + boardWithQueens);

        BoardWithQueens result = hill(boardWithQueens).getResultedBoard();
        System.out.println("Result: \n\n" + result + "\n\n" + result.F2());

        // pre test runs
        for (int i = 0; i < 100; ++i) {
            boardWithQueens = new BoardWithQueens();
            boardWithQueens.setQueensRandomly();
            hill(boardWithQueens);
        }

        //test
        int allGeneratedStates = 0;
        int noSolutionFound = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 20; ++i) {
            boardWithQueens = new BoardWithQueens();
            boardWithQueens.setQueensRandomly();
            SearchResponse response = hill(boardWithQueens);
            allGeneratedStates += response.getGeneratedStates();
            if (response.getResultedBoard().F2() != 0) {
                noSolutionFound++;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Average time: " + ((end - start) / 20d));
        System.out.println("Average no result found: " + (noSolutionFound / 20d));
        System.out.println("Average states: " + (allGeneratedStates / 20d));
    }

    private static SearchResponse hill(BoardWithQueens initialBoard) {

        int madeMoves = 0;

        Random random = new Random(System.currentTimeMillis());

        BoardWithQueens resultBoard = initialBoard.clone();

        for (int j = 0; j < 250; j++) {

            BoardWithQueens possibleResult = initialBoard.clone();

            while (true) {

                boolean noNewFound = true;

                List<Coordinates> coordinates = possibleResult.getQueensCoordinates();

                for (int i = 0; i < 100; ++i) {

                    int randomQueenId = random.nextInt(coordinates.size());
                    Coordinates randomQueen = coordinates.get(randomQueenId);

                    int x;
                    int y;
                    do {
                        x = random.nextInt(BoardWithQueens.BOARD_SIDE_SIZE);
                        y = random.nextInt(BoardWithQueens.BOARD_SIDE_SIZE);
                    } while (possibleResult.isQueen(x, y));

                    BoardWithQueens possibleBoard = possibleResult.clone();
                    possibleBoard.moveQueen(randomQueen, new Coordinates(x, y));

                    if (possibleBoard.F2() < possibleResult.F2()) {
                        possibleResult = possibleBoard;
                        noNewFound = false;
                        madeMoves++;
//                        System.out.println("i");
                        break;
                    }
                }

                if (noNewFound) {
                    break;
                }
            }

            if (resultBoard.F2() > possibleResult.F2()) {
                resultBoard = possibleResult;
            }
        }

        return new SearchResponse(resultBoard, madeMoves);
    }
}
