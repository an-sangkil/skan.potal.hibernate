package com.dongbu.potal.web.potal.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dongbu.potal.common.util.pagnation.PagingList;
import com.dongbu.potal.web.potal.bean.Schdule;
import com.dongbu.potal.web.potal.common.dao.AbstractCommonDao;

/**
 * SAMPLE
 * @author ask
 *
 */
@Repository
public class SchduleDao extends AbstractCommonDao {
	
	private final String NAMESPACE = this.getClass().getPackage().getName();
	
	@Override
	public int insert(Object obj, Map<String, Object> dataMap) throws Exception {
		
		dataMap.put("", "");
		return super.getSqlSession().insert(NAMESPACE + ".SchduleDao.insert" , dataMap );
	}

	@Override
	public int update(Object obj, Map<String, Object> dataMap) throws Exception {
		
		dataMap.put("", "");
		
		return super.getSqlSession().update(NAMESPACE + ".SchduleDao.update" , dataMap );
	}

	@Override
	public int delete(Object obj, Map<String, Object> dataMap) throws Exception {
		
		dataMap.put("", "");
		
		return super.getSqlSession().delete(NAMESPACE + ".SchduleDao.delete" , dataMap );
	}

	@Override
	public Class<?> select(Object obj, Map<String, Object> dataMap)
			throws Exception {
		
		dataMap.put("", "");
		
		return super.getSqlSession().selectOne(NAMESPACE + ".SchduleDao.select" , dataMap );
	}

	@Override
	public List<? extends Map<?,?>> selectList(Object obj, Class<?> classes, Map<String, Object> dataMap) throws Exception {
		
		Map<String,Object> searchMap =  new HashMap<String, Object>();
		searchMap.putAll(dataMap);
		
		if(classes.isInstance(obj)){
			System.out.println(classes.newInstance());
		}
		
		return super.getSqlSession().selectList(NAMESPACE + ".SchduleDao.selectList" , searchMap );
	}
	
	public PagingList selectPagingList(Map<String,Object> dataMap){
		
		return super.pagingList(dataMap, "selectPagingList");
	}
}
