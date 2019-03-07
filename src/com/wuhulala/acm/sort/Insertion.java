package com.wuhulala.acm.sort;

/**
 * 插入排序
 *
 * <p>依次向前插入，直到前面没有自己小的了</p>
 * @author wuhulala<br>
 * @date 2019/3/7<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class Insertion<T extends Comparable<T>> extends AbstractSorted<T> {
    public static void main(String[] args) {
        Integer[] a = {5, 4, 3, 2, 1};
        new Insertion<Integer>().sort(a);
    }

    @Override
    public void sort(T[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
                show(a);
            }
        }
    }
}
