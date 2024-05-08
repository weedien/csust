package cn.weedien.csust.advanced.syntax_analyzer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class GOU {

    // 输出文件路径
    private static final String path = "output.txt";
    // 关键字
    static String[] keyWordKey = {"const", "int", "char", "void", "main", "getint", "if", "else", "switch", "case", "default", "while", "for", "scanf", "printf", "return", "auto", "short", "long", "float", "double", "struct", "union", "enum", "typedef", "unsigned", "signed", "extern", "register", "static", "volatile", "do", "goto", "continue", "break", "sizeof"};
    // 关键字种别码
    static String[] keyWordValue = {"CONSTTK", "INTTK", "CHARTK", "VOIDTK", "MAINTK", "GETINTTK", "IFTK", "ELSETK", "SWITCHTK", "CASETK", "DEFAULTTK", "WHILETK", "FORTK", "SCANFTK", "PRINTFTK", "RETURNTK", "AUTOTK", "SHORTTK", "LONGTK", "FLOATTK", "DOUBLETK", "STRUCTTK", "UNIONTK", "ENUMTK", "TYPEDEFTK", "UNSIGNEDTK", "SIGNEDTK", "EXTERNTK", "REGISTERTK", "STATICTK", "VOLATILLETK", "DOTK", "GOTOTK", "CONTINUETK", "BREAKTK", "SIZEOFTK"};
    // 运算符
    static String[] operationKey = {"+", "-", "*", "/", "<", "<=", ">", ">=", "==", "!=", "="};
    // 运算符种别码
    static String[] operationValue = {"PLUS", "MINU", "MULT", "DIV", "LSS", "LEQ", "GRE", "GEQ", "EQL", "NEQ", "ASSIGN"};
    // 界符
    static String[] symbolKey = {":", ";", ",", "(", ")", "{", "}", "[", "]"};
    // 界符种别码
    static String[] symbolValue = {"COLON", "SEMICN", "COMMA", "LPARENT", "RPARENT", "LBRACE", "RBRACE", "LBRACK", "RBRACK"};
    // 语法分析当前单词的种别码
    static List<String> Tokens = new ArrayList<>();
    // 语法分析当前单词
    static List<String> vals = new ArrayList<>();
    // key为函数的方法名，Value值为1表示该方法是有返回值的方法
    static Map<String, Integer> NVoidFunction = new HashMap<>();
    static Map<String, String> keywords = null;
    static Map<String, String> operations = null;
    static Map<String, String> symbols = null;
    // 指向当前所读到字符串的位置的指针
    static int p, lines;
    // 语法分析当前单词位置
    static int q;
    static BufferedWriter bw = null;

    static {
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path)), StandardCharsets.UTF_8)); // 指定输出文件编码集
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        init(); // 初始化存储结构
        // 从resources文件夹下读取文件
        File file = new File("testfile.txt"); // 读取文件的位置
        lines = 1; // 从第一行开始
        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) { // 按行读取
                String str = input.nextLine(); // 获取当前行
                analyze(str); // 词法分析
                lines++; // 下一行
            }
        }
        yufa(); // 语法分析
        bw.flush();
        bw.close(); // 关闭输出流
    }

    // 初始化把数组转换为Map
    public static void init() {
        keywords = new HashMap<>(); // 关键词
        for (int i = 0; i < keyWordKey.length; i++) {
            keywords.put(keyWordKey[i], keyWordValue[i]);
        }
        operations = new HashMap<>(); // 运算符
        for (int i = 0; i < operationKey.length; i++) {
            operations.put(operationKey[i], operationValue[i]);
        }
        symbols = new HashMap<>(); // 界符
        for (int i = 0; i < symbolKey.length; i++) {
            symbols.put(symbolKey[i], symbolValue[i]);
        }
    }

    public static void analyze(String str) throws IOException {
        p = 0;
        char ch;
        str = str.trim();
        for (; p < str.length(); p++) {
            ch = str.charAt(p);
            if (Character.isDigit(ch)) { // 判断字符是否为数字
                digitCheck(str);
            } else if (Character.isLetter(ch) || ch == '_') { // 字母或_
                letterCheck(str);
            } else if (ch == '"') {
                stringCheck(str);
            } else if (ch == '\'') {
                charCheck(str);
            } else if (ch == ' ') {
                continue;
            } else {
                symbolCheck(str);
            }
        }
    }

    /*数字的识别
     * 1、识别退出：
     *   1.1、遇到空格符
     *   1.2、遇到运算符或者界符
     * 2、错误情况：
     *   2.1、两个及以上小数点
     *   2.2、掺杂字母
     * */
    public static void digitCheck(String str) throws IOException {

        StringBuilder token = new StringBuilder(String.valueOf(str.charAt(p++)));
        // 判断数字的小数点是否有且是否大于1
        int flag = 0;
        boolean err = false;
        char ch;
        for (; p < str.length(); p++) {
            ch = str.charAt(p);
            if (ch == ' ' || (!Character.isLetterOrDigit(ch) && ch != '.')) {
                // 遇到空格符,运算符或者界符
                break;
            } else if (err) {
                token.append(ch);
            } else {
                token.append(ch);
                if (ch == '.') {
                    if (flag == 1) {
                        err = true;
                    } else {
                        flag++;
                    }
                } else if (Character.isLetter(ch)) {
                    err = true;
                }
            }
        }
        if (token.charAt(token.length() - 1) == '.') {
            err = true;
        }
        Tokens.add("INTCON");
        vals.add(token.toString());
        if (p != str.length() - 1 || (p == str.length() - 1 && !Character.isDigit(str.charAt(p)))) {
            p--;
        }
    }

    // 标识符，关键字的识别
    public static void letterCheck(String str) throws IOException {
        StringBuilder token = new StringBuilder(String.valueOf(str.charAt(p++)));
        char ch;
        for (; p < str.length(); p++) {
            ch = str.charAt(p);
            if (!Character.isLetterOrDigit(ch) && ch != '_') {
                break;
            } else {
                token.append(ch);
            }
        }
        if (keywords.containsKey(token.toString())) {
            Tokens.add(keywords.get(token.toString()));
            vals.add(token.toString());
        } else {
            Tokens.add("IDENFR");
            vals.add(token.toString());
        }
        if (p != str.length() - 1 || (p == str.length() - 1 && (!Character.isLetterOrDigit(str.charAt(p)) && str.charAt(p) != '_'))) {
            p--;
        }
    }

    // 字符串检查
    public static void stringCheck(String str) throws IOException {

        StringBuilder token = new StringBuilder(String.valueOf(str.charAt(p++)));
        char ch;
        for (; p < str.length(); p++) {
            ch = str.charAt(p);
            token.append(ch);
            if (ch == '"') {
                break;
            }
        }
        String Token = token.toString().replace("\"", "");
        Tokens.add("STRCON");
        vals.add(Token);
    }

    // 字符常量
    public static void charCheck(String str) throws IOException {

        StringBuilder token = new StringBuilder(String.valueOf(str.charAt(p++)));
        char ch;
        for (; p < str.length(); p++) {
            ch = str.charAt(p);
            token.append(ch);
            if (ch == '\'') {
                break;
            }
        }
        String Token = token.toString().replace("'", "");
        Tokens.add("CHARCON");
        vals.add(Token);
    }

    // 符号的识别
    public static void symbolCheck(String str) throws IOException {

        String token = String.valueOf(str.charAt(p++));
        char ch;
        if (symbols.containsKey(token)) {
            Tokens.add(symbols.get(token));
            vals.add(token);
            p--;
        } else {
            if (operations.containsKey(token) || token.equals("!")) {
                if (p < str.length()) {
                    ch = str.charAt(p);
                    if (operations.containsKey(token + ch)) {
                        token += ch;
                        p++;
                        if (p < str.length()) {
                            ch = str.charAt(p);
                            if (operations.containsKey(token + ch)) {
                                token += ch;
                                Tokens.add(operations.get(token));
                                vals.add(token);
                            } else {
                                p--;
                                Tokens.add(operations.get(token));
                                vals.add(token);
                            }
                        } else {
                            Tokens.add(operations.get(token));
                            vals.add(token);
                        }
                    } else {
                        p--;
                        Tokens.add(operations.get(token));
                        vals.add(token);
                    }
                }
            } else {
                p--;
            }
        }
    }

    public static void yufa() throws IOException {
        q = 0;
        parse1(); // 从<程序>节点开始
    }

    public static void parse0() throws IOException {
        //＜字符串＞ ::=  "｛十进制编码为32,33,35-126的ASCII字符｝"
        MatchToken("STRCON");
        bw.write("<字符串>");
        bw.newLine();
        System.out.println("<字符串>");
    }

    public static void parse1() throws IOException {
        //＜程序＞ ::= ［＜常量说明＞］［＜变量说明＞］{＜有返回值函数定义＞|＜无返回值函数定义＞}＜主函数＞
        if (Objects.equals(Tokens.get(q), "CONSTTK")) {
            parse2(); //＜常量说明＞ 
        }
        if ((Objects.equals(Tokens.get(q), "INTTK") || Objects.equals(Tokens.get(q), "CHARTK")) && Objects.equals(Tokens.get(q + 1), "IDENFR") && !Objects.equals(Tokens.get(q + 2), "LPARENT")) {
            parse7(); //＜变量说明＞ 
        }
        while ((Objects.equals(Tokens.get(q), "INTTK") || Objects.equals(Tokens.get(q), "CHARTK") || Objects.equals(Tokens.get(q), "VOIDTK")) &&
                Objects.equals(Tokens.get(q + 1), "IDENFR") && Objects.equals(Tokens.get(q + 2), "LPARENT")) {
            if (Objects.equals(Tokens.get(q), "VOIDTK")) {
                parse10(); //＜无返回值函数定义＞ 
            } else {
                parse9(); //＜有返回值函数定义＞ 
            }
        }
        if (Objects.equals(Tokens.get(q), "VOIDTK") && Objects.equals(Tokens.get(q + 1), "MAINTK")) {
            parse13(); //＜主函数＞ 
        }
        if (q == Tokens.size()) {
            System.out.println("<程序>");
            bw.write("<程序>");
            bw.newLine();
        }
    }

    private static void parse2() throws IOException {
        //＜常量说明＞ ::=  const＜常量定义＞;{ const＜常量定义＞;}
        do {
            MatchToken("CONSTTK");
            parse3(); //＜常量定义＞
            MatchToken("SEMICN");
        } while (Objects.equals(Tokens.get(q), "CONSTTK"));
        System.out.println("<常量说明>");
        bw.write("<常量说明>");
        bw.newLine();

    }

    private static void parse3() throws IOException {
        //＜常量定义＞   ::=   int＜标识符＞＝＜整数＞{,＜标识符＞＝＜整数＞}| char＜标识符＞＝＜字符＞{,＜标识符＞＝＜字符＞}
        if (Objects.equals(Tokens.get(q), "INTTK")) {
            MatchToken("INTTK");
            MatchToken("IDENFR");
            MatchToken("ASSIGN");
            parse5(); //＜整数＞
            while (Objects.equals(Tokens.get(q), "COMMA")) {
                MatchToken("COMMA");
                MatchToken("IDENFR");
                MatchToken("ASSIGN");
                parse5();
            }
        }
        if (Objects.equals(Tokens.get(q), "CHARTK")) {
            MatchToken("CHARTK");
            MatchToken("IDENFR");
            MatchToken("ASSIGN");
            MatchToken("CHARCON");
            while (Objects.equals(Tokens.get(q), "COMMA")) {
                MatchToken("COMMA");
                MatchToken("IDENFR");
                MatchToken("ASSIGN");
                MatchToken("CHARCON");
            }
        }
        System.out.println("<常量定义>");
        bw.write("<常量定义>");
        bw.newLine();
    }

    private static void parse4() throws IOException {
//<无符号整数>
        MatchToken("INTCON");
        System.out.println("<无符号整数>");
        bw.write("<无符号整数>");
        bw.newLine();
    }

    private static void parse5() throws IOException {
        //＜整数＞ ::= ［＋｜－］＜无符号整数＞
        if (Objects.equals(Tokens.get(q), "PLUS"))
            MatchToken("PLUS");
        else if (Objects.equals(Tokens.get(q), "MINU"))
            MatchToken("MINU");
        parse4(); //<无符号整数>
        System.out.println("<整数>");
        bw.write("<整数>");
        bw.newLine();
    }

    private static void parse6() throws IOException {
        //＜声明头部＞ ::=  int＜标识符＞ |char＜标识符＞
        if (Objects.equals(Tokens.get(q), "INTTK"))
            MatchToken("INTTK");
        else if (Objects.equals(Tokens.get(q), "CHARTK"))
            MatchToken("CHARTK");
        MatchToken("IDENFR");
        System.out.println("<声明头部>");
        bw.write("<声明头部>");
        bw.newLine();
    }

    private static void parse7() throws IOException {
        //＜变量说明＞ ::= ＜变量定义＞;{＜变量定义＞;}
        do {
            parse8(); //＜变量定义＞
            MatchToken("SEMICN");
        } while ((Objects.equals(Tokens.get(q), "INTTK") || Objects.equals(Tokens.get(q), "CHARTK")) &&
                Objects.equals(Tokens.get(q + 1), "IDENFR") && !Objects.equals(Tokens.get(q + 2), "LPARENT"));
        System.out.println("<变量说明>");
        bw.write("<变量说明>");
        bw.newLine();
    }

    private static void parse8() throws IOException {
        //＜变量定义＞ ::= ＜类型标识符＞(＜标识符＞|＜标识符＞'['＜无符号整数＞']'){,(＜标识符＞|＜标识符＞'['＜无符号整数＞']')}
        if (Objects.equals(Tokens.get(q), "INTTK"))
            MatchToken("INTTK");
        else if (Objects.equals(Tokens.get(q), "CHARTK"))
            MatchToken("CHARTK");
        MatchToken("IDENFR");
        if (Objects.equals(Tokens.get(q), "LBRACK")) {
            MatchToken("LBRACK");
            parse4(); //<无符号整数>
            MatchToken("RBRACK");
        }
        while (Objects.equals(Tokens.get(q), "COMMA")) {
            MatchToken("COMMA");
            MatchToken("IDENFR");
            if (Objects.equals(Tokens.get(q), "LBRACK")) {
                MatchToken("LBRACK");
                parse4(); //<无符号整数>
                MatchToken("RBRACK");
            }
        }
        System.out.println("<变量定义>");
        bw.write("<变量定义>");
        bw.newLine();
    }

    private static void parse9() throws IOException {
        //＜有返回值函数定义＞ ::=  ＜声明头部＞'('＜参数表＞')' '{'＜复合语句＞'}'
        parse6(); //＜声明头部＞
        NVoidFunction.put(vals.get(q - 1), 1);
        MatchToken("LPARENT");
        parse12(); //＜参数表＞
        MatchToken("RPARENT");
        MatchToken("LBRACE");
        parse11(); //＜复合语句＞
        MatchToken("RBRACE");
        System.out.println("<有返回值函数定义>");
        bw.write("<有返回值函数定义>");
        bw.newLine();
    }

    private static void parse10() throws IOException {
        //＜无返回值函数定义＞ ::= void＜标识符＞'('＜参数表＞')''{'＜复合语句＞'}'
        MatchToken("VOIDTK");
        MatchToken("IDENFR");
        MatchToken("LPARENT");
        parse12(); //＜参数表＞
        MatchToken("RPARENT");
        MatchToken("LBRACE");
        parse11(); //＜复合语句＞
        MatchToken("RBRACE");
        System.out.println("<无返回值函数定义>");
        bw.write("<无返回值函数定义>");
        bw.newLine();
    }

    private static void parse11() throws IOException {
        //＜复合语句＞ ::=  ［＜常量说明＞］［＜变量说明＞］＜语句列＞
        if (Objects.equals(Tokens.get(q), "CONSTTK")) {
            parse2(); //＜常量说明＞
        }
        if ((Objects.equals(Tokens.get(q), "INTTK") || Objects.equals(Tokens.get(q), "CHARTK")) &&
                Objects.equals(Tokens.get(q + 1), "IDENFR") && !Objects.equals(Tokens.get(q + 2), "LPARENT")) {
            parse7(); //＜变量说明＞
        }
        parse26(); //＜语句列＞
        System.out.println("<复合语句>");
        bw.write("<复合语句>");
        bw.newLine();
    }

    private static void parse12() throws IOException {
        //＜参数表＞ ::=  ＜类型标识符＞＜标识符＞{,＜类型标识符＞＜标识符＞}| ＜空＞
        if (!Objects.equals(Tokens.get(q), "RPARENT"))// 如果下一个token为右小括号，则为空
        {
            if (Objects.equals(Tokens.get(q), "INTTK"))
                MatchToken("INTTK");
            else if (Objects.equals(Tokens.get(q), "CHARTK"))
                MatchToken("CHARTK");
            MatchToken("IDENFR");
            while (Objects.equals(Tokens.get(q), "COMMA")) {
                MatchToken("COMMA");
                if (Objects.equals(Tokens.get(q), "INTTK"))
                    MatchToken("INTTK");
                else if (Objects.equals(Tokens.get(q), "CHARTK"))
                    MatchToken("CHARTK");
                MatchToken("IDENFR");
            }
        }
        System.out.println("<参数表>");
        bw.write("<参数表>");
        bw.newLine();
    }

    private static void parse13() throws IOException {
        //＜主函数＞ ::= void main‘(’‘)’ ‘{’＜复合语句＞‘}’
        MatchToken("VOIDTK");
        MatchToken("MAINTK");
        MatchToken("LPARENT");
        MatchToken("RPARENT");
        MatchToken("LBRACE");
        parse11(); //＜复合语句＞
        MatchToken("RBRACE");
        System.out.println("<主函数>");
        bw.write("<主函数>");
        bw.newLine();
    }

    private static void parse14() throws IOException {
        //＜表达式＞ ::= ［＋｜－］＜项＞{＜加法运算符＞＜项＞}
        if (Objects.equals(Tokens.get(q), "PLUS"))
            MatchToken("PLUS");
        else if (Objects.equals(Tokens.get(q), "MINU"))
            MatchToken("MINU");
        parse15(); //＜项＞
        while (Objects.equals(Tokens.get(q), "PLUS") || Objects.equals(Tokens.get(q), "MINU")) {
            if (Objects.equals(Tokens.get(q), "PLUS"))
                MatchToken("PLUS");
            else if (Objects.equals(Tokens.get(q), "MINU"))
                MatchToken("MINU");
            parse15(); //＜项＞
        }
        System.out.println("<表达式>");
        bw.write("<表达式>");
        bw.newLine();
    }

    private static void parse15() throws IOException {
        //＜项＞ ::= ＜因子＞{＜乘法运算符＞＜因子＞}
        parse16(); //＜因子＞
        while (Objects.equals(Tokens.get(q), "MULT") || Objects.equals(Tokens.get(q), "DIV")) {
            if (Objects.equals(Tokens.get(q), "MULT"))
                MatchToken("MULT");
            else if (Objects.equals(Tokens.get(q), "DIV"))
                MatchToken("DIV");
            parse16(); //＜因子＞
        }
        System.out.println("<项>");
        bw.write("<项>");
        bw.newLine();
    }

    private static void parse16() throws IOException {
        //＜因子＞ ::= ＜标识符＞｜＜标识符＞'['＜表达式＞']'|'('＜表达式＞')'｜＜整数＞|＜字符＞｜＜有返回值函数调用语句＞
        if (Objects.equals(Tokens.get(q), "IDENFR")) {
            if (Objects.equals(Tokens.get(q + 1), "LBRACK")) {
                MatchToken("IDENFR");
                MatchToken("LBRACK");
                parse14(); //＜表达式＞
                MatchToken("RBRACK");
            } else if (Objects.equals(Tokens.get(q + 1), "LPARENT"))
                parse234(); //＜有返回值函数调用语句＞
            else MatchToken("IDENFR");
        } else if (Objects.equals(Tokens.get(q), "LPARENT")) {
            MatchToken("LPARENT");
            parse14(); //＜表达式＞
            MatchToken("RPARENT");
        } else if (Objects.equals(Tokens.get(q), "INTCON") || Objects.equals(Tokens.get(q), "PLUS") || Objects.equals(Tokens.get(q), "MINU"))
            parse5(); //＜整数＞
        else if (Objects.equals(Tokens.get(q), "CHARCON"))
            MatchToken("CHARCON");
        System.out.println("<因子>");
        bw.write("<因子>");
        bw.newLine();
    }

    private static void parse17() throws IOException {
        /*＜语句＞ ::= ＜条件语句＞｜＜循环语句＞| '{'＜语句列＞'}'
            | ＜有返回值函数调用语句＞; |＜无返回值函数调用语句＞;｜＜赋值语句＞;
            ｜＜读语句＞;｜＜写语句＞;｜＜空＞;|＜返回语句＞;*/
        if (Objects.equals(Tokens.get(q), "IFTK"))
            parse19(); //＜条件语句＞
        else if (Objects.equals(Tokens.get(q), "WHILETK") || Objects.equals(Tokens.get(q), "DOTK") || Objects.equals(Tokens.get(q), "FORTK"))
            parse21(); //＜循环语句＞
        else if (Objects.equals(Tokens.get(q), "LBRACE")) {
            MatchToken("LBRACE");
            parse26(); //＜语句列＞
            MatchToken("RBRACE");
        } else if (Objects.equals(Tokens.get(q), "IDENFR")) {
            if (Objects.equals(Tokens.get(q + 1), "LPARENT")) {
                parse234(); //＜有无返回值函数调用语句＞
                MatchToken("SEMICN");
            } else {
                parse18(); //＜赋值语句＞
                MatchToken("SEMICN");
            }
        } else if (Objects.equals(Tokens.get(q), "SCANFTK")) {
            parse27(); //＜读语句＞
            MatchToken("SEMICN");
        } else if (Objects.equals(Tokens.get(q), "PRINTFTK")) {
            parse28(); //＜写语句＞
            MatchToken("SEMICN");
        } else if (Objects.equals(Tokens.get(q), "RETURNTK")) {
            parse29(); //＜返回语句＞
            MatchToken("SEMICN");
        } else if (Objects.equals(Tokens.get(q), "SEMICN"))
            MatchToken("SEMICN");
        System.out.println("<语句>");
        bw.write("<语句>");
        bw.newLine();
    }

    private static void parse18() throws IOException {
        //＜赋值语句＞ ::=  ＜标识符＞＝＜表达式＞|＜标识符＞'['＜表达式＞']'=＜表达式＞
        MatchToken("IDENFR");
        if (Objects.equals(Tokens.get(q), "LBRACK")) {
            MatchToken("LBRACK");
            parse14(); //＜表达式＞
            MatchToken("RBRACK");
        }
        MatchToken("ASSIGN");
        parse14(); //＜表达式＞
        System.out.println("<赋值语句>");
        bw.write("<赋值语句>");
        bw.newLine();
    }

    private static void parse19() throws IOException {
        //＜条件语句＞ ::= if '('＜条件＞')'＜语句＞［else＜语句＞］
        MatchToken("IFTK");
        MatchToken("LPARENT");
        parse20(); //＜条件＞
        MatchToken("RPARENT");
        parse17(); //＜语句＞
        if (Objects.equals(Tokens.get(q), "ELSETK")) {
            MatchToken("ELSETK");
            parse17(); //＜语句＞
        }
        System.out.println("<条件语句>");
        bw.write("<条件语句>");
        bw.newLine();
    }

    private static void parse20() throws IOException {
        //＜条件＞    ::=  ＜表达式＞＜关系运算符＞＜表达式＞ //整型表达式之间才能进行关系运算｜＜表达式＞
        // 表达式为整型，其值为0条件为假，值不为0时条件为真
        parse14(); //＜表达式＞
        if (Objects.equals(Tokens.get(q), "LSS") || Objects.equals(Tokens.get(q), "LEQ") || Objects.equals(Tokens.get(q), "GRE")
                || Objects.equals(Tokens.get(q), "GEQ") || Objects.equals(Tokens.get(q), "EQL") || Objects.equals(Tokens.get(q), "NEQ")) {
            MatchToken(Tokens.get(q));
            parse14(); //＜表达式＞
        }
        System.out.println("<条件>");
        bw.write("<条件>");
        bw.newLine();
    }

    private static void parse21() throws IOException {
        //＜循环语句＞ ::=  while '('＜条件＞')'＜语句＞
        //          |do＜语句＞while '('＜条件＞')'
        //          |for'('＜标识符＞＝＜表达式＞;＜条件＞;＜标识符＞＝＜标识符＞(+|-)＜步长＞')'＜语句＞
        if (Objects.equals(Tokens.get(q), "WHILETK")) {
            MatchToken("WHILETK");
            MatchToken("LPARENT");
            parse20(); //＜条件＞
            MatchToken("RPARENT");
            parse17(); //＜语句＞
        } else if (Objects.equals(Tokens.get(q), "DOTK")) {
            MatchToken("DOTK");
            parse17(); //＜语句＞
            MatchToken("WHILETK");
            MatchToken("LPARENT");
            parse20(); //＜条件＞
            MatchToken("RPARENT");
        } else if (Objects.equals(Tokens.get(q), "FORTK")) {
            MatchToken("FORTK");
            MatchToken("LPARENT");
            MatchToken("IDENFR");
            MatchToken("ASSIGN");
            parse14(); //＜表达式＞
            MatchToken("SEMICN");
            parse20(); //＜条件＞
            MatchToken("SEMICN");
            MatchToken("IDENFR");
            MatchToken("ASSIGN");
            MatchToken("IDENFR");
            if (Objects.equals(Tokens.get(q), "PLUS"))
                MatchToken("PLUS");
            else if (Objects.equals(Tokens.get(q), "MINU"))
                MatchToken("MINU");
            parse22(); //＜步长＞
            MatchToken("RPARENT");
            parse17(); //＜语句＞
        }
        System.out.println("<循环语句>");
        bw.write("<循环语句>");
        bw.newLine();
    }

    private static void parse22() throws IOException {
        //＜步长＞::= ＜无符号整数＞
        parse4(); //＜无符号整数＞
        System.out.println("<步长>");
        bw.write("<步长>");
        bw.newLine();
    }

    private static void parse234() throws IOException {
        //＜有无返回值函数调用语句＞ ::= ＜标识符＞'('＜值参数表＞')'
        int FunctionType = 0;
        if (NVoidFunction.containsKey(vals.get(q))) {
            FunctionType = NVoidFunction.get(vals.get(q));
        }
        MatchToken("IDENFR");
        MatchToken("LPARENT");
        parse25(); //＜值参数表＞
        MatchToken("RPARENT");
        if (FunctionType == 1) {
            System.out.println("<有返回值函数调用语句>");
            bw.write("<有返回值函数调用语句>");
            bw.newLine();
        } else {
            System.out.println("<无返回值函数调用语句>");
            bw.write("<无返回值函数调用语句>");
            bw.newLine();
        }
    }

    private static void parse25() throws IOException {
        //＜值参数表＞ ::= ＜表达式＞{,＜表达式＞}｜＜空＞
        if (!Objects.equals(Tokens.get(q), "RPARENT")) {
            parse14(); //＜表达式＞
            while (Objects.equals(Tokens.get(q), "COMMA")) {
                MatchToken("COMMA");
                parse14();
            }
        }
        System.out.println("<值参数表>");
        bw.write("<值参数表>");
        bw.newLine();
    }

    private static void parse26() throws IOException {
        //＜语句列＞ ::= ｛＜语句＞｝
        while (!Objects.equals(Tokens.get(q), "RBRACE"))
            parse17(); //＜语句＞
        System.out.println("<语句列>");
        bw.write("<语句列>");
        bw.newLine();
    }

    private static void parse27() throws IOException {
        //＜读语句＞ ::=  scanf '('＜标识符＞{,＜标识符＞}')'
        MatchToken("SCANFTK");
        MatchToken("LPARENT");
        MatchToken("IDENFR");
        while (Objects.equals(Tokens.get(q), "COMMA")) {
            MatchToken("COMMA");
            MatchToken("IDENFR");
        }
        MatchToken("RPARENT");
        System.out.println("<读语句>");
        bw.write("<读语句>");
        bw.newLine();
    }

    private static void parse28() throws IOException {
        //＜写语句＞ ::= printf '(' ＜字符串＞,＜表达式＞ ')'
        //          | printf '('＜字符串＞ ')'
        //          | printf '('＜表达式＞')'
        MatchToken("PRINTFTK");
        MatchToken("LPARENT");
        if (Objects.equals(Tokens.get(q), "STRCON"))
            parse0(); //＜字符串＞
        else
            parse14(); //＜表达式＞
        if (Objects.equals(Tokens.get(q), "COMMA")) {
            MatchToken("COMMA");
            parse14(); //＜表达式＞
        }
        MatchToken("RPARENT");
        System.out.println("<写语句>");
        bw.write("<写语句>");
        bw.newLine();
    }

    private static void parse29() throws IOException {
        //＜返回语句＞ ::=  return['('＜表达式＞')']
        MatchToken("RETURNTK");
        if (Objects.equals(Tokens.get(q), "LPARENT")) {
            MatchToken("LPARENT");
            parse14(); //＜表达式＞
            MatchToken("RPARENT");
        }
        System.out.println("<返回语句>");
        bw.write("<返回语句>");
        bw.newLine();
    }

    public static void MatchToken(String expected) throws IOException {
        if (Objects.equals(Tokens.get(q), expected)) {
            System.out.println(Tokens.get(q) + " " + vals.get(q));
            bw.write(Tokens.get(q) + " " + vals.get(q));
            bw.newLine();
            q++; // 下一个单词
        }
    }
}