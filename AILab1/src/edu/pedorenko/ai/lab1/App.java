package edu.pedorenko.ai.lab1;

import edu.pedorenko.ai.lab1.vacuum_cleaner.ModelBasedVacuumCleaner;
import edu.pedorenko.ai.lab1.vacuum_cleaner.RandomVacuumCleaner;
import edu.pedorenko.ai.lab1.vacuum_cleaner.ReflectiveVacuumCleaner;
import edu.pedorenko.ai.lab1.vacuum_cleaner.VacuumCleaner;

public class App {

    private static final int MOVES = 100;

    public static void main(String[] args) {
        Field field = new Field();
        VacuumCleaner vacuumCleaner = new RandomVacuumCleaner(field, 3, 4);

        int generatedRubbish = 0;

        for (int i = 0; i < MOVES; ++i) {

            if (i % 5 == 0) {
                field.addRubbish();
                generatedRubbish++;
            }

            vacuumCleaner.makeAMove();
        }

        int cleanedRubbish = vacuumCleaner.getCleanedRubbishCounter();
        int wallHits = vacuumCleaner.getWallHitCounter();

        System.out.println("Cleaned rubbish: " + cleanedRubbish);
        System.out.println("Wall hits: " + wallHits);
        System.out.println("Efficiency: " + cleanedRubbish / (double) generatedRubbish * 100 + "%");
    }
}
