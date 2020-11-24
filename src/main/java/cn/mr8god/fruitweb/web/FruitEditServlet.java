package cn.mr8god.fruitweb.web;

import cn.mr8god.fruitweb.model.Fruit;
import cn.mr8god.fruitweb.util.JdbcUtil;

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

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet =null;
        try {
            connection = JdbcUtil.getconnection();
            String sql = "select * from fruitstore where id=" + id;

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                Fruit fruit = new Fruit();
                fruit.setId(resultSet.getInt(1));
                fruit.setName(resultSet.getString("name"));
                fruit.setPrice(resultSet.getDouble("price"));
                fruit.setNum(resultSet.getInt("num"));
                fruit.setRemark(resultSet.getString("remark"));
                req.setAttribute("fruit", fruit);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.free(statement, connection);
        }
        req.getRequestDispatcher("fruitEdit.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int num = Integer.parseInt(req.getParameter("num"));
        String remark = req.getParameter("remark");
        int id = Integer.parseInt(req.getParameter("id"));

        Connection connection = null;
        Statement statement = null;

        try {
            connection = JdbcUtil.getconnection();
            String sql = "update fruitstore set name='"+name+"',price="+price+",num="+num+", remark='"+remark+"' where id=" + id;
            statement = connection.createStatement();
            int ret = statement.executeUpdate(sql);
            resp.sendRedirect("fruitList");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            req.getRequestDispatcher("fruitEdit.jsp").forward(req,resp);
        } finally {
            JdbcUtil.free(statement, connection);
        }
    }
}
