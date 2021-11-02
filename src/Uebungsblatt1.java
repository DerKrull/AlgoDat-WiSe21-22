import java.util.Arrays;
import java.util.Random;

public class Uebungsblatt1 {

        public static void main(String[] args) {
                int[] sortArray = generateArray(1000,2000, true);
                int[] searchArray;
                int key = new Random().nextInt(50);
                //System.out.println(Arrays.toString(sortArray));
                //System.out.println("Key: " + key);

                System.out.println("");

                System.out.println("--- Insertion Sort ---");
                Analysis.clearData();
                Analysis.startTime();
                searchArray = insertionSort(sortArray);
                //System.out.println("Sortiert: " + Arrays.toString(searchArray));
                Analysis.stopTime();
                Analysis.printData();
                Analysis.clearData();

                System.out.println("");

                System.out.println("--- Merge Sort ---");
                Analysis.clearData();
                Analysis.startTime();
                searchArray = mergeSort(sortArray, 0, sortArray.length-1);
                //System.out.println("Sortiert: " + Arrays.toString(searchArray));
                Analysis.stopTime();
                Analysis.printData();
                Analysis.clearData();

                System.out.println("");

                System.out.println("--- Bubble Sort ---");
                Analysis.clearData();
                Analysis.startTime();
                searchArray = bubbleSort(sortArray);
                //System.out.println("Sortiert: " + Arrays.toString(searchArray));
                Analysis.stopTime();
                Analysis.printData();
                Analysis.clearData();

                System.out.println("");

                System.out.println("--- Linear Search ---");
                Analysis.clearData();
                Analysis.startTime();
                System.out.println("Gefunden an: " + linearSearch(searchArray, key));
                Analysis.stopTime();
                Analysis.printData();
                Analysis.clearData();

                System.out.println("");

                System.out.println("--- Binary Search ---");
                Analysis.clearData();
                Analysis.startTime();
                System.out.println("Gefunden an: " + binarySearch(searchArray,0,searchArray.length-1,key));
                Analysis.stopTime();
                Analysis.printData();
                Analysis.clearData();
        }

        public static int linearSearch (int[] arr, int key) {
                for(int i = 0; i < arr.length; i++) {
                        if(arr[i] == key) {
                                return i;
                        }
                        Analysis.addComparison();
                        Analysis.addArrayAccess();
                }
                return -1;
        }

        public static int binarySearch (int[] arr, int startSearch, int endSearch, int key) {
                if (startSearch == endSearch && arr[startSearch] == key) {
                        Analysis.addComparison();
                        Analysis.addArrayAccess();
                        return startSearch;
                } else if (startSearch == endSearch){
                        return -1;
                }


                int k = (startSearch + endSearch) / 2;

                if (arr[k] == key) {
                        Analysis.addArrayAccess();
                        Analysis.addComparison();
                        return k;
                } else if (arr[k] < key) {
                        Analysis.increaseArrayAccess(2);
                        Analysis.increaseComparisons(2);
                        return binarySearch(arr, k + 1, endSearch, key);
                } else {
                        Analysis.increaseArrayAccess(2);
                        Analysis.increaseComparisons(2);
                        return binarySearch(arr, startSearch, k-1, key);
                }
        }

        public static int[] insertionSort (int[] arr) {
                for (int j = 1; j < arr.length; j++) {
                        int key = arr[j];
                        Analysis.addArrayAccess();
                        int i = j - 1;
                        while (i >= 0 && arr[i] > key) {
                                Analysis.addComparison();
                                arr[i+1] = arr[i];
                                Analysis.addArrayAccess();
                                i = i - 1;
                        }
                        Analysis.addComparison();

                        arr[i+1] = key;
                        Analysis.addArrayAccess();
                }
                return arr;
        }
        public static int[] mergeSort(int[] arr, int startSort, int endSort) {
                if (startSort < endSort) {
                        Analysis.addComparison();
                        int q = (startSort + endSort) / 2;
                        mergeSort(arr, startSort, q);
                        mergeSort(arr, q+1, endSort);
                        merge(arr,startSort,q,endSort);
                }
                return arr;
        }

        public static int[] merge(int[] arr, int startFirstArray, int endFirstArray, int endSecondArray) {
                int lengthFirstArray = endFirstArray - startFirstArray + 1;
                int lengthSecondArray = endSecondArray - endFirstArray;
                int[] FirstArray = new int[lengthFirstArray+1];
                Analysis.addArrayAccess();
                int[] SecondArray = new int[lengthSecondArray+1];
                Analysis.addArrayAccess();
                int i = 0;
                int j = 0;
                for (i = 0; i < lengthFirstArray; i++) {
                        Analysis.addArrayAccess();
                        FirstArray[i] = arr[startFirstArray+i];
                }
                for (j = 0; j < lengthSecondArray; j++) {
                        Analysis.addArrayAccess();
                        SecondArray[j] = arr[endFirstArray+1+j];
                }
                FirstArray[lengthFirstArray] = Integer.MAX_VALUE;
                Analysis.addArrayAccess();
                SecondArray[lengthSecondArray] = Integer.MAX_VALUE;
                Analysis.addArrayAccess();
                i = 0;
                j = 0;
                for(int k = startFirstArray; k <= endSecondArray; k++) {
                        Analysis.addArrayAccess();
                        if (FirstArray[i] <= SecondArray[j]) {
                                Analysis.addComparison();
                                arr[k] = FirstArray[i];
                                Analysis.addArrayAccess();
                                i++;
                        } else {
                                Analysis.addComparison();
                                arr[k] = SecondArray[j];
                                Analysis.addArrayAccess();
                                j++;
                        }
                }
                return arr;
        }

        public static int[] bubbleSort(int[] arr) {
                for (int i = 0; i < arr.length; i++) {
                        Analysis.addArrayAccess();
                        for (int j = arr.length-1; j > i; j--) {
                                Analysis.addArrayAccess();
                                if (arr[j] < arr[j-1]) {
                                        int temp = arr[j];
                                        arr[j] = arr[j-1];
                                        arr[j-1] = temp;
                                }
                                Analysis.addComparison();
                        }
                }
                return arr;
        }

        public static int[] generateArray (int count, int bound, boolean distinct) {
                int[] arr = new int[count];
                for (int i = 0; i < arr.length; i++) {
                        arr[i] = (int)(Math.random()*bound);//note, this generates numbers from [0,9]

                        if(distinct) {
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
