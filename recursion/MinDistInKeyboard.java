package recursion;

import java.util.*;
public class MinDistInKeyboard {
    static  int n;
    static int memo[][][];

    static public int minimumDistance(String word) {
        n = word.length();
        memo = new int[n][27][27];  

        for (int[][] ii : memo) {
            for (int[] i : ii) {
                Arrays.fill(i, -1);
            }
        }

        return go(word, 0, -1, -1);
    }

    static int go(String s, int ind, int f1, int f2) {
        if (ind == n)
            return 0;

        if (memo[ind][f1 + 1][f2 + 1] != -1)
            return memo[ind][f1 + 1][f2 + 1];

        int curr = s.charAt(ind) - 'A';

        int c1 = dist(f1, curr) + go(s, ind + 1, curr, f2);
        int c2 = dist(f2, curr) + go(s, ind + 1, f1, curr);

        return memo[ind][f1 + 1][f2 + 1] = Math.min(c1, c2);
    }

    static int dist(int a, int b) {
        if (a == -1)
            return 0;

        int x1 = a / 6, y1 = a % 6;
        int x2 = b / 6, y2 = b % 6;

        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
