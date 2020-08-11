package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import common.PageInfoDto;
import dao.inter.ApplicationDao;
import dto.ApplicationDto;
import dto.join.ManagerApplicationListDto;
import mybatis.SqlMapClient;

public class ApplicationDaoImpl implements ApplicationDao {
	//세션 가져오기
	SqlSession session = SqlMapClient.getSession();	
	
	//게시글 ID로 신청한 멤버ID리스트 얻기
	public List<Integer> getAllMemberIdbyPostId(int post_id) {
		return session.selectList("manager.getAllMemberIdbyPostId", post_id);
	}
	
	//전체 신청 개수 가져오기
	public int getApplicationCount() {
		return session.selectOne("manager.getApplicationCount");
	}
	
	//전체 신청 리스트 가져오기(범위)
	public List<ManagerApplicationListDto> getApplicationRangeList(PageInfoDto info) {
		return session.selectList("manager.getApplicationRangeList", info);
	}

	//ID로 신청 정보 삭제하기
	public int deleteApplicationById(int application_id) {
		return session.delete("manager.deleteApplicationById", application_id);
	}
	
	//post_id로 신청 개수 얻기
	public int getCountApllicationByPostId(int post_id) {
		return session.selectOne("manager.getCountApllicationByPostId", post_id);
	}
	
	// 유저
	// 신청하기
	public int registerAppFromPostContent(Map<String, Integer> map) {
		return session.insert("user.registerAppFromPostContent", map);
	}
	
	// 신청수량 update
	public int updateAppFromPostPostContent(Map<String, Integer> map) {
		return session.update("user.updateAppFromPostContent", map);
	}
	
	// 신청 취소하기
	public int cancelAppFromMypage(int application_id) {
		return session.delete("user.cancelAppFromMypage", application_id);
	}
	
	// app id 찾기
	public ApplicationDto getappId(int application_id) {
		return session.selectOne("user.getappId",application_id);
	}
	
	
	// 신청 취소후 수량감소
	public int deleteupdateAppFromPostPostContent(ApplicationDto applicationDto) {
		return session.update("user.deleteupdateAppFromPostPostContent", applicationDto);
	}
	
}
