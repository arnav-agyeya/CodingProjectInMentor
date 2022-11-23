package com.hackerrank;


import java.io.*;
import java.util.*;

public class TextEditor {

    private static class SimpleTextEditor{
        StringBuilder sb;
        Stack<Operation> undoStack;

        SimpleTextEditor(){
            sb = new StringBuilder();
            undoStack = new Stack<>();
        }

        public void append(String str){
            sb.append(str);
            undoStack.add(new Operation(1, str));
        }

        public void delete(int k){
            StringBuilder deletedString = new StringBuilder();

            for(int i = sb.length()-k; i<sb.length(); i++){
                deletedString.append(sb.charAt(i));
            }

            for(int i = 0; i<k; i++){
                sb.deleteCharAt(sb.length()-1);
            }

            undoStack.add(new Operation(2, deletedString.toString()));
        }

        public void print(int k){
            System.out.println(sb.charAt(k-1));
        }

        public void undo(){
            if(undoStack.isEmpty())
                return;

            Operation last = undoStack.pop();

            if(last.opType == 1){
                for(int i = 0; i<last.valueString.length(); i++){
                    sb.deleteCharAt(sb.length()-1);
                }
            }
            else{
                sb.append(last.valueString);
            }
        }
    }

    private static class Operation{
        int opType;
        String valueString;

        Operation(int opType, String valueString){
            this.opType = opType;
            this.valueString = valueString;
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int q = Integer.parseInt(sc.next());
        SimpleTextEditor te = new SimpleTextEditor();

        for(int i = 0; i<q; i++){
            int opType = Integer.parseInt(sc.next());
//            System.out.println(te.sb.toString());

            switch(opType){
                case 1: te.append(sc.next()); break;
                case 2: te.delete(Integer.parseInt(sc.next())); break;
                case 3: te.print(Integer.parseInt(sc.next())); break;
                case 4: te.undo();
            }
        }

    }
}
