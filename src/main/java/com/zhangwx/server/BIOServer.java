package com.zhangwx.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * BIO blocking i/o
 * mian方法新建一个socket服务监听。。。
 * cmd
 * telnet 127.0.0.1 6666
 * 发起一个会话
 * 每开一个窗口，就会有一新建一个线程发起socket会话
 * BIO 每个窗口会阻塞监听。。。。
 */
public class BIOServer {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动了。。");
        while (true){
            System.out.println("accept...");
              Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    handler(socket);
                }
            });
        }

    }

    public static void handler(Socket socket){
        try {
//            System.out.println("当前线程id="+Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            InputStream inputStream =socket.getInputStream();
            while (true){
                System.out.println("read...");
                System.out.println("当前线程id="+Thread.currentThread().getName());
                int read =  inputStream.read(bytes);
                if (read != -1){
                    System.out.println(new String(bytes,0,read));
                }else {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("关闭client连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
