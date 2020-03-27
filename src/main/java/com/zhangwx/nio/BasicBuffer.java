package com.zhangwx.nio;

import java.nio.IntBuffer;

public class BasicBuffer {

    public static void main(String[] args) {

        //举例说明NIO intbuffer
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i=0;i<intBuffer.capacity();i++){
            System.out.println("inbuffer写入数据："+i);
            intBuffer.put(i);
        }

        //inbuffer 读写需要切换
        System.out.println("intbuffer切换读写");
        intBuffer.flip();

        while (intBuffer.hasRemaining()){
            System.out.println("取出数据："+intBuffer.get());
        }
    }
}
