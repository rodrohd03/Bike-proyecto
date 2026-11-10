import java.util.List;
import java.util.ArrayList;

/**
 * Model the common elements of an Electric bike (EB) that operates 
 * within the simulation, moving towards a target and potentially recharging.
 * * @author David J. Barnes and Michael Kölling
 * @author DP classes 
 * @version 2026.10.13
 */
public class ElectricBike 
{
    private String idBike;
    private EBCompany company;
    private Location location;
    private Location rechargingLocation;
    private Location targetLocation;
    private int idleCount;
    private int batteryCapacity;
    private int batteryLevel;
    private int wsCharged;
    private int chargesCount;
    private float chargestCost;
    private List<Dock> docks;
    
    /**
     * Constructor of class ElectricBike.
     * @param company The EB's operating company. Must not be null.
     * @param location The EB's starting {@link Location}. Must not be null.
     * @param targetLocation The EB's final destination {@link Location}. Must not be null.
     * @param id The identificator of the bike.
     * @param batteryCapacity The maximum capacity of the battery.
     * @throws NullPointerException If company, location, or targetLocation is null.
     */
    public ElectricBike(EBCompany company, Location location, Location targetLocation, String id, int batteryCapacity)
        {
            idBike = id;
            this.company = company;
            this.location = location;
            this.targetLocation = targetLocation;
            idleCount= 0;
            this.batteryCapacity = batteryCapacity;
            batteryLevel = this.batteryCapacity;
            wsCharged = 0;
            rechargingLocation = null;
            chargesCount = 0;
            chargestCost = 0;
            docks = new ArrayList<> ();
    }

    /**
     * @return The id of the electric bike.
     */
    public String getIdBike()
    {
        return idBike;    
    }

    /**
     * Set the current location.
     * @param location Where it is. Must not be null.
     * @throws NullPointerException If location is null.
     */
    public void setLocation(Location location)
    {
        this.location = location;
    }

    /**
     * Get the final target location.
     * @return Where this bike is ultimately headed.
     */
    public Location getTargetLocation()
    {
        return targetLocation;    
    }
    
    /**
     * Get the batteryCapacity.
     * @return the amount of kwh the bike can hold.
     */
    public int getBatteryCapacity()
    {
        return batteryCapacity;    
    }
    
    /**
     * Get the batteryLevel.
     * @return the battery percentage.
     */
    public int getBatteryLevel()
    {
        return batteryLevel;    
    }

    /**
     * Get the temporary recharging location.
     * @return The {@link Location} of the next {@link BikeStation} to visit, or null if no recharge is planned.
     */
    public Location getRechargingLocation()
    {
        //TODO: Complete this code
        return rechargingLocation;
    }

    /**
     * Checks if the current battery level is sufficient to cover a given distance.
     * //TODO complete the JavaDoc comments
     */
    public boolean enoughBattery(Location location1)
    {
        if(batteryLevel / 5 > location.distance(location1)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Set the required final target location.
     * @param location Where to go. Must not be null.
     * @throws NullPointerException If location is null.
     */
    public void setTargetLocation(Location location)
    {
        targetLocation = location;
    }

    /**
     * Gets a string representation of the planned route, including the recharging stop if one exists.
     * @return A string showing the route: {@code currentLocation -> [rechargingLocation ->] targetLocation}.
     */
    public String getStringRoute()
    {
       //TODO: Complete this code
       return null;
    }

    

    /**
     * Determines the optimal intermediate {@link BikeStation} to visit for recharging
     * if the bike cannot reach the final target directly.
     * Sets {@code rechargingLocation} to the chosen station's location.
     */
    public void calculateRechargingPosition(Location location2)
    {
        List<BikeStation> stations = company.getStations();
        int a = 0;
        int minD = 21;//max tabla +1
        //No permite ir a un station sin docks libres
        for(int i = a; i<stations.size();i++){ 
          if(enoughBattery(stations.get(i).getLocation())){
            if(minD < location.distance(stations.get(i).getLocation()) + location2.distance(stations.get(i).getLocation())){
                if (stations.get(a).getFreeDock() != null){//esta funcion checkea que tenga docks libres
                 minD = location.distance(stations.get(i).getLocation()) + location2.distance(stations.get(i).getLocation());
                 rechargingLocation = stations.get(a).getLocation();
                }
            }
          }
        }
    }

     /**
     * Calculates the optimal route for the bike. 
     * If there isn't enough battery to reach the target, it attempts to find an intermediate   
     * {@link BikeStation} and sets it as the {@code rechargingLocation}.
     * No permite ir a una station que no tenga docks libres, la complejidad sigue siendo de O(n) porque no recorre la lista 2 veces 
     */
    public void calculateRoute(Location location1)
    {
        if(enoughBattery(location1)){
            setTargetLocation(location1);
        }
        else{
            calculateRechargingPosition(location1);
        }
    }
    
    /**
     * Get the Manhattan-like distance to the final target location from the current location.
     * @return The distance to the target location.
     */
    public int distanceToTheTargetLocation()
    {
        return location.distance(targetLocation);
    }
 

    /**
     * Increments the count of recharges performed by this bike.
     * //TODO complete the JavaDoc comments
     */
    public void incrementCharges()
    {
        chargesCount ++;
    }

    /**
     * Adds a cost amount to the total charges cost.
     * //TODO complete the JavaDoc comments
     */
    public void incrementChargesCost(   )
    {
        //chargestCost = dockR.getChargingFee()*(batteryCapacity - batteryLevel);
    }
    
    /**
     * Simulates the recharging process when the bike arrives at a {@code rechargingLocation}.
     * The battery is charged to full capacity, the cost is calculated, and the route is recalculated.
     * //TODO complete the JavaDoc comments
     */
    public void recharge(   )
    {
        List<BikeStation> stations = company.getStations();
        boolean found = false;
        Dock dockR = null;
        for (int i = 0; i<stations.size()&&!found;i++){
            if (location == stations.get(i).getLocation()){
                found = true;   
                dockR = stations.get(i).getFreeDock();
                stations.get(i).getFreeDock().recharge(this);
            }
        }
        docks.add(dockR);
        incrementCharges();
        wsCharged = wsCharged + batteryCapacity - batteryLevel;
        chargestCost = dockR.getChargingFee()*(batteryCapacity - batteryLevel);
        batteryLevel=batteryCapacity;
        //recaluclar ruta 
    }   

    /**
     * Carries out a single step of the bike's actions.
     * Moves one step towards the target (recharging or final) or stays idle.
     * //TODO complete the JavaDoc comments
     */
    public void act(   )
    {
       //TODO: Complete this code 
    }

    /**
     * Reduces the battery level by the cost of one movement step (defined in {@link EBCompany#MOVINGCOST}).
     * Ensures the battery level does not go below zero.
     */
    public void reduceBatteryLevel(){
       //TODO: Complete this code 
    }

    /**
     * Returns a detailed string representation of the electric bike.
     * @return A string containing the bike's name, plate, battery info, charge counts, costs, idle count, and route.
     */
    @Override
    public String toString(){
        return "ElectricBike: " + idBike + ", " + batteryCapacity + "wh, " + batteryLevel + "wh, " + chargesCount + ", " + chargestCost + "€, " + 
        idleCount + ", " + location + ", " + targetLocation;
    }

   
}
