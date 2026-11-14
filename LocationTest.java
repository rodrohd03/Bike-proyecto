import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test implementation of the {@link Location} class.
 * Provides unit tests for methods like {@code distance()} and {@code nextLocation()}.
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2026.10.13 DP classes (adaptado a Java 8+)
 */
public class LocationTest
{
    /**
     * Default constructor for test class LocationTest.
     */
    public LocationTest()
    {
    }

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        // No setup required for Location tests
    }

    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        // No teardown required for Location tests
    }
    

    @Test
    public void testDistance()
    {
        Location location1 = new Location(1, 2);
        Location location2 = new Location(42, 12);
        assertEquals(41, location1.distance(location2));
    }

    @Test
    public void testAdjacentLocations()
    {
        Location location1 = new Location(0, 0);
        Location location2 = new Location(1, 0);
        assertEquals(location2, location1.nextLocation(location2));
    }

    @Test
    public void testNonAdjacentLocations()
    {
        Location location1 = new Location(0, 0);
        Location location2 = new Location(10, 10);
        assertNotEquals(location2, location1.nextLocation(location2));
    }
}


