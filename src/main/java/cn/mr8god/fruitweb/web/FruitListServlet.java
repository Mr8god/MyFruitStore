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
        Statement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtil.getconnection();

            String sql = "Select * from fruitstore";

            statement = connection.createStatement();

            resultSet = statement.executeQuery(sql);

            List<Fruit> fruits;
            fruits = new ArrayList<>();
            while (resultSet.next()) {
                Fruit fruit = new Fruit();
                fruit.setId(resultSet.getInt(1));
                fruit.setName(resultSet.getString("name"));
                fruit.setPrice(resultSet.getDouble("price"));
                fruit.setNum(resultSet.getInt("num"));
                fruit.setRemark(resultSet.getString("remark"));
                fruits.add(fruit);
            }
            req.setAttribute("fruits", fruits);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.free(resultSet, statement, connection);
        }

        req.getRequestDispatcher("fruitList.jsp").forward(req,resp);
    }
}
