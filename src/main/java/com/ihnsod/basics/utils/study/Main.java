package com.ihnsod.basics.utils.study;

/**
 * @author: Ihnsod
 * @create: 2019/01/20 13:20
 **/
public class Main {

    public static void main(String[] args) {
        BigFileReader.Builder builder = new BigFileReader.Builder("C:\\Users\\Ihnsod\\Desktop\\test_insert.txt",new IHandle() {

            @Override
            public void handle(String line) {
//                System.out.println(line);
//                increat();
            }
        });
        builder.withTreahdSize(8)
                .withCharset("gbk")
                .withBufferSize(1024*1024);
        BigFileReader bigFileReader = builder.build();
        bigFileReader.start();
    }
}
