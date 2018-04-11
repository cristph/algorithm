package sort;

public class Sort<T extends Comparable<? super T>> {

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

    public void hybridSort(T[] array, int left, int right) {
        quickSort(array, left, right);
        insertSort(array, left, right);
    }
}
