import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Testat1 {

    static int[] testArray1 = {10,1000,10000000}; // Länge der Arrays
    static int[] testArray2 = {100, 1000000, Integer.MAX_VALUE}; // Wertebereich der Test Arrays
    static int[] testArray3 = {0, 10, 50}; // Identische Elemente in %
    static int[] testArray4 = {2, 5, 10}; // Vorsortierung an welchen Stellen die randoms eingefügt wird

    public static void main(String[] args) {
        /*
        TODO write output to file
        */
        createFile();

        for(int i = 1; i < 5; i++) {
            for(int j = 1; j < 5; j++) {
                if(i != j) {
                for(int x = 0; x < 3; x++) {
                    for(int y = 0; y < 3; y++) {
                            runAlgorithms(generateTest("testarray" + i + x, "testarray" + j + y));
                        }
                    }
                }
            }
        }



        writeToFile("Test", "");

    }

    public static int[] generateTest(String test1, String test2){

        //Default Werte
        int laenge = 1000;
        int wertebereich = 1000;
        int identical = 0;
        int vorsortiert = 0;

        if(test1.charAt(9) == 1){
            //Länge
            if(test2.charAt(9)==1){
                //Länge  -> Leer
                return null;
            }else if(test2.charAt(9) == 2){
                //Wertebereich
                laenge = testArray1[test1.charAt(10)];
                wertebereich = testArray2[test2.charAt(10)];

                int[] res = generateRandomArray(laenge, wertebereich);
                return res;
            }else if(test2.charAt(9) == 3){
                //identische Elemente
                laenge = testArray1[test1.charAt(10)];
                identical = testArray3[test2.charAt(10)];

                int[] res = generateIdenticalArray(laenge, identical,wertebereich);
                return res;
            }else if(test2.charAt(9) == 4){
                //Vorsortierung
                laenge = testArray1[test1.charAt(10)];
                vorsortiert = testArray4[test2.charAt(10)];

                int[] res = generatePartialSortedArray(laenge, 0, wertebereich, vorsortiert);
                return res;
            }

        }else if(test1.charAt(9) == 2){
            //Wertebereich
            if(test2.charAt(9)==1){
                //Länge
                wertebereich = testArray2[test1.charAt(10)];
                laenge = testArray1[test2.charAt(10)];

                int [] res = generateRandomArray(laenge, wertebereich);
                return res;
            }else if(test2.charAt(9) == 2){
                //Wertebereich -> Leer
                return null;
            }else if(test2.charAt(9) == 3){
                //identische Elemente
                wertebereich = testArray2[test1.charAt(10)];
                identical = testArray3[test2.charAt(10)];

                int[] res = generateIdenticalArray(laenge, identical,wertebereich);
                return res;
            }else if(test2.charAt(9) == 4){
                //Vorsortierung
                wertebereich = testArray2[test1.charAt(10)];
                vorsortiert = testArray4[test2.charAt(10)];

                int[] res = generatePartialSortedArray(laenge, 0, wertebereich, vorsortiert);
                return res;
            }
        }else if(test1.charAt(9) == 3){
            //identische Elemente
            if(test2.charAt(9)==1){
                //Länge
                identical = testArray3[test1.charAt(10)];
                laenge = testArray1[test2.charAt(10)];

                int[] res = generateIdenticalArray(laenge, identical,wertebereich);
                return res;
            }else if(test2.charAt(9) == 2){
                //Wertebereich
                identical = testArray3[test1.charAt(10)];
                wertebereich = testArray2[test2.charAt(10)];

                int[] res = generateIdenticalArray(laenge, identical,wertebereich);
                return res;
            }else if(test2.charAt(9) == 3){
                //identische Elemente -> Leer
                return null;
            }else if(test2.charAt(9) == 4){
                //Vorsortierung
                identical = testArray3[test1.charAt(10)];
                vorsortiert = testArray4[test2.charAt(10)];

                int[] res = combinedTest(laenge, identical, wertebereich, vorsortiert);
                return res;
            }
        }else if(test1.charAt(9) == 4){
            //Vorsortierung
            if(test2.charAt(9)==1){
                //Länge
                vorsortiert = testArray4[test1.charAt(10)];
                laenge = testArray1[test2.charAt(10)];

                int[] res = generatePartialSortedArray(laenge, 0, wertebereich, vorsortiert);
                return res;
            }else if(test2.charAt(9) == 2){
                //Wertebereich
                vorsortiert = testArray4[test1.charAt(10)];
                wertebereich = testArray2[test2.charAt(10)];

                int[] res = generatePartialSortedArray(laenge, 0, wertebereich, vorsortiert);
                return res;
            }else if(test2.charAt(9) == 3){
                //identische Elemente
                vorsortiert = testArray4[test1.charAt(10)];
                identical = testArray3[test2.charAt(10)];

                int[] res = combinedTest(laenge, identical, wertebereich, vorsortiert);
                return res;
            }else if(test2.charAt(9) == 4){
                //Vorsortierung -> Leer
                return null;
            }
        }
        return null;
    }

    public static void createFile() {
        try {
            File myObj = new File("E:\\OneDrive - Sammlung\\OneDrive - informatik.hs-fulda.de\\3. Semester\\AI1012 - AlgoDat\\Übungen\\Testat 1\\testat1.csv");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeToFile(String testcase, String data) {
        try {
            FileWriter myWriter = new FileWriter("E:\\OneDrive - Sammlung\\OneDrive - informatik.hs-fulda.de\\3. Semester\\AI1012 - AlgoDat\\Übungen\\Testat 1\\testat1.csv", true);
            myWriter.write(testcase);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static int[] combinedTest(int amount, int percentageIdentical, int maxRandomNumber, int randomLevel) {
        int[] arr1 = generateIdenticalArray(amount/2, percentageIdentical, maxRandomNumber);
        int[] arr2 = generatePartialSortedArray(amount/2, 0, maxRandomNumber, randomLevel);
        int[] res = new int[amount];
        int j = 0;
        int k = 0;
        for(int i = 0; i < res.length; i++) {
            if (i % 2 == 0) {
                res[i] = arr1[j];
                j++;
            } else {
                res[i] = arr2[k];
                k++;
            }
        }
        return res;
    }

    public static int[] generateIdenticalArray(int amount, int percentageIdentical, int maxRandomNumber) {
        if(percentageIdentical > 100 || percentageIdentical < 0) {
            System.out.println("Percentage Identical has to be between 0 and 100");
            System.exit(42);
        }

        int inputIdentical = 100/percentageIdentical;
        int identicalAmount = amount / inputIdentical;
        int restAmount = amount - identicalAmount;

        int[] percentageIdenticalArray = new int[identicalAmount];
        int randomNumber = (int) Math.floor(Math.random() * (maxRandomNumber));
        for(int i = 0; i < percentageIdenticalArray.length; i++) {
            percentageIdenticalArray[i] = randomNumber;
        }

        int[] restArray = generateRandomArray(restAmount, maxRandomNumber);
        int[] result = new int[amount];
        int j = 0;
        int k = 0;
        for(int i = 0; i < result.length; i++) {
            if(i % inputIdentical == inputIdentical - 1) {
                result[i] = percentageIdenticalArray[j];
                j++;
            } else {
                result[i] = restArray[k];
                k++;
            }
        }
        return result;
    }

    public static int[] generatePartialSortedArray(int amount, int startSorted, int maxRandomNumber, int randomLevel) {
        int[] arrSorted = generateSortedArray(amount, startSorted);
        int[] arrRandom = generateRandomArray(amount, maxRandomNumber);
        int[] arrMixed = new int[amount];
        int j = 0;
        int k = 0;
        for(int i = 0; i < amount; i++) {
            if(i % randomLevel == randomLevel - 1) {
                arrMixed[i] = arrRandom[j];
                j++;
            } else {
                arrMixed[i] = arrSorted[k];
                k++;
            }
        }

        return arrMixed;
    }

    public static int [] generateSortedArray(int amount, int startSorted) {
        int[] arr = new int[amount];
        int i = 0;
        for(int j = startSorted; j < amount + startSorted; j++) {
            arr[i] = j;
            i++;
        }
        return arr;
    }

    public static int[] generateRandomArray(int amount, int maxRandomNumber) {
        int[] arr = new int[amount];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*(maxRandomNumber));//note, this generates numbers from [0,9]
        }
        return arr;
    }

    public static double[] runAlgorithms(int[] arr) {
        double timeMerge = System.nanoTime();
        mergeSort(arr,0, arr.length - 1);
        timeMerge = System.nanoTime() - timeMerge / 1000;

        double timeQuick = System.nanoTime();
        randomizedQuicksort(arr,0, arr.length - 1);
        timeQuick = System.nanoTime() - timeQuick / 1000;

        int[] secondArray = new int[arr.length];
        double timeCount = System.nanoTime();
        countingSort(arr, secondArray, bound); //TODO
        timeCount = System.nanoTime() - timeCount / 1000;

        double timeHeap = System.nanoTime();
        heapSort(arr);
        timeHeap = System.nanoTime() - timeHeap / 1000;

        double[] timeAll = {timeMerge, timeQuick, timeCount, timeHeap};
        return timeAll;
    }

    public static int[] mergeSort(int[] arr, int startSort, int endSort) {
        if (startSort < endSort) {
            int q = (startSort + endSort) / 2;
            mergeSort(arr, startSort, q);
            mergeSort(arr, q + 1, endSort);
            merge(arr, startSort, q, endSort);
        }
        return arr;
    }

    public static void merge(int[] arr, int startFirstArray, int endFirstArray, int endSecondArray) {
        int lengthFirstArray = endFirstArray - startFirstArray + 1;
        int lengthSecondArray = endSecondArray - endFirstArray;
        int[] FirstArray = new int[lengthFirstArray + 1];
        int[] SecondArray = new int[lengthSecondArray + 1];
        int i;
        int j;
        for (i = 0; i < lengthFirstArray; i++) {
            FirstArray[i] = arr[startFirstArray + i];
        }
        for (j = 0; j < lengthSecondArray; j++) {
            SecondArray[j] = arr[endFirstArray + 1 + j];
        }
        FirstArray[lengthFirstArray] = Integer.MAX_VALUE;
        SecondArray[lengthSecondArray] = Integer.MAX_VALUE;
        i = 0;
        j = 0;
        for (int k = startFirstArray; k <= endSecondArray; k++) {
            if (FirstArray[i] <= SecondArray[j]) {
                arr[k] = FirstArray[i];
                i++;
            } else {
                arr[k] = SecondArray[j];
                j++;
            }
        }
    }

    public static void randomizedQuicksort (int[] A, int p, int r) {
        if (p < r) {
            int q = randomizedPartition(A,p,r);
            randomizedQuicksort(A, p, q-1);
            randomizedQuicksort(A,q+1,r);
        }
    }

    public static int randomizedPartition(int[] A, int p, int r) {
        Random randomNum = new Random();
        int i = randomNum.nextInt(r);
        int temp = A[i];
        A[i] = A[r];
        A[r] = temp;
        return partition(A,p,r);
    }

    public static int partition(int[] A, int p, int r) {
        int x = A[r];
        int i = p - 1;
        int temp;
        for (int j = p; j < r-1; j++) {
            if (A[j] <= x ) {
                i = i + 1;
                temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        temp = A[i+1];
        A[i+1] = A[r];
        A[r] = temp;
        return i + 1;
    }

    public static void countingSort (int[] A, int[] B, int k) {
        int[] C = new int[k];

        // Count different Numbers in A
        for (int value : A) {  //for(int j = 0; j < A.length; j++) {
            C[value] = C[value] + 1;
        }

        // Convert sums to indices, by adding them up
        for(int i = 1; i < k; i++) {
            C[i] = C[i] + C[i-1];
        }

        // Convert indices down to Arrays starting with 0
        for(int i = 0; i < C.length; i++) {
            C[i]--;
        }

        // Write A[j] to the corresponding index in B
        for(int j = A.length - 1; j >= 0; j--) {
            B[C[A[j]]] = A[j];
            C[A[j]] = C[A[j]] - 1;
        }
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
        int l = 2 * i + 1;
        int r = 2 * i + 2;
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
}
