package com.uplevel_codes;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PowerFunction {

    static final int modder = 1000000007;

    static Integer returnModedRes(Long num){
        return Math.toIntExact(num % modder);
    }

    static Integer calculate_power(Long a, Long b) {
        // Write your code here.
        System.out.println("A:"+a);
        System.out.println("B:"+b);
        System.out.println();

        if (b == 0) {
            return 1;
        }

        if (b==1){
            return returnModedRes(a);
        }


        a = Long.valueOf(calculate_power(a * a, b / 2));



        return returnModedRes(a);
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

        Long a; Long b;
        try {
            File f = new File("input.json");
            InputStream is = new FileInputStream("src/com/uplevel_codes/input.json");
            String jsonTxt = IOUtils.toString(is, "UTF-8");
            System.out.println(jsonTxt);
            JSONObject json = new JSONObject(jsonTxt);

            a = Long.parseLong((String) json.get("a"));
            b = Long.parseLong(String.valueOf(json.get("b")));
        } catch (Exception ex) {
            System.err.println("reading-input-failed-json");
            ex.printStackTrace();
            throw ex;
        }

        System.setOut(System.err);
        Integer result = calculate_power(a, b);
        System.out.print(result);
    }
}
