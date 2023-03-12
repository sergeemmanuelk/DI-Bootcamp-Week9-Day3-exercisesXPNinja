public class Main {
    public static void main(String[] args) {
        int[] array = {2, 7, 5, 3, 0, 8, 1};
        int[] surpassers = getSurpasserCount(array);
        System.out.print("Surpasser counts: ");
        for (int count : surpassers) {
            System.out.print(count + " ");
        }
    }

    private static int[] merge(int[] left, int[] right, int[] count) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
                count[0] += left.length - i;
            }
        }
        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }
        return result;
    }

    private static int[] mergeSort(int[] array, int[] count) {
        int n = array.length;
        if (n <= 1) {
            return array;
        }
        int mid = n / 2;
        int[] left = new int[mid];
        int[] right = new int[n - mid];
        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, n - mid);
        mergeSort(left, count);
        mergeSort(right, count);
        return merge(left, right, count);
    }

    public static int[] getSurpasserCount(int[] array) {
        int n = array.length;
        int[] count = new int[1];
        mergeSort(array, count);
        int[] surpassers = new int[n];
        for (int i = 0; i < n; i++) {
            surpassers[i] = n - i - count[0];
        }
        return surpassers;
    }
}