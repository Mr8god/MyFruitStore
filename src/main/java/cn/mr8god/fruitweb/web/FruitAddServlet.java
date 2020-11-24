package cn.mr8god.fruitweb.web;

import cn.mr8god.fruitweb.model.Fruit;
import cn.mr8god.fruitweb.util.JdbcUtil;
import org.apache.commons.beanutils.BeanUtils;

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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int num = Integer.parseInt(req.getParameter("num"));
        String remark = req.getParameter("remark");

        Connection connection = null;
        Statement statement = null;

        try {
            connection = JdbcUtil.getconnection();
            String sql = "insert into fruitstore values(default, '"+name+"', "+price+", "+num+", '"+remark+"')";
            statement = connection.createStatement();
            int ret = statement.executeUpdate(sql);
            resp.sendRedirect("fruitList");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            req.getRequestDispatcher("fruitAdd.jsp").forward(req,resp);
        } finally {
            JdbcUtil.free(statement, connection);
        }
    }
}
