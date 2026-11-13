import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Iterator;

/**
 * Models an Electric Vehicle Charging Station.
 * A charging station contains multiple {@link Dock} units.
 * @author DP classes 
 * @version 2026.10.13
 */
public class BikeStation
{
    private String idStation;
    private Location location;
    private List<Dock> docks;
    
    /**
     * Constructor for objects of class BikeStation.
     * Initializes the fields with default or empty values.
     */
    public BikeStation(  )
    {
        idStation = "";
        location = null;
        docks = new ArrayList<>();
    }

    
    /**
     * Constructor for objects of class BikeStation.
     * @param id The unique identifier of the station (e.g., "CC01"). Must not be null.
     * @param location The geographic location of the station. Must not be null.
     */
    public BikeStation(String id, Location location)
    {
        this.idStation = id;
        this.location = location;
        this.docks = new ArrayList<>();
    }
    
    
    /**
     * @return The unique identifier of the charging station.
     */
    public String getId()
    {  
        return this.idStation;
    }
    
    
    /**
     * Sets the id of the station, if idStation is not null or empty
     * @param idStation The new id of the station.
     */
    public void setIdStation(String idStation)
    {
        if (idStation != null && !idStation.isEmpty()) {
            this.idStation = idStation;
        }   
    }
    
    
    /**
     * @return Location of the Charging station
     */
    public Location getLocation()
    {
        return this.location;   
    }
    
    
    /**
     * @return The collection of Docks as an ArrayList with a copy
     */
    public List<Dock> getDocksList()
    {
       return new ArrayList<> (docks);
    }
    
    /**
     * Sorts the Docks list by ChargeSpeed, if not by charging fee, and if
     * not by ID
     */
    public void sortDocks() 
    {
        Collections.sort(this.docks, new ComparatorDocksChargeSpeed());
    }
    
    /**
     * Adds a new {@link Dock} to the station and keeps the collection ordered by:
     * Charging speed descending
     * Charging fee ascending
     * Dock id ascending
     * @param dock The new Dock unit.
     */
    public void addDock(Dock dock)
    {
       if(dock != null){
       docks.add(dock);
       Collections.sort(docks, new ComparatorDocksChargeSpeed());
    }
    }
    
    /**
     * Retrieves the first free {@link Dock} found at the station.
     * @return A free {@link Dock}, or possibly throws an exception if none are found (depending on stream implementation details).
     * **Note: The implementation assumes at least one free dock exists if called.**
     */
    public Dock getFreeDock()
    {
        Dock aux = null;
        boolean found = false;
        Iterator<Dock> it = docks.iterator();
        while(it.hasNext() && found == false){
            aux = it.next();
            if(aux.isFree() == true)
            found = true;
        }
        
        return aux;        
    }
    
    
    /**
     * Set the current location of the charging station.
     * @param location Where it is. Must not be null.
     * @throws NullPointerException If location is null.
     */
    public void setLocation(Location location)
    {
        if (location == null) {
            throw new NullPointerException("Location cannot be null");
        }
       this.location = location;   
    }
    
    public int getTotalRecharges() {
        int total = 0;
        for (Dock dock : this.docks){
            total = total + dock.getNumberEBRecharged();
        }
        return total;
    }
    
    
    /**
     * Returns a string representation of the bike station following the output template.
     * @return A string containing the station ID, total recharged bikes, and location.
     */
    @Override
    public String toString()
    {
        int totalRecharged = 0;
        for(Dock dock: docks){
            totalRecharged += dock.getBikesRecharged().size();
        }
        return "BikeStation: " + idStation + ", " + totalRecharged + ", " + location;
    }
}
