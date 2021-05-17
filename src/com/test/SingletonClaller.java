package com.test;

import java.util.*;

public class SingletonClaller {


//      0	1	2	3	4
// 0	1	0	1	0	0
// 1	1	1	0	0	0
// 2	0	0	1	0	1
// 3	0	0	0	0	0
// 4	1	0	1	1	1


    // mapentry -> (1,1)--->(0,0)
    public void findPath(int[][] matrix){
        int columnSize = matrix.length;
        int rowSize = matrix[0].length;

        int is[] = new int[]{-1,0,1};
        int js[] = new int[]{-1,-1,-1};

        Map<Indices, Indices> parentCells = new HashMap<>();

        Queue<Indices> queue = new ArrayDeque<>();
        Indices indices = new Indices(0, 0);
        queue.add(indices);
        parentCells.put(indices, indices);

        while (!queue.isEmpty()){
            Indices popIndices = queue.poll();

            if(popIndices.x == columnSize-1 && popIndices.y == rowSize-1){
                Indices popIndicesCopy = popIndices;
                while(true){
                    Indices tempIndices = parentCells.get(popIndicesCopy);
                    System.out.println(tempIndices);
                    popIndicesCopy = tempIndices;
                    if(tempIndices.x == 0 && tempIndices.y == 0) break;

                }
                return;
            }

            for (int i = 0; i < 8; i++) {
                int newX = popIndices.x + is[i];
                int newY = popIndices.y + js[i];
                Indices newIndices = new Indices(newX, newY);
                if(isValidCell(newX, newY, rowSize, columnSize) && matrix[newX][newY]!=0 &&
                   !parentCells.containsKey(newIndices)) {
                    queue.add(newIndices);
                    parentCells.put(newIndices, popIndices);
                }
            }
        }

        // print not able to find

    }

    public boolean isValidCell(int i, int j, int rowLength, int columnLength){

        if(i<0 || i> columnLength || j<0 || j>rowLength)
            return false;
        return true;

    }

    private class Indices{
        int x;
        int y;

        public Indices(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }



}
