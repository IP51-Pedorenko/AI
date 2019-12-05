package edu.pedorenko.ai.lab4;

public class Cave {

    private int[][] caveState = {
            {0, 0, 0, 0, 0},
            {0, 666, 0, 0, 0},
            {0, 13, 777, 0, 0},
            {0, 0, 0, 0, 13},
            {0, 0, 13, 0, 0}};

    private boolean viyAlive = true;

    public Sensors getInformation(int x, int y) {

        Sensors sensors = new Sensors();

        int pos1x = x != 4 ? x + 1:-1;
        int pos1y = y;
        int pos2x = x != 0 ? x - 1 : -1;
        int pos2y = y;
        int pos3x = x;
        int pos3y = y != 4 ? y + 1 : -1;
        int pos4x = x;
        int pos4y = y != 0 ? y - 1 : -1;

        if ((pos1x != -1 && caveState[pos1x][pos1y] == 666) ||
                (pos2x != -1 && caveState[pos2x][pos2y] == 666) ||
                (pos3y != -1 && caveState[pos3x][pos3y] == 666) ||
                (pos4y != -1 && caveState[pos4x][pos4y] == 666))
            sensors.setStink(true);

        if ((pos1x != -1 && caveState[pos1x][pos1y] == 13) ||
                (pos2x != -1 && caveState[pos2x][pos2y] == 13) ||
                (pos3y != -1 && caveState[pos3x][pos3y] == 13) ||
                (pos4y != -1 && caveState[pos4x][pos4y] == 13))
            sensors.setWind(true);

        return sensors;
    }

    public Actions getResult(int x, int y) {
        Actions actions = new Actions();
        if (!viyAlive) {
            actions.setScream(true);
        }
        if ((caveState[x][y] == 666 && viyAlive) || (caveState[x][y] == 13))
        {
            actions.setEnemy(true);
            return actions;
        }
        if (caveState[x][y] == 777)
        {
            actions.setShine(true);
            caveState[x][y] = 0;
            return actions;
        }

        return actions;
    }

    public void throwLance(int x, int y, char direction)
    {
        if(direction=='l' && x != 4)
        {
            for(int i=x+1; i<5; i++)
            {
                if (caveState[i][y] == 666) viyAlive = false;
            }
        }
        if (direction == 'r' && x != 0)
        {
            for (int i = x - 1; i >-1; i--)
            {
                if (caveState[i][y] == 666) viyAlive = false;
            }
        }
        if (direction == 'u' && y != 4)
        {
            for (int i = y + 1; i < 5; i++)
            {
                if (caveState[x][i] == 666) viyAlive = false;
            }
        }
        if (direction == 'd' && y != 0)
        {
            for (int i = y - 1; i >-1; i--)
            {
                if (caveState[x][i] == 666) viyAlive = false;
            }
        }
    }

}
