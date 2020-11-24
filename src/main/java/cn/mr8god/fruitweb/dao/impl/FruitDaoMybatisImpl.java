package cn.mr8god.fruitweb.dao.impl;

import cn.mr8god.fruitweb.dao.FruitDao;
import cn.mr8god.fruitweb.model.Fruit;
import cn.mr8god.fruitweb.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author Mr8god
 * @date 2020年11月24日03:55:56
 * @time 2020年11月24日03:55:58
 */
public class FruitDaoMybatisImpl implements FruitDao {
    @Override
    public boolean save(Fruit fruit) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            FruitDao fruitDao = sqlSession.getMapper(FruitDao.class);
            boolean ret = fruitDao.save(fruit);
            sqlSession.commit();
            return ret;
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return false;
    }

    @Override
    public void del(int id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            FruitDao fruitDao = sqlSession.getMapper(FruitDao.class);
            fruitDao.del(id);
            sqlSession.commit();
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Override
    public Fruit findById(int id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            FruitDao fruitDao = sqlSession.getMapper(FruitDao.class);
            return fruitDao.findById(id);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Override
    public boolean ifUpdateFruit(Fruit fruit) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            FruitDao fruitDao = sqlSession.getMapper(FruitDao.class);
            boolean ret = fruitDao.ifUpdateFruit(fruit);
            sqlSession.commit();
            return ret;
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return false;
    }

    @Override
    public List<Fruit> findAll() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            FruitDao fruitDao = sqlSession.getMapper(FruitDao.class);
            return fruitDao.findAll();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
