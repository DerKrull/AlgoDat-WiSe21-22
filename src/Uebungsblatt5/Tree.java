package Uebungsblatt5;

public class Tree {
    int freq;
    Tree left;
    Tree right;
    Tree parent;

    public Tree (int freq, Tree left, Tree right, Tree parent) {
        this.freq = freq;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public static int parent(int i) {
        return i / 2;
    }

    public static int left(int i) {
        return 2 * i + 1;
    }

    public static int right(int i) {
        return 2 * i + 2;
    }

    public int getFreq() {
        return freq;
    }

    @Override
    public String toString() {
        return "  (" + freq + " left:" + left + " right: " + right + ")";
    }
}

