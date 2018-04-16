package sort;

import java.util.Arrays;
import java.util.Random;

public class Main {

    static Sort<Integer> sort = new Sort<>();

    private static <T extends Comparable<? super T>> boolean compare(T[] array1, T[] array2) {
        if (array1.length != array2.length) return false;
        for (int i = 0; i < array1.length; i++) {
            if (array1[i].compareTo(array2[i]) != 0) return false;
        }
        return true;
    }

    private static <T> void print(T[] array) {
        System.out.println(Arrays.toString(array));
    }

    private static void testHeapSort() {
        int count = 10000;
        Integer[] array = new Integer[count];
        Random rd = new Random();
        for (int i = 0; i < count; i++) {
            array[i] = rd.nextInt(count);
        }
        Integer[] array_copy = Arrays.copyOf(array, count);
        sort.heapSort(array);
        print(array);
        Arrays.sort(array_copy);
        print(array_copy);
        if (compare(array, array_copy)) {
            System.out.println("HeapSort Success");
        } else {
            System.out.println("HeapSort Fail");
        }
    }

    private static void testInsertSort() {
        int count = 10000;
        Integer[] array = new Integer[count];
        Random rd = new Random();
        for (int i = 0; i < count; i++) {
            array[i] = rd.nextInt(count);
        }
        Integer[] array_copy = Arrays.copyOf(array, count);
        sort.insertSort(array, 0, array.length - 1);
        print(array);
        Arrays.sort(array_copy);
        print(array_copy);
        if (compare(array, array_copy)) {
            System.out.println("InsertSort Success");
        } else {
            System.out.println("InsertSort Fail");
        }
    }

    private static void testQuickSort() {
        int count = 10000;
        Integer[] array = new Integer[count];
        Random rd = new Random();
        for (int i = 0; i < count; i++) {
            array[i] = rd.nextInt(count);
        }
        Integer[] array_copy = Arrays.copyOf(array, count);
        sort.improvedQuickSort(array, 0, array.length - 1);
        print(array);
        Arrays.sort(array_copy);
        print(array_copy);
        if (compare(array, array_copy)) {
            System.out.println("QuickSort Success");
        } else {
            System.out.println("QuickSort Fail");
        }
    }

    public static void testMergeSort() {
        int count = 10000;
        Integer[] array = new Integer[count];
        Random rd = new Random();
        for (int i = 0; i < count; i++) {
            array[i] = rd.nextInt(count);
        }
        Integer[] array_copy = Arrays.copyOf(array, count);
        sort.improvedMergeSort(array, 0, array.length - 1);
        print(array);
        Arrays.sort(array_copy);
        print(array_copy);
        if (compare(array, array_copy)) {
            System.out.println("MergeSort Success");
        } else {
            System.out.println("MergeSort Fail");
        }
    }

    public static void testGetKthMin() {
        int count = 100;
        Integer[] array = new Integer[count];
        Random rd = new Random();
        for (int i = 0; i < count; i++) {
            array[i] = rd.nextInt(count * 2);
        }
        Integer[] array_copy = Arrays.copyOf(array, count);
        int k = rd.nextInt(count);
        int kthMax1 = sort.getKthMin(array, 0, array.length - 1, k);
        Arrays.sort(array_copy);
        int kthMax2 = array_copy[k - 1];
        if (kthMax1 == kthMax2) {
            System.out.println("getKthMin Success");
        } else {
            System.out.println("getKthMin Fail");
        }
    }

    public static void main(String[] args) {
        testHeapSort();
        testInsertSort();
        testQuickSort();
        testMergeSort();
        for (int i = 0; i < 100; i++) {
            testGetKthMin();
        }
    }
}
