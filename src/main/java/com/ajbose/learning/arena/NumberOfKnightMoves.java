package com.ajbose.learning.arena;/* IMPORTANT: Multiple classes and nested static classes are supported */


import java.util.*;


public class NumberOfKnightMoves {

    public static void main(String args[]) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt() - 1;
        int column = scanner.nextInt() - 1;
        if (row < 0 || column < 0 || row > 10 || column > 10) {
            System.out.println("0");
            return;
        }

        int n = scanner.nextInt();
        LinkedList<Position> positionQueue = new LinkedList<Position>();
        Set<Position> finalPositionSet = new HashSet<Position>(10);
        Position startPosition = new Position(row, column, 0);
        positionQueue.add(startPosition);
        while (!positionQueue.isEmpty()) {
            Position currentPosition = positionQueue.remove();
            if (currentPosition.n == n - 1) {
                finalPositionSet.addAll(getNextPositions(currentPosition));
            } else {
                positionQueue.addAll(getNextPositions(currentPosition));
            }
        }

        System.out.println(finalPositionSet.size());
    }

    static Set<Position> getNextPositions(Position position) {
        Set<Position> positionSet = new HashSet<Position>();
        int row = position.row;
        int column = position.column;
        int n = position.n;
        if (row - 2 >= 0 && column - 1 >= 0) {
            positionSet.add(new Position(row - 2, column - 1, n + 1));
        }
        if (row - 2 >= 0 && column + 1 < 10) {
            positionSet.add(new Position(row - 2, column + 1, n + 1));
        }
        if (row + 2 < 10 && column + 1 < 10) {
            positionSet.add(new Position(row + 2, column + 1, n + 1));
        }
        if (row + 2 < 10 && column - 1 >= 0) {
            positionSet.add(new Position(row + 2, column - 1, n + 1));
        }
        if (column - 2 >= 0 && row - 1 >= 0) {
            positionSet.add(new Position(row - 1, column - 2, n + 1));
        }
        if (column - 2 >= 0 && row + 1 < 10) {
            positionSet.add(new Position(row + 1, column - 2, n + 1));
        }
        if (column + 2 < 10 && row + 1 < 10) {
            positionSet.add(new Position(row + 1, column + 2, n + 1));
        }
        if (column + 2 < 10 && row - 1 >= 0) {
            positionSet.add(new Position(row - 1, column + 2, n + 1));
        }
        return positionSet;
    }

    static class Position {
        public int row;
        public int column;
        public int n;

        Position(int row, int column, int n) {
            this.row = row;
            this.column = column;
            this.n = n;
        }

        @Override
        public boolean equals(Object other) {
            Position instance2 = (Position) other;
            if ((this.row == instance2.row) && (this.column == instance2.column)) {
                return true;
            }
            return false;
        }
    }
}
