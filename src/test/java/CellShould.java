
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CellShould {

    @Test
    public void not_change_state_when_it_has_two_neighbours() {
        Cell alive_cell = new Cell(State.ALIVE);
        Cell dead_cell  = new Cell(State.DEAD);
        alive_cell.updateState(2);
        dead_cell.updateState(2);
        assertThat(alive_cell.getState()).isEqualTo(State.ALIVE);
        assertThat(dead_cell.getState()).isEqualTo(State.DEAD);
    }

    @Test
    public void set_state_as_alive_when_it_has_three_neighbours() {
        Cell alive_cell = new Cell(State.ALIVE);
        Cell dead_cell  = new Cell(State.DEAD);
        alive_cell.updateState(3);
        dead_cell.updateState(3);
        assertThat(alive_cell.getState()).isEqualTo(State.ALIVE);
        assertThat(dead_cell.getState()).isEqualTo(State.ALIVE);
    }

    @Test
    public void set_state_as_dead_when_it_has_less_than_two_neighbours() {
        Cell cell_1 = new Cell(State.ALIVE);
        Cell cell_2 = new Cell(State.ALIVE);
        cell_1.updateState(0);
        cell_2.updateState(1);
        assertThat(cell_1.getState()).isEqualTo(State.DEAD);
        assertThat(cell_2.getState()).isEqualTo(State.DEAD);
    }

    @Test
    public void set_state_as_dead_when_it_has_more_than_three_neighbours() {
        Cell cell_1 = new Cell(State.ALIVE);
        Cell cell_2 = new Cell(State.ALIVE);
        cell_1.updateState(4);
        cell_2.updateState(5);
        assertThat(cell_1.getState()).isEqualTo(State.DEAD);
        assertThat(cell_2.getState()).isEqualTo(State.DEAD);
    }

    @Test
    public void have_a_string_representation() {
        assertThat(new Cell(State.ALIVE).toString()).isEqualTo("1");
        assertThat(new Cell(State.DEAD).toString()).isEqualTo("0");
    }
}
