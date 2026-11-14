

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class BikeStationTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BikeStationTest
{
    /**
     * Default constructor for test class BikeStationTest
     */
    public BikeStationTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void testAddDock()
    {
        Location location1 = new Location(1, 2);
        BikeStation bikeStat1 = new BikeStation("003", location1);
        Dock dock1 = new Dock("003", 5, 2);
        bikeStat1.addDock(dock1);
        bikeStat1.getDocksList();
        assertEquals(dock1, bikeStat1.getFreeDock());
    }
}
