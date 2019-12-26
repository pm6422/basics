package org.infinity.javabasics.sort;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BubbleSort {

    public static void main(String[] args) {
        int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
        int b[] = Arrays.copyOf(a, a.length);
        System.out.println("sort");
        sort(a);
        print(a);
        printBlankLine();
        System.out.println("optimizeSort");
        optimizeSort(b);
        print(b);
    }

    public static void sort(int[] a) {
        int complexity = 0;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                }
                complexity++;
            }
        }
        System.out.println("array length: " + a.length);
        System.out.println("complexity: " + complexity);
    }

    public static void optimizeSort(int[] a) {
        int complexity = 0;
        for (int i = 0; i < a.length - 1; i++) {
            boolean isSorted = true; // 有序标记，每一轮的初始是true
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    // 有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                }
                complexity++;
            }
            if (isSorted) { // 提前排好序，直接退出大循环
                break;
            }
        }
        System.out.println("array length: " + a.length);
        System.out.println("complexity: " + complexity);
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void print(int[] a) {
        String result = Arrays.stream(a).boxed().map(i -> "" + i).collect(Collectors.joining(", ", "[", "]"));
        System.out.println(result);
    }

    public static void printBlankLine() {
        System.out.println("◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘");
    }
}
