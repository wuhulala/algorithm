package com.wuhulala.acm.sort;

/**
 * 排序模板
 *
 * @author wuhulala<br>
 * @date 2019/3/7<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public abstract class AbstractSorted<T extends Comparable<T>> {

    /**
     * 选择排序，每次都选择最小的放在前面
     *
     * @param a
     */
    public abstract void sort(T[] a);

    protected boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    protected void exch(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    protected boolean isSorted(T[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    protected void show(T[] a) {
        for (T t : a) {
            System.out.print(t + " ");
        }
        System.out.println("");
    }
}
