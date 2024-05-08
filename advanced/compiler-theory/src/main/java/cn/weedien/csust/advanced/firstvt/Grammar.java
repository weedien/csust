package cn.weedien.csust.advanced.firstvt;

public class Grammar {
    public String left;// 产生式左部
    public String right;// 产生式右部

    public Grammar() {

    }

    public Grammar(String left, String right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return left + " → " +
                right;
    }
}