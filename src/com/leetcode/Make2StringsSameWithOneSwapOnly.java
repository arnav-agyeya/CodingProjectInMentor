package com.leetcode;

public class Make2StringsSameWithOneSwapOnly {

    public static boolean buddyStrings(String A, String B) {
        int l1 = A.length();
        int l2 = B.length();
        if (l1 != l2) return false;
        if (A.equals(B)) {
            int[] cnt = new int[26];
            for (char ch : A.toCharArray()) {
                cnt[ch - 'a']++;
                if (cnt[ch - 'a'] > 1) {
                    return true;
                }
            }
            return false;
        }

        for (int i = 0; i < l1; i++) {
            if (A.charAt(i) == B.charAt(i)) continue;
            int in = i + 1;
            while (in < l1 && A.charAt(i) != B.charAt(in) && A.charAt(in) != B.charAt(i)) {
                in++;
            }
            if (in >= l1) return false;
            if (A.charAt(in) != B.charAt(i) || A.charAt(i) != B.charAt(in)) continue;
            StringBuilder Asb = new StringBuilder(A);
            Asb.setCharAt(i, B.charAt(i));
            Asb.setCharAt(in, B.charAt(in));

            return Asb.toString().equals(B);
        }

        return false;
    }

    public static void main(String[] args) {
        boolean b = buddyStrings("ab", "ac");
        System.out.println(b);
    }
}
