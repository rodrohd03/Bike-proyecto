import java.util.ArrayList;
import java.util.List;
/**
 * Model a dock unit within a {@link BikeStation}.
 * It tracks its charging capabilities, fee, and the electric bikes it has recharged.
 * @author David J. Barnes and Michael Kölling
 * @author DP classes 
 * @version 2026.10.13
 */
public class Dock
{
    private String idDock;
    private int chargingSpeed;
    private float chargingFee;
    private List<ElectricBike> bikesRecharged;
    private float amountCollected;
    private boolean free;

    /**
     * Constructor for objects of class Dock.
    * @param id The unique identifier of the dock.
     * @param speed The charging speed in wh.
     * @param fee The charging fee in €/wh.    */
    public Dock(String id, int speed, float fee)
    {
        idDock = id;
        chargingSpeed = speed;
        chargingFee = fee;
        bikesRecharged = new ArrayList<>();
        amountCollected = 0.0f;
        free = true;
    }

    
    /**
     * @return The unique identifier of the dock.
     */
    public String getIdDock()
    {
        return idDock;
    }
    
    
    /**
     * @return The charging speed of the dock in wh.
     */
    public int getChargingSpeed()
    {
        return chargingSpeed;
    }
    
    
    /**
     * @return The charging fee per wh.
     */
    public float getChargingFee()
    {
        return chargingFee;
    }
    
    
    /**
     * @return True if the dock is free, false otherwise.
     */
    public boolean isFree()
    {
        return free;
    }
    
    
    /**
     * Sets the free status of the dock.
     * @param free The new free status.
     */
    public void setFree(boolean free)
    {
        this.free = free;
    }
    
    
    /**
     * @return The collection of electric bikes recharged by this dock.
     */
    public List<ElectricBike> getBikesRecharged()
    {
        return bikesRecharged;
    }

    
    /**
     * @return The total amount collected by this dock in €.
     */
    public float getAmountCollected()
    {
        return amountCollected;
    }
    
    
    /**
     * Returns a string representation of the dock, including its ID, speed, fee, and the number of EVs recharged.
     * @return A string representation of the dock.
     */
    public String toString()
    {
        String formattedId = idDock.replace("_"," ");//Para que coincida con los resultados del anexo
        return "Dock: " + formattedId + ", " + chargingSpeed + "wh, " + chargingFee + 
        "€, " + bikesRecharged.size() + ", " + amountCollected + "€";
    }    

    
    /**
     * @return The total number of {@link ElectricBike}s that have been recharged by this dock.
     */
    public int getNumerEVRecharged(){
        return bikesRecharged.size();
    }
    
    
    /**
     * Simulates the charging process for an {@link ElectricBike}.
     * Increases the amount collected and registers the bike as recharged.
     * //TODO complete the JavaDoc comments
     */
    public float recharge(   ){
        //TODO: Complete this code
        return 0;
    }

    //TODO: Complete this code

}
