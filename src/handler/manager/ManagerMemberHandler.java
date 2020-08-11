package handler.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import common.PageInfoDto;
import common.PageInfoProcess;
import dao.inter.MemberDao;
import dto.MemberDto;


@Controller
public class ManagerMemberHandler {
	
	//스프링 빈 객체 할당
	@Resource(name="memberDao")
	MemberDao memberDao;
	
	//멤버 리스트 보기
	@RequestMapping("/memberList.do")
	public ModelAndView memberList(HttpServletRequest request, HttpServletResponse response) {
		
		//페이지 사이즈 지정
		PageInfoProcess.getInstance().setPageSize(10);
		
		//페이지 정보 갱신
		PageInfoDto info = PageInfoProcess.getInstance().process(memberDao.getMeberCount() ,request.getParameter("pageNum"));
		
		//멤버가 존재하면
		if(info.getCnt()>0) {
			
			//멤버 리스트 가져오기
			List<MemberDto> members = memberDao.getMemberRangeList(info);
			
			//멤버 리스트 셋팅
			request.setAttribute("members",members);
		}
		
		//페이지 정보
		request.setAttribute("info", info);

		//모듈 컨트롤
		request.setAttribute("pageControl", "memberList");
		
		//탬플릿 페이지로 이동
		return new ModelAndView("manager/managerPage");
	}
	
	//회원 상태 변경 선택 폼
	@RequestMapping("/setMemberStatus.do")
	public ModelAndView setMemberStatus(HttpServletRequest request, HttpServletResponse response) {
		
		//파라미터 받기
		String member_id = request.getParameter("member_id");
		
		//그대로 넘겨주기
		request.setAttribute("member_id", member_id);
		
		//상태 변경 페이지로 이동
		return new ModelAndView("manager/setMemberStatus");
	}
	
	//멤버 상태 변경하기
	@RequestMapping("/setMemberStatusPro.do")
	public ModelAndView setMemberStatusPro(HttpServletRequest request, HttpServletResponse response) {
		
		//파라미터 받기
		String member_id = request.getParameter("member_id");
		String member_status = request.getParameter("member_status");
	
		//맵 셋팅
		Map<String, String> map = new HashMap<String, String>();
		map.put("member_id", member_id);
		map.put("member_status", member_status);
		
		//상태 변경
		int result = memberDao.updateMemberStatus(map);
		
		//결과 셋팅
		request.setAttribute("result", result);
		
		//처리 페이지로 이동
		return new ModelAndView("manager/setMemberStatusPro");
	}
	
}
