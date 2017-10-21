package com.wuhulala.acm.unionfind.poj1182;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class Node {
    private int start;
    private int end;

    public Node() {
    }

    public Node(int start, int end) {
        this.start = start;
        this.end = end;
    }

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
    private int count;

    public UnionFind(int n, List<Node> nodes) {
        this.n = n;
        this.count = n;
        this.nodes = nodes;
        par = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            par[i] = i;
        }
        Iterator<Node> it = nodes.iterator();
        while (it.hasNext()) {
            Node node = it.next();
            addUnion(node);
        }
    }

    private int getParent(int child) {
        if (par[child] == child) {
            return child;
        }
        par[child] = getParent(par[child]);
        return par[child];
    }

    public void addUnion(Node node) {
        int x = getParent(node.getEnd());
        int y = getParent(node.getStart());
        if (x != y) {
            par[x] = y;
            count --;
        }
    }

    public boolean connected(int x, int y) {
        return getParent(x) == getParent(y);
    }

    public int result() {
        return count;
    }
}

/**
 * 食物链
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Node> nodeList = new ArrayList<Node>();
        int[] en = new int[n + 1];
        UnionFind uf = new UnionFind(n * 3, nodeList);
        int result = 0;
        for (int i = 0; i < m; i++) {
            Node node = new Node();

            int type = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();

            if (!judge(uf, type, x, y, n, en)) {
                result++;
            }
        }
        System.out.println(result);
    }

    private static boolean judge(UnionFind uf, int type, int x, int y, int n, int[] en) {
        if (x > n || y > n) {
            return false;
        }
        if (type == 1) {
            if (uf.connected(x, y + n) || uf.connected(x, y + 2 * n)) {
                return false;
            } else {
                uf.addUnion(new Node(x, y));
                uf.addUnion(new Node(x + n, y + n));
                uf.addUnion(new Node(x + 2 * n, y + 2 * n));
            }
        } else if (type == 2) {
            if (x == y) {
                return false;
            }
            if(uf.connected(x, y) || uf.connected(x, y + 2 * n)){
                return false;
            }else {
                uf.addUnion(new Node(x, y + n));
                uf.addUnion(new Node(x + n, y + 2 * n));
                uf.addUnion(new Node(x + 2 * n, y));
            }
        }
        return true;
    }
}
