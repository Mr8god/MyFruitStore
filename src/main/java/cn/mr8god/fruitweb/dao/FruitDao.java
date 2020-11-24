package cn.mr8god.fruitweb.dao;

import cn.mr8god.fruitweb.model.Fruit;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Mr8god
 * @date 2020年11月24日02:32:06
 * @time 2020年11月24日02:32:08
 */
public interface FruitDao {

    @Insert("insert into fruitstore values(default, #{name}, #{price}, #{num}, #{remark})")
    boolean save(Fruit fruit);

    @Delete("delete from fruitstore where id=#{id}")
    void del(int id);

    @Select("select * from fruitstore where id=#{id}")
    Fruit findById(int id);

    @Update("update fruitstore set name=#{name}, price=#{price},num=#{num}, remark=#{remark} where id=#{id}")
    boolean ifUpdateFruit(Fruit fruit);

    @Select("Select * from fruitstore")
    List<Fruit> findAll();
}
