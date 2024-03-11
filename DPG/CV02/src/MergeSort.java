public final class MergeSort {
    public static void run(int[] array) {
        int[] aux =  new int[array.length];
        mergeSort(array, aux, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int[] aux, int left, int right) {
        if(left == right) return;
        int middleIndex = (left + right)/2;
        mergeSort(array, aux, left, middleIndex);
        mergeSort(array, aux, middleIndex + 1, right);
        merge(array, aux, left, right);
        if (right + 1 - left >= 0) System.arraycopy(aux, left, array, left, right + 1 - left);
    }

    private static void merge(int[] array, int[] aux, int left, int right) {
        int middleIndex = (left + right)/2;
        int leftIndex = left;
        int rightIndex = middleIndex + 1;
        int auxIndex = left;
        while(leftIndex <= middleIndex && rightIndex <= right) {
            if (array[leftIndex] >= array[rightIndex]) {
                aux[auxIndex] = array[leftIndex++];
            } else {
                aux[auxIndex] = array[rightIndex++];
            }
            auxIndex++;
        }
        while (leftIndex <= middleIndex) {
            aux[auxIndex] = array[leftIndex++];
            auxIndex++;
        }
        while (rightIndex <= right) {
            aux[auxIndex] = array[rightIndex++];
            auxIndex++;
        }
    }
}
