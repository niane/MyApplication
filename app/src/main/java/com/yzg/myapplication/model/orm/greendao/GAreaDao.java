package com.yzg.myapplication.model.orm.greendao;

import com.yzg.myapplication.model.bean.AreaBean;
import com.yzg.myapplication.model.bean.AreaBeanDao;
import com.yzg.myapplication.model.orm.AreaDao;

import java.util.List;

/**
 * Created by yzg on 2017/5/25.
 */

public class GAreaDao implements AreaDao {
    private GDataBaseHelper gDataBaseHelper;

    public GAreaDao(GDataBaseHelper gDataBaseHelper){
        this.gDataBaseHelper = gDataBaseHelper;
    }

    @Override
    public List<AreaBean> listByLevel(String level) {
        return gDataBaseHelper.getReadableSession()
                        .getAreaBeanDao()
                        .queryBuilder()
                        .where(AreaBeanDao.Properties.Level.eq(level))
                        .list();
    }
}
