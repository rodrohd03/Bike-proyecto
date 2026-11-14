

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
        EBCompany eBCompan1 = new EBCompany("ebc");
        Location location1 = new Location(2, 1);
        Location location2 = new Location(321, 213);
        ElectricBike electric1 = new ElectricBike(eBCompan1, location1, location2, "001", 65);
        assertEquals(65, electric1.getBatteryCapacity());
        assertEquals(9999, electric1.getArrivingStep());
        assertEquals(location2, electric1.getTargetLocation());
        assertEquals(false, electric1.getArrived());
        assertEquals(false, electric1.getCanMove());
        assertEquals("001", electric1.getIdBike());
        assertEquals(null, electric1.getRechargingLocation());
    }

    @Test
    public void testEnoughBattery()
    {
        Location location1 = new Location(1, 1);
        Location location2 = new Location(10, 10);
        EBCompany eBCompan1 = new EBCompany("adsf");
        ElectricBike electric1 = new ElectricBike(eBCompan1, location1, location2, "001", 15);
        Location location3 = new Location(2, 2);
        assertEquals(false, electric1.enoughBattery(location2));
        assertEquals(true, electric1.enoughBattery(location3));
    }

    @Test
    public void testCalculateRoute()
    {
        Location Inicio = new Location(0, 0);
        Location locAccesible = new Location(1, 1);
        Location estacion = new Location(2, 2);
        Location inaccesible = new Location(5, 5);
        EBCompany eBCompan1 = new EBCompany("asf");
        Dock dock1 = new Dock("002", 5, 5);
        BikeStation bikeStat1 = new BikeStation("003", estacion);
        eBCompan1.addBikeStation(bikeStat1);
        bikeStat1.addDock(dock1);
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
        EBCompany eBCompan1 = new EBCompany("1");
        Location recarga = new Location(0, 0);
        BikeStation bikeStat1 = new BikeStation("2", recarga);
        Location location1 = new Location(4, 4);
        Dock dock1 = new Dock("3", 5, 5);
        bikeStat1.addDock(dock1);
        eBCompan1.addBikeStation(bikeStat1);
        ElectricBike electric1 = new ElectricBike(eBCompan1, recarga, location1, "5", 35);
        electric1.reduceBatteryLevel();
        electric1.reduceBatteryLevel();
        electric1.reduceBatteryLevel();
        electric1.reduceBatteryLevel();
        electric1.recharge(2);
        assertEquals(35, electric1.getBatteryLevel());
    }
}




