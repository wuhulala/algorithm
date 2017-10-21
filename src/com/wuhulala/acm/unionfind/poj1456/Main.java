package com.wuhulala.acm.unionfind.poj1456;


import java.util.*;

class Node {
    private int start;
    private int end;
    private int length;

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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


}

class UnionFind {
    private int n;
    private int[] par;
    private int count;

    public UnionFind(int n) {
        this.count = n;
        this.n = n;
        par = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            par[i] = i;
        }
    }

    private int getParent(int child) {
        if (par[child] == child) {
            return child;
        }
        par[child] = getParent(par[child]);
        return par[child];
    }

    public boolean addUnion(int x, int y) {
        int fx = getParent(x);
        int fy = getParent(y);
        if (fx != fy) {
            par[fx] = fy;
            this.count--;
            return true;
        }
        return false;
    }

    public int calcResult() {
        return count;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean visit[] = new boolean[10010];
        List<Node> nodeList;
        while (sc.hasNextInt()) {
            for (int i = 0; i < visit.length; i++) {
                visit[i] = false;
            }
            int n = sc.nextInt();
            nodeList = new ArrayList<Node>();
            for (int i = 0; i < n; i++) {
                Node node = new Node();
                node.setLength(sc.nextInt());
                node.setStart(sc.nextInt());
                node.setEnd(0);
                nodeList.add(node);
            }
            Collections.sort(nodeList, new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o2.getLength() - o1.getLength();
                }
            });
            //UnionFind uf = new UnionFind(maxValue + 1000);
            int result = 0;
            int len = nodeList.size();
            for (int i = 0; i < len; i++) {
                Node node = nodeList.get(i);

                for (int j = node.getStart(); j > 0; --j) {
                    if (!visit[j]) {
                        result += node.getLength();
                        visit[j] = true;
                        break;
                    }
                }

            }
            System.out.println(result);
        }
    }
}
