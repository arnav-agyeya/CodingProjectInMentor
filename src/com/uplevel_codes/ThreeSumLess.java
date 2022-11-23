package com.uplevel_codes;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ThreeSumLess {

    static Integer count_triplets(Integer target, ArrayList<Integer> numbers) {
        // Write your code here.
        numbers.sort(Integer::compare);
        System.out.println(numbers);

        int tot = 0;

        for (int i = 0; i < numbers.size(); i++) {
//            if(i>0 && numbers.get(i).equals(numbers.get(i-1))){
//                continue;
//            }
            tot+=twoSum(numbers, i, target-numbers.get(i));
        }
        return tot;
    }

    private static int twoSum(ArrayList<Integer> numbers, int i, int target) {
        int l = i+1;
        int r = numbers.size()-1;
        int count = 0;
        while(l<r){
            int tot = numbers.get(l)+numbers.get(r);
            if(tot>=target){
                r=r-1;
            }
            else{
                System.out.println("I:" + i);
                System.out.println("L:" + l);
                System.out.println("R:" + r);
                System.out.println("========");
                count+=r-l;
                l++;
            }
        }

        return count;

    }

    static ArrayList<Integer> input_list_int32(Object data) {
        JSONArray json_array = (JSONArray) data;
        ArrayList<Integer> argument = new ArrayList<Integer>();
        for (Object json_array_item : json_array) {
            argument.add((Integer) json_array_item);
        }
        return argument;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Integer> arr;
        Integer target;
        try {
            File f = new File("input.json");
            InputStream is = new FileInputStream("src/com/uplevel_codes/input.json");
            String jsonTxt = IOUtils.toString(is, "UTF-8");
            System.out.println(jsonTxt);
            JSONObject json = new JSONObject(jsonTxt);

            arr = input_list_int32(json.get("numbers"));
            target = (Integer) (json.get("target"));
        } catch (Exception ex) {
            System.err.println("reading-input-failed-json");
            ex.printStackTrace();
            throw ex;
        }

        System.setOut(System.err);
        Integer result = count_triplets(target, arr);
        System.out.print(result);
    }
}
