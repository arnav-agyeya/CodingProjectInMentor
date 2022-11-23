package com.hackerrank;


import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class GridChallenge {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.next());

        IntStream.range(0, t).forEach(tItr -> {
            int n = Integer.parseInt(sc.next());

            List<String> grid = IntStream.range(0, n).mapToObj(i -> sc.next())
                    .collect(toList());

            System.out.println(grid);

            String result = Result.gridChallenge(grid);

            System.out.println(result);
        });
    }

    private class Result {

        /*
         * Complete the 'gridChallenge' function below.
         *
         * The function is expected to return a STRING.
         * The function accepts STRING_ARRAY grid as parameter.
         */

        public static String gridChallenge(List<String> grid) {
            // Write your code here
            char[][] ca = new char[grid.size()][];
            int i = 0;
            for(String s : grid){
                ca[i] = s.toCharArray();
                Arrays.sort(ca[i]);
                i++;
            }
            i=0;
            for(i = 0; i<grid.get(0).length(); i++){
                for(int j=1; j<grid.size(); j++){
                    if(ca[j][i]<ca[j-1][i])
                        return "NO";
                }
            }

            return "YES";

        }

    }
}
