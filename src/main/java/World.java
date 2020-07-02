import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class World {

    private final Cell[][] population;

    public World(int dimension) {
        this.population = new Cell[dimension][dimension];
        populateWorld();
    }

    private void populateWorld() {
        for (Cell[] cells : population)
            for (int i = 0; i < cells.length; i++)
                cells[i] = new Cell(State.DEAD);
    }

    public int size() {
        return population.length * population[0].length;
    }

    public int getState() {
        int state = State.DEAD;
        for (Cell[] cells : population)
            for (Cell cell : cells) state |= cell.getState();
        return state;
    }

    public void setAliveCellAt(int coordinateX, int coordinateY) {
        population[coordinateX][coordinateY].setState(State.ALIVE);
    }

    public int stateOfCellAt(int coordinateX, int coordinateY) {
        return population[coordinateX][coordinateY].getState();
    }

    public int countAliveNeighboursOf(int coordinateX, int coordinateY) {
        int sum = 0;
        for(Pair<Integer, Integer> coordinate : getNeighboursCoordinates(coordinateX, coordinateY))
            sum += population[coordinate.getKey()][coordinate.getValue()].getState();
        return sum;
    }

    private List<Pair<Integer, Integer>> getNeighboursCoordinates(int coordinateX, int coordinateY) {
        List<Pair<Integer, Integer>> coordinates = new ArrayList<>();
        for (int x : getNeighbourIndexes(coordinateX)) {
            for (int y : getNeighbourIndexes(coordinateY)) coordinates.add(new Pair<>(x, y));
        }
        coordinates.remove(new Pair<>(coordinateX, coordinateY));
        return coordinates;
    }

    private int[] getNeighbourIndexes(int coordinate) {
        return IntStream.rangeClosed(
                Math.max(coordinate - 1, 0),
                Math.min(coordinate + 1, population[0].length - 1)
        ).toArray();
    }

    public void mutate() {
        int[][] neighbours = new int[population.length][population[0].length];
        for(int x : matrixIndexes()){
            for(int y : matrixIndexes()) neighbours[x][y] = countAliveNeighboursOf(x, y);
        }
        for(int x : matrixIndexes()){
            for(int y : matrixIndexes()) population[x][y].updateState(neighbours[x][y]);
        }
    }

    private int[] matrixIndexes() {
        return IntStream.rangeClosed(0, population[0].length - 1).toArray();
    }

    @Override
    public String toString() {
        String world = "";
        for (Cell[] cells : population) {
            for (Cell cell : cells) world += cell;
            world += "\n";
        }
        return world;
    }

}
