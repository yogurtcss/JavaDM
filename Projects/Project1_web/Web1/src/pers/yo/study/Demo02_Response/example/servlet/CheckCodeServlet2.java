package pers.yo.study.Demo02_Response.example.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/* 制作验证码图片
*
*  */


@WebServlet("/checkCodeServlet2")
public class CheckCodeServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 100; //100px
        int height = 100; //100px
        //在内存中生成一张图，指定 宽、高、图片类型
        BufferedImage img = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );

        //画矩形：先填充出矩形的图案，然后描绘矩形的边框
        Graphics g = img.getGraphics(); //要在此img上作画，先拿到此img图像的画笔！
        g.setColor( Color.PINK ); //设置画笔颜色
        g.fillRect( 0,0,width,height ); //填充矩形区域的颜色

        g.setColor( Color.BLACK );
        g.drawRect( 0,0,width-1,height-1 ); //画边框

        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; //待随机抽取的字符串
        Random rand = new Random();

        for( int i=0; i<4; i++ ){
            int index = rand.nextInt( str.length() );
            char ch = str.charAt( index );
            g.drawString( ch+"", width/5*i, height/2 );
        }

        g.setColor( Color.GREEN ); //更改画笔颜色

        for( int i=1; i<=10; i++ ){
            int x1 = rand.nextInt( width );
            int x2 = rand.nextInt( width );
            int y1 = rand.nextInt( height );
            int y2 = rand.nextInt( height );

            g.drawLine( x1, y1, x2, y2 );
        }


        ImageIO.write( img, "jpg", response.getOutputStream() );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost( request, response );
    }
}