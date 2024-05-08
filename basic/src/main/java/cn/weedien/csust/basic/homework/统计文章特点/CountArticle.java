package cn.weedien.csust.basic.homework.统计文章特点;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;

public class CountArticle {
    public static void main(String[] args) {
        URL url = CountArticle.class.getClassLoader().getResource("article.txt");
        // 从文件中读取文章内容
        assert url != null;
        String article = readFromFile(url.getPath());
        // 统计文章中的句子数、段落数、单词数
        int sentenceCount = countSentences(article);
        int paragraphCount = countParagraphs(article);
        int wordCount = countWords(article);
        // 显示结果
        System.out.println("The article has " + sentenceCount + " sentences.");
        System.out.println("The article has " + paragraphCount + " paragraphs.");
        System.out.println("The article has " + wordCount + " words.");
    }

    // 从文件中读取文章内容
    public static String readFromFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner sc = new Scanner(new File(fileName));
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine()).append("\n");
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    // 统计文章中的句子数
    public static int countSentences(String article) {
        // 句子分隔符为标点符号，包括句号、问号、感叹号和分号
        String[] sentences = article.split("[.?!;]");
        return sentences.length;
    }

    // 统计文章中的段落数
    public static int countParagraphs(String article) {
        // 段落分隔符为回车换行符或者空行
        String[] paragraphs = article.split("\n\n|\r\n\r\n");
        return paragraphs.length;
    }

    // 统计文章中的单词数
    public static int countWords(String article) {
        // 单词分隔符为空格或者标点符号，忽略大小写和重复单词
        HashSet<String> words = new HashSet<>();
        String[] tokens = article.split("[\\s,.?!;]+");
        for (String token : tokens) {
            words.add(token.toLowerCase());
        }
        return words.size();
    }
}