package com.wuhulala.acm.sort;

/**
 * 希尔排序
 *
 * @author wuhulala<br>
 * @date 2019/3/7<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class Shell<T extends Comparable<T>> extends AbstractSorted<T> {

    public static void main(String[] args) {
        Integer[] a = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        new Shell<Integer>().sort(a);
    }

    @Override
    public void sort(T[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;
        }
        show(a);

        while (h >= 1) {
            for (int i = 0; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    System.out.println(">>>>>>>>>>>>a[" + j + "] = " + a[j] + ", a[" + (j-h) + "] = " + a[j - h] + ", j = " + j + ", h = " + h);
                    exch(a, j, j - h);
                    show(a);
                }
            }
            h = h / 3;
        }
    }
}
