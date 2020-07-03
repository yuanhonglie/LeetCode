package com.example.homlee.leetcode;

import java.util.Arrays;

public class SortTest {

    //冒泡排序
    public void bubbleSort(int[] array) {
        int size = array.length;
        for (int i = 0; i < size; i++) {
            boolean changed = false;
            for (int j = 0; j < size - i - 1; j++) {
                if (array[j] > array[j+1]) {
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    changed = true;
                }
            }
            if (!changed) {
                break;
            }
        }
    }

    //插入排序
    public void insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int temp = array[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (temp < array[j]) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = temp;
        }
    }

    //选择排序
    public void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }

            if (min != i) {
                int temp = array[min];
                array[min] = array[i];
                array[i] = temp;
            }
        }
    }

    //归并排序
    public void mergeSort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] array, int p, int r) {
        if (p >= r) {
            return;
        }

        int q = (p + r) / 2;
        mergeSort(array, p, q);
        mergeSort(array, q + 1, r);

        merge(array, p, q, r);
    }

    private void merge(int[] array, int p, int q, int r) {
        int[] tmp = new int[r - p + 1];
        int i = p;
        int j = q+1;
        int k = 0;
        while (i <= q && j <= r) {
            if (array[i] <= array[j]) {
                tmp[k++] = array[i++];
            } else {
                tmp[k++] = array[j++];
            }
        }


        int start = i;
        int end = q;
        if (j <= r) {
            start = j;
            end = r;
        }

        while (start <= end) {
            tmp[k++] = array[start++];
        }

        j = p;
        for (i = 0; i < tmp.length; i++) {
            array[j++] = tmp[i];
        }
    }
    //快速排序
    public void quickSort(int[] array) {

    }

    public static void println(int[] array) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                builder.append(",");
            }
            builder.append(array[i]);
        }
        System.out.println(builder);
    }

    public static int[] createNewArray() {
        int[] array = {9,2,1,3,6,7,23,8,3,5,6};
        return array;
    }

    public static void main(String[] args) {
        int[] array = createNewArray();
        println(array);
        SortTest sort = new SortTest();
        System.out.println("bubbleSort:");
        sort.bubbleSort(array);
        println(array);
        System.out.println("insertionSort:");
        array = createNewArray();
        sort.insertionSort(array);
        println(array);
        System.out.println("selectionSort:");
        array = createNewArray();
        sort.selectionSort(array);
        println(array);
        System.out.println("mergeSort:");
        array = createNewArray();
        sort.mergeSort(array);
        println(array);
    }
}
