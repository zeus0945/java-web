package com.tedu.dao;
/*
 * dao:data access object 数据访问对象
 * 表示连接并访问数据的一层
 * EmpMapper.xml文件的接口
 * com.tedu.dao.EmpMapper
 */

import java.util.List;

import com.tedu.pojo.Emp;

public interface EmpMapper {
	 // 在Empmapper.xml中的一条SQL语句要对应接口中的一个方法
	//1.查询所有员工信息
	public List<Emp> findAll();
	
	//2.新增一个员工信息
	public int addEmp(Emp emp);
	//3.修改员工信息
	public int updateById2(Emp emp);
	public int deleteById(Integer id);
}
