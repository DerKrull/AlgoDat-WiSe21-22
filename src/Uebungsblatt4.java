public class Uebungsblatt4 {
    public static void main(String[] args) {
        int count = 100;
        int bound = 5000;
        int[] sortArray = generateArray(count,bound, 0,false);
        int[] sortedArray = new int[sortArray.length];
        System.out.println();

        System.out.println("--- Heap Sort ---");
        Analysis.clearData();
        Analysis.startTime();
        heapSort(sortArray);
        //System.out.println("Sortiert: " + Arrays.toString(sortArray));
        Analysis.stopTime();
        Analysis.printData();
        Analysis.clearData();
    }

    public static void heapSort(int[] arr) {
        buildMaxHeap(arr);
        int heapsize = arr.length;
        for (int i = arr.length - 1; i >= 1; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapsize = heapsize - 1;
            maxHeapify(arr, 0, heapsize);
        }
    }

    public static void buildMaxHeap(int[] arr) {
        int heapsize = arr.length;
        for (int i = (arr.length / 2 - 1); i >= 0; i--) {
            maxHeapify(arr, i, heapsize);
        }
    }

    public static void maxHeapify(int[] arr, int i, int heapsize) {
        int l = left(i);
        int r = right(i);
        int largest = i;
        if (l < heapsize && arr[l] > arr[i]) {
            largest = l;
        }
        if (r < heapsize && arr[r] > arr[largest]) {
            largest = r;
        }
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            maxHeapify(arr, largest, heapsize);
        }
    }

    public static int left(int i) {
        return 2 * i + 1;
    }

    public static int right(int i) {
        return 2 * i + 2;
    }


    public static int[] generateArray(int count, int bound, int bottomBarrier, boolean distinct) {
        int[] arr = new int[count];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (bound - bottomBarrier) + bottomBarrier);//note, this generates numbers from [0,9]

            if (distinct) {
                for (int j = 0; j < i; j++) {
                    if (arr[i] == arr[j]) {
                        i--; //if a[i] is a duplicate of a[j], then run the outer loop on i again
                        break;
                    }
                }
            }
        }
        return arr;
    }
}
