package com.wuhulala.acm.unionfind.poj2524;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class Node {
    private int start;
    private int end;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}

class UnionFind {
    private int n;
    private List<Node> nodes;
    private int[] par;

    public UnionFind(int n, List<Node> nodes) {
        this.n = n;
        this.nodes = nodes;
        par = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            par[i] = i;
        }
    }

    private int getParent(int child) {
        if (par[child] == child) {
            return child;
        }
        //路径压缩
        par[child] = getParent(par[child]);
        return par[child];
    }

    private void addUnion(Node node) {
        int x = getParent(node.getEnd());
        int y = getParent(node.getStart());
        if (x != y) {
            par[x] = y;
        }
    }

    public int calcResult() {
        Iterator<Node> it = nodes.iterator();
        while (it.hasNext()) {
            Node node = it.next();
            addUnion(node);
        }
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (par[i] == i) {
                count++;
            }
        }
        return count;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> result = new ArrayList<Integer>();
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            if (n == 0 && m == 0) {
                int count = 0;
                Iterator<Integer> it = result.iterator();
                while (it.hasNext()) {
                    System.out.println("Case "+ ++count +": " + it.next());
                }
                break;
            }
            List<Node> nodeList = new ArrayList<Node>();
            for (int i = 0; i < m; i++) {
                Node node = new Node();
                node.setStart(sc.nextInt());
                node.setEnd(sc.nextInt());
                nodeList.add(node);
            }
            int one = new UnionFind(n, nodeList).calcResult();
            if (one < 0) one = 0;
            result.add(one);
        }
    }
}
