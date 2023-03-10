package ctrl.server;

import ctrl.dao.StationDao;
import ctrl.factory.AdminFactory;
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

        StationDao stationCtrl = AdminFactory.instance().getStationCtrl();
        stationCtrl.updateStation(newStation);

        String path = contextPath + "/admin/operation.jsp?msg=succeed";
        if(newStation != null){
            request.getSession().setAttribute("station",newStation);
        }else{
            path = contextPath + "/admin/operation.jsp?msg=err";
        }

        response.sendRedirect(path);
    }

    //  ?????????????????????????????????
    private void doGetStationById (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int stationId = Integer.getInteger(request.getParameter("stationId"));
        StationDao stationCtrl = AdminFactory.instance().getStationCtrl();
        String path = contextPath + "/getInfo.jsp?type=stations";
        Station station = stationCtrl.getStationById(stationId);

        request.setAttribute("station",station);

        request.getRequestDispatcher(path).forward(request,response);
    }

    //  ???????????????
    private void doGetAllStations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        StationDao stationCtrl = AdminFactory.instance().getStationCtrl();
        //  ?????????????????????????????? ????????????
        List<Station> stations = new ArrayList<>();
        //  ???????????????????????? stations
        stations = stationCtrl.getAllStations();
        //  ???????????????????????????
        int stationNum = stations.size();

        System.out.println("??????????????????"+stationNum);

        String path = null;

        if (stationNum != 0){
            path = "/getInfo.jsp?type=stations";
            request.setAttribute("stations",stations);
        }else{
            path = "/getInfo.jsp?msg=nothing";
        }

        //  ????????????
        request.getRequestDispatcher(path).forward(request,response);
    }

    //  ????????????
    private void doDeleteStationById(HttpServletRequest request, HttpServletResponse response) throws IOException{

        int stationId = Integer.parseInt(request.getParameter("stationId"));
        System.out.println("????????????????????????"+stationId);
        StationDao stationCtrl = AdminFactory.instance().getStationCtrl();
        boolean deleted = stationCtrl.deleteStationById(stationId);

        String path = contextPath + "/admin/operation.jsp?msg=succeed";

        if(!deleted){
            path = contextPath + "/admin/operation.jsp?msg=fail";
        }

        //  ????????????URL???????????????????????????????????????
        response.sendRedirect(path);
    }

    private void doRegisterStation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Station station = new Station();

        String stationName = request.getParameter("stationName");
        String stationIntro = request.getParameter("stationIntro");

        station = new Station(0,stationName,stationIntro);

        StationDao stationCtrl = AdminFactory.instance().getStationCtrl();
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
