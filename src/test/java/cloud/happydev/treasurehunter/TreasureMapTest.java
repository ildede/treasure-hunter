package cloud.happydev.treasurehunter;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class TreasureMapTest {

    @Test
    void EMPTY_TREASURE_MAP_HAS_ALL_BLANK_TILES() {
        TreasureMap treasureMap = new TreasureMap(3, 3);

        Object[][] expected = {
                {"  ", "  ", "  "},
                {"  ", "  ", "  "},
                {"  ", "  ", "  "}
        };
        assertArrayEquals(expected, treasureMap.getTable());
    }

    @Test
    void EMPTY_TREASURE_MAP_HAS_NO_ACTIONS_LEFT() {
        TreasureMap treasureMap = new TreasureMap(3, 3);

        assertFalse(treasureMap.hasActionsLeft());
    }

    @Test
    void TRESURE_IS_PLACED_TOP_LEFT() {
        TreasureMap treasureMap = new TreasureMap(3, 3);
        treasureMap.addTreasure(1, 1, 5);

        Object[][] expected = {
                {"5", "  ", "  "},
                {"  ", "  ", "  "},
                {"  ", "  ", "  "}
        };
        assertArrayEquals(expected, treasureMap.getTable());
    }

    @Test
    void TRESURE_IS_PLACED_TOP_RIGHT() {
        TreasureMap treasureMap = new TreasureMap(3, 3);
        treasureMap.addTreasure(3, 1, 5);

        Object[][] expected = {
                {"  ", "  ", "5"},
                {"  ", "  ", "  "},
                {"  ", "  ", "  "}
        };
        assertArrayEquals(expected, treasureMap.getTable());
    }

    @Test
    void TRESURE_IS_PLACED_BOTTOM_LEFT() {
        TreasureMap treasureMap = new TreasureMap(3, 3);
        treasureMap.addTreasure(1, 3, 5);

        Object[][] expected = {
                {"  ", "  ", "  "},
                {"  ", "  ", "  "},
                {"5", "  ", "  "}
        };
        assertArrayEquals(expected, treasureMap.getTable());
    }

    @Test
    void TRESURE_IS_PLACED_BOTTOM_RIGTH() {
        TreasureMap treasureMap = new TreasureMap(3, 3);
        treasureMap.addTreasure(3, 3, 5);

        Object[][] expected = {
                {"  ", "  ", "  "},
                {"  ", "  ", "  "},
                {"  ", "  ", "5"}
        };
        assertArrayEquals(expected, treasureMap.getTable());
    }

    @Test
    void MOUNTAIN_IS_PLACED_TOP_LEFT() {
        TreasureMap treasureMap = new TreasureMap(3, 3);
        treasureMap.addMountain(1, 1);

        Object[][] expected = {
                {"X", "  ", "  "},
                {"  ", "  ", "  "},
                {"  ", "  ", "  "}
        };
        assertArrayEquals(expected, treasureMap.getTable());
    }

    @Test
    void MOUNTAIN_IS_PLACED_TOP_RIGHT() {
        TreasureMap treasureMap = new TreasureMap(3, 3);
        treasureMap.addMountain(3, 1);

        Object[][] expected = {
                {"  ", "  ", "X"},
                {"  ", "  ", "  "},
                {"  ", "  ", "  "}
        };
        assertArrayEquals(expected, treasureMap.getTable());
    }

    @Test
    void MOUNTAIN_IS_PLACED_BOTTOM_LEFT() {
        TreasureMap treasureMap = new TreasureMap(3, 3);
        treasureMap.addMountain(1, 3);

        Object[][] expected = {
                {"  ", "  ", "  "},
                {"  ", "  ", "  "},
                {"X", "  ", "  "}
        };
        assertArrayEquals(expected, treasureMap.getTable());
    }

    @Test
    void MOUNTAIN_IS_PLACED_BOTTOM_RIGTH() {
        TreasureMap treasureMap = new TreasureMap(3, 3);
        treasureMap.addMountain(3, 3);

        Object[][] expected = {
                {"  ", "  ", "  "},
                {"  ", "  ", "  "},
                {"  ", "  ", "X"}
        };
        assertArrayEquals(expected, treasureMap.getTable());
    }

    @Test
    void ADVENTURER_IS_PLACED_TOP_LEFT_WITH_PROPER_ORIENTATION() {
        TreasureMap treasureMap = new TreasureMap(3, 3);
        treasureMap.addAdventurer(1, 1, Orientation.N, "A", "");

        Object[][] expected = {
                {"A↑", "  ", "  "},
                {"  ", "  ", "  "},
                {"  ", "  ", "  "}
        };
        assertArrayEquals(expected, treasureMap.getTable());
    }

    @Test
    void ADVENTURER_IS_PLACED_TOP_RIGHT_WITH_PROPER_ORIENTATION() {
        TreasureMap treasureMap = new TreasureMap(3, 3);
        treasureMap.addAdventurer(3, 1, Orientation.O, "A", "");

        Object[][] expected = {
                {"  ", "  ", "A←"},
                {"  ", "  ", "  "},
                {"  ", "  ", "  "}
        };
        assertArrayEquals(expected, treasureMap.getTable());
    }

    @Test
    void ADVENTURER_IS_PLACED_BOTTOM_LEFT_WITH_PROPER_ORIENTATION() {
        TreasureMap treasureMap = new TreasureMap(3, 3);
        treasureMap.addAdventurer(1, 3, Orientation.S, "A", "");

        Object[][] expected = {
                {"  ", "  ", "  "},
                {"  ", "  ", "  "},
                {"A↓", "  ", "  "}
        };
        assertArrayEquals(expected, treasureMap.getTable());
    }

    @Test
    void ADVENTURER_IS_PLACED_BOTTOM_RIGTH_WITH_PROPER_ORIENTATION() {
        TreasureMap treasureMap = new TreasureMap(3, 3);
        treasureMap.addAdventurer(3, 3, Orientation.E, "A", "");

        Object[][] expected = {
                {"  ", "  ", "  "},
                {"  ", "  ", "  "},
                {"  ", "  ", "A→"}
        };
        assertArrayEquals(expected, treasureMap.getTable());
    }

    @Test
    void ADVENTURER_MOVES_RIGHT() {
        TreasureMap treasureMap = new TreasureMap(3, 3);
        treasureMap.addAdventurer(2, 2, Orientation.E, "A", "A");

        treasureMap.executeAdventurerAction();

        Object[][] expected = {
                {"  ", "  ", "  "},
                {"  ", "  ", "A→"},
                {"  ", "  ", "  "}
        };
        assertArrayEquals(expected, treasureMap.getTable());
    }

    @Test
    void ADVENTURER_MOVES_LEFT() {
        TreasureMap treasureMap = new TreasureMap(3, 3);
        treasureMap.addAdventurer(2, 2, Orientation.O, "A", "A");

        treasureMap.executeAdventurerAction();

        Object[][] expected = {
                {"  ", "  ", "  "},
                {"A←", "  ", "  "},
                {"  ", "  ", "  "}
        };
        assertArrayEquals(expected, treasureMap.getTable());
    }

    @Test
    void ADVENTURER_MOVES_UP() {
        TreasureMap treasureMap = new TreasureMap(3, 3);
        treasureMap.addAdventurer(2, 2, Orientation.N, "A", "A");

        treasureMap.executeAdventurerAction();

        Object[][] expected = {
                {"  ", "A↑", "  "},
                {"  ", "  ", "  "},
                {"  ", "  ", "  "}
        };
        assertArrayEquals(expected, treasureMap.getTable());
    }

    @Test
    void ADVENTURER_MOVES_DOWN() {
        TreasureMap treasureMap = new TreasureMap(3, 3);
        treasureMap.addAdventurer(2, 2, Orientation.S, "A", "A");

        treasureMap.executeAdventurerAction();

        Object[][] expected = {
                {"  ", "  ", "  "},
                {"  ", "  ", "  "},
                {"  ", "A↓", "  "}
        };
        assertArrayEquals(expected, treasureMap.getTable());
    }

    @Test
    void ADVENTURER_DOES_NOT_MOVE_ON_MOUNTAIN() {
        TreasureMap treasureMap = new TreasureMap(3, 3);
        treasureMap.addMountain(2, 3);
        treasureMap.addAdventurer(2, 2, Orientation.S, "A", "A");

        treasureMap.executeAdventurerAction();

        Object[][] expected = {
                {"  ", "  ", "  "},
                {"  ", "A↓", "  "},
                {"  ", "X",  "  "}
        };
        assertArrayEquals(expected, treasureMap.getTable());
    }

    @Test
    void ADVENTURER_MOVES_ON_TREASURE_KEEPING_IT() {
        TreasureMap treasureMap = new TreasureMap(3, 3);
        treasureMap.addTreasure(2, 3, 5);
        treasureMap.addAdventurer(2, 2, Orientation.S, "A", "A");

        treasureMap.executeAdventurerAction();

        Object[][] expected = {
                {"  ", "  ", "  "},
                {"  ", "  ", "  "},
                {"  ", "A↓", "  "}
        };
        assertArrayEquals(expected, treasureMap.getTable());
        assertEquals(5, treasureMap.getAdventurers().get(0).getTreasureCount());
    }

    @Test
    void TREASURE_MAP_CAN_HAVE_ALL_TOKEN_AND_MULTIPLE_ADVENTURERS() {
        TreasureMap treasureMap = new TreasureMap(3, 3);
        treasureMap.addTreasure(1, 1, 99);
        treasureMap.addMountain(1, 2);
        treasureMap.addTreasure(1, 3, 1);
        treasureMap.addAdventurer(2, 1, Orientation.O, "A", "");
        treasureMap.addAdventurer(2, 2, Orientation.O, "B", "");
        treasureMap.addAdventurer(2, 3, Orientation.O, "C", "");
        treasureMap.addAdventurer(3, 1, Orientation.S, "D", "");
        treasureMap.addAdventurer(3, 3, Orientation.N, "E", "");

        Object[][] expected = {
                {"99", "A←", "D↓"},
                {"X",  "B←", "  "},
                {"1",  "C←", "E↑"}
        };
        assertArrayEquals(expected, treasureMap.getTable());
    }

    @Test
    void MULTIPLE_ADVENTURERS_CAN_MOVE_AT_SAME_TIME() {
        TreasureMap treasureMap = new TreasureMap(3, 3);
        treasureMap.addTreasure(1, 1, 99);
        treasureMap.addMountain(1, 2);
        treasureMap.addTreasure(1, 3, 1);
        treasureMap.addAdventurer(2, 1, Orientation.O, "A", "A");
        treasureMap.addAdventurer(2, 2, Orientation.O, "B", "A");
        treasureMap.addAdventurer(2, 3, Orientation.O, "C", "A");
        treasureMap.addAdventurer(3, 1, Orientation.S, "D", "A");
        treasureMap.addAdventurer(3, 3, Orientation.N, "E", "A");

        treasureMap.executeAdventurerAction();

        Object[][] expected = {
                {"A←", "  ", "  "},
                {"X",  "B←", "D↓"},
                {"C←", "  ", "E↑"}
        };
        assertArrayEquals(expected, treasureMap.getTable());
        assertEquals(99, treasureMap.getAdventurers().get(0).getTreasureCount());
        assertEquals("A", treasureMap.getAdventurers().get(0).getName());
        assertEquals(new LinkedList<>(Collections.singletonList(Action.W)), treasureMap.getAdventurers().get(0).getActions());
        assertEquals(0, treasureMap.getAdventurers().get(1).getTreasureCount());
        assertEquals("B", treasureMap.getAdventurers().get(1).getName());
        assertEquals(new LinkedList<>(), treasureMap.getAdventurers().get(1).getActions());
        assertEquals(1, treasureMap.getAdventurers().get(2).getTreasureCount());
        assertEquals("C", treasureMap.getAdventurers().get(2).getName());
        assertEquals(new LinkedList<>(Collections.singletonList(Action.W)), treasureMap.getAdventurers().get(2).getActions());
    }
}
