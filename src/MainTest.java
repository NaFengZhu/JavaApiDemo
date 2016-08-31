import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Created by zhunafeng on 8/25/16.
 */
public class MainTest {

    public static void main(String[] args) {
        compareMaps(2000);
        compareMaps(5000000);

    }

    private static void compareMaps(final int size) {
        final String [] ar = new String[size];
        for (int i = 0; i < size; ++i) {
            ar[i] = "String #"+i;
        }

        for (int i = 0;i < 10; ++i) {
            testMap(ar,new HashMap<String ,Integer>(size),"hashMap");
            System.gc();
            testMap(ar,new IdentityHashMap<String,Integer>(size),"IdentityHashMap");
            System.gc();
        }

    }

    private static void testMap(final String[] ar,final Map<String,Integer> map, final String name) {
        final long currentTime = System.currentTimeMillis();
        for (int i = 0;i < ar.length; ++i)
            map.put(ar[i],i);

        int failed = 0;
        for (int i = 0; i < ar.length; ++i) {

            if (map.get(ar[i]) != i) {
                ++failed;
            }
        }

        System.out.println(name + " time = "+(System.currentTimeMillis() - currentTime) + "sec");

        if (map.size() != ar.length) {
            System.out.println("Size check failed");
        }
        if (failed !=  0) {
            System.out.println("Test fail");
        }
    }
}
