package dao.inter;

import java.util.List;
import java.util.Map;

import common.PageInfoDto;
import dto.ApplicationDto;
import dto.join.ManagerApplicationListDto;

public interface ApplicationDao {
	public List<ManagerApplicationListDto> getApplicationRangeList(PageInfoDto info);
	public List<Integer> getAllMemberIdbyPostId(int post_id);
	public int getCountApllicationByPostId(int post_id);
	public int deleteApplicationById(int application_id);
	public int getApplicationCount();
	public int registerAppFromPostContent(Map<String, Integer> map);
	public int updateAppFromPostPostContent(Map<String, Integer> map);
	
	// 신청 취소하기
	public int cancelAppFromMypage(int application_id);
	public int deleteupdateAppFromPostPostContent(ApplicationDto applicationDto);
	public ApplicationDto getappId(int application_id);

	
}

