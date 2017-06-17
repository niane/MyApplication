package com.yzg.myapplication.model.orm.ormlite;

import com.j256.ormlite.dao.Dao;
import com.yzg.myapplication.model.bean.AreaBean;
import com.yzg.myapplication.model.orm.AreaDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yzg on 2017/3/13.
 */

public class LAreaDao implements AreaDao{
    private Dao<AreaBean, Integer> areaDaoOpe;
    private LDataBaseHelper helper;

    public LAreaDao(LDataBaseHelper helper){
        this.helper = helper;
        try {
            areaDaoOpe = helper.getDao(AreaBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<AreaBean> listByLevel(String level){
        try {
            return areaDaoOpe.queryBuilder().where().eq("level", level).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
