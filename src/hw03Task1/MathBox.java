package hw03Task1;

import javax.management.StandardEmitterMBean;
import java.util.TreeSet;

class MathBox {
/**
 * Создаём коллекцию
 */
    TreeSet<Number> coll = new TreeSet<>();
    public MathBox(Number[] number) {
        int nl = number.length;
        for (int i=0; i<nl; i++) {
            coll.add(number[i]);
        }
    }
/**
 * Метод суммирующий элементы коллекции
 */ 
    public Number summator () {
        double sum = 0;
        for (Number el : coll) {
            sum += el.doubleValue();
        }
        return sum;
    }
/**
 * Метод перезаписывающий имеющующся коллекцию элементами, поделёнными на определённый делитель
 */ 
    public void splitter (double d) {
        TreeSet<Number> temp = new TreeSet<>();
        for (Number el : coll) {
            temp.add((el.intValue() / d));
        }
        coll.clear();
        coll = temp;
    }
/**
 * Метод удаляющий найденный в коллекции Integer
 */ 
    public void remover (Integer d) {
        if (coll.contains(d.doubleValue())) {
            coll.remove(d.doubleValue());
        }
    }
    
//     // public void remover (Integer d) {
//     //     boolean b;
//     //     try {
//     //         b = coll.contains(d);
//     //     }
//     //     catch (ClassCastException e) {
//     //         b = coll.contains((float)d);
//     //     }
//     //     finally {
//     //         if (b) {
//     //             coll.remove(d);
//     //         }
//     //     }
//     // }


    @Override
    public String toString() {
        String totalString = "";
        for (Number el : coll) {
            totalString += el+", ";
        }
        return totalString.substring(0,totalString.length()-2);
    }
}
