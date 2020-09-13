package com.company.leetcode.kuaishou;

public class week6 {
    public static int dp(int[][] memo,int[] nums,int left,int right){
        if(left+1==right)
            return 0;
        if(memo[left][right]>0)
            return memo[left][right];
        int ans=0;
        for(int i=left+1;i<right;i++){
            ans=Math.max(ans,nums[i]*nums[left]*nums[right]+dp(memo,nums,left,i)+dp(memo,nums,i,right));
        }
        memo[left][right]=ans;
        return ans;
    }
    public int maxCoins(int[] nums) {//leetcode312错气球；
        int n=nums.length+2;
        int[] newN=new int[n];
        for(int i=0;i<nums.length;i++){
            newN[i+1]=nums[i];
        }
        newN[0]=newN[nums.length+1]=1;
        int[][] memo=new int[n][n];
        return dp(memo,newN,0,n-1);
    }

    long M=1000000007;
    public int checkRecord(int n) {//leetcode552学生出勤记录2
        long a0l0=1,a0l1=0,a0l2=0,a1l0=0,a1l1=0,a1l2=0;
        for(int i=0;i<=n;i++){
            long new_a0l0=(a0l0+a0l1+a0l2)%M;
            a0l2=a0l1;
            a0l1=a0l0;
            a0l0=new_a0l0;
            long new_a1l0=(a0l0+a1l1+a1l2+a1l0)%M;
            a1l2=a1l1;
            a1l1=a1l0;
            a1l0=new_a1l0;
        }
        return (int)a1l0;
    }

    public boolean check(int[] nums,int x,int m){
        int sum=0,cnt=1;
        for(int i=0;i<nums.length;i++){
            if(sum+nums[i]>x){
                cnt++;
                sum=nums[i];
            }else
                sum+=nums[i];
        }
        return cnt<=m;
    }
    public int splitArray(int[] nums, int m) {//leetcode410分割数组的最大值；
        int left=0,right=0;
        for(int i=0;i<nums.length;i++){
            right+=nums[i];
            if(left<nums[i])
                left=nums[i];
        }
        while(left<=right){
            int mid=(left+right)/2;
            if(check(nums,mid,m))
                right=mid-1;
            else
                left=mid+1;
        }
        return left;
    }

    public int dpmax(int[] array,int k){
        int len=array.length,rollSum=0,roollMax=array[0];
        for(int a:array){
            if(rollSum>0)
                rollSum+=a;
            else
                rollSum=a;
            roollMax=Math.max(roollMax,rollSum);
        }
        if(roollMax<=k)
            return roollMax;
        int max=Integer.MIN_VALUE;
        for(int i=0;i<len;i++){
            int sum=0;
            for(int j=i;j<len;j++){
                sum+=array[j];
                if(sum>max&&sum<=k)
                    max=sum;
                if(max==k)
                    return max;
            }
        }
        return max;
    }
    public int maxSumSubmatrix(int[][] matrix, int k) {//leetcode363矩形区域不超过k的最大数值和
        int row=matrix.length,col=matrix[0].length;
        int max=Integer.MIN_VALUE;
        for(int l=0;l<col;l++){
            int[] array=new int[row];
            for(int r=l;r<col;r++){
                for(int i=0;i<row;i++)
                    array[i]+=matrix[i][r];
                max=Math.max(max,dpmax(array,k));
                if(max==k)
                    return max;
            }
        }
        return max;
    }

    public int minDistance(String word1, String word2) {//leetcode72编辑距离
        int len1=word1.length();
        int len2=word2.length();
        int[][] dp=new int[len1+1][len2+1];
        for(int i=0;i<=len1;i++)
            dp[i][0]=i;
        for(int i=0;i<=len2;i++)
            dp[0][i]=i;
        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1))
                    dp[i][j]=dp[i-1][j-1];
                else
                    dp[i][j]=Math.min(Math.min(dp[i-1][j],dp[i-1][j-1]),dp[i][j-1])+1;
            }
        }
        return dp[len1][len2];
    }

    public int longestValidParentheses(String s) {//leetcode32最长有效括号
        int left=0,right=0,maxlength=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                left++;
            }else
                right++;
            if(left==right){
                maxlength=Math.max(maxlength,2*right);
            }else if(right>left){
                left=right=0;
            }
        }
        left=right=0;
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)=='('){
                left++;
            }else{
                right++;
            }
            if(left==right){
                maxlength=Math.max(maxlength,2*left);
            }else if(left>right){
                left=right=0;
            }
        }
        return maxlength;}


        public int maximalSquare(char[][] matrix) {//leetcode221最大正方形
            if(matrix==null||matrix.length==0||matrix[0].length==0)
                return 0;
            int width=matrix[0].length,nw=0,nextNW=0,maxL=0;
            int[] dp=new int[width+1];
            for(char[] mat:matrix){
                nw=0;
                for(int i=0;i<width;i++){
                    nextNW=dp[i+1];
                    if(mat[i]=='1'){
                        dp[i+1]=Math.min(Math.min(dp[i],dp[i+1]),nw)+1;
                        maxL=Math.max(maxL,dp[i+1]);
                    }else
                        dp[i+1]=0;
                    nw=nextNW;
                }
            }
            return maxL*maxL;
        }

}
