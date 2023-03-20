package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @BeforeEach
    void setUp() {
        System.out.println("SetUp");
    }

    @AfterEach
    void tearDdsdsfown() {
        System.out.println("TearDown");
    }

    @Test
    void testIsVisible() {
        // voir setVisible();
        System.out.println("isVisible");
        setVisible();
    }

    @Test
    void hasBomb() {
        System.out.println("hasBomb");
    }

    @Test
    void setBomb() {
        System.out.println("setBomb");
    }

    @Test
    void setVisible() {
        System.out.println("setVisible");
        Cell cell = new Cell();
        assertFalse(cell.isVisible());
        cell.setVisible();
        assertTrue(cell.isVisible());
        assertEquals(true, cell.isVisible());

    }
}