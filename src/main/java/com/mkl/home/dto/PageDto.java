package com.mkl.home.dto;

public class PageDto {

	private int startPage;//화면에서 보여질 시작 페이지 번호
	private int endPage;//화면에서 보여질 마지막 페이지 번호
	private boolean next;//현재 보고있는 페이지 이상으로 페이지가 있는지 여부 >
	private boolean prev;//현재 보고있는 페이지 이하로 페이지가 있는지 여부 <
	private int total;//총 글의 개수
	private Criteria criteria;//criteria 내의 값들을 불러오기 위한 객체 선언
	
	public PageDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PageDto(Criteria criteria, int total) {
		
		this.criteria = criteria;
		this.total = total;
		this.endPage = (int) (Math.ceil((criteria.getPageNum()/10.0)) * 10);// ceil 올림
		//Math.ceil((criteria.getPageNum()/10.0))식에 의해서 1~10페이지는 endpage값이 10으로
		// 11~20페이지는 endpage값이 20으로 세팅 됨
		
		//총 128개의 글이 존재
		// 1,2,3,4,5,6,7,8,9,10 nest
		// prev 11 12 13 14 15
		this.startPage = this.endPage -9;
		
		int realEndPage = (int) Math.ceil(total*1.0 / criteria.getAmount());

		if(realEndPage < this.endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1;
		// startPage 값은 1, 21, 31, 41, .... 1보다 크면 무조건 prev가 true 표시되어야 함
		this.next = this.endPage < realEndPage;
		//마지막 페이지 번호가 실제 페이지 번호보다 작은 경우에만 next가 존재해야 함
	}
		
	
	public PageDto(int startPage, int endPage, boolean next, boolean prev, int total, Criteria criteria) {
		super();
		this.startPage = startPage;
		this.endPage = endPage;
		this.next = next;
		this.prev = prev;
		this.total = total;
		this.criteria = criteria;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public boolean getPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Criteria getCriteria() {
		return criteria;
	}
	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}
	
}
