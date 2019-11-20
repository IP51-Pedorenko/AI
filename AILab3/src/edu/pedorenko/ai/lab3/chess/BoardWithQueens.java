package edu.pedorenko.ai.lab3.chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BoardWithQueens {

    public static final int BOARD_SIDE_SIZE = 8;

    private static Random random = new Random(System.currentTimeMillis());

    private int[][] board =
                   {{0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0}};

    public BoardWithQueens() {
    }

    public BoardWithQueens(int[][] board) {
        this.board = board;
    }

    public void setQueensRandomly() {

        int queensCountDown = BOARD_SIDE_SIZE;
        do {
            int i = random.nextInt(BOARD_SIDE_SIZE);
            int j = random.nextInt(BOARD_SIDE_SIZE);
            if (board[i][j] != 1) {
                board[i][j] = 1;
                queensCountDown--;
            }
        } while (queensCountDown != 0);
    }

    public boolean isQueen(Coordinates coordinates) {
        return isQueen(coordinates.getI(), coordinates.getJ());
    }

    public boolean isQueen(int i, int j) {
        return board[i][j] == 1;
    }

    public void moveQueen(Coordinates from, Coordinates to) {
        if (!isQueen(from)) throw new RuntimeException("No queen on position: " + from);
        if (isQueen(to)) throw new RuntimeException("There is already a queen on position: " + to);
        board[from.getI()][from.getJ()] = 0;
        board[to.getI()][to.getJ()] = 1;
    }

    public List<Coordinates> getQueensCoordinates() {
        List<Coordinates> coordinates = new ArrayList<>();
        for (int i = 0; i < BOARD_SIDE_SIZE; ++i) {
            for (int j = 0; j < BOARD_SIDE_SIZE; ++j) {
                if (isQueen(i, j)) {
                    coordinates.add(new Coordinates(i, j));
                }
            }
        }
        return coordinates;
    }

    public int F2() {

        int pairsAmount = 0;

        for (int i = 0; i < BOARD_SIDE_SIZE; ++i) {
            int queensOnRow = 0;
            for (int j = 0; j < BOARD_SIDE_SIZE; ++j) {
                if (isQueen(i, j)) {
                    queensOnRow++;
                }
            }
            pairsAmount += queensOnRow * (queensOnRow - 1);
        }

        for (int j = 0; j < BOARD_SIDE_SIZE; ++j) {
            int queensOnColumn = 0;
            for (int i = 0; i < BOARD_SIDE_SIZE; ++i) {
                if (isQueen(i, j)) {
                    queensOnColumn++;
                }
            }
            pairsAmount += queensOnColumn * (queensOnColumn - 1);
        }

        for (int k = BOARD_SIDE_SIZE - 1; k > 0; --k) {
            int queensOnMainDiagonal = 0;
            for (int j = 0; j < BOARD_SIDE_SIZE - k; ++j) {
                int i = k + j;
                if (isQueen(i, j)) {
                    queensOnMainDiagonal++;
                }
            }
            pairsAmount += queensOnMainDiagonal * (queensOnMainDiagonal - 1);
        }
        for (int k = 0; k < BOARD_SIDE_SIZE; ++k) {
            int queensOnMainDiagonal = 0;
            for (int i = 0; i < BOARD_SIDE_SIZE - k; ++i) {
                int j = k + i;
                if (isQueen(i, j)) {
                    queensOnMainDiagonal++;
                }
            }
            pairsAmount += queensOnMainDiagonal * (queensOnMainDiagonal - 1);
        }

        for (int k = BOARD_SIDE_SIZE - 1; k >= 0; --k) {
            int queensOnOtherDiagonal = 0;
            for (int j = 0; j < BOARD_SIDE_SIZE - k; ++j) {
                int i = BOARD_SIDE_SIZE - k - j - 1;
                if (isQueen(i, j)) {
                    queensOnOtherDiagonal++;
                }
            }
            pairsAmount += queensOnOtherDiagonal * (queensOnOtherDiagonal - 1);
        }
        for (int k = 1; k < BOARD_SIDE_SIZE; ++k) {
            int queensOnOtherDiagonal = 0;
            for (int i = BOARD_SIDE_SIZE - 1; i >= k; --i) {
                int j = k + BOARD_SIDE_SIZE - i - 1;
                if (isQueen(i, j)) {
                    queensOnOtherDiagonal++;
                }
            }
            pairsAmount += queensOnOtherDiagonal * (queensOnOtherDiagonal - 1);
        }

        return pairsAmount;
    }

    public BoardWithQueens clone() {
        int[][] boardClone = new int[BOARD_SIDE_SIZE][BOARD_SIDE_SIZE];
        for (int i = 0; i < BOARD_SIDE_SIZE; ++i) {
            System.arraycopy(board[i], 0, boardClone[i], 0, BOARD_SIDE_SIZE);
        }
        return new BoardWithQueens(boardClone);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        Arrays.stream(board).forEach(row -> stringBuffer.append(Arrays.toString(row)).append("\n"));
        return stringBuffer.toString();
    }
}
