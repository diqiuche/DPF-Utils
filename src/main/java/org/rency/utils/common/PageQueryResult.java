/**
 * 
 */
package org.rency.utils.common;

import java.io.Serializable;
import java.util.List;

/**
 * @desc 分页查询结果
 * @author T-rency
 * @param <T>
 * @date 2014年11月18日 下午3:38:52
 */
public class PageQueryResult<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2224169418188346059L;
	private int totalCount;
	private int pageSize;
	private int pageNo;
	private List<T> list;
	
	public PageQueryResult(){
		this.totalCount = 0;
		this.pageNo = 0;
		this.pageSize = 0;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}	

}
