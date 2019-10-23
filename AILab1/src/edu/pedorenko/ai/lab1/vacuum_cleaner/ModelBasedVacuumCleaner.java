package edu.pedorenko.ai.lab1.vacuum_cleaner;

import edu.pedorenko.ai.lab1.Field;
import java.util.Random;

public class ModelBasedVacuumCleaner implements VacuumCleaner {

    private Field field;
    private int currentX;
    private int currentY;

    private String[][] fieldModel = {{".", ".", ".", ".", ".", ".", ".", ".", "."},
            {".", ".", ".", ".", ".", ".", ".", ".", "."},
            {".", ".", ".", ".", ".", ".", ".", ".", "."},
            {".", ".", ".", ".", ".", ".", ".", ".", "."},
            {".", ".", ".", ".", ".", ".", ".", ".", "."},
            {".", ".", ".", ".", ".", ".", ".", ".", "."},
            {".", ".", ".", ".", ".", ".", ".", ".", "."},
            {".", ".", ".", ".", ".", ".", ".", ".", "."},
            {".", ".", ".", ".", ".", ".", ".", ".", "."}};

    private int cleanedRubbishCounter = 0;
    private int wallHitCounter = 0;
    private Random random = new Random(System.currentTimeMillis());

    public ModelBasedVacuumCleaner(Field field, int currentX, int currentY) {
        this.field = field;
        this.currentX = currentX;
        this.currentY = currentY;
    }


    public void makeAMove() {
        if (field.isRubbish(currentX, currentY)) {

            field.removeRubbish(currentX, currentY);
            cleanedRubbishCounter++;

        } else {

            while (true) {
                Action action = Action.values()[random.nextInt(4)];

                boolean stop = false;

                switch (action) {

                    case MOVE_LEFT:
                        if (isKnownWall(currentX, currentY - 1)) {
                            continue;
                        }
                        move(currentX, currentY - 1);
                        stop = true;
                        break;

                    case MOVE_RIGHT:
                        if (isKnownWall(currentX, currentY + 1)) {
                            continue;
                        }
                        move(currentX, currentY + 1);
                        break;

                    case MOVE_UP:
                        if (isKnownWall(currentX - 1, currentY)) {
                            continue;
                        }
                        move(currentX - 1, currentY);
                        break;

                    case MOVE_DOWN:
                        if (isKnownWall(currentX + 1, currentY)) {
                            continue;
                        }
                        move(currentX + 1, currentY);
                        break;

                }

                if (stop) {
                    break;
                }
            }
        }
    }

    private boolean isKnownWall(int nextX, int nextY) {
        return fieldModel[nextX][nextY].equals("0");
    }


    private void move(int nextX, int nextY) {
        if (!field.isWall(nextX, nextY)) {
            currentX = nextX;
            currentY = nextY;
        } else {
            fieldModel[nextX][nextY] = "0";
            wallHitCounter++;
        }
    }

    public int getCleanedRubbishCounter() {
        return cleanedRubbishCounter;
    }

    public int getWallHitCounter() {
        return wallHitCounter;
    }
}
