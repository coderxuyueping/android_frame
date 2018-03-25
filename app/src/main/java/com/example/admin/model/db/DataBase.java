package com.example.admin.model.db;

import com.example.admin.base.AppStatus;
import com.example.admin.model.bean.User;
import com.example.admin.model.db.gen.DaoMaster;
import com.example.admin.model.db.gen.DaoSession;
import com.example.admin.model.db.gen.UserDao;

import java.util.List;


/**
 * Created by xyp on 2018/3/15.
 */

public class DataBase {
    private static DataBase dataBase;
    private DaoSession daoSession;

    public static DataBase getDataBase() {
        if (dataBase == null) {
            synchronized (DataBase.class) {
                if (dataBase == null) {
                    dataBase = new DataBase();
                }
            }
        }
        return dataBase;
    }

    private DaoSession getDaoSession() {
        if (daoSession == null) {
            DaoMaster.DevOpenHelper helper = new MySQLiteOpenHelper(AppStatus.getInstance());
            DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    /**
     * 增
     */
    public <T> long insert(T t) {
        return getDaoSession().insert(t);
    }

    /**
     * 删
     */
    public <T> void delete(T t) {
        getDaoSession().delete(t);
    }

    public void delete(int age){
        String sql = "delete from " + UserDao.TABLENAME +
                " where age=" + age;
        getDaoSession().getDatabase().execSQL(sql);
    }

    /**
     * 改
     */
    public <T> void update(T t) {
        getDaoSession().update(t);
    }

    public void update(int age, String update){
        User user = getDaoSession().queryBuilder(User.class)
                .where(UserDao.Properties.Age.eq(age))
                .limit(1)
                .unique();
        user.setName(update);
        getDaoSession().update(user);
    }
    /**
     * 查
     */
    public void query() {
        List<User> list = getDaoSession().queryBuilder(User.class).list();
    }

}
