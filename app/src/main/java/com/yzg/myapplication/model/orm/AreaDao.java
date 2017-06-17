package com.yzg.myapplication.model.orm;

import com.yzg.myapplication.model.bean.AreaBean;

import java.util.List;

/**
 * Created by yzg on 2017/5/25.
 */

public interface AreaDao {
    List<AreaBean> listByLevel(String level);
}
