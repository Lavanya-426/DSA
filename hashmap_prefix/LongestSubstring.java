package hashmap_prefix;

import java.util.*;
public class LongestSubstring {
    public static int longestBalanced(String s) {
        int n = s.length();
        int zc=0, oc=0;
        for(int i=0;i<n;i++){
            if(s.charAt(i)=='1') oc++;
            else zc++;
        }
        if(oc==zc) return n;
        int mini= Math.min(oc,zc);
        int maxi=0;
        Map<Integer,Integer> hm= new HashMap<>();
        hm.put(0,-1);
        int len=0;
        int c=0;
        for(int i=0;i<n;i++){
            if(s.charAt(i)=='0') c--;
            else c++;
            if(hm.containsKey(c)) {
                len=i-hm.get(c);
                maxi=Math.max(maxi,len);
            }
           
             if(hm.containsKey(c+2)) {
                len=i-hm.get(c+2);
                if(len<= 2*mini)
                maxi=Math.max(maxi,len);
            }
             if(hm.containsKey(c-2)) {
                len= i-hm.get(c-2);
                if(len<=2*mini)
                maxi=Math.max(maxi,i-hm.get(c-2)); 
            }
            hm.putIfAbsent(c,i);
        }

        return maxi;
    }
    public static void main(String ar[]){
        
        System.out.print("\n"+longestBalanced("01111100"));
    }
}