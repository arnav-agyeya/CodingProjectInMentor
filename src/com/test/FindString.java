package com.test;

public class FindString {


    public int numberOfString(int input1, String[] input2, String input3){

        int result =0;
        for(String strInArray : input2){
            int count = 0;
            boolean isPossible = true;
            for(int i =0; i< strInArray.length();i++){
                char charAtStr = strInArray.charAt(i);
                char charAtInput3 = input3.charAt(i);
                if(charAtStr == charAtInput3){
                    continue;
                }
                if(charAtInput3 - charAtStr == 1){
                    count++;
                }
                else{
                    isPossible = false;
                    break;
                }
            }
            if(count <= 1 && isPossible){
                result ++;
            }

        }
        return result;

    }

    public static void main(String[] args) {
        FindString findString = new FindString();
        String[] input2 = new String[]{"ADCD", "ACCD", "ACBD", "ABCD", "ACCD", "ACBD"};
        String input3 = "ACCD";
        int input1 = 6;

        System.out.println(findString.numberOfString(input1, input2, input3));
    }
}
