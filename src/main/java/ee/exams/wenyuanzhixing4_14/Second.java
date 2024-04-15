package ee.exams.wenyuanzhixing4_14;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Joey
 * @version 1.0
 */
public class Second {
    /*
测试用例
6 AAABBB 2
9 AAABBBCCC 2
12 AAABBBCCCDDD 2
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        String tasks = in.next();
        int M = in.nextInt();

        int result = solution(N, tasks, M);

        System.out.println(result);
    }
    private static int solution(int N, String s, int M) {
        //使用哈希表统计任务数量
        int[][] hash = new int[26][1];
        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i) - 'A'][0]++;
        }
        //哈希表排序
        Arrays.sort(hash, (a, b)-> {
            return a[0] > b[0] ? -1 : 1;
        });

        //统计最多任务的类型数量
        int count = 1;
        for (int i = 1; i < 26; i++) {
            if (hash[i][0] == hash[i - 1][0]) {
                count++;
            } else {
                break;
            }
        }
        System.out.println(count);
        //同一类型任务的最大任务数
        int maxTaskNum = hash[0][0];
        System.out.println(maxTaskNum);
        int base = (maxTaskNum - 1) * (M + 1);
        System.out.println(base);
        int left = N <= (base + count) ? 0 : (N - base - count);
        return base + count + left;
    }
}
