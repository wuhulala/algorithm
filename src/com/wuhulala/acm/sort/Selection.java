package com.wuhulala.acm.sort;

/**
 * 选择排序
 *
 * @author wuhulala<br>
 * @date 2019/3/7<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class Selection<T extends Comparable<T>> extends AbstractSorted<T>{

    public static void main(String[] args) {
        Integer[] a = {5, 4, 3, 2, 1};
        new Selection<Integer>().sort(a);
    }

    /**
     * 选择排序，每次都选择最小的放在前面
     *
     * @param a
     */
    public void sort(T[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if(less(a[j], a[min])){
                    min = j;
                }
            }
            exch(a, i , min);
            show(a);
            if(isSorted(a)){
                return;
            }
        }
    }

}
