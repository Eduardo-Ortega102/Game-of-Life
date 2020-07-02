import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WorldShould {

    @Test
    public void be_a_square() {
        assertThat(new World(3).size()).isEqualTo(9);
        assertThat(new World(4).size()).isEqualTo(16);
    }

    @Test
    public void be_full_of_dead_cells_at_the_beginning() {
        assertThat(new World(3).getState()).isEqualTo(State.DEAD);
    }

    @Test
    public void set_an_alive_cell_in_a_position() {
        World world = new World(3);
        world.setAliveCellAt(0, 0);
        assertThat(world.stateOfCellAt(0, 0)).isEqualTo(State.ALIVE);
    }

    @Test
    public void be_alive_when_it_has_alive_cells() {
        World world = new World(3);
        world.setAliveCellAt(0, 0);
        world.setAliveCellAt(0, 1);
        assertThat(world.getState()).isEqualTo(State.ALIVE);
    }

    @Test
    public void have_a_string_representation() {
        World world = new World(3);
        world.setAliveCellAt(0, 0);
        world.setAliveCellAt(1, 1);
        assertThat(world.toString()).isEqualTo(
                "100\n" +
                "010\n" +
                "000\n"
        );
    }

    @Test
    public void count_alive_neighbours_of_position() {
        World world = new World(3);
        world.setAliveCellAt(0, 0);
        assertThat(world.countAliveNeighboursOf(0, 0)).isEqualTo(0);
        world.setAliveCellAt(0, 1);
        assertThat(world.countAliveNeighboursOf(0, 0)).isEqualTo(1);
        world.setAliveCellAt(1, 1);
        world.setAliveCellAt(2, 2);
        assertThat(world.countAliveNeighboursOf(0, 0)).isEqualTo(2);
        assertThat(world.countAliveNeighboursOf(0, 1)).isEqualTo(2);
        assertThat(world.countAliveNeighboursOf(0, 2)).isEqualTo(2);
        assertThat(world.countAliveNeighboursOf(1, 0)).isEqualTo(3);
        assertThat(world.countAliveNeighboursOf(1, 1)).isEqualTo(3);
        assertThat(world.countAliveNeighboursOf(1, 2)).isEqualTo(3);
        assertThat(world.countAliveNeighboursOf(2, 0)).isEqualTo(1);
        assertThat(world.countAliveNeighboursOf(2, 1)).isEqualTo(2);
        assertThat(world.countAliveNeighboursOf(2, 2)).isEqualTo(1);
    }

    @Test
    public void mutate_population() {
        World world = new World(3);
        world.setAliveCellAt(0, 0);
        world.setAliveCellAt(0, 1);
        world.setAliveCellAt(1, 1);
        world.setAliveCellAt(2, 2);
        assertThat(world.toString()).isEqualTo(
                "110\n" +
                "010\n" +
                "001\n"
        );
        world.mutate();
        assertThat(world.toString()).isEqualTo(
                "110\n" +
                "111\n" +
                "000\n"
        );
        world.mutate();
        assertThat(world.toString()).isEqualTo(
                "101\n" +
                "101\n" +
                "010\n"
        );
        world.mutate();
        assertThat(world.toString()).isEqualTo(
                "000\n" +
                "101\n" +
                "010\n"
        );
        world.mutate();
        assertThat(world.toString()).isEqualTo(
                "000\n" +
                "010\n" +
                "010\n"
        );
        world.mutate();
        assertThat(world.toString()).isEqualTo(
                "000\n" +
                "000\n" +
                "000\n"
        );
    }
}
