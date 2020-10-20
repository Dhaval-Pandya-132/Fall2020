package com.multithreding.Quetion8;

import java.util.ArrayList;

public class main {

    public static void main(String args[]){
        TreeNode parent = new TreeNode();
        TreeNode child = new TreeNode();
        child.children.add("test");
        parent.addChild(child);
        System.out.println("Children node :"+ parent.children.get(0));

    }


}
