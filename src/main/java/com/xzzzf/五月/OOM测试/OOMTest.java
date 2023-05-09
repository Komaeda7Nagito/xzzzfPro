package com.xzzzf.五月.OOM测试;

import java.util.ArrayList;
import java.util.List;


/**
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=F:\dumpTest
 *
 * 1.占用内存过大的对象有哪些 (Histogram)
 * 2.被谁引用的 (dominator_tree)
 * 3.定位到具体的代码 (thread_overview)
 */
public class OOMTest {

    public static void main(String[] args) {
        List<byte[]> memoryLeakArray = new ArrayList<>();
        for (int i = 0;i < 1024;i++) {
            byte[] bytes = new byte[1024 * 1024]; // 1M
            memoryLeakArray.add(bytes); // 造成OOM
        }
    }
}
