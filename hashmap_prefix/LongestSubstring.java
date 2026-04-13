package hashmap_prefix;

 
import java.util.*;
public class LongestSubstring {
    public int longestBalanced(String s) {
        int n = s.length();
        int[] z = new int[n], o = new int[n], diff = new int[n];
        int cnt0 = 0, cnt1 = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') cnt0++;
            else cnt1++;
            z[i] = cnt0; o[i] = cnt1; diff[i] = o[i] - z[i];
        }

        int maxlen = 0;
        Map<Integer, Integer> ind = new HashMap<>(), ind0 = new HashMap<>(), ind1 = new HashMap<>();
        ind.put(0, -1);

        for (int i = 0; i < n; i++) {
            int pref = diff[i];
            if (ind.containsKey(pref)) maxlen = Math.max(maxlen, i - ind.get(pref));
            
            if (ind.containsKey(pref + 2)) {
                int j = ind.get(pref + 2);
                int sub1s = o[i] - (j >= 0 ? o[j] : 0);
                if (cnt1 > sub1s) maxlen = Math.max(maxlen, i - j);
                else if (ind1.containsKey(pref + 2)) maxlen = Math.max(maxlen, i - ind1.get(pref + 2));
            }

            if (ind.containsKey(pref - 2)) {
                int j = ind.get(pref - 2);
                int sub0s = z[i] - (j >= 0 ? z[j] : 0);
                if (cnt0 > sub0s) maxlen = Math.max(maxlen, i - j);
                else if (ind0.containsKey(pref - 2)) maxlen = Math.max(maxlen, i - ind0.get(pref - 2));
            }
            
            ind.putIfAbsent(pref, i);
            if (z[i] > 0) ind0.putIfAbsent(pref, i);
            if (o[i] > 0) ind1.putIfAbsent(pref, i);
        }
        return maxlen;
    }
}