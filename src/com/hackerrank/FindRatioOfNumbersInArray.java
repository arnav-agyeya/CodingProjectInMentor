package com.hackerrank;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;



public class FindRatioOfNumbersInArray {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        Result.plusMinus(arr);

        bufferedReader.close();
    }

    public class Result {

        /*
         * Complete the 'plusMinus' function below.
         *
         * The function accepts INTEGER_ARRAY arr as parameter.
         */

        public static void plusMinus(List<Integer> arr) {

            arr.sort(Integer::compare);

            int countOfNegative = Collections.binarySearch(arr, -1) < 0 ? Collections.binarySearch(arr, -1) * -1 -1: Collections.binarySearch(arr, -1) + 1;
            int countOfZeros = (Collections.binarySearch(arr, 1) < 0 ? Collections.binarySearch(arr, 1)*-1 -1: Collections.binarySearch(arr, 1)) - countOfNegative;
            if (Collections.binarySearch(arr,0) < 0){
                countOfZeros = 0;
            }

            int countOfPositives = arr.size() - countOfNegative - countOfZeros;


            System.out.printf("%.6f%n", (countOfPositives*1.0)/arr.size());
            System.out.printf("%.6f%n",(countOfNegative*1.0)/arr.size());
            System.out.printf("%.6f%n", (countOfZeros*1.0)/arr.size());

        }

    }
}

