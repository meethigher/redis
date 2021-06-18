package demo16_redis_jedis.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo16_redis_jedis.domain.Province;
import demo16_redis_jedis.service.ProvinceService;
import demo16_redis_jedis.service.impl.ProvinceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/redis/findProvince")
public class FindProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service查询
        ProvinceService service = new ProvinceServiceImpl();
//        List<Province> list = service.findAll();
//        //2.序列化list为json
//        ObjectMapper om = new ObjectMapper();
//        String json = om.writeValueAsString(list);
//        System.out.println(json);
        //通过缓存
        String json = service.findAllJson();
        //3.响应结果
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
