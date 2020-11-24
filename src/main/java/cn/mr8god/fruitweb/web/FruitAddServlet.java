package cn.mr8god.fruitweb.web;

import cn.mr8god.fruitweb.model.Fruit;
import cn.mr8god.fruitweb.util.JdbcUtil;
import lombok.SneakyThrows;
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
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String name = req.getParameter("name");
//        double price = Double.parseDouble(req.getParameter("price"));
//        int num = Integer.parseInt(req.getParameter("num"));
//        String remark = req.getParameter("remark");
        Fruit fruit=new Fruit();
        BeanUtils.populate(fruit, req.getParameterMap());

        Connection connection = null;
//        Statement statement = null;
        PreparedStatement statement = null;
        try {
            connection = JdbcUtil.getconnection();
//            String sql = "insert into fruitstore values(default, '"+name+"', "+price+", "+num+", '"+remark+"')";
//            statement = connection.createStatement();
//            int ret = statement.executeUpdate(sql);
            String sql = "insert into fruitstore values(default, ?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, fruit.getName());
            statement.setDouble(2, fruit.getPrice());
            statement.setInt(3, fruit.getNum());
            statement.setString(4, fruit.getRemark());
            statement.executeUpdate();
            resp.sendRedirect("fruitList");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            req.getRequestDispatcher("fruitAdd.jsp").forward(req,resp);
        } finally {
            JdbcUtil.free(statement, connection);
        }
    }
}
