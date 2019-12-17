package pers.yo.study.Demo02_Response.example.servlet;


import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/*
java.awt.Image抽象类 的一个子类 java.awt.image.BufferedImage 是带数据缓冲区的图像类，
BufferedImage 生成的图片在内存里有一个图像缓冲区，
利用这个缓冲区我们可以很方便的操作这个图片，
通常用来做图片修改操作如大小变换、图片变灰、设置图片透明或不透明等，

构造方法：
public BufferedImage (int width,int height,int imageType)  //imageType 指定图像类型，一般是给定的常量

*  */

//2019-12-17 16:57:00 好多方法看不懂，回来在谷歌


@WebServlet("/checkCodeServlet") //第一步先把这个注解改了！
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //宽高的单位都是像素 px，写代码时不要带这个单位
        int width = 100;  //表示 100px
        int height = 50; //表示50px

        /* 1.创建一个对象：在内存中代表一张图片(即 验证码图片对象)
        * BufferedImage image = new BufferedImage( int width, int height, int imageType --一般是给定的常量  )
        * 第三个参数imageType通常为一个常量：BufferedImage.TYPE_INT_RGB
        * ▲ BufferedImage.TYPE_INT_RGB常量，表示一个图像，该图像具有打包成整数像素的 8 位 RGB 颜色分量，默认是黑色背景
        *  */
        BufferedImage image = new BufferedImage( width,height, BufferedImage.TYPE_INT_RGB );

        /* 好多方法看不懂！回来再百度方法
        * 2.美化图片
        * (1)填充背景色
        * (2)画边框
        *  */
        //2.1 填充背景色
        Graphics g = image.getGraphics(); //已有的图片的 画笔对象
        //Color类在 java.awt包下，已经被我用 java.awt.* 全引进来了……
        g.setColor( Color.PINK ); //设置画笔颜色
        g.fillRect( 0,0,width,height );

        //2.2画边框
        g.setColor( Color.BLUE );
        g.drawRect( 0,0, width-1, height-1 );

        //待随机抽取的字符串str
        String str = "ABCDEFGHIJKalsdjflkjaoe0123456789";
        //生成随机的下标(索引)，以在str中随机抽取字符
        Random rand = new Random();

        for( int i=1; i<=4; i++ ){
            /* Random实例对象.nextInt( int bound边界 )
            * 从指定的范围 [ 0, bound ) 左开右闭区间 内 ， 包含左端0，不包含右端bound
            * 生成一个随机数(在这里是 字符串的下标)
            *  */
            int index = rand.nextInt( str.length() ); //生成随机的下标
            char ch = str.charAt( index ); //根据随机下标，获取字符串中的该 “随机的”字符
            g.drawString( String.valueOf(ch), width/5*i, height/2 );
        }

        //2.4 画干扰线
        g.setColor( Color.GREEN );

        //随机生成坐标点
        for( int i=0; i<10; i++ ){
            int x1 = rand.nextInt( width );
            int x2 = rand.nextInt( width );

            int y1 = rand.nextInt( height );
            int y2 = rand.nextInt( height );

            g.drawLine( x1,y1, x2,y2 );
        }

        /* 3.将成品图片以字节输出流的方式，
        * 输出到页面中
        *
        * ImageIO工具类，这个类中的方法都是静态方法，可以用来进行简单的图片 IO 操作
        * ImageIO.write( 必需-RenderedImage im图片对象 , -- render渲染
        *                必需-String formatName后缀名-当输出到页面或保存到本地文件时使用的,
        *                必需-OutputStream output字节输出流-使用response响应的字节输出流servletOutputStream输出到页面中 )
        *  */
        ImageIO.write( image, "jpg", response.getOutputStream() );


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost( request, response );
    }
}
