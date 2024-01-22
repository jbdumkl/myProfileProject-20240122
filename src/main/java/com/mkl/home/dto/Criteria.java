package com.mkl.home.dto;

public class Criteria {

	private int amount=9;//한 페이지 당 출력될 글의 개수
	private int pageNum=1;//현재 페이지 번호가 저장될 변수
	//현재 페이지 번호가 저장될 변수(게시판 클릭시 첫번째로 보여질 페이지가 1페이지이므로 1로 초기값 설정)
	private int startNum;//유저가 선택한 페이지에서 시작할 글의 번호
	public Criteria() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Criteria(int amount, int pageNum, int startNum) {
		super();
		this.amount = amount;
		this.pageNum = pageNum;
		this.startNum = startNum;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	
}
