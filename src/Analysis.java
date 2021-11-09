public class Analysis {
    static long comparisons = 0;
    static long arrayAccess = 0;
    static double time = 0;

    public static void addComparison() {
        comparisons++;
    }

    public static void addArrayAccess() {
        arrayAccess++;
    }

    public static void increaseComparisons(int count) {
        comparisons = comparisons + count;
    }

    public static void increaseArrayAccess(int count) {
        arrayAccess = arrayAccess + count;
    }

    public static void startTime () {
        time = System.nanoTime();
    }

    public static void stopTime() {
        time = System.nanoTime() - time;
    }

    public static void printData() {
        System.out.println("Laufzeit: " + time/1000 + " ms");
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Array accesses: " + arrayAccess);
    }

    public static void clearData() {
        comparisons = 0;
        arrayAccess = 0;
        time = 0;
    }
}
