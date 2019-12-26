package org.infinity.javabasics.sort;

public class InsertBubble {
    public static void main(String[] args) {
        sort(BubbleSort.a);
        BubbleSort.print(BubbleSort.a);
    }

    public static void sort(int[] a) {
        int complexity = 0;
        int temp = 0;
        for (int i = 1; i < a.length; i++) {
            int j = i - 1;
            temp = a[i];
            for (; j >= 0 && temp < a[j]; j--) {
                a[j + 1] = a[j];                       //将大于temp的值整体后移一个单位
                complexity++;
            }
            a[j + 1] = temp;
        }
        System.out.println("array length: " + a.length);
        System.out.println("complexity: " + complexity);
    }
}
