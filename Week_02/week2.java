package com.company.leetcode.kuaishou;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class week2 {
    public List<List<String>> groupAnagrams(String[] strs) {//leetcode49，字母异位词分组
        Map<Integer,List<String>> map=new HashMap<>();
        List<List<String>> ans=new ArrayList<>();
        int len=strs.length;
        if(len<=0)
            return ans;
        int[] arr={2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101};
        for(String s:strs){
            int sLen=s.length();
            int start=0;
            int res=1;
            while(start<sLen){
                res*=arr[s.charAt(start++)-'a'];
            }
            if(map.containsKey(res)){
                List<String> ls=map.get(res);
                ls.add(s);
                map.put(res,ls);
            }else{
                List<String> ls=new ArrayList<>();
                ls.add(s);
                map.put(res,ls);
            }
        }
        for(List<String> ls:map.values()){
            ans.add(ls);
        }
        return ans;
    }

public int nthUglyNumber(int n) {//丑数
    if(n==1)
        return 1;
    int a=0,b=0,c=0;
    int[] dp=new int[n];
    dp[0]=1;
    for(int i=1;i<n;i++){
        int n1=dp[a]*2;
        int n2=dp[b]*3;
        int n3=dp[c]*5;
        dp[i]=Math.min(n1,Math.min(n2,n3));
        if(dp[i]==n1)
            a++;
        if(dp[i]==n2)
            b++;
        if(dp[i]==n3)
            c++;
    }
    return dp[n-1];

    }

    public int[] topKFrequent(int[] nums, int k) {//前k个高频数
        Map<Integer,Integer> map=new HashMap<>();
        for(int num:nums)
            map.put(num,map.getOrDefault(num,0)+1);
        List<Integer>[] bulte=new List[nums.length+1];
        for(int a:map.keySet()){
            int i=map.get(a);
            if(bulte[i]==null)
                bulte[i]=new ArrayList<>();
            bulte[i].add(a);
        }
        List<Integer> l=new ArrayList<>();
        for(int i=nums.length;i>=0&&l.size()<=k;i--){
            if(bulte[i]!=null){
                l.addAll(bulte[i]);
            }
        }
        int[] result=new int[k];
        for(int i=0;i<k;i++)
            result[i]=l.get(i);
        return result;
    }
}
