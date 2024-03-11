public final class QuickSort {
    public static void run(int[] array) {
        quicksort(array, 0, array.length - 1);
    }

    private static void quicksort(int[] array, int left, int right) {
        if(left < right){
            int boundary = left;
            for(int i = left + 1; i < right; i++){
                if(array[i] > array[left]){
                    swap(array, i, ++boundary);
                }
            }
            swap(array, left, boundary);
            quicksort(array, left, boundary);
            quicksort(array, boundary + 1, right);
        }
    }

    private static void swap(int[] array, int left, int right){
        int tmp = array[right];
        array[right] = array[left];
        array[left] = tmp;
    }
}
