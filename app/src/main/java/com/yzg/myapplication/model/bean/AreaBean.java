package com.yzg.myapplication.model.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by yzg on 2017/3/13.
 */

@Entity(nameInDb = "tab_area", createInDb = false)
@DatabaseTable(tableName = "tab_area")
public class AreaBean {
    @Id
    @DatabaseField
    private String id;
    @DatabaseField
    private String code;
    @DatabaseField
    private String name;
    @DatabaseField
    private String parent_id;
    @DatabaseField
    private String first_letter;
    @DatabaseField
    private String level;

    @Generated(hash = 1318666025)
    public AreaBean(String id, String code, String name, String parent_id,
            String first_letter, String level) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.parent_id = parent_id;
        this.first_letter = first_letter;
        this.level = level;
    }

    @Generated(hash = 1823161578)
    public AreaBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getFirst_letter() {
        return first_letter;
    }

    public void setFirst_letter(String first_letter) {
        this.first_letter = first_letter;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
