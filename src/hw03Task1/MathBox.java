package hw03Task1;

import javax.management.StandardEmitterMBean;
import java.util.TreeSet;

public class MathBox {
    TreeSet<Number> coll = new TreeSet <>();
    public MathBox(Number[] number) {
        int nl = number.length;
        for (int i=0; i<nl; i++) {
            coll.add(number[i]);
        }
    }
    public int summator () {
        int sum = 0;
        for (Number ts : coll) {
            sum += ts.intValue();
        }
        return sum;
    }

    public void splitter (int d) {
        float temp;
        for (Number ts : coll) {
            temp = (float)ts / d;
            coll.remove(ts);
            coll.add(temp);
        }
    }

    @Override
    public String toString() {
        String totalString = "";
        for (Number ts : coll) {
            totalString += ts+", ";
        }

        return totalString.substring(0,totalString.length()-2);
    }
}