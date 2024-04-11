package ee.exams.huawei4_10;

import java.util.*;

/**
 * @author Joey
 * @version 1.0
 */
public class One {
    public static void main(String[] args) {
        // please define the JAVA input here. For example: Scanner s = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        int N = Integer.parseInt(in.nextLine());
        String[][] records = new String[N][];
        for (int i = 0; i < N; i++) {
            records[i] = in.nextLine().split(",");
            //System.out.println(Arrays.toString(records[i]));
        }

        int n = Integer.parseInt(in.nextLine());
        Map<String, Integer> factor = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] factors = in.nextLine().split(",");
            //System.out.println(Arrays.toString(factors));
            factor.put(factors[0], Integer.parseInt(factors[1]));
        }
        //System.out.println(factor.toString());
        // please finish the function body here.
        //遍历一次数据即可
        //存放记录
        Set<String> set = new HashSet<>();
        //存放结果
        Map<String, Integer> result = new TreeMap();

        for (String[] record : records) {
            //首先查看是否有同样记录存在，如果存在跳过
            String key = record[0] + record[1] + record[2];
            if (set.contains(key)) continue;

            set.add(key); //将记录放进set

            //未有相同记录，判断计费时长是否合法，并计算当前记录总费用
            Integer time = Integer.parseInt(record[3]);
            if (time < 0 || time > 100) time = 0;

            Integer total = time == 0 ? 0 : time * factor.getOrDefault(record[2], 0);
            result.put(record[1], result.getOrDefault(record[1], 0) + total);
        }
        System.out.println(result.toString());
        /*
5
1627845600,client1,factorA,10
1627845605,client2,factorB,15
1627845610,client1,factorA,5
1627845610,client1,factorB,8
1627845620,client2,factorB,20
2
factorA,5
factorB,6

         */

        // please define the JAVA output here. For example: System.out.println(s.nextInt());
        System.out.println();

    }
}
