import java.util.*;
/**
 * Compares two {@link ElectricBike} objects based on their arriving step int in ascending order.
 * This can be used for sorting lists of electric bikes.
 * @version 2026.10.13
 */

public class ComparatorEBArrivingStep implements Comparator<ElectricBike> {
    public int compare(ElectricBike eb1, ElectricBike eb2){
        if(eb1.getArrivingStep() == eb2.getArrivingStep())
            return new ComparatorEBIdBike().compare(eb1, eb2);
            else if(eb1.getArrivingStep()>eb2.getArrivingStep())
                return 1;
            else
                return -1;
        
    }
}