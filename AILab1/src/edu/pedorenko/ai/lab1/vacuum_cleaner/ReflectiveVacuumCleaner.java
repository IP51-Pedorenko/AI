package edu.pedorenko.ai.lab1.vacuum_cleaner;

import edu.pedorenko.ai.lab1.Field;
import java.util.Random;

public class ReflectiveVacuumCleaner implements VacuumCleaner {

    private Field field;
    private int currentX;
    private int currentY;

    private int cleanedRubbishCounter = 0;
    private int wallHitCounter = 0;
    private Random random = new Random(System.currentTimeMillis());

    public ReflectiveVacuumCleaner(Field field, int currentX, int currentY) {
        this.field = field;
        this.currentX = currentX;
        this.currentY = currentY;
    }

    public void makeAMove() {

        if (field.isRubbish(currentX, currentY)) {

            field.removeRubbish(currentX, currentY);
            cleanedRubbishCounter++;

        } else {

            Action action = Action.values()[random.nextInt(4)];

            switch (action) {

                case MOVE_LEFT:
                    move(currentX, currentY - 1);
                    break;

                case MOVE_RIGHT:
                    move(currentX, currentY + 1);
                    break;

                case MOVE_UP:
                    move(currentX - 1, currentY);
                    break;

                case MOVE_DOWN:
                    move(currentX + 1, currentY);
                    break;

            }
        }
    }

    public int getCleanedRubbishCounter() {
        return cleanedRubbishCounter;
    }

    public int getWallHitCounter() {
        return wallHitCounter;
    }

    private void move(int nextX, int nextY) {
        if (!field.isWall(nextX, nextY)) {
            currentX = nextX;
            currentY = nextY;
        } else {
            wallHitCounter++;
        }
    }
}
