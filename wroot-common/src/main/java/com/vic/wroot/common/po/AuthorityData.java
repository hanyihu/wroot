package com.vic.wroot.common.po;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 构建权限的简单数据
 * @author VIC
 *
 */
public class AuthorityData {

	/**
	 * 需要新增的id集合
	 */
	private List<Integer> needAdd = new ArrayList<Integer>();
	
	/**
	 * 需要删除的id集合
	 */
	private List<Integer> needDelete = new ArrayList<Integer>();
	
	/**
	 * 
	 * @param olds 原来的数据库中的IDS
	 * @param news 现在的IDS
	 * @return
	 */
	public static AuthorityData instance(List<Integer> olds, List<Integer> nows){
		Map<Integer, Integer> oldMap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> nowMap = new HashMap<Integer, Integer>();
		for(Integer old : olds) {
			oldMap.put(old, old);
		}
		for(Integer now : nows) {
			nowMap.put(now, now);
		}
		List<Integer> adds = new ArrayList<Integer>();
		List<Integer> dels = new ArrayList<Integer>();
		for(Integer key : oldMap.keySet()){
			if(nowMap.get(key) == null) {//当前的IDS中没有 则表示应该删除
				dels.add(key);
			}
		}
		
		for(Integer key : nowMap.keySet()){
			if(oldMap.get(key) == null) {//原来的的IDS中没有 则表示应该新增
				adds.add(key);
			}
		}
		
		return new AuthorityData(adds, dels);
	}
	
	
	private AuthorityData(){}
	
	

	private AuthorityData(List<Integer> needAdd, List<Integer> needDelete) {
		this.needAdd = needAdd;
		this.needDelete = needDelete;
	}


	public List<Integer> getNeedAdd() {
		return needAdd;
	}

	public void setNeedAdd(List<Integer> needAdd) {
		this.needAdd = needAdd;
	}

	public List<Integer> getNeedDelete() {
		return needDelete;
	}

	public void setNeedDelete(List<Integer> needDelete) {
		this.needDelete = needDelete;
	}


	@Override
	public String toString() {
		return "AuthorityData [needAdd=" + needAdd + ", needDelete=" + needDelete  + "]";
	}
	
	
	public static void main(String[] args) {
		Integer [] old = {1,2,3,4};
		Integer[] now = {3,4,5,6};
		AuthorityData a = AuthorityData.instance(Arrays.asList(old), Arrays.asList(now));
		System.out.println(a);
	}


	public Integer[] getNeedDeleteArr() {
		return needDelete == null ? new Integer[] {} : needDelete.toArray(new Integer[0]);
	}


	public Integer[] getNeedAddArr() {
		return needAdd == null ? new Integer[] {} : needAdd.toArray(new Integer[0]);
	}
	
}
