package com.leetcode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParellelHashStrings {

    public static void main(String[] args) {
        List<String> stringSet = readStrings()
                .stream()
                .map(str -> Arrays.stream(str.split(" "))
                                  .collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        ConcurrentHashMap<String, Integer> collect = new ConcurrentHashMap<>();
                stringSet.parallelStream().forEach(str->collect.put(str, collect.getOrDefault(str, 0)+1));
        ConcurrentHashMap<String, Integer> concurrentHashMap =
                collect.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(ConcurrentHashMap::new,
                (map, entry) -> map.put(entry.getKey(), map.getOrDefault(entry.getKey(), 0) + 1),
                        (m1, m2) -> m1.forEach((key, value) -> m2.put(key, m2.getOrDefault(key, 0) + 1)));
        System.out.println(collect);
    }

    private static ArrayList<String> readStrings() {
        ArrayList<String> list = new ArrayList();
        Scanner in = new Scanner(System.in);
        int i = 3;
        try{
            while(i>0){
                i--;
                list.add(in.nextLine().toLowerCase().replaceAll("[.]", ""));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            in.close();
        }
        return list;
    }
}
