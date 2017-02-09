/*
   Given a Binary Tree having positive and negative nodes, the task is to find maximum sum level in it.
    
    1) Gets input intgers from the command line.
    2) Forms a Binary Tree
    2-1) Applied an assumption that the input comes in in an order of level.
    2-2) Inputs fill the leftmost node first.
      example : java BinaryMaxLevel 10 -3 7 6 -5 2
           10
         /     \
       -3       7
      /   \   /   \
     6    -5 2
    3) It forms a queue with a root node and keep the original size of it to iterate as many times as it indicates.
    4) Insert new left and right nodes into the queue.
    5) Each time of the end of level, update a new max summation of a level
    6) Iterates through the loop with the extended steps of 3), 4) and 5).
    7) The main function prints out the result value.

    http://www.practice.geeksforgeeks.org/problem-page.php?pid=700448
   */

import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

class Node {
    private int val;
    private Node left = null;
    private Node right = null;

    public Node getLeft() { return left; }
    public Node getRight() { return right; }
    public int getVal() { return val; }
    public void setVal(int value) { this.val = value; }
    public void setLeft() {
        left = new Node();
        return;
    }
    public void setRight() {
        right = new Node();
        return;
    }
}

class BinaryTree {
    private Node root = new Node();

    public Node getRoot() { return root; }

    public void insert(int[] inputs) {
        Queue<Node> treeQ = new LinkedList<Node>();
        int length = inputs.length;
        int index = 0;
        Node n = root;
        //n.setVal(inputs[0]);
        treeQ.add(n);
        while(treeQ.size() != 0) {
            int size = treeQ.size();
            for(int i = 0; i < size; i++) {
                if(index >= length) return;
                n = treeQ.remove();
                n.setVal(inputs[index]);
                if(index + 1 < length) {
                    n.setLeft();
                    treeQ.add(n.getLeft());
                }
                if(index + 2 < length) {
                    n.setRight();
                    treeQ.add(n.getRight());
                }
                index++;
            }
        }
        return;
    }
}

public class BinaryMaxLevel {

    private static int max = Integer.MIN_VALUE;
    private static Queue<Node> q = new LinkedList<Node>();

    public static int getLevelMax(BinaryTree tree) {
        Node n = tree.getRoot();
        q.offer(n);
        while(q.size() != 0) {
            int size = q.size();
            int sum = 0;
            for(int i = 0; i < size; i++) {
                Node x = q.remove();
                if(x != null) {
                    sum += x.getVal();
                    q.offer(x.getLeft());
                    q.offer(x.getRight());
                }
            }
            if(sum > max) max = sum;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] inputs = new int[args.length];
        for(int i = 0; i < args.length; i++) {
            inputs[i] = Integer.parseInt(args[i]);
        }
        BinaryTree btree = new BinaryTree();
        btree.insert(inputs);
        System.out.println(getLevelMax(btree));
        return;
    }
}
