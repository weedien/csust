package cn.weedien;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ForEachTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list = list.stream().map(num -> num + 2).collect(Collectors.toList());
        System.out.println(list);
    }
}
