package com.joe.gmail.manage.util;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class PmsUploadUtil {
    public static String upLoadImage(MultipartFile multipartFile) {


        String imgUrl = ProperiesUtil.getProperiesValue("globaConf.properties", "img_server");
        TrackerServer trackerServer = null;

        //配置fdfs的全局连接地址
        String tracker = PmsUploadUtil.class.getResource("/tracker.conf").getPath();//获得配置文件的

        try {
            ClientGlobal.init(tracker);

        } catch (Exception e) {
            e.printStackTrace();
        }

        TrackerClient trackerClient = new TrackerClient();

        //获得一个trackerServer的实例
        try {
            trackerServer = trackerClient.getTrackerServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StorageClient storageClient = new StorageClient(trackerServer,null);

        try {

            byte[] bytes = multipartFile.getBytes();//获得上传的二进制对象

            String originalFilename = multipartFile.getOriginalFilename();//a.jpg
            int i = originalFilename.lastIndexOf(".");
            String extName = originalFilename.substring(i+1);

            String[] upload_file = storageClient.upload_file(bytes, extName, null);

            for (String upLoadInfo : upload_file){
                imgUrl+="/"+upLoadInfo;
            }
            System.out.println(imgUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return imgUrl;
    }
}
