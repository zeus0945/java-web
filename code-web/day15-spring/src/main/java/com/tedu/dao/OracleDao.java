package com.tedu.dao;
/*+
 * Dao层实现类
 * 用于访问mysql数据库
 */
public class OracleDao implements Dao{

	@Override
	public void findAll() {
		System.out.println("Oracl:查询到了所有员工信息 ...");
	}

}
