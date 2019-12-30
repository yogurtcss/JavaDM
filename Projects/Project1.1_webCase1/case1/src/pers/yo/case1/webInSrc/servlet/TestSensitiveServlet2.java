package pers.yo.case1.webInSrc.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/testSensitiveServlet2") //为 注解的servlet模板 修改的代码，加个斜杠。servlet名字首字母小写嗷！
public class TestSensitiveServlet2 extends HttpServlet {
    private List<String> sensitiveList = new ArrayList<>(); //敏感词汇集合
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            ServletContext servletContext = this.getServletContext();

            //我佛了，是我的路径写错了……正确的路径是 "/WEB-INF/classes/pers/yo/case1/props/sensitiveWords.txt"
            String realPath = servletContext.getRealPath( "/WEB-INF/classes/pers/yo/case1/props/sensitiveWords.txt" );
            //---------读取文件。  ▲ 注意：原本的敏感词sensitiveWords.txt 需【事先】以UTF-8编码格式保存！！
            /* InputStreamReader isr_utf8 = new InputStreamReader( new FileInputStream(readPath), "UTF-8" );
             * 注意，第二个参数字符集 "UTF-8"可以替换为 变量StandardCharsets.UTF_8
             *  */
            InputStreamReader isr_utf8 = new InputStreamReader( new FileInputStream(realPath), StandardCharsets.UTF_8 );
            //因为原输入文件是UTF-8编码，不能直接用 FileReader读入！！
            // BufferedReader br = new BufferedReader( new FileReader(realPath) );
            BufferedReader br = new BufferedReader( isr_utf8 );
            //---------将文件中的每一行数据添加到list中
            String line = null;
            while( (line=br.readLine())!=null ){ //未读到文件末尾
                sensitiveList.add(line); //私有变量sensitiveList只允许在本类中可任意调用嗷！
            }
            br.close(); //释放资源
            System.out.println( sensitiveList );

        }catch( Exception e ){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response); //为 注解的servlet模板 新增的代码
    }
}
