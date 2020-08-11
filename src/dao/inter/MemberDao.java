package dao.inter;

import java.util.List;
import java.util.Map;

import common.PageInfoDto;
import dto.MemberDto;

public interface MemberDao {
	public List<MemberDto> getMemberRangeList(PageInfoDto info);
	public int updateMemberStatus(Map<String, String> map);
	public int check(int member_id, String password);
	public int getMeberCount();
	public int checkNickname(String nickname);
	public int checkEmail(String member_email);
	public int insertMember(MemberDto member);
	public MemberDto getMemberFromLogin(String member_email);
	public int  check(String member_email, String password);
	public String checkStatus(String member_email);
	public int activateStatusFromLogin(int member_id);
	public MemberDto getMemberFromMypage(int member_id);
	public int modifyInfor(MemberDto memberDto);
	public int deleteMemberFromMypage(int member_id);
	
	public int getMemberIdByNickname(String nickname);
	
	
}
