package com.company.leetcode.kuaishou;

public class week4 {
    public int findMin(int[] nums) {//leetcode153寻找旋转数组的最小值
        int len=nums.length;
        if(len==1)
            return nums[0];
        int l=0,r=len-1,mid=0;
        while(l<=r){
            mid=(l+r)/2;
            if(mid-1>=0&&nums[mid]<nums[mid-1])
                return nums[mid];
            if(nums[0]<=nums[mid])
                l=mid+1;
            else
                r=mid-1;
        }
        return nums[0];
    }

    public void dfs(char[][] grid,int r,int c){
        int nl=grid.length,nc=grid[0].length;
        if(r<0||c<0||r>=nl||c>=nc||grid[r][c]=='0')
            return;
        grid[r][c]='0';
        dfs(grid,r-1,c);
        dfs(grid,r+1,c);
        dfs(grid,r,c-1);
        dfs(grid,r,c+1);
    }
    public int numIslands(char[][] grid) {//leetcode200 岛屿数量
        if(grid==null||grid.length==0)
            return 0;
        int nl=grid.length,nc=grid[0].length;
        int res=0;
        for(int r=0;r<nl;r++){
            for(int c=0;c<nc;c++){
                if(grid[r][c]=='1'){
                    res++;
                    dfs(grid,r,c);
                }
            }
        }
        return res;
    }

    public int jump(int[] nums) {//leetcoe45跳跃游戏2
        int len=nums.length;
        int end=0,maxI=0,step=0;
        for(int i=0;i<len;i++){
            maxI=Math.max(maxI,i+nums[i]);
            if(i==end&&i!=len-1){
                end=maxI;
                step++;
            }
        }
        return step;
    }
}
