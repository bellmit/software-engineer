import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/12 15:48
 * @Description
 */
public class IteratorHashMap {

    public static void main(String[] args) {

        HashMap<Integer, Integer> map = new HashMap<>(8);

        map.put(1, 2);
        map.put(2, 2);
        map.put(3, 2);
        map.put(4, 2);


       /* Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> tempIt = it.next();
            System.out.println(tempIt.getKey() + " A " + tempIt.getValue());
        }

        for (Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<Integer, Integer> tempNext = iterator.next();
            System.out.println(tempNext.getKey() + " A " + tempNext.getValue());
        }*/

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
        }





        /*Iterator iterator = map.entrySet().iterator();

        while(iterator.hasNext()) {
            Map.Entry<Integer, Integer> tempNext = (Map.Entry)iterator.next();
            System.out.println(tempNext.getKey() + " AAA " + tempNext.getValue());
        }
*/


    }

}
