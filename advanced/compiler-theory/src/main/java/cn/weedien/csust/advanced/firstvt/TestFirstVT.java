package cn.weedien.csust.advanced.firstvt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TestFirstVT {
    public static final String RIGHT_INFER_QUOTE = " → ";
    public static final String REGEX = "\\|";
    // 用于存所有产生式
    public List<Grammar> grammars;
    // 后进先出栈
    public ArrayDeque<String> stack;
    // 数组F
    public int[][] F = new int[26][94];// 非终结符范围A ~ Z，终结符范围'!' ~ '~'
    // 结果
    Map<Character, List<Character>> result;

    public TestFirstVT() throws IOException {
        init();
    }

    public TestFirstVT(List<Grammar> grammars, ArrayDeque<String> stack) {
        this.grammars = grammars;
        this.stack = stack;
    }

    /*测试案例
S → #E#
E → E+T
E → T
T → T*F
T → F
F → P!F|P
P → (E)
P → i



S → a|@|(T)
T → T,S|S
    */
    public static void main(String[] args) throws IOException {
        TestFirstVT firstvt = new TestFirstVT();
        firstvt.solve();
        firstvt.print();
    }

    /**
     * 输入以及初始化
     */
    public void init() throws IOException {
        grammars = new ArrayList<>();
        stack = new ArrayDeque<>();
        System.out.println("输入文法的产生式：形如A → Ba");
        System.out.println("约定所有非终结符为英文大写字母,终结符为!至~(ASCII码值33~126)");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        while (!(line = br.readLine().trim()).isEmpty()) {
            String[] strings = line.split(RIGHT_INFER_QUOTE);
            for (String s : strings[1].split(REGEX)) {
                Grammar grammar = new Grammar(strings[0], s);
                grammars.add(grammar);
                char cT = s.charAt(0);
                if (!Character.isUpperCase(cT)) {// 形如A → a...
                    insert(strings[0].charAt(0), cT);
                } else if (s.length() > 1) {
                    if (!Character.isUpperCase((cT = s.charAt(1)))) {// 形如A → Ba...
                        insert(strings[0].charAt(0), cT);
                    }
                }
            }
        }
        // System.out.println(grammars.toString());
        System.out.println("输入结束........");
    }

    /**
     * @param A 非终结符
     * @param a 终结符
     */
    public void insert(char A, char a) {
        if (F[A - 'A'][a - '!'] == 0) {
            F[A - 'A'][a - '!'] = 1;
            stack.addLast(A + "" + a);
            // 输出栈顶的内容
            System.out.println("(" + A + "," + a + ")");
        }
    }

    /**
     * 主程序
     */
    public void solve() {
        // init();
        if (stack == null) {
            System.out.println("未初始化！");
            return;
        }
        while (!stack.isEmpty()) {
            // 取栈顶
            String s = stack.removeLast();
            char cN = s.charAt(0);
            char cT = s.charAt(1);
            for (Grammar grammar : grammars) {
                if (grammar.right.charAt(0) == cN) {// 形如A → B...
                    insert(grammar.left.charAt(0), cT);
                }
            }
        }
    }

    /**
     * @return 所有非终结符的FIRSTVT集
     */
    public Map<Character, List<Character>> getResult() {
        result = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 94; j++) {
                if (F[i][j] == 1) {
                    char cN = (char) (i + 'A');
                    char cT = (char) (j + '!');
                    if (result.containsKey(cN)) {
                        result.get(cN).add(cT);
                    } else {
                        List<Character> cTList = new ArrayList();
                        cTList.add(cT);
                        result.put(cN, cTList);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 打印所有非终结符的FIRSTVT集
     */
    public void print() {
        System.out.println("所有非终结符的FIRSTVT集为：");
        // 得到结果
        getResult();
        for (Character cN : result.keySet()) {
            // 得到非终结符的FIRSTVT集
            List<Character> cT = result.get(cN);
            System.out.printf("FIRSTVT(%c)={%s}%n", cN, cT.toString().substring(1, 3 * cT.size() - 1));
        }
    }
}


