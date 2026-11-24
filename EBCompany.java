import java.util.*;

/**
 * Models the operation of an Electric Bike (EB) Company. 
 * It manages a fleet of {@link ElectricBike}s and a network of {@link BikeStation}s.
 * * @author David J. Barnes and Michael Kölling
 * @author DP classes
 * @version 2026.10.13
 */
public class EBCompany  
{
    public static final int movingCost = 5;
    private String name;
    private List<ElectricBike> subscribedBikes;
    private List<BikeStation> stations;

    /**
     * Constructor for objects of class EVCompany.
     * @param name The name of the company.
     */
    public EBCompany(String name)
    {
        this.name = name;
        this.subscribedBikes = new ArrayList<>();
        this.stations = new ArrayList<>();

    }

    
    /**
     * Adds an {@link ElectricBike} to the company's fleet.
     * @param bike The electric bike to add.
     */
    public void addElectricBike(ElectricBike bike)
    {       
        if(bike != null){
            subscribedBikes.add(bike);
        }
    }

    
    /**
     * Adds a {@link BikeStation} to the company's network.
     * @param station The bike station to add.
     */
    public void addBikeStation(BikeStation station)
    {       
        if(station != null){ 
            stations.add(station);
        }
    }
    
    
    /**
     * Retrieves a {@link BikeStation} by its unique ID.
     * @param id The ID of the station to find.
     * @return The {@link BikeStation} with the matching ID, or {@code null} if not found.
     */
    public BikeStation getBikeStation(String id)
    {
        BikeStation aux = null;
        for(BikeStation station : stations){
            if(station.getId().equals(id)){
                aux = station;
            }
        }
        return aux;
    }

    
    /**
     * Retrieves a {@link BikeStation} by its {@link Location}.
     * @param location The {@link Location} of the station to find.
     * @return The {@link BikeStation} at the matching location, or {@code null} if not found.
     */
    public BikeStation getBikeStation(Location location)
    {
        BikeStation aux = null;
        for(BikeStation station : stations){
            if(station.getLocation().equals(location)){
                aux = station;
            }
        }
        return aux;
    }
    
    
    /**
     * @return the name of the company.
     */
    public String getName()
    {
        return name;
    }
    
    
    /**
     * @return The total number of managed {@link BikeStation}s.
     */
    public int getNumberOfStations(){
        return stations.size();
    }
    
    
    /**
     * @return The total number of managed {@link ElectricBike}s.
     */
    public int getNumberOfBikes(){
        return subscribedBikes.size();
    }
    
    
    /**
     * Clears all managed bikes and stations, resetting the company to an empty state.
     */
    public void reset(){
        subscribedBikes.clear();
        stations.clear();
    }
    
    
    /**
     * @return List<BikeStation> stations as an ArrayList
     */
    public List<BikeStation> getStations (){
        return new ArrayList<> (stations);
    }
}
    
