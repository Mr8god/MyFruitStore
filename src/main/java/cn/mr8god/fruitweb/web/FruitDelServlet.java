package cn.mr8god.fruitweb.web;

import cn.mr8god.fruitweb.util.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Mr8god
 * @date 2020年11月21日09:25:47
 * @time 2020年11月21日09:25:51
 */
@WebServlet("/fruitDel")
public class FruitDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Connection connection = null;
        Statement statement = null;

        try {
            connection = JdbcUtil.getconnection();
            String sql = "delete from fruitstore where id=" + id;
            System.out.println(sql);
            statement = connection.createStatement();
            int ret = statement.executeUpdate(sql);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.free(statement, connection);
        }
        resp.sendRedirect("fruitList");
    }
}
