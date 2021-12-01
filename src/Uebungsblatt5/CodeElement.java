package Uebungsblatt5;

public class CodeElement {
    String letter;
    int freq;

    public CodeElement (String letter, int freq) {
        this.freq = freq;
        this.letter = letter;
    }

    @Override
    public String toString() {
        return "(" + this.letter + " : " + this.freq + ")";
    }
}
