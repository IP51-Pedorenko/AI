package edu.pedorenko.ai.lab1;

import java.util.Arrays;
import java.util.Random;

public class Field {

    private String[][] fields = {{"0", "0", "0", "0", "0", "0", "0", "0", "0"},
                                 {"0", ".", "0", "0", "0", "0", "0", "0", "0"},
                                 {"0", ".", ".", ".", ".", "0", ".", "0", "0"},
                                 {"0", ".", "0", "0", ".", "0", ".", ".", "0"},
                                 {"0", ".", "0", ".", ".", "0", ".", "0", "0"},
                                 {"0", ".", "0", ".", ".", ".", ".", ".", "0"},
                                 {"0", ".", ".", "0", ".", "0", ".", ".", "0"},
                                 {"0", ".", "0", "0", ".", "0", "0", "0", "0"},
                                 {"0", "0", "0", "0", "0", "0", "0", "0", "0"}};

    private Random random = new Random(System.currentTimeMillis());

    public Field() {
    }

    public boolean isWall(int x, int y) {
        return fields[x][y].equals("0");
    }

    public boolean isRubbish(int x, int y) {
        return fields[x][y].equals("X");
    }

    public void removeRubbish(int x, int y) {
        if (!isRubbish(x, y)) {
            throw new RuntimeException("Attempt to remove rubbish from field [ " + x + ", " + y + "]. No rubbish here");
        }

        fields[x][y] = ".";
    }

    public void addRubbish() {

        if (Arrays.stream(fields)
                .flatMap(Arrays::stream)
                .anyMatch(field -> field.equals("."))) {

            int x;
            int y;
            do {
                x = random.nextInt(7) + 1;
                y = random.nextInt(7) + 1;
            } while (!fields[x][y].equals("."));

            fields[x][y] = "X";
        }
    }
}
