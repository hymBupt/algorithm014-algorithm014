package com.company.leetcode.kuaishou;

public class week1 {
    public int removeDuplicates(int[] nums) {
        if(nums==null)
            return 0;
        int i=0;
        for(int j=0;j<nums.length;j++){
            if(nums[j]!=nums[i]){
                i++;
                nums[i]=nums[j];
            }
        }
        return i+1;
    }
    public void reverse(int[] nums,int start,int right){
        while(start<right){
            int temp=nums[start];
            nums[start]=nums[right];
            nums[right]=temp;
            start++;
            right--;
        }
    }
    public void rotate(int[] nums, int k) {
        int len=nums.length;
        k=k%len;
        reverse(nums,0,len-1);
        reverse(nums,0,k-1);
        reverse(nums,k,len-1);
    }
}
