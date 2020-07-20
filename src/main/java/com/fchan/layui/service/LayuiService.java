package com.fchan.layui.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LayuiService {


    public String popImg(HttpServletResponse response)  {
        ServletOutputStream outputStream = null;
        try {
            File file = ResourceUtils.getFile("classpath:static/image/myHuckleberryFriend.jpg");
            BufferedImage bufferedImage = ImageIO.read(file);   //这里读出来的图片大小是和原来一样大的

            BufferedImage bufferedImageNew = new BufferedImage(300,300,BufferedImage.TYPE_INT_RGB);
            bufferedImageNew.getGraphics().drawImage(bufferedImage,0,0,300,300,null);

            //返回给前端的图片肯定是要最新的,不能有缓存
            response.setHeader("Cache-Control","no-store");
            response.setHeader("Pragma","no-cache");
            response.setDateHeader("Expires",0);
            response.setContentType("image/jpg");
            outputStream = response.getOutputStream();
            ImageIO.write(bufferedImageNew,"jpg",outputStream);
            return file.getName();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public String popQrCode(HttpServletResponse response, String content, int width, int height)  {
        Map<EncodeHintType,Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");    //字符编码
        hints.put(EncodeHintType.MARGIN,0);                 //二维码与图片边距
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q); //容错等级 L、M、Q、H 其中 L 为最低, H 为最高,等级越高存储信息越少
        BitMatrix bitMatrix;
        ServletOutputStream outputStream = null;
        try {
            //参数顺序分别为:编码内容,编码类型,生成图片的宽度,生成图片的高度,设置参数
            //高度和宽度都是以像素为单位
            //一般content都是url,扫描后可以自动跳转到指定的地址
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width,height,hints);

            //返回给前端的图片肯定是要最新的,不能有缓存
            response.setHeader("Cache-Control","no-store");
            response.setHeader("Pragma","no-cache");
            response.setDateHeader("Expires",0);
            response.setContentType("image/jpg");
            outputStream = response.getOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix,"jpg",outputStream);
            return "success";
        } catch (WriterException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }finally{
            if(outputStream != null){
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String [] args){
        List<Map<String,Object>> params = new ArrayList<>();
        Map<String,Object> temp = new HashMap<>();
        temp.put("id",1);
        temp.put("name","qq");
        temp.put("height","180");
        params.add(temp);
        temp = new HashMap<>();
        temp.put("id",1);
        temp.put("name","www");
        temp.put("height","111");
        params.add(temp);
        temp = new HashMap<>();
        temp.put("id",2);
        temp.put("name","eee");
        temp.put("height","222");
        params.add(temp);

        Map groupBy = params.stream().collect(Collectors.groupingBy(it->it.get("id")));
        System.out.println(groupBy);

    }


}
