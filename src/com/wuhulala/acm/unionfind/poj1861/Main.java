package com.wuhulala.acm.unionfind.poj1861;

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
    private List<Node> nodes;
    private int[] par;
    private List<Node> activeNodes;

    public UnionFind(int n, List<Node> nodes) {
        this.n = n;
        this.nodes = nodes;
        par = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            par[i] = i;
        }
        activeNodes = new ArrayList<Node>();
    }

    private int getParent(int child) {
        if (par[child] == child) {
            return child;
        }
        par[child] = getParent(par[child]);
        return par[child];
    }

    private void addUnion(Node node) {
        int x = getParent(node.getEnd());
        int y = getParent(node.getStart());
        if (x != y) {
            par[x] = y;
            activeNodes.add(node);
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

    public List<Node> getActiveNodes() {
        return activeNodes;
    }
}

/**
 * 题意： 根据给的节点以及节点的连线判断节点的存在几个连通量，并且保证连同量越少越好，并且构成这些连同量的成本最低
 *
 * 计算权重最小的并差集/最小生成树
 *
 * 1. 先根据权重排序
 * 2. 然后添加联通量，如果两个连同量通过此边连通，添加此条边到边集
 *
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Node> nodeList = new ArrayList<Node>();
        for (int i = 0; i < m; i++) {
            Node node = new Node();
            node.setStart(sc.nextInt());
            node.setEnd(sc.nextInt());
            node.setLength(sc.nextInt());
            nodeList.add(node);
        }
        Collections.sort(nodeList, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getLength() - o2.getLength();
            }
        });
        UnionFind uf = new UnionFind(n, nodeList);
        uf.calcResult();
        //输出最小的集线器个数
        //
        //输出集合大小
        List<Node> activeNodes = uf.getActiveNodes();
        int cableLength = 0;

        Iterator<Node> nit = activeNodes.iterator();
        int result = Integer.MIN_VALUE;

        while (nit.hasNext()){
            Node node = nit.next();
            result = result > node.getLength() ? result : node.getLength();
        }
        System.out.println(result);
        System.out.println(activeNodes.size());

        nit = activeNodes.iterator();

        while (nit.hasNext()){
            Node node = nit.next();
            System.out.println(node.getStart() + " " + node.getEnd());
        }
    }
}
