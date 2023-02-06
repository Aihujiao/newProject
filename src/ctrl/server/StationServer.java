package ctrl.server;

import ctrl.factory.StationFactory;
import ctrl.dao.StationDao;
import model.Station;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/StationServer")
public class StationServer extends HttpServlet {
    String contextPath = null;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        contextPath = request.getContextPath();
        String op = request.getParameter("op");
        if(op.equals("stationUpdate")){
            this.doUpdateStation(request,response);
        } else if (op.equals("getStationById")) {
            this.doGetStationById(request, response);
        } else if (op.equals("getAllStations")) {
            this.doGetAllStations(request, response);
        } else if (op.equals("stationDeleteById")) {
            this.doDeleteStationById(request, response);
        } else if (op.equals("stationRegister")) {
            this.doRegisterStation(request, response);
        }
    }
    
    private void doUpdateStation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Station newStation = null;
        int stationId = Integer.parseInt(request.getParameter("stationId"));
        String newName = request.getParameter("newName");
        String newIntro = request.getParameter("newIntro");

        newStation = new Station(stationId,newName,newIntro);

        StationDao stationCtrl = StationFactory.instance().getStationCtrl();
        stationCtrl.updateStation(newStation);

        String path = contextPath + "/admin/operation.jsp?msg=succeed";
        if(newStation != null){
            request.getSession().setAttribute("station",newStation);
        }else{
            path = contextPath + "/admin/operation.jsp?msg=err";
        }

        response.sendRedirect(path);
    }

    //  该方法可能存在逻辑问题
    private void doGetStationById (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int stationId = Integer.getInteger(request.getParameter("stationId"));
        StationDao stationCtrl = StationFactory.instance().getStationCtrl();
        String path = contextPath + "/getInfo.jsp?type=stations";
        Station station = stationCtrl.getStationById(stationId);

        request.setAttribute("station",station);

        request.getRequestDispatcher(path).forward(request,response);
    }

    //  管理员操作
    private void doGetAllStations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        StationDao stationCtrl = StationFactory.instance().getStationCtrl();
        //  创建数组集合用于存储 员工数据
        List<Station> stations = new ArrayList<>();
        //  将员工集合赋值给 stations
        stations = stationCtrl.getAllStations();
        //  判断集合中元素个数
        int stationNum = stations.size();

        System.out.println("所有状态数为"+stationNum);

        String path = null;

        if (stationNum != 0){
            path = "/getInfo.jsp?type=stations";
            request.setAttribute("stations",stations);
        }else{
            path = "/getInfo.jsp?msg=nothing";
        }

        //  带参跳转
        request.getRequestDispatcher(path).forward(request,response);
    }

    //  员工操作
    private void doDeleteStationById(HttpServletRequest request, HttpServletResponse response) throws IOException{

        int stationId = Integer.parseInt(request.getParameter("stationId"));
        System.out.println("得到的状态编号是"+stationId);
        StationDao stationCtrl = StationFactory.instance().getStationCtrl();
        boolean deleted = stationCtrl.deleteStationById(stationId);

        String path = contextPath + "/admin/operation.jsp?msg=succeed";

        if(!deleted){
            path = contextPath + "/admin/operation.jsp?msg=fail";
        }

        //  直接使用URL传参到新页面使页面进行判断
        response.sendRedirect(path);
    }

    private void doRegisterStation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Station station = new Station();

        String stationName = request.getParameter("stationName");
        String stationIntro = request.getParameter("stationIntro");

        station = new Station(0,stationName,stationIntro);

        StationDao stationCtrl = StationFactory.instance().getStationCtrl();
        boolean registered = stationCtrl.registerStation(station);

        String path = "/admin/operation.jsp?msg=succeed";
        if (registered){
            request.setAttribute("station",station);
        }else {
            path = "/admin/operation.jsp?msg=fail";
        }

        request.getRequestDispatcher(path).forward(request,response);
    }
}
