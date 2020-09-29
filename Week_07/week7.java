package com.company.leetcode.kuaishou;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class week7 {
    int[] line=new int[9];
    int[] column=new int[9];
    int[][] block=new int[3][3];
    List<int[]> space=new ArrayList<>();
    boolean valid=false;
    public void flip(int i,int j,int digit){
        line[i]^=(1<<digit);
        column[j]^=(1<<digit);
        block[i/3][j/3]^=(1<<digit);
    }
    public void dfs(char[][] board,int pos){
        if(pos==space.size()){
            valid=true;
            return;
        }
        int[] data=space.get(pos);
        int i=data[0],j=data[1];
        int mask=~(line[i]|column[j]|block[i/3][j/3])&0x1ff;
        for(;mask!=0&&!valid;mask&=(mask-1)){
            int digitmask=mask&(-mask);
            int digit=Integer.bitCount(digitmask-1);
            flip(i,j,digit);
            board[i][j]=(char)(digit+'0'+1);
            dfs(board,pos+1);
            flip(i,j,digit);
        }
    }
    public void solveSudoku(char[][] board) {//leetcode解数独
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]=='.'){
                    space.add(new int[]{i,j});
                }else{
                    int digit=board[i][j]-'0'-1;
                    flip(i,j,digit);
                }
            }
        }
        dfs(board,0);
    }

    int[] queue;
    int[] cols;
    int[] hill;
    int[] dale;
    int n;
    List<List<String>> output=new ArrayList<>();
    public boolean isAttack(int row,int col){
        int res=queue[row]+cols[col]+hill[row-col+n-1]+dale[row+col];
        return (res==0)?true:false;
    }
    public void placeQueue(int row,int col){
        queue[row]=col;
        cols[col]=1;
        hill[row-col+n-1]=1;
        dale[row+col]=1;
    }
    public void removeQueue(int row,int col){
        queue[row]=0;
        cols[col]=0;
        hill[row-col+n-1]=0;
        dale[row+col]=0;
    }
    public void addResult(){
        List<String> l=new ArrayList<>();
        for(int i=0;i<n;i++){
            int col=queue[i];
            StringBuilder sb=new StringBuilder();
            for(int j=0;j<col;j++)
                sb.append(".");
            sb.append("Q");
            for(int j=0;j<n-col-1;j++)
                sb.append(".");
            l.add(sb.toString());
        }
        output.add(l);
    }
    public void backTask(int row){
        for(int i=0;i<n;i++){
            if(isAttack(row,i)){
                placeQueue(row,i);
                if(row+1==n)
                    addResult();
                else
                    backTask(row+1);
                removeQueue(row,i);
            }
        }
    }
    public List<List<String>> solveNQueens(int n) {//leetcode51N皇后
        this.n=n;
        queue=new int[n];
        cols=new int[n];
        hill=new int[2*n-1];
        dale=new int[2*n-1];
        backTask(0);
        return output;
    }


    List<String> result=new ArrayList<>();
    class TrieNode{
        Map<Character,TrieNode> children=new HashMap<>();
        String word=null;
        public TrieNode(){

        }
    }
    public void back(int x,int y,TrieNode root,char[][] board){
        char letter=board[x][y];
        TrieNode cur=root.children.get(letter);

        if(cur.word!=null){
            result.add(cur.word);
            cur.word=null;
        }

        board[x][y]='#';

        int[] sx={0,1,0,-1};
        int[] sy={1,0,-1,0};
        for(int i=0;i<4;i++){
            int newX=x+sx[i];
            int newY=y+sy[i];
            if(newX<0||newX>=board.length||newY<0||newY>=board[0].length)
                continue;
            if(cur.children.containsKey(board[newX][newY])){
                back(newX,newY,cur,board);
            }
        }

        board[x][y]=letter;

        if(cur.children.isEmpty()){
            root.children.remove(letter);
        }
    }
    public List<String> findWords(char[][] board, String[] words) {//leetcode212单词搜索2
        TrieNode root=new TrieNode();
        for(String w:words){
            TrieNode node=root;

            for(int i=0;i<w.length();i++){
                if(node.children.containsKey(w.charAt(i))){
                    node=node.children.get(w.charAt(i));
                }else{
                    TrieNode n=new TrieNode();
                    node.children.put(w.charAt(i),n);
                    node=n;
                }
            }
            node.word=w;
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(root.children.containsKey(board[i][j])){
                    back(i,j,root,board);
                }
            }
        }
        return result;
    }

}
