package com.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/541/week-3-june-15th-june-21st/3363/
 */
public class SurroundedRegions {

    public static void solve(char[][] board) {
        int length = board.length;
        int width = board[0].length;
        boolean[][] vis = new boolean[length][width];
        for(boolean[] row : vis){
            Arrays.fill(row,false);
        }

        for(int i=0; i< length;i++){
            for (int j=0; j<width;j++){
                if(i==0 || i==length-1 || j==0 || j==width-1){
                    if(board[i][j]=='O'){
                        dfs(board,vis,i,j);
                    }
                }
            }
        }
        for(int i=0; i< length;i++){
            for (int j=0; j<width;j++){
                if(board[i][j]=='O'){
                    board[i][j]='X';
                }
                if(board[i][j]=='#'){
                    board[i][j]='O';
                }
                System.out.print(board[i][j]+" ");
            }
            System.out.println(" ");
        }

    }

    private static void dfs(char[][] board, boolean[][] vis, int i, int j) {
        if(!isValid(vis,i,j)){
            return;
        }
        vis[i][j] = true;
        if(board[i][j]=='X'){
            return;
        }
        board[i][j]='#';
        dfs(board,vis,i-1,j);
        dfs(board,vis,i+1,j);
        dfs(board,vis,i,j-1);
        dfs(board,vis,i,j+1);

    }

    private static boolean isValid(boolean[][] vis, int i, int j) {

        if(i<0 || j<0 || i>=vis.length || j>=vis[0].length){
            return false;
        }
        return !vis[i][j];
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{{'X', 'X', 'X', 'X'},
                                      {'X', 'O', 'O', 'X'},
                                      {'X', 'X', 'O', 'X'},
                                      {'X', 'X', 'O', 'X'},
                                      {'O', 'O', 'O', 'X'}};
        solve(board);
    }
}
