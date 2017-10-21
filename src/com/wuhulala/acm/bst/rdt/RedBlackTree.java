package com.wuhulala.acm.bst.rdt;

/**
 * @author wuhulala
 * @version 1.0
 * @date 2017/10/21
 * @description 红黑树
 */
public class RedBlackTree<K extends Comparable<K>, V> {
    private Node<K, V> root;

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    static class Node<K, V> {
        K key;
        V value;
        Node left;
        Node right;
        boolean color;
        int N;

        Node(K key, V value, int n, boolean color) {
            this.key = key;
            this.value = value;
            this.N = n;
            this.color = color;
        }
    }


    public int size() {
        return size(root);
    }

    /**
     * 计算当前节点对应的子树大小
     * @param node 节点
     * @return 子树节点个数
     */
    private int size(Node<K, V> node) {
        if (node == null) return 0;
        else return node.N;
    }

    /**
     * 是否为红节点
     *
     * @param node 目标节点
     * @return 结果
     */
    private boolean isRed(Node<K, V> node){
        return node != null && node.color == RED;
    }


    /**
     * 左旋转
     *
     * @param target 当前位置目前的节点
     * @return 旋转后的当前位置对应的节点
     */
    private Node rotateLeft(Node<K, V>  target){
        Node<K, V>  result = target.right;
        // 旋转节点
        target.right = result.left;
        result.left = target;
        // 改变颜色
        result.color = target.color;
        target.color = RED;
        // 更新子树大小
        result.N = target.N;
        target.N = size(target.left) + size(target.right) + 1;
        return result;
    }

    /**
     * 右旋转
     *
     * @param target 当前位置目前的节点
     * @return 旋转后的当前位置对应的节点
     */
    private Node rotateRight(Node<K, V>  target){
        Node<K, V>  result = target.left;
        // 旋转节点
        target.left = result.right;
        result.right = target;

        // 改变颜色
        result.color = target.color;
        target.color = RED;

        //更新子树大小
        result.N = target.N;
        target.N = size(target.left) + size(target.right) + 1;

        return result;
    }

    /**
     * 转换颜色
     * @param target 当前节点
     * @description 把当前节点的颜色设置为红色，所有子节点颜色设为黑色
     */
    public void flipColor(Node target){
        target.color = RED;
        target.left.color = BLACK;
        target.right.color = BLACK;
    }

    /**
     * 插入新键值
     * @param k key
     * @param v value
     */
    public void put(K k, V v){
        root = put(root, k, v);
        // 根节点的颜色永远是黑色的，防止在两个子节点都为红色的时候通过转换颜色把根节点的颜色转换为红色
        root.color = BLACK;
    }

    /**
     * 把新键插入当前节点的子树
     */
    private Node<K, V> put(Node<K, V>parent, K k, V v) {
        if(parent == null){
            return new Node<K, V>(k, v, 1, RED);
        }
        int cmp = k.compareTo(parent.key);
        if(cmp < 0){
            parent.left = put(parent.left, k, v);
        }else if(cmp > 0){
            parent.right = put(parent.right, k, v);
        }else{
            parent.value = v;
        }
        //更新完之后判断是否需要变换

        // 如果右子节点是红色而左子节点是黑色的，进行左旋转
        if(isRed(parent.right) && !isRed(parent.left)) {
            parent = rotateLeft(parent);
        }

        // 如果左子节点以及它的左子节点都是红色，进行右旋转
        if(isRed(parent.left) && isRed(parent.left.left)){
            parent = rotateRight(parent);
        }

        // 如果左子节点和右子节点都是红色，进行颜色转换
        if(isRed(parent.left) && isRed(parent.right)){
            flipColor(parent);
        }

        // 更新当前节点的子树大小
        parent.N = size(parent.left) + size(parent.right) + 1;

        return parent;
    }


    /**
     * 查询和二叉树一样，因为本质都是二叉树。
     * @param k
     * @return
     */
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
}
