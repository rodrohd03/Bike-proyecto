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
     * Sets the identifier of the dock, if idDock is not null or empty
     * @param idDock The new identifier of the dock.
     */
    public void setIdDock(String idDock) 
    {
        if (idDock != null && !idDock.isEmpty()){
            this.idDock = idDock;
        }
    }
    
    
    /**
     * @return The charging speed of the dock in wh.
     */
    public int getChargingSpeed()
    {
        return chargingSpeed;
    }
    
    
     /**
     * Sets the charging speed of the dock.
     * @param chargingSpeed The new charging speed.
     */
    public void setChargingSpeed(int chargingSpeed) 
    {
        this.chargingSpeed = chargingSpeed;
    }
    
    
    /**
     * @return The charging fee per wh.
     */
    public float getChargingFee()
    {
        return chargingFee;
    }
    
    
    /**
     * Sets the charging fee of the dock.
     * @param chargingFee The new charging fee.
     */
    public void setChargingFee(float chargingFee) 
    {
        this.chargingFee = chargingFee;
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
    public void setFree()
    {
        free = true;
    }
    
    
    /**
     * @return The collection of electric bikes recharged by this dock.
     */
    public List<ElectricBike> getBikesRecharged()
    {
        return new ArrayList<> (bikesRecharged);
    }

    
    /**
     * @return The total amount collected by this dock in €.
     */
    public float getAmountCollected()
    {
        return amountCollected;
    }   

    
    /**
     * @return The total number of {@link ElectricBike}s that have been recharged by this dock.
     */
    public int getNumberEBRecharged(){
        return bikesRecharged.size();
    }
    
    
     /**
     * Adds a {@link ElectricBike} to bikesRecharged.
     */
    public void addBike (ElectricBike bike){
        bikesRecharged.add(bike);
    }
    
    
    /**
     * Increases the amount collected and registers the bike as recharged.
     */
    public void collectPayment(ElectricBike bike){
        int kwh= 0;
        kwh = bike.getBatteryCapacity() - bike.getBatteryLevel();
        amountCollected += chargingFee * kwh ;
    }
    
    
    /**
     * Simulates the charging process for an {@link ElectricBike}.
     * Increases the amount collected and registers the bike as recharged.
     * @param bike The electric bike to be recharged
     */
    public void recharge(ElectricBike bike){
        addBike(bike);
        collectPayment(bike);
        free = false;
    }
    
    
    /**
     * Returns a string representation of the dock, including its ID, speed, fee, and the number of EVs recharged.
     * @return A string representation of the dock.
     */
    public String toString()
    {
        String formattedId = idDock.replace(" ","_");//Para que coincida con los resultados del anexo
        return "(Dock: " + formattedId + ", " + chargingSpeed + "wh, " + chargingFee + 
        "€, " + bikesRecharged.size() + ", " + amountCollected + "€" + ")";
    } 
}
