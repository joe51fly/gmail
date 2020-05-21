package com.joe.gmail.manage;


import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class GmailManageWebApplicationTests {

    @Test
    void contextLoads() throws IOException, MyException {
        //配置fdfs的全局连接地址
        String tracker = GmailManageWebApplicationTests.class.getResource("tracker.conf").getPath();//获得配置文件的

        ClientGlobal.init(tracker);

        TrackerClient trackerClient = new TrackerClient();

        //获得一个trackerServer的实例
        TrackerServer trackerServer = trackerClient.getTrackerServer();

        StorageClient storageClient = new StorageClient(trackerServer,null);

        String[] upload_file = storageClient.upload_file("F:/Applications/a.jpg", "jpg", null);
        for (String upLoadInfo : upload_file){
            System.out.println(upLoadInfo);
        }



    }

}
