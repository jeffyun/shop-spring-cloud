package com.github.jeffyun.trace;


import javassist.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.instrument.Instrumentation;
import java.util.Arrays;
import java.util.Map;

public class WebTraceAgent {


    public static void premain(String args, Instrumentation inst) {
        System.out.println("拦截 servlet");
        inst.addTransformer((loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> {
//            if (className != null && className.indexOf("servlet") != -1){
//                System.out.println(">>>>>>"+className);
//
//            }
            if (!"javax/servlet/http/HttpServlet".equals(className)) {
                return null;
            }

            try {
                return buildMonitorByte(loader, className.replaceAll("/", "."));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    private static byte[] buildMonitorByte(ClassLoader loader, String className) throws Exception {

        ClassPool pool = new ClassPool();
        pool.insertClassPath(new LoaderClassPath(loader));

        CtClass ctClass = pool.getCtClass(className);

        CtMethod method = ctClass.getDeclaredMethod("service", pool.get(new String[]{"javax.servlet.http.HttpServletRequest",
                "javax.servlet.http.HttpServletResponse"}));

        //复制方法
        CtMethod copyMethod = CtNewMethod.copy(method, ctClass, new ClassMap());

        //原有方法重命名
        method.setName(method.getName() + "$agent");


//        copyMethod.setBody("{\n" +
//                "    Object trace = com.github.jeffyun.trace.WebTraceAgent.begin($args);\n" +
//                "    try{\n" +
//                "        " + copyMethod.getName() + "$agent($$);\n" +
//                "    }final {\n" +
//                "        com.github.jeffyun.trace.WebTraceAgent.end(trace);\n" +
//                "    }}");


        copyMethod.setBody("{\n" +
                "               Object trace= com.github.jeffyun.trace.WebTraceAgent.begin($args);\n" +
                "                try {\n" +
                "                     " + copyMethod.getName() + "$agent($$);\n" +
                "                } finally {\n" +
                "                    com.github.jeffyun.trace.WebTraceAgent.end(trace);\n" +
                "                }\n" +
                "            }");
        ctClass.addMethod(copyMethod);

        return ctClass.toBytecode();
    }


    public static Object begin(Object args[]) {
        HttpServletRequest request = (HttpServletRequest) args[0];
        HttpServletResponse response = (HttpServletResponse) args[1];
        WebTraceInfo trace = new WebTraceInfo();
        trace.setParams(request.getParameterMap());
        trace.setCookie(request.getCookies());
        trace.setUrl(request.getRequestURI());
        trace.setBegin(System.currentTimeMillis());
//        String traceId = UUID.randomUUID().toString().replaceAll("-", "");
//        TraceSession session = new TraceSession(traceId, "0");
//        trace.traceId = traceId;
//        trace.eventId = session.getParameterMaparentId() + "." + session.getNextEventId();
        return trace;
    }


    public static void end(Object webTraceInfo) {
        WebTraceInfo trace = (WebTraceInfo) webTraceInfo;

        trace.setUseTime(System.currentTimeMillis() - trace.getBegin());

        System.out.println(trace);

    }

    public static class WebTraceInfo {
        private String traceId;
        private String eventId;
        private Long begin;
        private String url;
        private Map<String, String[]> params;
        private Cookie[] cookie;
        private String handler;
        private Long useTime;


        public WebTraceInfo() {
        }

        public String getTraceId() {
            return traceId;
        }

        public WebTraceInfo setTraceId(String traceId) {
            this.traceId = traceId;
            return this;
        }

        public String getEventId() {
            return eventId;
        }

        public WebTraceInfo setEventId(String eventId) {
            this.eventId = eventId;
            return this;
        }

        public Long getBegin() {
            return begin;
        }

        public WebTraceInfo setBegin(Long begin) {
            this.begin = begin;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public WebTraceInfo setUrl(String url) {
            this.url = url;
            return this;
        }

        public Map<String, String[]> getParams() {
            return params;
        }

        public WebTraceInfo setParams(Map<String, String[]> params) {
            this.params = params;
            return this;
        }

        public Cookie[] getCookie() {
            return cookie;
        }

        public WebTraceInfo setCookie(Cookie[] cookie) {
            this.cookie = cookie;
            return this;
        }

        public String getHandler() {
            return handler;
        }

        public WebTraceInfo setHandler(String handler) {
            this.handler = handler;
            return this;
        }

        public Long getUseTime() {
            return useTime;
        }

        public WebTraceInfo setUseTime(Long useTime) {
            this.useTime = useTime;
            return this;
        }

        @Override
        public String toString() {
            return "WebTraceInfo{" +
                    "traceId='" + traceId + '\'' +
                    ", eventId='" + eventId + '\'' +
                    ", begin=" + begin +
                    ", url='" + url + '\'' +
                    ", params=" + params +
                    ", cookie=" + Arrays.toString(cookie) +
                    ", handler='" + handler + '\'' +
                    ", useTime=" + useTime +
                    '}';
        }
    }
}
