package cn.mr8god.fruitweb.service.impl;

import cn.mr8god.fruitweb.dao.FruitDao;
import cn.mr8god.fruitweb.model.Fruit;
import cn.mr8god.fruitweb.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr8god
 * @date 2020年11月24日02:40:48
 * @time 2020年11月24日02:40:50
 */
@Service
public class FruitServiceImpl implements FruitService {
    private FruitDao fruitDao;
    @Autowired
    public void setFruitDao(FruitDao fruitDao) {
        System.out.println("helloworld");
        this.fruitDao = fruitDao;
    }
    @Override
    public boolean saveFruit(Fruit fruit) {
        return fruitDao.save(fruit);
    }

    @Override
    public void delFruitById(int id) {
        fruitDao.del(id);
    }

    @Override
    public Fruit findFruitById(int id) {
        return fruitDao.findById(id);
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        return fruitDao.ifUpdateFruit(fruit);
    }

    @Override
    public List<Fruit> findAllFruits() {
        return fruitDao.findAll();
    }
}
