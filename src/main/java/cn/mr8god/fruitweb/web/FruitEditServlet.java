package cn.mr8god.fruitweb.web;

import cn.mr8god.fruitweb.model.Fruit;
import cn.mr8god.fruitweb.service.FruitService;
import cn.mr8god.fruitweb.service.impl.FruitServiceImpl;
import cn.mr8god.fruitweb.util.JdbcUtil;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Mr8god
 * @date 2020年11月24日01:41:36
 * @time 2020年11月24日01:41:41
 */
@WebServlet("/fruitEdit")
public class FruitEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        FruitService fruitService = new FruitServiceImpl();
        Fruit fruit = fruitService.findFruitById(id);
        req.setAttribute("fruit", fruit);
        req.getRequestDispatcher("fruitEdit.jsp").forward(req,resp);

    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Fruit fruit=new Fruit();
        BeanUtils.populate(fruit, req.getParameterMap());

        FruitService fruitService = new FruitServiceImpl();
        boolean ret = fruitService.updateFruit(fruit);
        if (ret) {
            resp.sendRedirect("fruitList");
        } else {
            req.getRequestDispatcher("fruitEdit.jsp").forward(req,resp);
        }
    }
}
