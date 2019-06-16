//package com.github.jeffyun.trace;
//
//import org.mortbay.jetty.Server;
//import org.mortbay.jetty.webapp.WebAppContext;
//
//import static org.junit.Assert.*;
//
//public class WebTraceAgentTest {
//
//
//    public static void main(String[] args) {
//        // jetty web test
//        try {
//            Server server = new Server(8008);//设置端口号
//            WebAppContext context = new WebAppContext();
//            context.setContextPath("/");//访问路径
//            context.setResourceBase(WebTraceAgentTest.class.getResource("/webapp/").getPath());//路径
//            context.setDescriptor(WebTraceAgentTest.class.getResource("/webapp/WEB-INF/web.xml").getPath());//读取web.xml文件
//            server.setHandler(context);
//            server.start();
//            System.out.println("启动成功：端口号：8008");
//            server.join();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}