package com.example.lily.kaoshi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*
 * DAO:Data Access Object,数据访问对象
 * 封装的一个类，用来执行对数据库的操作。将SQLiteDataBase和SQLiteOpenHelper都封装到这个
 * 类中，在其他的类中只需要定义DAO类的对象，利用对象调用相应的方法就可以执行数据库的增、删、改、查操作。
 * 封装操作数据库的crud的方法
 */
public class PersonDAO {

	private SQLiteDatabase db;

	// 获得SQLiteDatabase数据库对象
	public PersonDAO(Context context) {
		DBHelper helper = new DBHelper(context);
		db = helper.getWritableDatabase();
		
	}

	// 执行插入操作
	public long insert(String table, ContentValues values) {
		// String sql = "insert into user(name,age) values('tom',20)";
		// db.execSQL(sql);
		/*
		 * 
		 * db.insert(table, nullColumnHack, values)
		 * 1 table 表名
		 * 2 nullColumnHack,
		 *      设置为null时   不允许插入一个完全空的行，没有定义至少一个字段
		 *      如果提供的values为空，没有指定列名，一个空行不允许被插入 。insert into name() values()不允许执行
		 *      如果不设置为null，nullColumnHack 参数提供一个可以为空的列名 当值为空时插入一个null值
		 * 3 values,是ContentValues对象
		 * 返回值为long类型，执行成功返回行号，否则返回-1
		 */
		return db.insert(table, null, values);
	}

	// 修改语句
	public int update(String table, ContentValues values, String whereClause,
			String[] whereArgs) {
		// String sql="update user set name='kate',age=22 where _id=？";
		/*
		 * table,表名 values，封装修改之后的键值对 whereClause where子句
		 * whereArgs，字符串数组，给where子句的占位符赋值 返回值为影响表的行数
		 */
		return db.update(table, values, whereClause, whereArgs);
	}

	// 执行删除语句
	public int delete(String table, String whereClause, String[] whereArgs) {
		return db.delete(table, whereClause, whereArgs);
	}

	
	/*
	 * sql,查询语句，条件可以有占位符 selectionArgs，字符串数组，给占位符赋值
	 */
	public Cursor select(String sql, String[] selectionArgs) {
		return db.rawQuery(sql, selectionArgs);
	}

	
	public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
		//columns,字符串数组，指定查询的列名，如果查询全部字段，可以传null
		return db.query(table, columns, selection, selectionArgs, groupBy,
				having, orderBy);
	}
}
