import java.util.*;
/**
 * Compares two {@link BikeStation} objects by their total number of recharges int in descending order 
 * This can be used for sorting lists of bike stations.
 * @author  
 * @version 2026.10.13
 */
public class ComparatorBikeStationRecharges implements Comparator<BikeStation>{
    /**
     * Compares two {@link BikeStation} objects based on their total number of recharges int in descending order
     * @param be1 The first bike station
     * @param be2 The second bike station
     * 
     * @return -1 if be1 has more charges than be2. 1 if be1 has less charges than be2. 
     * If they have the same number of charges they sort by the comparator
     */
    public int compare(BikeStation be1, BikeStation be2){
        if(be1.getTotalRecharges() == be2.getTotalRecharges())
        return new ComparatorBikeStationId().compare(be1,be2);
            else if (be1.getTotalRecharges() < be2.getTotalRecharges())
                return 1;
            else
                return -1;
    }
}