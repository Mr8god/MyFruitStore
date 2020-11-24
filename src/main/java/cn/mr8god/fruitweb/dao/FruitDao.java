package cn.mr8god.fruitweb.dao;

import cn.mr8god.fruitweb.model.Fruit;

import java.util.List;

/**
 * @author Mr8god
 * @date 2020年11月24日02:32:06
 * @time 2020年11月24日02:32:08
 */
public interface FruitDao {

    boolean save(Fruit fruit);

    void del(int id);

    Fruit findById(int id);

    boolean ifUpdateFruit(Fruit fruit);

    List<Fruit> findAll();
}
