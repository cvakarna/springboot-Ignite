package com.cva.demo.model;

import org.apache.ignite.cache.query.annotations.QuerySqlField;
import org.apache.ignite.cache.query.annotations.QueryTextField;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
	
	@JsonProperty
	@QuerySqlField(index = true)
	private String ItemId;
	@JsonProperty
	@QuerySqlField
	private String ItemType;
	
	@QueryTextField
	private String ItemInfoJson;
	@JsonProperty
	@QuerySqlField
	private String CompanyName;
	@JsonProperty
	@QuerySqlField
	private String CompanyId;
    public Item() {
		
	}
	public String getItemId() {
		return ItemId;
	}
	public void setItemId(String itemId) {
		ItemId = itemId;
	}
	public String getItemType() {
		return ItemType;
	}
	public void setItemType(String itemType) {
		ItemType = itemType;
	}
	public String getItemInfoJson() {
		return ItemInfoJson;
	}
	public void setItemInfoJson(String itemInfoJson) {
		ItemInfoJson = itemInfoJson;
	}
	public String getCompanyName() {
		return CompanyName;
	}
	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
	public String getCompanyId() {
		return CompanyId;
	}
	public void setCompanyId(String companyId) {
		CompanyId = companyId;
	}
	public Item(String itemId, String itemType, String itemInfoJson, String companyName, String companyId) {
		super();
		ItemId = itemId;
		ItemType = itemType;
		ItemInfoJson = itemInfoJson;
		CompanyName = companyName;
		CompanyId = companyId;
	}
	
}
