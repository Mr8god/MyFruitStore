package cn.mr8god.fruitweb.web;

import cn.mr8god.fruitweb.model.Fruit;
import cn.mr8god.fruitweb.service.FruitService;
import cn.mr8god.fruitweb.service.impl.FruitServiceImpl;
import cn.mr8god.fruitweb.util.JdbcUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr8god
 * @date 2020年11月20日15:58:22
 * @time 2020年11月20日15:58:14
 */
@WebServlet("/fruitList")
public class FruitListServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        FruitService fruitService = applicationContext.getBean("fruitService", FruitService.class);

        List<Fruit> fruits = fruitService.findAllFruits();
        req.setAttribute("fruits", fruits);
        req.getRequestDispatcher("fruitList.jsp").forward(req, resp);

    }
}
