package ee.exams.wenyuanzhixing4_14;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Joey
 * @version 1.0
 */
public class First {
    /*
    用例1
3
1 10 10
3 4 11
4 5 3
用例2
4
1 3 11
2 4 12
4 6 13
5 6 14

用例3
4
1 3 13
2 4 12
1 6 20
3 7 5

     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //课程数
        int N = in.nextInt();
        //课程 [begin,end,value]
        int[][] courses = new int[N][3];
        for (int i = 0; i < N; i++) {
            courses[i][0] = in.nextInt();
            courses[i][1] = in.nextInt();
            courses[i][2] = in.nextInt();
        }
        int result = solution(courses);

        System.out.println(result);
    }
    private static int solution(int[][] courses) {

        //按照右边界排序
        Arrays.sort(courses, (a,b) -> {
            if (a[1] == b[1]) {
                return a[0] < b[0] ? -1 : 1;
            }
            return a[1] < b[1] ? -1 : 1;
        });

        for (int[] c : courses) {
            System.out.println(Arrays.toString(c));
        }

        int len = courses.length;
        //int maxEnd = courses[len - 1][1];
        //dp[i] 表示 0-i 下标范围(不含i)的课程不冲突的情况下的最大价值
        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = courses[0][2];
//        //随 dp 更新，更新 能取到最大价值，每个时间段对应的课程下标
//        int[] timeSegmentCourseNum = new int[maxEnd + 1];
//        Arrays.fill(timeSegmentCourseNum, -1);
//        for (int i = courses[0][0]; i < courses[0][1]; i++) {
//            timeSegmentCourseNum[i] = 0;
//        }
        for (int i = 2; i <= len; i++) {
            int num = i - 1;
            if (courses[num][0] >= courses[num - 1][1]) {
                dp[i] = dp[i - 1] + courses[num][2];
            } else {
                int index = num - 1;
                while (index >= 0 && courses[index][1] > courses[num][0]) {
                    index--;
                }
                dp[i] = Math.max(dp[i - 1], dp[index + 1] + courses[num][2]);
            }
        }

        System.out.println(Arrays.toString(dp));
        return dp[len];
    }
}
