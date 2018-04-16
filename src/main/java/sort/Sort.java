package sort;

import search.Search;

import java.util.Arrays;

public class Sort<T extends Comparable<? super T>> implements Search<T> {

    /**
     * 堆排序
     */
    private void siftDown(T[] heap, int start, int m) {
        int i = start, j = 2 * i + 1;
        T t = heap[i];
        while (j <= m) {
            if (j < m && heap[j].compareTo(heap[j + 1]) < 0) j++;
            if (t.compareTo(heap[j]) >= 0) break;
            heap[i] = heap[j];
            i = j;
            j = 2 * i + 1;
        }
        heap[i] = t;
    }

    private void swap(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public void heapSort(T[] array) {
        int len = array.length;
        for (int i = (len - 2) / 2; i >= 0; i--) {
            siftDown(array, i, len - 1);
        }
        for (int j = 0; j < len; j++) {
            swap(array, 0, len - 1 - j);
            siftDown(array, 0, len - 2 - j);
        }
    }


    /**
     * 插入排序
     */
    public void insertSort(T[] array, int left, int right) {
        if (left == right) return;
        for (int i = left + 1; i <= right; i++) {
            T tmp = array[i];
            int j = i;
            for (; j > left; j--) {
                if (tmp.compareTo(array[j - 1]) < 0) {
                    array[j] = array[j - 1];
                } else {
                    break;
                }
            }
            array[j] = tmp;
        }
    }


    /**
     * 改进后的快排
     */

    /**
     * 此函数在表前端、尾端和中间点三者取中值，交换到表尾
     *
     * @param array
     * @param left
     * @param right
     * @return
     */
    private T median3(T[] array, int left, int right) {
        int mid = (left + right) / 2, k = left;
        if (array[mid].compareTo(array[k]) < 0) k = mid;
        if (array[right].compareTo(array[k]) < 0) k = right;
        if (k != left) swap(array, k, left);
        if (mid != right && array[mid].compareTo(array[right]) < 0) swap(array, mid, right);
        return array[right];
    }

    private int partition(T[] array, int left, int right) {
        if (left >= right) return left;
        T pivot = median3(array, left, right);
        int i = left, j = right - 1;
        while (true) {
            while (i < j && array[i].compareTo(pivot) < 0) i++;
            while (i < j && array[j].compareTo(pivot) > 0) j--;
            if (i < j) {
                swap(array, i, j);
                i++;
                j--;
            } else {
                break;
            }
        }
        if (array[i].compareTo(pivot) > 0) {
            array[right] = array[i];
            array[i] = pivot;
        }
        return i;
    }

    private final int M = 15;

    public void quickSort(T[] array, int left, int right) {
        if (right - left <= M) return;
        int pivotPos = partition(array, left, right);
        if (left < pivotPos - 1) quickSort(array, left, pivotPos - 1);
        if (pivotPos + 1 < right) quickSort(array, pivotPos + 1, right);
    }

    public void improvedQuickSort(T[] array, int left, int right) {
        quickSort(array, left, right);
        insertSort(array, left, right);
    }


    /**
     * 归并排序
     */
    private void merge(T[] array, int left, int mid, int right) {
        T[] copy = Arrays.copyOfRange(array, left, right + 1);
        int i = 0, j = mid - left + 1, p = left;
        while (i <= mid - left && j <= right - left) {
            if (copy[i].compareTo(copy[j]) <= 0) array[p++] = copy[i++];
            else array[p++] = copy[j++];
        }
        while (i <= mid - left) array[p++] = copy[i++];
        while (j <= right - left) array[p++] = copy[j++];
    }

    public void mergeSort(T[] array, int left, int right) {
        if (left >= right) return;
        if (right - left <= M) return;
        int mid = (left + right) / 2;
        if (mid - left > 1) mergeSort(array, left, mid);
        if (right - mid > 2) mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    public void improvedMergeSort(T[] array, int left, int right) {
        mergeSort(array, left, right);
        insertSort(array, left, right);
    }


    /**
     * 求第k小的数
     */
    @Override
    public T getKthMin(T[] array, int left, int right, int k) {
        if (left == right) return array[left];
        int pivotPos = partition(array, left, right);
        if (pivotPos - left >= k) {
            return getKthMin(array, left, pivotPos - 1, k);
        } else if (pivotPos - left + 1 == k) {
            return array[pivotPos];
        } else {
            return getKthMin(array, pivotPos + 1, right, k - pivotPos + left - 1);
        }
    }



}
