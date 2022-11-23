package com.hackerrank;

import java.io.*;



public class TImeConversion12To24 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.timeConversion(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    private class Result {

        /*
         * Complete the 'timeConversion' function below.
         *
         * The function is expected to return a STRING.
         * The function accepts STRING s as parameter.
         */

        public static String timeConversion(String s) {
            // Write your code here
            StringBuilder sb = new StringBuilder();
            char[] charArray = s.toCharArray();
            int h1 = Integer.parseInt(String.valueOf(charArray[0]));
            int h2 = Integer.parseInt(String.valueOf(charArray[1]));

            int hours = h1*10+h2;

            if(s.contains("AM")){
                if(hours == 12){
                    sb.append('0');
                    sb.append('0');
                }
                else{
                    sb.append(h1);
                    sb.append(h2);
                }
            }
            else {
                hours +=12;
                sb.append(hours/10);
                sb.append(h2%10);

            }
            sb.append(s, 2, s.length()-3);

            return sb.toString();

        }

    }
}

