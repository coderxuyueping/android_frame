package com.example.admin.model.db;

import android.content.Context;

import com.example.admin.model.db.gen.DaoMaster;
import com.example.admin.model.db.gen.UserDao;
import com.github.yuweiguocn.library.greendao.MigrationHelper;

import org.greenrobot.greendao.database.Database;


/**
 * Created by xyp on 2018/3/15.
 */

public class MySQLiteOpenHelper extends DaoMaster.DevOpenHelper {
    private static final String DB_NAME = "user.db";

    public MySQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.DEBUG = true;
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {

            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        }, UserDao.class);//UserDao是数据库实体类自动生成得，这里需要把所有的都加上
    }
}
