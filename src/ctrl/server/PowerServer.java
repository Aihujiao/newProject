package ctrl.server;

import ctrl.factory.PowerFactory;
import ctrl.dao.PowerDao;
import model.Power;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/PowerServer")
public class PowerServer extends HttpServlet {
    String contextPath = null;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        contextPath = request.getContextPath();
        String op = request.getParameter("op");
        if(op.equals("powerUpdate")){
            this.doUpdatePower(request,response);
        } else if (op.equals("getPowerById")) {
            this.doGetPowerById(request, response);
        } else if (op.equals("getAllPowers")) {
            this.doGetAllPowers(request, response);
        } else if (op.equals("powerDeleteById")) {
            this.doDeletePowerById(request, response);
        } else if (op.equals("powerRegister")) {
            this.doRegisterPower(request, response);
        } else if (op.equals("toPowerRegister")) {
            this.toRegisterPower(request, response);
        }
    }
    
    private void doUpdatePower(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Power newPower = null;
        int powerId = Integer.parseInt(request.getParameter("powerId"));
        String newName = request.getParameter("newName");
        int newLevel = Integer.parseInt(request.getParameter("newPassword"));
        String newIntro = request.getParameter("newGender");

        newPower = new Power(powerId,newName,newLevel,newIntro);

        PowerDao powerCtrl = PowerFactory.instance().getPowerCtrl();
        powerCtrl.updatePower(newPower);

        String path = contextPath + "/power/operation.jsp?msg=succeed";
        if(newPower != null){
            request.getSession().setAttribute("power",newPower);
        }else{
            path = contextPath + "/power/operation.jsp?msg=err";
        }

        response.sendRedirect(path);
    }

    //  该方法可能存在逻辑问题
    private void doGetPowerById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int powerId = Integer.getInteger(request.getParameter("powerId"));

        PowerDao powerCtrl = PowerFactory.instance().getPowerCtrl();
        Power power = powerCtrl.getPowerById(powerId);

        String path = contextPath+"/getInfo.jsp?type=power";

        request.setAttribute("power",power);

        request.getRequestDispatcher(path).forward(request,response);
    }

    //  管理员操作
    private void doGetAllPowers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PowerDao powerCtrl = PowerFactory.instance().getPowerCtrl();
        //  创建数组集合用于存储 权限数据
        List<Power> powers = new ArrayList<>();
        //  将权限集合赋值给 powers
        powers = powerCtrl.getAllPowers();
        //  判断集合中元素个数
        int powerNum = powers.size();

        String path = null;
        if (powerNum != 0){
            path = "/getInfo.jsp?type=powers";
            request.setAttribute("powers",powers);
        }else{
            path = "/getInfo.jsp?msg=nothing";
        }

        //  带参跳转
        request.getRequestDispatcher(path).forward(request,response);
    }

    //  权限操作
    private void doGetSameDepartmentPowers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }

    private void doDeletePowerById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        int powerId = Integer.parseInt(request.getParameter("powerId"));

        System.out.println(powerId);
        PowerDao powerCtrl = PowerFactory.instance().getPowerCtrl();
        boolean deleted = powerCtrl.deletePowerById(powerId);

        String path = contextPath + "/power/operation.jsp?msg=succeed";

        if(!deleted){
            path = contextPath + "/power/operation.jsp?msg=fail";
        }

        //  直接使用URL传参到新页面使页面进行判断
        response.sendRedirect(path);
    }

    private void doRegisterPower(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Power power = new Power();
        String powerName = request.getParameter("powerName");
        int powerLevel = Integer.parseInt(request.getParameter("powerLevel"));
        String powerIntro = request.getParameter("powerIntro");

        power = new Power(0,powerName,powerLevel,powerIntro);

        PowerDao powerCtrl = PowerFactory.instance().getPowerCtrl();
        boolean registered = powerCtrl.registerPower(power);

        String path = "/admin/operation.jsp?msg=succeed";
        if (registered){
            request.setAttribute("power",power);
        }else {
            path = "/admin/operation.jsp?msg=fail";
        }

        request.getRequestDispatcher(path).forward(request,response);
    }

    private void toRegisterPower(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PowerDao powerCtrl = PowerFactory.instance().getPowerCtrl();
        List<Power> powers = powerCtrl.getAllPowers();
        request.setAttribute("powers",powers);

        request.getRequestDispatcher("/admin/powerRegister.jsp").forward(request,response);
    }
}
