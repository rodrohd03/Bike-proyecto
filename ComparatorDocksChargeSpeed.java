import java.util.*;
/**
 * Write a description of class ComparatorDocksChargeSpeed here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class ComparatorDocksChargeSpeed implements Comparator<Dock>
{
    public int compare(Dock d1, Dock d2){
        if(d1.getChargingSpeed()==d2.getChargingSpeed())
            return new ComparatorDocksChargingFee().compare(d1, d2);
            else if(d1.getChargingSpeed()<d2.getChargingSpeed())
                return 1;
        else
            return -1;
}
}