package cn.mr8god.fruitweb.web;

import cn.mr8god.fruitweb.model.Fruit;
import cn.mr8god.fruitweb.service.FruitService;
import cn.mr8god.fruitweb.service.impl.FruitServiceImpl;
import cn.mr8god.fruitweb.util.JdbcUtil;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Mr8god
 * @date 2020年11月21日09:25:47
 * @time 2020年11月21日09:25:51
 */
@WebServlet("/fruitAdd")
public class FruitAddServlet extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Fruit fruit=new Fruit();
        BeanUtils.populate(fruit, req.getParameterMap());

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        FruitService fruitService = applicationContext.getBean("fruitService", FruitService.class);

        boolean ret = fruitService.saveFruit(fruit);
        if (ret) {
            resp.sendRedirect("fruitList");
        } else {
            req.getRequestDispatcher("fruitAdd.jsp").forward(req,resp);
        }
    }
}
