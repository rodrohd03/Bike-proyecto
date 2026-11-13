import java.util.*;
/**
 * Write a description of class ComparatorBikeStationRecharges here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ComparatorBikeStationRecharges implements Comparator<BikeStation>{
    public int compare(BikeStation be1, BikeStation be2){
        if(be1.getTotalRecharges() == be2.getTotalRecharges())
        return new ComparatorBikeStationId().compare(be1,be2);
            else if (be1.getTotalRecharges() < be2.getTotalRecharges())
                return 1;
            else
                return -1;
    }
}