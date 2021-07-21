package com.leetcode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FindBusiestDay {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int bookingCount = Integer.parseInt(scanner.next());
        List<String[]> arrivalDeparture = new ArrayList<>();
        for (int i = 0; i < bookingCount; i++) {
            String[] dates = {scanner.next(), scanner.next()};
            arrivalDeparture.add(dates);
        }
        System.out.println(getBusiestDay(arrivalDeparture));
    }

    public static String getBusiestDay(List<String[]> arrivalDeparture){

        List<LocalDate> arrival = new ArrayList<>();
        List<LocalDate> dept = new ArrayList<>();

        int lengthOfResult = 2*arrivalDeparture.size();
        int ccount = 0;

        for (String[] strings : arrivalDeparture) {
            LocalDate date = getLocalDate(strings, 0);
            LocalDate date1 = getLocalDate(strings, 1);
            arrival.add(date);
            dept.add(date1);
        }

        arrival.sort(LocalDate::compareTo);
        dept.sort(LocalDate::compareTo);

        LocalDate maxDate = null;
        int maxBooking = -1;
        int runningCount = 0;
        int arrivalTop = 0;
        int deptTop = 0;

        while (ccount!= lengthOfResult ) {
            if(arrivalTop<arrival.size() && arrival.get(arrivalTop).compareTo(dept.get(deptTop))<0){
                runningCount++;
                if(runningCount>maxBooking){
                    maxDate = arrival.get(arrivalTop);
                    maxBooking = runningCount;
                }
                arrivalTop++;
            }
            else {
                deptTop++;
                runningCount--;
            }

            ccount++;

        }

        assert maxDate != null;
        return maxDate.toString();
    }

    private static LocalDate getLocalDate(String[] strings, int i) {
        List<Integer> collectArrivalDate =
                Arrays.stream(strings[i].split("-")).map(Integer::parseInt).collect(Collectors.toList());
        return LocalDate.of(collectArrivalDate.get(0), collectArrivalDate.get(1), collectArrivalDate.get(2));
    }
}
