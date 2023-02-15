package ctrl.server;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import ctrl.dao.AdminDao;
import ctrl.factory.AdminFactory;
import model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/ProfileUpdateServer")
public class ProfileServer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("跳到了ProfileUpdateServer");
        // 实例化smartupload的工具类
        SmartUpload smartUpload = new SmartUpload();
        // 实例化（初始化）工具类
        smartUpload.initialize(getServletConfig(), request, response);
        //  在session 里拿adminId
        Admin admin = (Admin)request.getSession().getAttribute("admin");
        int adminId = admin.getAdminId();
        String adminProfile = admin.getAdminProfile();
        System.out.println("输出adminId = "+adminId);

        try {
            // 确定smartupload的工作
            smartUpload.upload();
            // 获取上传的文件(多文件)
            Files files = smartUpload.getFiles();
            //  D:\ideaW\myProject\NewProject\web\imgs
            String path = "D:\\PersonalFile\\upload\\";
            System.out.println(path);
            File file = files.getFile(0);
            String fileName = file.getFileName();
            String filePath = path + adminProfile;

            //  引入 io流 File JFile 代表要被删除的图片
            java.io.File JFile = new java.io.File(filePath);

            //  判断系统中是否存在这个文件
            if(adminProfile != null){
                boolean deleted = JFile.delete();
                if(deleted){
                    System.out.println("删除了原图片");
                }
            }

            String webPath = "/admin/operation.jsp";
            long timeCode = System.currentTimeMillis();

            //  系统文件绝对路径
            path = path + timeCode +fileName;
            //  保存到数据库文件名称
            filePath = timeCode + fileName;
            System.out.println("上传的文件全路径 "+path);
            file.saveAs(path);

            //  重新为 admin 对象赋值
            admin = new Admin();
            admin.setAdminId(adminId);
            admin.setAdminProfile(filePath);
            AdminDao adminCtrl = AdminFactory.instance().getAdminCtrl();
            boolean updated = adminCtrl.updateAdminProfile(admin);

            if(updated){
                webPath = "/adminLogin.jsp";
            }else{
                request.setAttribute("msg","fail");
            }

            System.out.println(webPath);
            request.getRequestDispatcher(webPath).forward(request,response);


        } catch (SmartUploadException e) {
            throw new RuntimeException(e);
        }
    }
}
