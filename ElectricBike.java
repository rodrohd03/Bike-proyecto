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
    private Dock currentDock;
    boolean arrived;
    boolean canMove;
    private List<Dock> docks;
    int arrivingStep;
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
            currentDock = null;
            arrived = false;
            canMove = false;
            arrivingStep = 9999;
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
        return rechargingLocation;
    }
    /**
     * Get the step number the bike arrived to its destiny.
     * @return arrivingStep.
     */
    public int getArrivingStep(){
        return arrivingStep;
    }
    /**
     * Get List<Dock> of docks where the bike recharged.
     * @return {@link docks}.
     */
    public List<Dock> getDocks(){
        return docks;
    }
    /**
     * Checks if the current battery level is sufficient to cover a given distance.
     * //TODO complete the JavaDoc comments
     */
    public boolean enoughBattery(Location location1)
    {
        if(batteryLevel / EBCompany.movingCost >= location.distance(location1)){
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
       return "Localizacion actual: " + location + " Localizacion de recarga: " +rechargingLocation + " Localizacion destino: " + targetLocation;
    }

    /**
     * Determines the optimal intermediate {@link BikeStation} to visit for recharging
     * if the bike cannot reach the final target directly.
     * Sets {@code rechargingLocation} to the chosen station's location.
     */
    public void calculateRechargingPosition(Location location2)
    {
        List<BikeStation> stations = company.getStations();
        int minD = 999;//tamaño que nunca se accederá
        canMove=true;
        //No permite ir a un station sin docks libres
        for(int i = 0; i<stations.size();i++){ 
          if(enoughBattery(stations.get(i).getLocation())){
            if(!location.equals(stations.get(i).getLocation())){  
                if(minD > location.distance(stations.get(i).getLocation()) + location2.distance(stations.get(i).getLocation())){
                    if (stations.get(i).getFreeDock() != null){//esta funcion checkea que tenga docks libres
                     minD = location.distance(stations.get(i).getLocation()) + location2.distance(stations.get(i).getLocation());
                     rechargingLocation = stations.get(i).getLocation();
                    }
                }
            }
          }
        }
        if (minD == 999){
            //System.out.println ("No hay estaciones con docks libres");
            rechargingLocation = null;
            canMove = false;
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
            canMove = true;
            setTargetLocation(location1);
            rechargingLocation = null;
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
    public void incrementChargesCost(Dock dock)
    {
        chargestCost += dock.getChargingFee()*(batteryCapacity - batteryLevel);
    }
    
    /**
     * Simulates the recharging process when the bike arrives at a {@code rechargingLocation}.
     * The battery is charged to full capacity, the cost is calculated, and the route is recalculated.
     * //TODO complete the JavaDoc comments
     */
    public void recharge(int step)
    {
        List<BikeStation> stations = company.getStations();
        boolean found = false;
        Dock dockR = null;
        for (int i = 0; i<stations.size() && !found; i++){
            if (location.equals(stations.get(i).getLocation())){
                found = true;   
                dockR = stations.get(i).getFreeDock();
                dockR.recharge(this);
                currentDock = dockR;
            }
        }
        //Variables necesarias para mostrar el mensaje del paso
        int charged= batteryCapacity-batteryLevel;
        float cost= dockR.getChargingFee()*charged;
        
        docks.add(dockR);
        incrementCharges();
        wsCharged = wsCharged + batteryCapacity - batteryLevel;
        incrementChargesCost(dockR);
        batteryLevel=batteryCapacity;
        //Mensaje del paso
        System.out.println("(step: " + step + " - ElectricBike: " + idBike + 
        " recharges: " + charged + "wh at dock: " + 
        dockR.getIdDock() + " with cost: " + cost + "€ ********)");
        
        calculateRoute(targetLocation);
    }   

    /**
     * Reduces the battery level by the cost of one movement step (defined in {@link EBCompany#MOVINGCOST}).
     * Ensures the battery level does not go below zero.
     */
    public void reduceBatteryLevel(){
        batteryLevel = batteryLevel - EBCompany.movingCost;
        if (batteryLevel < 0) {
        batteryLevel = 0;
        }
    }
    
    /**
     * Carries out a single step of the bike's actions.
     * Moves one step towards the target (recharging or final) or stays idle.
     * //TODO complete the JavaDoc comments
     */
    public void act(int step)
    {
        if(currentDock != null){
            currentDock.setFree();
            currentDock = null;
        }
        if(!arrived && canMove){
            if(rechargingLocation == null){
                location = location.nextLocation(targetLocation);
                reduceBatteryLevel();
                if(location.equals(targetLocation)){
                    arrived = true;
                    arrivingStep= step;
                    //Mensaje de llegada del paso
                    System.out.println("(step: " + step + " - ElectricBike: " 
                    + idBike + " at target destination ********)");
                }
            }
            else{
                if(!location.equals(rechargingLocation)){
                    location = location.nextLocation(rechargingLocation);
                    reduceBatteryLevel();
                }
                if(location.equals(rechargingLocation)){
                    rechargingLocation = null;
                    recharge(step);
                }
            }
        }
        else{
            idleCount++; 
        }
    }


    /**
     * Returns a detailed string representation of the electric bike.
     * @return A string containing the bike's name, plate, battery info, charge counts, costs, idle count, and route.
     */
    @Override
    public String toString(){
        if(rechargingLocation == null){
        return "ElectricBike: " + idBike + ", " + batteryCapacity + "wh, " + batteryLevel + "wh, " + chargesCount + ", " + chargestCost + "€, " + 
        idleCount + ", " + location + ", " + targetLocation;    
        }
        else{
        return "ElectricBike: " + idBike + ", " + batteryCapacity + "wh, " + batteryLevel + "wh, " + chargesCount + ", " + chargestCost + "€, " + 
        idleCount + ", " + location + ", " +rechargingLocation + ", " + targetLocation;
        }
    }

}
