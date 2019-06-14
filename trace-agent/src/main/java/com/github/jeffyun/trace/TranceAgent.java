package com.github.jeffyun.trace;

import javassist.*;
import javassist.bytecode.AccessFlag;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 * @author jianfeng.Wu
 * @date 2019/6/13 9:53
 */
public class TranceAgent {

    public static void premain(String args, Instrumentation inst) {
        System.out.println("拦截 server");

        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                if (className == null || loader == null) {
                    return null;
                }

                try {
                    if ("com/github/jeffyun/service/impl/OrderServiceImpl".equals(className)) {
                        return buildMonitorBytes(loader, className.replaceAll("/", "."));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });

    }

    private static byte[] buildMonitorBytes(ClassLoader loader, String className) throws Exception {
        ClassPool classPool = new ClassPool();
        classPool.insertClassPath(new LoaderClassPath(loader));
        //根据className获取class
        CtClass ctClass = classPool.getCtClass(className);

        //当前类所有public method，并且是非抽象、非静态、非本地
        for (CtMethod method : ctClass.getDeclaredMethods()) {
            int accflags = method.getModifiers();
            if (!AccessFlag.isPublic(method.getModifiers())) {
                continue;
            }
            if ((method.getModifiers() & AccessFlag.ABSTRACT) != 0) {
                continue;
            }
            if ((method.getModifiers() & AccessFlag.STATIC) != 0) {
                continue;
            }
            if ((method.getModifiers() & AccessFlag.NATIVE) != 0) {
                continue;
            }



            System.out.println(method.getName() + "=" + accflags);

            //复制新方法
            CtMethod copyMethod = CtNewMethod.copy(method, ctClass, new ClassMap());
            //原方法重命名
            method.setName(method.getName() + "$agent");
            // 原方法 改为私有方法 否则在dubbo进行二次转换出现异常
//            method.setModifiers(AccessFlag.setPrivate(method.getModifiers()));

            if (copyMethod.getReturnType().getName().equals("void")) {
                copyMethod.setBody("{\n" +
                        "        Object trace = com.github.jeffyun.trace.TranceAgent.begin($args);\n" +
                        "        try {\n" +
                        "            " + copyMethod.getName() + "$agent($$);\n" +
                        "        } finally {\n" +
                        "            com.github.jeffyun.trace.TranceAgent.end(trace);\n" +
                        "        }}");
            } else {
                copyMethod.setBody("{\n" +
                        "        Object trace = com.github.jeffyun.trace.TranceAgent.begin($args);\n" +
                        "        try {\n" +
                        "            return " + copyMethod.getName() + "$agent($$);\n" +
                        "        } finally {\n" +
                        "            com.github.jeffyun.trace.TranceAgent.end(trace);\n" +
                        "        }}");

            }
            ctClass.addMethod(copyMethod);
        }
        return ctClass.toBytecode();
    }

    public static Object begin(Object[] args) {
        TraceInfo info = new TraceInfo(args, System.currentTimeMillis());
        return info;
    }

    public static void end(Object trance) {
        TraceInfo info = (TraceInfo) trance;
        info.setUseTime(System.currentTimeMillis() - info.getBegin());
        System.out.println(info);


    }

    public static class TraceInfo {
        Object[] args;
        Long begin;
        Long useTime;

        public TraceInfo() {
        }

        public TraceInfo(Object[] args, Long begin) {
            this.args = args;
            this.begin = begin;
        }

        public Object[] getArgs() {
            return args;
        }

        public TraceInfo setArgs(Object[] args) {
            this.args = args;
            return this;
        }

        public Long getBegin() {
            return begin;
        }

        public TraceInfo setBegin(Long begin) {
            this.begin = begin;
            return this;
        }

        public Long getUseTime() {
            return useTime;
        }

        public TraceInfo setUseTime(Long useTime) {
            this.useTime = useTime;
            return this;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", TraceInfo.class.getSimpleName() + "[", "]")
                    .add("args=" + Arrays.toString(args))
                    .add("begin=" + begin)
                    .add("useTime=" + useTime)
                    .toString();
        }
    }


}
