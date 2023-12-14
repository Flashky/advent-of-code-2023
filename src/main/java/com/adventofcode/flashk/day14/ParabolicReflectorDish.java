package com.adventofcode.flashk.day14;

import com.adventofcode.flashk.common.Collider2D;
import com.adventofcode.flashk.common.Vector2;

import java.util.ArrayList;
import java.util.List;

public class ParabolicReflectorDish {

    private static final char ROUNDED_ROCK = 'O';
    private static final char CUBED_ROCK = '#';
    private static final char EMPTY = '.';

    private int rows;
    private int cols;
    private List<Collider2D> roundedRocks = new ArrayList<>();
    private List<Collider2D> cubedRocks = new ArrayList<>();

    public ParabolicReflectorDish(List<String> inputs) {

        rows = inputs.size();
        cols = inputs.get(0).length();

        int y = rows;
        for(String input : inputs) {

            char[] values = input.toCharArray();
            for(int i = 0; i < values.length; i++) {
                int x = i+1;
                if(values[i] == ROUNDED_ROCK) {
                    roundedRocks.add(new Collider2D(new Vector2(x,y)));
                } else if(values[i] == CUBED_ROCK) {
                    cubedRocks.add(new Collider2D(new Vector2(x,y)));
                }
            }
            y--;
        }

    }

    public long solveA() {
        move(Direction.NORTH);
        paint(0);
        return countRocks();
    }

    public long solveB(int cycles) {
        System.out.println();
        System.out.println("Cycle = " + 0 + " | Value = " + countRocks());
        for(int i = 0; i < cycles; i++) {
            move(Direction.NORTH);
            move(Direction.WEST);
            move(Direction.SOUTH);
            move(Direction.EAST);
            System.out.println("Cycle = " + (i+1) + " | Value = " + countRocks());
            //paint(i+1);
        }

        // TODO realmente no podemos bruteforcear
        // TODO Check: https://github.com/Flashky/advent-of-code-2022/tree/master/src/main/java/com/adventofcode/flashk/day17
        // TODO buscar un patrón de repetición tal que una vez encontrado ese patrón, podamos comprobar cuantas veces
        // TODO Se vuelve a repetir de aquí a un millón de ciclos

        // Problema 1:
        // ¿Cuando verificar repetición de ciclo?
        // - Hay que tiltear primero en las cuatro direcciones.
        // Una vez tilteado, hay que comprobar si el estado actual del tablero es un estado conocido.

        return countRocks();
    }

    private void move(Direction direction) {
        boolean move;
        do {
            move = tilt(direction);
        } while (move);
    }

    private long countRocks() {
        return roundedRocks.stream().map(r -> r.getStart().getY()).reduce(0, Integer::sum);
    }

    private boolean tilt(Direction direction) {

        Vector2 directionVector = direction.getMove();
        Vector2 directionVectorRollback = direction.getRollback();

        boolean movement = false;
        for(Collider2D roundedRock : roundedRocks) {

            if(isValidRock(roundedRock, direction)) {
                roundedRock.transform(directionVector);
                if(collidesWithAnyRock(roundedRock)) {
                    roundedRock.transform(directionVectorRollback);
                } else {
                    movement = true;
                }
            }
        }
        return movement;
    }

    private boolean isValidRock(Collider2D roundedRock, Direction direction) {
        return switch (direction) {
            case NORTH -> roundedRock.getStart().getY() != rows;
            case WEST -> roundedRock.getStart().getX() > 1;
            case SOUTH -> roundedRock.getStart().getY() > 1;
            case EAST -> roundedRock.getStart().getX() != cols;
        };
    }
    private boolean collidesWithAnyRock(Collider2D roundedRock) {
        if(roundedRocks.stream().filter(r -> r != roundedRock).anyMatch(r -> r.collidesWith(roundedRock))) {
            return true;
        }

        return cubedRocks.stream().anyMatch(c -> c.collidesWith(roundedRock));
    }

    private void paint(int cycle) {

        System.out.println();
        for(int y = 10; y > 0; y--) {
            for(int x = 1; x <= cols; x++) {
                int finalX = x;
                int finalY = y;
                if(roundedRocks.stream().anyMatch(c -> (c.getStart().getX() == finalX) && (c.getStart().getY() == finalY))){
                    System.out.print(ROUNDED_ROCK);
                } else if(cubedRocks.stream().anyMatch(c -> (c.getStart().getX() == finalX) && (c.getStart().getY() == finalY))){
                    System.out.print(CUBED_ROCK);
                } else {
                    System.out.print(EMPTY);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}
