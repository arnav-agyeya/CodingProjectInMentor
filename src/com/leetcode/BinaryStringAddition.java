package com.leetcode;

public class BinaryStringAddition {

    public int getInt(char ch){
        if(ch=='0')
            return 0;
        return 1;
    }
    public String addBinary(String a, String b) {

        int aLen = a.length();
        int bLen = b.length();
        StringBuilder res = new StringBuilder();

        a= getReverseString(a);
        b=getReverseString(b);

        if(aLen<bLen){
            String tmp = a;
            a = b;
            b = tmp;
        }
        boolean cf = false;

        cf=add(a, b,0, bLen, res,false);
        cf=add(a,b,bLen,aLen,res,cf);

        if(cf){
            res.append("1");
        }

        return res.reverse().toString();

    }

    private String getReverseString(String res) {
        return new StringBuilder(res).reverse().toString();
    }

    private boolean add(String a, String b, int sPos, int bLen, StringBuilder res, boolean cf) {
        for(int in=sPos; in < bLen; in++){

            int d1 = (a.length()>in)?getInt(a.charAt(in)):0;
            int d2 = (b.length()>in)?getInt(b.charAt(in)):0;
            int tRes = cf?d1+d2+1:d1+d2;

            res.append(tRes%2);
            cf = (tRes / 2) != 0;
        }
        return cf;
    }
}
