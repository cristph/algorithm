package search;

public interface Search<T extends Comparable<? super T>> {

    public T getKthMin(T[] array, int left, int right, int k);
}
