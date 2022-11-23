package com.uplevel_codes;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/*
* Input:
*
{
  "arr": [2, 1, 6, 3, 1, 3, 5, 4, 4, 5, 6, 2],
  "target": 11
}
*/

class Solution {


    static ArrayList<ArrayList<Integer>> four_sum(ArrayList<Integer> arr, Integer target) {
        // Write your code here.
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        arr.sort(Integer::compare);
        System.out.println(arr);
        for (int i = 0; i < arr.size(); i++) {
            if (i > 0 && Objects.equals(arr.get(i), arr.get(i - 1)))
                continue;
            threeSum(arr, i, target - arr.get(i), ans);
        }

        return ans;
    }

    private static void threeSum(ArrayList<Integer> arr, int i, int target, ArrayList<ArrayList<Integer>> ans) {

        for (int j = i + 1; j < arr.size(); j++) {
            if (j > i + 1 && Objects.equals(arr.get(j), arr.get(j - 1)))
                continue;
            twoSum(arr, i, j, target - arr.get(j), ans);
        }
    }

    private static void twoSum(ArrayList<Integer> arr, int i, int j, int target, ArrayList<ArrayList<Integer>> ans) {
        int l = j + 1;
        int r = arr.size() - 1;
        while (l < r) {
            int total = arr.get(l) + arr.get(r);
            if (total == target) {
                System.out.println("I:" + i);
                System.out.println("J:" + j);
                System.out.println("L:" + l);
                System.out.println("R:" + r);
                ans.add(new ArrayList<>(Arrays.asList(arr.get(i), arr.get(j), arr.get(l), arr.get(r))));
                r--;
                l++;
                while (l < r && Objects.equals(arr.get(l), arr.get(l - 1))) {
                    l++;
                }

            } else if (total > target) {
                r = r - 1;
            } else {
                l++;
            }
        }

    }

}

class FourSomeProblem {

    static void output_int32(Integer argument) {
        output_string.append(argument);
    }

    static void output_list_int32(ArrayList<Integer> argument) {
        output_string.append('[');
        int count = argument.size();
        for (Integer i : argument) {
            output_int32(i);
            count = count - 1;
            if (count > 0) {
                output_string.append(',');
                output_string.append(' ');
            }
        }
        output_string.append(']');
    }

    static void output_list_list_int32(ArrayList<ArrayList<Integer>> argument) {
        output_string.append('[');
        output_string.append('\n');
        int count = argument.size();
        for (ArrayList<Integer> i : argument) {
            output_list_int32(i);
            count = count - 1;
            if (count > 0) {
                output_string.append(',');
            }
            output_string.append('\n');
        }
        output_string.append(']');
    }


    static Integer input_int32(Object data) {
        Integer argument = (Integer) data;
        return argument;
    }


    static ArrayList<Integer> input_list_int32(Object data) {
        JSONArray json_array = (JSONArray) data;
        ArrayList argument = new ArrayList<Integer>();
        for (Object json_array_item : json_array) {
            argument.add(input_int32(json_array_item));
        }
        return argument;
    }


    private static final DecimalFormat float_formatter = new DecimalFormat("0.00");
    private static final StringBuilder output_string = new StringBuilder();

    public static void main(String[] args) throws Exception {

        ArrayList<Integer> arr;
        Integer target;
        try {
            File f = new File("input.json");
            InputStream is = new FileInputStream("src/com/uplevel_codes/input.json");
            String jsonTxt = IOUtils.toString(is, "UTF-8");
            System.out.println(jsonTxt);
            JSONObject json = new JSONObject(jsonTxt);

            arr = input_list_int32(json.get("arr"));
            target = input_int32(json.get("target"));
        } catch (Exception ex) {
            System.err.println("reading-input-failed-json");
            ex.printStackTrace();
            throw ex;
        }

        PrintStream original_out = System.out;
        System.setOut(System.err);
        ArrayList<ArrayList<Integer>> result = Solution.four_sum(arr, target);
        System.setOut(original_out);
        output_list_list_int32(result);
        output_string.append('\n');
        System.out.print(output_string.toString());
    }
}

