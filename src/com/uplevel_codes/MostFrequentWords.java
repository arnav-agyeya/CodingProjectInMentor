package com.uplevel_codes;


import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import org.apache.commons.io.IOUtils;
import org.json.*;


/*Input:
{
  "k": 4,
  "words": ["car", "bus", "taxi", "car", "driver", "candy", "race", "car", "driver", "fare", "taxi"]
}
* */
class SolutionMostFrequentWords {

    static ArrayList<String> k_most_frequent(Integer k, ArrayList<String> words) {
        // Write your code here.
//        System.out.println(words);

        Map<String, Integer> frequency = new HashMap<>();

        for(String word : words){
            frequency.put(word, frequency.getOrDefault(word, 0)+1);
        }

        System.out.println(frequency);

        List<String> ans = frequency.keySet().stream().sorted((o1,o2)->{
            if(frequency.get(o2)==frequency.get(o1)){
                return String.CASE_INSENSITIVE_ORDER.compare(o1,o2);
            }
            return Integer.compare(frequency.get(o2),frequency.get(o1));
        }).toList().subList(0,k);


        return new ArrayList<>(ans);
    }


}

class MostFrequentWords {

    static void output_str(String argument) {
        output_string.append(JSONObject.quote(argument));
    }

    static void output_list_str(ArrayList<String> argument) {
        output_string.append('[');
        int count = argument.size();
        for (String i : argument) {
            output_str(i);
            count = count - 1;
            if (count > 0) {
                output_string.append(',');
                output_string.append(' ');
            }
        }
        output_string.append(']');
    }


    static Integer input_int32(Object data) {
        Integer argument = (Integer) data;
        return argument;
    }


    static String input_str(Object data) {
        String argument = (String) data;
        return argument;
    }


    static ArrayList<String> input_list_str(Object data) {
        JSONArray json_array = (JSONArray) data;
        ArrayList argument = new ArrayList<String>();
        for (Object json_array_item : json_array) {
            argument.add(input_str(json_array_item));
        }
        return argument;
    }


    private static final DecimalFormat float_formatter = new DecimalFormat("0.00");
    private static final StringBuilder output_string = new StringBuilder();

    public static void main(String[] args) throws Exception {

        Integer k;
        ArrayList<String> words;
        try {
            InputStream is = new FileInputStream("src/com/uplevel_codes/input.json");
            String jsonTxt = IOUtils.toString(is, "UTF-8");
            System.out.println(jsonTxt);
            JSONObject data = new JSONObject(jsonTxt);

            k = input_int32(data.get("k"));
            words = input_list_str(data.get("words"));
        } catch (Exception ex) {
            System.err.println("reading-input-failed-json");
            ex.printStackTrace();
            throw ex;
        }

        PrintStream original_out = System.out;
        System.setOut(System.err);
        ArrayList<String> result = SolutionMostFrequentWords.k_most_frequent(k, words);
        System.setOut(original_out);
        output_list_str(result);
        output_string.append('\n');
        System.out.print(output_string.toString());
    }
}
