package com.mkl.home.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mkl.home.dao.BoardDao;
import com.mkl.home.dao.MemberDao;
import com.mkl.home.dto.Criteria;
import com.mkl.home.dto.MemberDto;
import com.mkl.home.dto.PageDto;
import com.mkl.home.dto.QAboardDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class BoardController {

	@Autowired
	private SqlSession sqlSession;
	
	@GetMapping(value = "/board")
	public String board(HttpServletRequest request, Model model, Criteria criteria) {
		
		if(request.getParameter("pageNum") != null) {//유저가 게시판 페이지 벊를 클릭해서 리스트로 온 경우
			criteria.setPageNum(Integer.parseInt(request.getParameter("pageNum")));
			// 문자열로 넘어온 
		}
		
		request.getParameter("pageNum");//유저가 클릭한 페이지 번호
		
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		
		int total = dao.boardAllCountDao();//게시판에 등록된 총 글의 개수
		
		PageDto pageDto = new PageDto(criteria, total);
		
		List<QAboardDto> dtos = dao.listDao(criteria.getAmount(), criteria.getPageNum());

		model.addAttribute("list", dtos);
		model.addAttribute("pageDto", pageDto);
		model.addAttribute("pageNum", criteria.getPageNum());
	
		return "list";
	}
	
	@GetMapping(value = "/writeForm")
	public String writeForm(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response) throws IOException {
		
		String sessionId = (String) session.getAttribute("sessionId");
		
		if(sessionId == null) {//로그인하지 않은 회원이 글쓰기 버튼 클릭한 경우
			response.setContentType("text/html;charset=utf-8");//utf-8로 경고창에 출력될 문자셋 셋팅
			response.setCharacterEncoding("utf-8");

			PrintWriter printout = response.getWriter();
			
			printout.println("<script>alert('"+ "로그인한 회원만 글을 쓸 수 있습니다." +"');location.href='"+"login"+"';</script>");
			printout.flush();
		} else {
			MemberDao dao = sqlSession.getMapper(MemberDao.class);
			
			MemberDto memberDto = dao.memberInfoDao(sessionId);//현재 로그인 중인 아이디의 회원정보를 가져오기
			model.addAttribute("memberDto", memberDto);
		}
		
		return "writeForm";
	}
		
	@GetMapping(value = "/write")
	public String write(HttpServletRequest request, Model model) {
		
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		
		dao.writeDao(request.getParameter("qbmid"), request.getParameter("qbmname"), request.getParameter("qbmemail"), request.getParameter("qbtitle"), request.getParameter("qbcontent"));

		return "redirect:board";
	}
	
	@GetMapping(value = "/contentView")
	public String contentView(HttpServletRequest request, Model model, HttpServletResponse response) throws IOException {
			
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		
		QAboardDto boardDto = dao.contentViewDao(request.getParameter("qbnum"));
			
		if(boardDto == null) {//글이 삭제된 경우(db 검색 실패)
			response.setContentType("text/html;charset=utf-8");//utf-8로 경고창에 출력될 문자셋 셋팅
			response.setCharacterEncoding("utf-8");
			
			PrintWriter printout = response.getWriter();
				
			printout.println("<script>alert('"+ "해당 글은 삭제된 글입니다." +"');location.href='"+"board"+"';</script>");
			printout.flush();
		} else {
			model.addAttribute("boardDto", boardDto);
		}
		return "contentView";
	}
	
	@GetMapping(value = "/contentModify")
	public String contentModify(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response) throws IOException {

		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		
		String sid = (String) session.getAttribute("sessionId");//현재 로그인 중인 아이디
		QAboardDto boardDto = dao.contentViewDao(request.getParameter("qbnum"));
		//해당 번호 글의 모든 정보(글번호ㅡ 글쓴이아이디, 글쓴이이름, 글쓴이이메일, 글제목, 글내용, 글등록일)
		
		if(sid.equals(boardDto.getQbmid())) {//글을 쓴 회원과 현재 로그인 중인 아이디 같은 경우->글수정 가능
			model.addAttribute("boardDto", boardDto);
		} else if(sid.equals("admin")) {//만약 로그인중인 아이디가 관리자아이디(admin)인 경우
			model.addAttribute("boardDto", boardDto);
		} else {//글을 쓴 회원과 현재 로그인 중인 아이디가 다른 경우->글수정 불가능
			response.setContentType("text/html;charset=utf-8");//utf-8로 경고창에 출력될 문자셋 셋팅
			response.setCharacterEncoding("utf-8");
			
			PrintWriter printout = response.getWriter();
			
			printout.println("<script>alert('"+ "글 수정은 해당 글을 쓴 회원만 가능합니다." +"');location.href='"+"board"+"';</script>");
			printout.flush();
		}
		return "contentModify";
	}
	
	@GetMapping(value = "/contentModifyOk")
	public String contentModifyOk(HttpServletRequest request, Model model) {
	
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.contentModifyDao(request.getParameter("qbnum"), request.getParameter("qbtitle"), request.getParameter("qbcontent"));
	
		model.addAttribute("boardDto", dao.contentViewDao(request.getParameter("qbnum")));
		//수정된 글의 번호로 레코드를 다시 가져와 contentView.jsp에 전송
		return "contentView";
	}
	
	@GetMapping(value = "/contentDelete")
	public String contentDelete(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response) throws IOException {
		
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		
		String sid = (String) session.getAttribute("sessionId");//현재 로그인 중인 아이디
		QAboardDto boardDto = dao.contentViewDao(request.getParameter("qbnum"));
		//해당 번호 글의 모든 정보(글번호, 글쓴이아이디, 글쓴이이름, 글쓴이이메일, 글제목, 글내용, 글등록일)
		
		if(sid.equals(boardDto.getQbmid())) {// 글을 쓴 회원과 현재 로그인 중인 아이디 같은 경우->글수정 가능
			dao.contentDeleteDao(request.getParameter("qbnum"));
		} else if(sid.equals("admin")) {//만약 로그인중인 아이디가 관리자아이디(admin)인 경우
			dao.contentDeleteDao(request.getParameter("qbnum"));
		} else {//글을 쓴 회원과 현재 로그인 중인 아이디가 다른 경우->글수정 불가능
			response.setContentType("text/html;charset=utf-8");//utf-8로 경고창에 출력될 문자셋 셋팅
			response.setCharacterEncoding("utf-8");
			
			PrintWriter printout = response.getWriter();
			
			printout.println("<script>alert('"+ "글 삭제는 해당 글을 쓴 회원만 가능합니다." +"');location.href='"+"board"+"';</script>");
			printout.flush();
		}
		
		return "redirect:board";
	}	
		
	@GetMapping(value = "/profile")
	public String profile() {
		return "profile";
	}
	
	@GetMapping(value = "/contact")
	public String contact() {
		return "contact";
	}
}
