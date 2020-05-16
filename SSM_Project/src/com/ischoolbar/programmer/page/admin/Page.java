package com.ischoolbar.programmer.page.admin;

public class Page {
	
	private int page=1;
	private int rows;
	private int offset;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getOffset() {
		return offset=(page-1)*rows;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
}
