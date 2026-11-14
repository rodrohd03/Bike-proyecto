

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class DockTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DockTest
{
    /**
     * Default constructor for test class DockTest
     */
    public DockTest()
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
    public void testRecharge()
    {
        EBCompany eBCompan1 = new EBCompany("Test1");
        Location start = new Location(2, 1);
        Location target = new Location(412, 32);
        ElectricBike electric1 = new ElectricBike(eBCompan1, start, target, "01", 35);
        electric1.reduceBatteryLevel();
        electric1.reduceBatteryLevel();
        electric1.reduceBatteryLevel();
        Dock dock1 = new Dock("001", 5, 5);
        dock1.recharge(electric1);
        dock1.getChargingFee();
        assertEquals(1, dock1.getNumberEBRecharged());
        assertEquals(75, dock1.getAmountCollected(), 0.1);
    }
}
