package cn.mr8god.fruitweb.service;

import cn.mr8god.fruitweb.model.Fruit;

import java.util.List;

/**
 * @author Mr8god
 * @date 2020年11月24日02:33:45
 * @time 2020年11月24日02:33:47
 */
public interface FruitService {

    /**
     * save the fruit
     * @param fruit just my fruit instance
     */
    boolean saveFruit(Fruit fruit);

    /**
     * @param id just the id of fruit
     */
    void delFruitById(int id);

    /**
     * @param id just the id of the fruit
     * @return the fruit
     */
    Fruit findFruitById(int id);


    /**
     * @param fruit just the fruit
     * @return if the result of the update for fruit
     */
    boolean updateFruit(Fruit fruit);

    /**
     * @return the list of the fruit
     */
    List<Fruit> findAllFruits();
}
