package cn.mr8god.fruitweb.dao.impl;

import cn.mr8god.fruitweb.dao.FruitDao;
import cn.mr8god.fruitweb.model.Fruit;
import cn.mr8god.fruitweb.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr8god
 * @date 2020年11月24日02:32:52
 * @time 2020年11月24日02:32:54
 */
public class FruitDaoJdbcImpl implements FruitDao {

    @Override
    public boolean save(Fruit fruit) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JdbcUtil.getconnection();
            String sql = "insert into fruitstore values(default, ?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, fruit.getName());
            statement.setDouble(2, fruit.getPrice());
            statement.setInt(3, fruit.getNum());
            statement.setString(4, fruit.getRemark());
            statement.executeUpdate();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            JdbcUtil.free(statement, connection);
        }
    }

    @Override
    public void del(int id) {
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
    }

    @Override
    public Fruit findById(int id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
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
                return fruit;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.free(statement, connection);
        }
        return null;
    }

    @Override
    public boolean ifUpdateFruit(Fruit fruit) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = JdbcUtil.getconnection();
            String sql = "update fruitstore set name='"+fruit.getName()+"',price="+fruit.getPrice()+",num="+fruit.getNum()+", remark='"+fruit.getRemark()+"' where id=" + fruit.getId();
            statement = connection.createStatement();
            int ret = statement.executeUpdate(sql);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            JdbcUtil.free(statement, connection);
        }

    }

    @Override
    public List<Fruit> findAll() {
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
            return fruits;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.free(resultSet, statement, connection);
        }
        return null;
    }
}
