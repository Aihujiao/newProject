package ctrl.upload;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet(value = "/uploadServer")
public class uploadServer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        System.out.println("本次上传文件用户是"+type);
        String toPath = "/"+type+"Server";
        // 实例化smartupload的工具类
        SmartUpload smartUpload = new SmartUpload();
        // 实例化（初始化）工具类
        smartUpload.initialize(getServletConfig() , request , response);
        try {
            // 确定smartupload的工作
            smartUpload.upload();
            String path = "/imgs/";
            System.out.println(path);
            // 获取上传的文件(多文件)
            Files files = smartUpload.getFiles();
            // 获取上传文件的数量
            int count = files.getCount();
            for(int i = 0 ; i < count ; i++){
                File file = files.getFile(i);
                String fileName = file.getFileName();
                path = path + fileName;
                //  加入时间毫秒作为区分
//                path = path + System.currentTimeMillis() + fileName;
                file.saveAs(path);
            }

            //  不知能不能携带update.jsp的参数继续跳转
            //  不能一直传
            request.getRequestDispatcher(toPath).forward(request,response);
        } catch (SmartUploadException e) {
            throw new RuntimeException(e);
        }
    }
}
