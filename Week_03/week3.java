package com.company.leetcode.kuaishou;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class week3 {
    public void getPath(List<TreeNode> l,TreeNode root,TreeNode target){
        Stack<TreeNode> s=new Stack<>();
        TreeNode p=root,prev=null;
        while(p!=null||!s.isEmpty()){
            while(p!=null){
                s.push(p);
                l.add(p);
                if(p==target)
                    return;
                p=p.left;
            }
            p=s.peek();
            if(p.right==null||p.right==prev){
                s.pop();
                l.remove(l.size()-1);
                prev=p;
                p=null;
            }else
                p=p.right;
        }
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {//二叉树的最近公共祖先，非递归
        if(root==null)
            return null;
        List<TreeNode> l1=new ArrayList<>();
        List<TreeNode> l2=new ArrayList<>();
        getPath(l1,root,p);
        getPath(l2,root,q);
        TreeNode last=null;
        int n=Math.min(l1.size(),l2.size());
        for(int i=0;i<n;i++){
            if(l1.get(i)!=l2.get(i))
                return last;
            last=l1.get(i);
        }
        return last;
    }

    public void getL(List<Integer> l,List<List<Integer>> ll,int n,int k,int index)   {if(l.size()==k){
        ll.add(new ArrayList<>(l));
        return ;}
        for(int i=index;i<=n;i++){
            l.add(i);
            getL(l,ll,n,k,i+1);
            l.remove(l.size()-1);
        }
    }
    public List<List<Integer>> combine(int n, int k) {//组合
        if(n<0||k<0||k>n)
            return null;
        List<List<Integer>> ll=new ArrayList<>();
        List<Integer> l=new ArrayList<>();
        getL(l,ll,n,k,1);
        
        return ll;
    }


}
