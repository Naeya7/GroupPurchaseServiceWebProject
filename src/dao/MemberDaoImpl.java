package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import common.PageInfoDto;
import dao.inter.MemberDao;
import dto.MemberDto;
import mybatis.SqlMapClient;

public class MemberDaoImpl implements MemberDao{
	
	//세션 가져오기
	SqlSession session = SqlMapClient.getSession();
	
	//전체 멤버 수
	public int getMeberCount() {
		return session.selectOne("manager.getMeberCount");
	}
	
	//전체 멤버 리스트(범위)
	public List<MemberDto> getMemberRangeList(PageInfoDto info) {
		return session.selectList("manager.getMemberRangeList", info);
	}
	
	//자기 멤버 정보 보기
	public MemberDto getMemberFromMypage(int member_id){
		return session.selectOne("user.getMemberFromMypage", member_id);
	}
	
	//멤버 아이디 비밀번호 체크
	public int check(int member_id, String password) {
		int result = 0;
		MemberDto memberDto = getMemberFromMypage(member_id);
		if(memberDto.getPassword().equals(password)) {
			result = 1;
		} else {
			result = 0;
		}
		return result;
	}
		
	//특정 멤버 상태 바꾸기
	public int updateMemberStatus(Map<String, String> map) {
		return session.update("manager.updateMemberStatus", map);
	}
	
	//닉네임 중복 검사
	public int checkNickname(String nickname) {
		return session.selectOne("user.checkNickname", nickname);
	}
	
	//이메일 중복 검사
	public int checkEmail(String member_email) {
		return session.selectOne("user.checkEmail", member_email);
	}
	
	//멤버 등록
	public int insertMember(MemberDto member) {
		return session.insert("user.insertMember", member);
	}
	
	//멤버 로그인
	public MemberDto getMemberFromLogin(String member_email) {
		return session.selectOne("user.getMemberFromLogin", member_email);
	}
	
	//멤버 로그인 체크
	public int check(String member_email, String password) {
		int result = 0;
		int cnt = checkEmail(member_email);
		String status = checkStatus(member_email);
		MemberDto memberDto = getMemberFromLogin(member_email);
		if(cnt == 0 || !memberDto.getPassword().equals(password)) {
			// 아이디가 없거나 비밀번호가 일치하지 않음
			result = 0;
		} else {
			// 아이디 있고 비밀번호 일치
			if(status.equals("active")) {
				// 활동 회원
				result = 1;
			} else if(status.equals("suspended")){
				// 활동 정지 회원
				result = 2;
			} else if(status.equals("dormant")) {
				// 휴면상태 회원
				result = 3;
			} else if(status.equals("inactive")) {
				// 탈퇴한 회원 --> 로그인 정보 틀린 것과 동일한 result
				result = 0;
			}
		}
		return result;
	}
	
	//상태 체크하기
	public String checkStatus(String member_email) {
		return session.selectOne("user.checkStatus", member_email);
	}
	
	// 휴면상태 -> 활동상태 변경
	public int activateStatusFromLogin(int member_id) {
		return session.update("user.activateStatusFromLogin", member_id);
	}
	
	// 정보 수정
	public int modifyInfor(MemberDto memberDto) {
		return session.update("user.modifyInfor", memberDto);
	}	
	
	//멤버 회원 탈퇴(상태변경)
	public int deleteMemberFromMypage(int member_id) {
		return session.update("user.deleteMemberFromMypage", member_id);
	}	
	
	//닉네임으로 멤버 아이디 얻기
	public int getMemberIdByNickname(String nickname) {
		return session.selectOne("user.getMemberIdByNickname", nickname);
	}
	

	

}
