import java.util.*;
/**
 * Compares two {@link Dock} objects by their charging fee int in ascending order
 * This can be used for sorting lists of docks.
 * @author  
 * @version 2026.10.13
 */
public class ComparatorDocksChargingFee implements Comparator<Dock>
{
    /**
     * Compares two {@link Dock} objects based on their charging fee in ascending order
     * @param d1 The first dock
     * @param d2 The second dock
     * 
     * @return -1 if d1 has a lower charging fee than d2. 1 if d1 has a higher charging fee than d2.
     * If both have the same charging fee they sort by the comparator
     */
    public int compare(Dock d1, Dock d2){
        if(d1.getChargingFee()==d2.getChargingFee())
            return new ComparatorDocksId().compare(d1, d2);
        else if(d1.getChargingFee()>d2.getChargingFee())
            return 1;
        else
            return -1;
    }
}