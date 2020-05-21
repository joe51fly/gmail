package com.joe.gmail.manage.controller;

import com.joe.gmail.manage.util.ProperiesUtil;
import org.csource.common.MyException;

import java.io.*;

public class TestFdfs {

    public static void main(String[] args) throws IOException, MyException {

        // 方案1
        // 获取代码工程路径，生产环境上只有class文件，没有java文件与代码工程，这种方式不可取
        File file = new File("");
        System.out.println("方案1------------>"+file.getCanonicalPath());

        // 方案2
        // 获取代码工程路径，生产环境上使用会出现问题
        System.out.println("方案2------------>"+System.getProperty("user.dir"));

        // 方案3
        // 获取class文件根目录（路径中包含空格会被转义为%20，此时new File会失败）
        file = new File(TestFdfs.class.getResource("/").getPath());
        System.out.println("方案3------------>"+file.getCanonicalPath());

        // 方案4
        // 获取class文件目录（路径中包含空格会被转义为%20，此时new File会失败）
        file = new File(TestFdfs.class.getResource("").getPath());
        System.out.println("方案4------------>"+file.getCanonicalPath());

        // 方案5
        // 可以看出来，这种方法的效果和方案3效果相同
        file = new File(TestFdfs.class.getClassLoader().getResource("").getPath());
        System.out.println("方案5------------>"+file.getCanonicalFile());

        // 由于路径可能包含空格，new file可能失败，所以可以直接打开流读取文件
        InputStream stream = TestFdfs.class.getResource("/globaConf.properties").openStream();
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader bufReader=new BufferedReader(reader);

        String str = null;
        while ((str = bufReader.readLine()) != null)
        {
            System.out.println("方案6------------>"+str);
        }

        String file1 = TestFdfs.class.getResource("/globaConf.properties").getFile();
        System.out.println("getResource-----"+file1);

        //方案6
        //使用java.util.Properties获取
        /*Properties properties = new Properties();
        properties.load(new FileInputStream(file1));
        String img_server = properties.getProperty("img_server");
        System.out.println(img_server);
        */

        String img_server = ProperiesUtil.getProperiesValue("globaConf.properties", "img_server");
        System.out.println(img_server);
    }
}
