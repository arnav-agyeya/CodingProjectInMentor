package com.hackerrank;

import java.io.*;
import java.util.*;

public class TwoStackAsQueues {

    private static class QFromStack {
        Stack<Integer> eq;
        Stack<Integer> dq;

        public QFromStack(){
            eq = new Stack<>();
            dq = new Stack<>();
        }

        public void add(int n){
            eq.add(n);
        }

        public int peek(){
            if(dq.isEmpty())
                emptyEq();

            return dq.peek();
        }

        public int pop(){
            if(dq.isEmpty())
                emptyEq();

            return dq.pop();
        }

        public void emptyEq(){
            while(!eq.isEmpty()){
                dq.add(eq.pop());
            }
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner sc = new Scanner(System.in);

        int queries;
        queries = Integer.parseInt(sc.next());

        QFromStack q = new QFromStack();

        while(queries>0){
            int operation;
            operation = Integer.parseInt(sc.next());

            if(operation == 1){
                int val = Integer.parseInt(sc.next());
                q.add(val);
            }
            else if(operation == 2){
                q.pop();
            }
            else{
                System.out.println(q.peek());
            }
        }
    }
}
