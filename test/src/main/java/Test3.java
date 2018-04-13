import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

import java.util.Map;

public class Test3 {
    public static void main(String[] args) {
        RangeMap<Integer, Integer> map = TreeRangeMap.create();
        map.put(Range.closed(1,2), 2);

        for(Map.Entry<Range<Integer>, Integer> entry : map.asMapOfRanges().entrySet()) {
            Range<Integer> range = entry.getKey();
            System.out.println(range);
            System.out.println(range.lowerEndpoint());
        }
        System.out.println(Range.openClosed(1,1));
    }
}
