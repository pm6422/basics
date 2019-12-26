package org.infinity.javabasics.sort;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BubbleSort {

    public static void main(String[] args) {
        int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
        BubbleSort sorter = new BubbleSort();
        sorter.sort(a);
        sorter.print(a);
    }

    public void sort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    this.swap(a, j, j + 1);
                }
            }
        }
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private void print(int[] a) {
        String result = Arrays.stream(a).boxed().map(i -> "" + i).collect(Collectors.joining(", "));
        System.out.println(result);
    }
}
