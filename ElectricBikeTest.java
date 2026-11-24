

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class ElectricBikeTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ElectricBikeTest
{
    private Location Inicio;
    Location locAccesible;
    Location estacion;
    Location inaccesible;
    EBCompany eBCompan1;
    Dock dock1;
    BikeStation bikeStat1;
    /**
     * Default constructor for test class ElectricBikeTest
     */
    public ElectricBikeTest()
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
        Inicio = new Location(0, 0);
        locAccesible = new Location(1, 1);
        estacion = new Location(2, 2);
        inaccesible = new Location(5, 5);
        eBCompan1 = new EBCompany("asf");
        dock1 = new Dock("002", 5, 5);
        bikeStat1 = new BikeStation("003", estacion);
        eBCompan1.addBikeStation(bikeStat1);
        bikeStat1.addDock(dock1);
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
    public void testCreation()
    {   
        setUp();
        ElectricBike electric1 = new ElectricBike(eBCompan1, Inicio, inaccesible, "001", 65);
        assertEquals(65, electric1.getBatteryCapacity());
        assertEquals(9999, electric1.getArrivingStep());
        assertEquals(inaccesible, electric1.getTargetLocation());
        assertEquals(false, electric1.getArrived());
        assertEquals(false, electric1.getCanMove());
        assertEquals("001", electric1.getIdBike());
        assertEquals(null, electric1.getRechargingLocation());
    }

    @Test
    public void testEnoughBattery()
    {
        setUp();
        ElectricBike electric1 = new ElectricBike(eBCompan1, Inicio, inaccesible, "001", 15);
        Location location3 = new Location(2, 2);
        assertEquals(false, electric1.enoughBattery(inaccesible));
        assertEquals(true, electric1.enoughBattery(location3));
    }

    @Test
    public void testCalculateRoute()
    {
        setUp();
        ElectricBike electric1 = new ElectricBike(eBCompan1, Inicio, locAccesible, "010", 15);
        electric1.calculateRoute(locAccesible);
        assertEquals(true, electric1.getCanMove());
        assertEquals(null, electric1.getRechargingLocation());
        electric1.calculateRoute(inaccesible);
        assertEquals(estacion, electric1.getRechargingLocation());
        assertEquals(true, electric1.getCanMove());
        Location location1 = new Location(4545, 454554);
        electric1.calculateRoute(location1);
        assertEquals(false, electric1.getCanMove());
    }

   

    @Test
    public void testRecharge()
    {
        setUp();
        ElectricBike electric1 = new ElectricBike(eBCompan1, estacion, inaccesible, "5", 35);
        electric1.reduceBatteryLevel();
        electric1.reduceBatteryLevel();
        electric1.reduceBatteryLevel();
        electric1.reduceBatteryLevel();
        electric1.recharge(2);
        assertEquals(35, electric1.getBatteryLevel());
    }
}




