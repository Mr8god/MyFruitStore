package cn.mr8god.fruitweb.dao.impl;

import cn.mr8god.fruitweb.dao.FruitDao;
import cn.mr8god.fruitweb.model.Fruit;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Mr8god
 * @date 2020年11月24日02:32:52
 * @time 2020年11月24日02:32:54
 */
public class FruitDaoSpringJdbcImpl implements FruitDao {
    private final JdbcTemplate jdbcTemplate;

    public FruitDaoSpringJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean save(Fruit fruit) {
        try {
            this.jdbcTemplate.update("insert into fruitstore values(default, ?,?,?,?)", fruit.getName(), fruit.getPrice(), fruit.getNum(), fruit.getRemark());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void del(int id) {
        this.jdbcTemplate.update("delete from fruitstore where id=?", id);

    }

    @Override
    public Fruit findById(int id) {
        Fruit fruit = this.jdbcTemplate.queryForObject("select * from fruitstore where id=?", new Object[]{id}, new RowMapper<Fruit>() {
            @Override
            public Fruit mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Fruit fruit = new Fruit();
                fruit.setId(resultSet.getInt(1));
                fruit.setName(resultSet.getString("name"));
                fruit.setPrice(resultSet.getDouble("price"));
                fruit.setNum(resultSet.getInt("num"));
                fruit.setRemark(resultSet.getString("remark"));
                return fruit;
            }
        });
        return fruit;
    }

    @Override
    public boolean ifUpdateFruit(Fruit fruit) {
        try {
            this.jdbcTemplate.update("update fruitstore set name=?, price=?, num=?, remark=? where id=?", fruit.getName(), fruit.getPrice(), fruit.getNum(), fruit.getRemark(), fruit.getId());
            return true;
//26:37
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Fruit> findAll() {
        return this.jdbcTemplate.query("Select * from fruitstore", new RowMapper<Fruit>() {
            @Override
            public Fruit mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Fruit fruit = new Fruit();
                fruit.setId(resultSet.getInt(1));
                fruit.setName(resultSet.getString("name"));
                fruit.setPrice(resultSet.getDouble("price"));
                fruit.setNum(resultSet.getInt("num"));
                fruit.setRemark(resultSet.getString("remark"));
                return fruit;
            }
        });
    }
}
