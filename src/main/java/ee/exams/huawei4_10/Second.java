package ee.exams.huawei4_10;

import java.util.*;

/**
 * @author Joey
 * @version 1.0
 */
public class Second {
    private static int[] father;
    private static void init() {
        for (int i = 0; i < father.length; i++) {
            father[i] = i;
        }
    }
    private static int find(int u) {
        if (u == father[u]) {
            return u;
        }
        else return father[u] = find(father[u]);
    }
    private static boolean isSame(int u, int v) {
        u = find(u);
        v = find(v);
        return u == v ? true : false;
    }
    //加入 边 u->v
    private static void join(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) return;
        father[u] = v;
    }
    public static void main(String[] args) {
        // please define the JAVA input here. For example: Scanner s = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[][] images = new int[N][N];
        for (int i = 0; i < N * N; i++) {
            images[i / N][i % N] = in.nextInt();
        }
        //System.out.println(Arrays.toString(images[0]));


        // please finish the function body here.
        //可以使用并查集
        father = new int[N];
        init();
        //遍历图像，加入并查集中
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (images[i][j] > 0) {
                    join(i, j);

                }
            }
        }
        //System.out.println(Arrays.toString(father));

        //获得并查集的情况下，如何获得集合
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isSame(i, j) && images[i][j] != 0) {
                    map.put(find(i), map.getOrDefault(find(i), 0) + images[i][j]);
                }
            }
        }
        //System.out.println(map.toString());
        Collection<Integer> values = map.values();
        List<Integer> list = new ArrayList<>(values);
        list.sort((a,b)->{
            return b.compareTo(a);
        });
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i != list.size() - 1) System.out.print(" ");
        }
        /*
5
0 0 50 0 0
0 0 0 25 0
50 0 0 0 15
0 25 0 0 0
0 0 15 0 0

6
0 20 0 30 0 0
20 0 0 0 0 0
0 0 0 60 0 0
30 0 60 0 0 0
0 0 0 0 0 20
0 0 0 0 20 0
         */

        // please define the JAVA output here. For example: System.out.println(s.nextInt());

    }
}
