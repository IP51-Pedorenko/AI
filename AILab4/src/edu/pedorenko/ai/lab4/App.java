package edu.pedorenko.ai.lab4;

public class App {

    public static void main(String[] args) {

        Cave cave = new Cave();
        Agent homa = new Agent(cave);

        while (!homa.isWon() && !homa.isLost()) {

            homa.processSensors(cave.getInformation(homa.getX(), homa.getY()));
            homa.makeMove();
            homa.processActions(cave.getResult(homa.getX(), homa.getY()));
        }

        System.out.println(homa.isWon() ? "Homa win" : homa.getScore() < 0 ? "Homa lost" : "Homa escaped with gold");
        System.out.println(homa.didKillViy() ? "Homa killed Viy" : "Homa didn't kill Viy");
        System.out.println("Score: " + homa.getScore());
    }
}
