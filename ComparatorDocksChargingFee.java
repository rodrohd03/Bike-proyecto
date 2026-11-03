import java.util.*;
/**
 * Write a description of class ComparatorDocksChargingFee here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ComparatorDocksChargingFee implements Comparator<Dock>
{
    public int compare(Dock d1, Dock d2){
        if(d1.getChargingFee()==d2.getChargingFee())
            return new ComparatorDocksId().compare(d1, d2);
        else if(d1.getChargingFee()>d2.getChargingFee())
            return 1;
        else
            return -1;
    }
}