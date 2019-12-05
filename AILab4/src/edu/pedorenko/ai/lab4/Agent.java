package edu.pedorenko.ai.lab4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class Agent {

    private int score;
    private boolean won;
    private boolean lost;

    private int[][] predictedCave = {
            {-1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1},
            {0, -1, -1, -1, -1}};

    private boolean viyAlive = true;

    private boolean hasLance = true;

    private Cave cave;

    private int ladiesCounter = 3;

    private int goldCounter = 1;

    private int returnCounter;

    private List<Coordinates> visited = new ArrayList<>();

    private Coordinates currPosition = new Coordinates(4, 0);

    public Agent(Cave cave) {
        this.cave = cave;
        visited.add(currPosition);
    }

    public void processSensors(Sensors sensors) {
        int pos1x = currPosition.getX() != 4 ? currPosition.getX() + 1 : -1;
        int pos1y = currPosition.getY();
        int pos2x = currPosition.getX() != 0 ? currPosition.getX() - 1 : -1;
        int pos2y = currPosition.getY();
        int pos3x = currPosition.getX();
        int pos3y = currPosition.getY() != 4 ? currPosition.getY() + 1 : -1;
        int pos4x = currPosition.getX();
        int pos4y = currPosition.getY() != 0 ? currPosition.getY() - 1 : -1;

        if (!sensors.isStink() && !sensors.isWind()) {
            if (pos1x != -1) predictedCave[pos1x][pos1y] = 0;
            if (pos2x != -1) predictedCave[pos2x][pos2y] = 0;
            if (pos3y != -1) predictedCave[pos3x][pos3y] = 0;
            if (pos4y != -1) predictedCave[pos4x][pos4y] = 0;
        }
        if (sensors.isWind()) {
            if (pos1x != -1 && predictedCave[pos1x][pos1y] != 0 && predictedCave[pos1x][pos1y] != 1 && predictedCave[pos1x][pos1y] != 3 && predictedCave[pos1x][pos1y] != 5) {
                if (predictedCave[pos1x][pos1y] == -1) predictedCave[pos1x][pos1y] = 1;
                else if (predictedCave[pos1x][pos1y] == 2) predictedCave[pos1x][pos1y] = 3;
            }
            if (pos2x != -1 && predictedCave[pos2x][pos2y] != 0 && predictedCave[pos2x][pos2y] != 1 && predictedCave[pos2x][pos2y] != 3 && predictedCave[pos2x][pos2y] != 5) {
                if (predictedCave[pos2x][pos2y] == -1) predictedCave[pos2x][pos2y] = 1;
                else if (predictedCave[pos2x][pos2y] == 2) predictedCave[pos2x][pos2y] = 3;
            }
            if (pos3y != -1 && predictedCave[pos3x][pos3y] != 0 && predictedCave[pos3x][pos3y] != 1 && predictedCave[pos3x][pos3y] != 3 && predictedCave[pos3x][pos3y] != 5) {
                if (predictedCave[pos3x][pos3y] == -1) predictedCave[pos3x][pos3y] = 1;
                else if (predictedCave[pos3x][pos3y] == 2) predictedCave[pos3x][pos3y] = 3;
            }
            if (pos4y != -1 && predictedCave[pos4x][pos4y] != 0 && predictedCave[pos4x][pos4y] != 1 && predictedCave[pos4x][pos4y] != 3 && predictedCave[pos4x][pos4y] != 5) {
                if (predictedCave[pos4x][pos4y] == -1) predictedCave[pos4x][pos4y] = 1;
                else if (predictedCave[pos4x][pos4y] == 2) predictedCave[pos4x][pos4y] = 3;
            }
        } else {
            if (pos1x != -1 && (predictedCave[pos1x][pos1y] == 3 || predictedCave[pos1x][pos1y] == 1)) {
                if (predictedCave[pos1x][pos1y] == 3) predictedCave[pos1x][pos1y] = 2;
                else if (predictedCave[pos1x][pos1y] == 1) predictedCave[pos1x][pos1y] = -1;
            }
            if (pos2x != -1 && (predictedCave[pos2x][pos2y] == 3 || predictedCave[pos2x][pos2y] == 1)) {
                if (predictedCave[pos2x][pos2y] == 3) predictedCave[pos2x][pos2y] = 2;
                else if (predictedCave[pos2x][pos2y] == 1) predictedCave[pos2x][pos2y] = -1;
            }
            if (pos3y != -1 && (predictedCave[pos3x][pos3y] == 3 || predictedCave[pos3x][pos3y] == 1)) {
                if (predictedCave[pos3x][pos3y] == 3) predictedCave[pos3x][pos3y] = 2;
                else if (predictedCave[pos3x][pos3y] == 1) predictedCave[pos3x][pos3y] = -1;
            }
            if (pos4y != -1 && (predictedCave[pos4x][pos4y] == 3 || predictedCave[pos4x][pos4y] == 1)) {
                if (predictedCave[pos4x][pos4y] == 3) predictedCave[pos4x][pos4y] = 2;
                else if (predictedCave[pos4x][pos4y] == 1) predictedCave[pos4x][pos4y] = -1;
            }
        }

        if (sensors.isStink() && viyAlive) {
            if (pos1x != -1 && predictedCave[pos1x][pos1y] != 0 && predictedCave[pos1x][pos1y] != 2 && predictedCave[pos1x][pos1y] != 3 && predictedCave[pos1x][pos1y] != 4) {
                if (predictedCave[pos1x][pos1y] == -1) predictedCave[pos1x][pos1y] = 2;
                else if (predictedCave[pos1x][pos1y] == 1) predictedCave[pos1x][pos1y] = 3;
            }
            if (pos2x != -1 && predictedCave[pos2x][pos2y] != 0 && predictedCave[pos2x][pos2y] != 2 && predictedCave[pos2x][pos2y] != 3 && predictedCave[pos2x][pos2y] != 4) {
                if (predictedCave[pos2x][pos2y] == -1) predictedCave[pos2x][pos2y] = 2;
                else if (predictedCave[pos2x][pos2y] == 1) predictedCave[pos2x][pos2y] = 3;
            }
            if (pos3y != -1 && predictedCave[pos3x][pos3y] != 0 && predictedCave[pos3x][pos3y] != 2 && predictedCave[pos3x][pos3y] != 3 && predictedCave[pos3x][pos3y] != 4) {
                if (predictedCave[pos3x][pos3y] == -1) predictedCave[pos3x][pos3y] = 2;
                else if (predictedCave[pos3x][pos3y] == 1) predictedCave[pos3x][pos3y] = 3;
            }
            if (pos4y != -1 && predictedCave[pos4x][pos4y] != 0 && predictedCave[pos4x][pos4y] != 2 && predictedCave[pos4x][pos4y] != 3 && predictedCave[pos4x][pos4y] != 4) {
                if (predictedCave[pos4x][pos4y] == -1) predictedCave[pos4x][pos4y] = 2;
                else if (predictedCave[pos4x][pos4y] == 1) predictedCave[pos4x][pos4y] = 3;
            }
        } else {
            if (pos1x != -1 && (predictedCave[pos1x][pos1y] == 3 || predictedCave[pos1x][pos1y] == 2)) {
                if (predictedCave[pos1x][pos1y] == 3) predictedCave[pos1x][pos1y] = 1;
                else if (predictedCave[pos1x][pos1y] == 2) predictedCave[pos1x][pos1y] = -1;
            }
            if (pos2x != -1 && (predictedCave[pos2x][pos2y] == 3 || predictedCave[pos2x][pos2y] == 2)) {
                if (predictedCave[pos2x][pos2y] == 3) predictedCave[pos2x][pos2y] = 1;
                else if (predictedCave[pos2x][pos2y] == 2) predictedCave[pos2x][pos2y] = -1;
            }
            if (pos3y != -1 && (predictedCave[pos3x][pos3y] == 3 || predictedCave[pos3x][pos3y] == 2)) {
                if (predictedCave[pos3x][pos3y] == 3) predictedCave[pos3x][pos3y] = 1;
                else if (predictedCave[pos3x][pos3y] == 2) predictedCave[pos3x][pos3y] = -1;
            }
            if (pos4y != -1 && (predictedCave[pos4x][pos4y] == 3 || predictedCave[pos4x][pos4y] == 2)) {
                if (predictedCave[pos4x][pos4y] == 3) predictedCave[pos4x][pos4y] = 1;
                else if (predictedCave[pos4x][pos4y] == 2) predictedCave[pos4x][pos4y] = -1;
            }
        }

    }

    public void makeMove() {
        predictedCave[currPosition.getX()][currPosition.getY()] = 0;

        int pos1x = currPosition.getX() != 4 ? currPosition.getX() + 1 : -1;
        int pos1y = currPosition.getY();
        int pos2x = currPosition.getX() != 0 ? currPosition.getX() - 1 : -1;
        int pos2y = currPosition.getY();
        int pos3x = currPosition.getX();
        int pos3y = currPosition.getY() != 4 ? currPosition.getY() + 1 : -1;
        int pos4x = currPosition.getX();
        int pos4y = currPosition.getY() != 0 ? currPosition.getY() - 1 : -1;


        List<Coordinates> notVisited = new ArrayList<>();

        if (pos1x != -1 && !visited.contains(new Coordinates(pos1x, pos2y)))
            notVisited.add(new Coordinates(pos1x, pos1y));
        if (pos2x != -1 && !visited.contains(new Coordinates(pos2x, pos2y)))
            notVisited.add(new Coordinates(pos2x, pos2y));
        if (pos3y != -1 && !visited.contains(new Coordinates(pos3x, pos3y)))
            notVisited.add(new Coordinates(pos3x, pos3y));
        if (pos4y != -1 && !visited.contains(new Coordinates(pos4x, pos4y)))
            notVisited.add(new Coordinates(pos4x, pos4y));

        List<Coordinates> notVisitedSafe = notVisited.stream().filter(coordinates -> predictedCave[coordinates.getX()][coordinates.getY()] == 0).collect(Collectors.toList());
        List<Coordinates> notVisitedUnknown = notVisited.stream().filter(coordinates -> predictedCave[coordinates.getX()][coordinates.getY()] == -1).collect(Collectors.toList());

        if (notVisitedSafe.size() != 0) {
            returnCounter = 0;
            currPosition = notVisitedSafe.get(0);
            score -= 1;
            visited.add(currPosition);
        } else if (visited.indexOf(currPosition) > 0 && returnCounter <= 16) {
            int returnPositionId = visited.indexOf(currPosition) - 1;
            returnCounter += 1;
            currPosition = visited.get(returnPositionId);

            score -= 1;
        } else if (hasLance) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (predictedCave[i][j] == 3 && hasLance) {
                        pos1x = i != 3 ? i + 1 : -1;
                        pos1y = j;
                        pos2x = i != 0 ? i - 1 : -1;
                        pos2y = j;
                        pos3x = i;
                        pos3y = j != 3 ? j + 1 : -1;
                        pos4x = i;
                        pos4y = j != 0 ? j - 1 : -1;

                        if (pos1x != -1 && predictedCave[pos1x][pos1y] == 0) {
                            score -= 100;
                            score -= Math.abs(currPosition.getX() - pos1x);
                            score -= Math.abs(currPosition.getY() - pos1y);

                            currPosition = new Coordinates(pos1x, pos1y);

                            cave.throwLance(currPosition.getX(), currPosition.getY(), 'r');
                        } else if (pos2x != -1 && predictedCave[pos2x][pos2y] == 0) {
                            score -= 100;
                            score -= Math.abs(currPosition.getX() - pos2x);
                            score -= Math.abs(currPosition.getY() - pos2y);

                            currPosition = new Coordinates(pos2x, pos2y);

                            cave.throwLance(currPosition.getX(), currPosition.getY(), 'l');
                        } else if (pos3y != -1 && predictedCave[pos3x][pos3y] == 0) {
                            score -= 100;
                            score -= Math.abs(currPosition.getX() - pos3x);
                            score -= Math.abs(currPosition.getY() - pos3y);

                            currPosition = new Coordinates(pos3x, pos3y);

                            cave.throwLance(currPosition.getX(), currPosition.getY(), 'd');
                        } else if (pos4y != -1 && predictedCave[pos4x][pos4y] == 0) {
                            score -= 100;
                            score -= Math.abs(currPosition.getX() - pos4x);
                            score -= Math.abs(currPosition.getY() - pos4y);

                            currPosition = new Coordinates(pos4x, pos4y);

                            cave.throwLance(currPosition.getX(), currPosition.getY(), 'u');
                        }
                    }
                }
            }

            hasLance = false;
        } else if (returnCounter > 16 && notVisitedUnknown.size() != 0) {
            returnCounter = 0;
            currPosition = notVisitedUnknown.get(0);
            score -= 1;
            visited.add(currPosition);
        } else if (goldCounter < 1) {
            lost = true;
        } else {
            boolean move = false;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if ((predictedCave[i][j] == 1 || predictedCave[i][j] == 2) && !move) {
                        move = true;
                        score -= Math.abs(currPosition.getX() - i);
                        score -= Math.abs(currPosition.getY() - j);

                        currPosition = new Coordinates(i, j);
                    }
                }
            }
            if (!move) {
                lost = true;
            }
        }
    }

    public void processActions(Actions actions) {
        if (actions.isShine()) {
            score += 1000;
            goldCounter--;
        }
        if (actions.isScream() && viyAlive) {
            viyAlive = false;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (predictedCave[i][j] == 3) predictedCave[i][j] = 1;
                    else if (predictedCave[i][j] == 2) predictedCave[i][j] = -1;
                }
            }
        }
        if (actions.isEnemy()) {
            score -= 1000;
            lost = true;
        }
        if (goldCounter == 0) {
            won = true;
        }
    }

    public boolean isWon() {
        return won;
    }

    public boolean isLost() {
        return lost;
    }

    public int getX() {
        return currPosition.getX();
    }

    public int getY() {
        return currPosition.getY();
    }

    public boolean didKillViy() {
        return !viyAlive;
    }

    public int getScore() {
        return score;
    }
}
