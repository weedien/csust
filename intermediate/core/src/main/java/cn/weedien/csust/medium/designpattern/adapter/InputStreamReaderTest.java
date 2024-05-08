package cn.weedien.csust.medium.designpattern.adapter;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author weedien
 * @date 2023/12/7
 */
public class InputStreamReaderTest {
    public static void main(String[] args) {
        try {
            InputStream is = new ClassPathResource("hello.txt").getInputStream();
//            FileInputStream fis = new FileInputStream("hello.txt");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
