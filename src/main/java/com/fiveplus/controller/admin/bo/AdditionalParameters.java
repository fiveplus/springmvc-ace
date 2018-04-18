package com.fiveplus.controller.admin.bo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能：子节点信息
 * @author fiveplus
 *
 */
public class AdditionalParameters implements Serializable{
	/**
	 * 子节点列表
	 */
	private List<Item> children = new ArrayList<Item>();
	/**
	 * 节点的Id
	 */
	private String id;
	/**
	 * 是否选中
	 */
	@JsonProperty( "item-selected" ) 
	private boolean itemSeleted;
	public List<Item> getChildren() {
		return children;
	}
	public void setChildren(List<Item> children) {
		this.children = children;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isItemSeleted() {
		return itemSeleted;
	}
	public void setItemSeleted(boolean itemSeleted) {
		this.itemSeleted = itemSeleted;
	}
	
}
