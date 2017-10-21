package com.wuhulala.acm.bst.bst;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

/**
 * 功能说明: 二叉查找树<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2017/10/8<br>
 */
public class BST<K extends Comparable, V> {

    private Node<K, V> root;

    static class Node<K, V> {
        private K key;
        private V value;
        private Node left;
        private Node right;
        private int N;

        public Node(K key, V value, int n) {
            this.key = key;
            this.value = value;
            N = n;
        }
    }


    public int size() {
        return size(root);
    }

    private int size(Node<K, V> root) {
        if (root == null) return 0;
        else return root.N;
    }

    public V get(K k) {
        return get(root, k);
    }

    private V get(Node<K, V> node, K k) {
        if (node == null) return null;
        int cmp = k.compareTo(node.key);
        if (cmp == 0) return node.value;
        else if (cmp < 0) return (V) get(node.left, k);
        else return (V) get(node.right, k);
    }

    public void put(K k, V v) {
        if (root != null) put(root, k, v);
        else root = new Node(k, v, 1);
    }

    private void put(Node<K, V> node, K k, V v) {
        int cmp = k.compareTo(node.key);
        if (cmp < 0) {
            if (node.left != null) {
                put(node.left, k, v);
            } else {
                node.left = new Node(k, v, 1);
            }
        } else if (cmp > 0) {
            if (node.right != null) {
                put(node.right, k, v);
            } else {
                node.right = new Node(k, v, 1);
            }
        } else {
            node.value = v;
        }
        node.N = size(node.left) + size(node.right) + 1;
    }

    public K max() {
        if(root == null) return null;
        return (K) max(root).key;
    }

    private Node max(Node<K, V> node) {
        if (node.right != null) {
            return  max(node.right);
        }
        return node;
    }

    public K min() {
        if(root == null) return null;
        return (K) min(root).key;
    }

    private Node min(Node<K, V> node) {
        if (node.left != null) {
            return min(node.left);
        }
        return node;
    }

    private V floor(K k) {
        Node node = floor(root, k);
        if (node == null) return null;
        return (V) node.value;
    }

    private Node floor(Node<K, V> node, K k) {
        if (node == null) return null;
        int cmp = k.compareTo(node.key);
        if (cmp == 0) {
            return node;
        } else if (cmp > 0) {
            Node node1 = floor(node.right, k);
            if (node1 != null) return node1;
            return node;
        } else {
            return floor(node.left, k);
        }
    }

    private V ceiling(K k) {
        Node node = ceiling(root, k);
        if (node == null) return null;
        return (V) node.value;
    }

    private Node ceiling(Node<K, V> node, K k) {
        if (node == null) return null;
        int cmp = k.compareTo(node.key);
        if (cmp == 0) {
            return node;
        } else if (cmp < 0) {
            Node node1 = ceiling(node.left, k);
            if (node1 != null) return node1;
            return node;
        } else {
            return ceiling(node.right, k);
        }
    }

    /**
     * 查找第k大
     *
     * @param k
     * @return
     */
    private K select(int k) {
        Node node = select(root, k);
        return node == null ? null : (K) node.key;
    }

    private Node select(Node<K, V> node, int k) {
        if (node == null) return null;
        int t = size(node.left) + 1;
        if (t > k) return select(node.left, k);
        else if (t < k) return select(node.right, k - t - 1);
        else return node;
    }

    /**
     * 返回整个查找树中比k小的个数
     *
     * @param k
     * @return
     */
    public int rank(K k) {
        return rank(root, k);
    }

    private int rank(Node<K, V> node, K k) {
        if (node == null) return 0;
        int cmp = k.compareTo(node.key);
        if (cmp < 0) {
            return rank(node.left, k);
        } else if (cmp > 0) {
            return size(node.left) + 1 + rank(node.right, k);
        } else {
            return size(node.left);
        }
    }

    public void delete(K k) {
        root = delete(root, k);
    }

    private Node delete(Node<K, V> node, K k) {
        if (node == null) return null;
        int cmp = k.compareTo(node.key);
        if (cmp < 0) {
            delete(node.left, k);
        } else if (cmp > 0) {
            delete(node.right, k);
        } else {
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;
            //如果左右子树均不为空
            //选取右子树中最小的节点并在右子树中删除它
            Node tmp = node;
            node = (Node<K, V>) min(tmp.right);
            node.right = deleteMin(tmp.right);
            node.left = tmp.left;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void deleteMin() {
        deleteMin(root);
    }

    private Node deleteMin(Node<K, V> node) {
        if (node.left == null) return node.left;
        node.left = deleteMin(node.left);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }


    public void deleteMax() {
        deleteMax(root);
    }

    private Node deleteMax(Node<K, V> node) {
        if (node.right == null) return node.right;
        node.right = deleteMin(node.right);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public Iterable<K> keys() {
        return keys(min(), max());
    }

    public Iterable<K> keys(K min, K max) {
        Queue<K> result = new ArrayDeque<K>();
        keys(root, result, min, max);
        return result;
    }

    private void keys(Node<K, V> node, Queue<K> result, K min, K max) {
        if (node == null) return;

        int cmpMin = min.compareTo(node.key);
        int cmpMax = max.compareTo(node.key);

        if (cmpMin < 0) {
            keys(node.left, result, min, max);
        }
        if(cmpMin <=0 && cmpMax >=0){
            result.add(node.key);
        }
        if(cmpMax > 0){
            keys(node.right, result, min, max);
        }
    }

    private void add(Queue<K> result, Node<K, V> node) {
        if(node.left != null){

        }
    }

    public void printTree() {
        Queue<Node> queue = new ArrayDeque<Node>();
        printNode(root, queue);
    }

    private void printNode(Node x, Queue<Node> queue) {
        queue.add(x);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            System.out.print(node.value + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        bst.put(6, 6);
        bst.put(2, 2);
        bst.put(1, 1);
        bst.put(5, 5);
        bst.put(8, 8);
        bst.put(7, 7);
        bst.put(9, 9);

        Iterator<Integer> keys = bst.keys(2,8).iterator();
        while (keys.hasNext()){
            System.out.println(keys.next());
        }

        System.out.println("================= before delete ");
        bst.printTree();

        System.out.println("================= after delete ");
        bst.delete(6);
        bst.printTree();

        System.out.println(bst.max());
        System.out.println(bst.min());
        System.out.println(bst.ceiling(5));
        System.out.println(bst.floor(5));

        System.out.println("第2大：：" + bst.select(2));

        System.out.println("BST中比2小的个数：" + bst.rank(2));
        System.out.println("BST中比2小的个数：" + bst.rank(1));
        System.out.println("BST中比2小的个数：" + bst.rank(0));


        System.out.println("deleteMax");
        System.out.println(bst.max());
        bst.deleteMax();
        System.out.println(bst.max());

        System.out.println("deleteMin");
        System.out.println(bst.min());
        bst.deleteMin();
        System.out.println(bst.min());

    }
}
