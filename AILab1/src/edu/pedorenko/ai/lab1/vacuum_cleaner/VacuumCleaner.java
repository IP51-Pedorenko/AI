package edu.pedorenko.ai.lab1.vacuum_cleaner;

public interface VacuumCleaner {

    void makeAMove();

    int getCleanedRubbishCounter();

    int getWallHitCounter();
}
