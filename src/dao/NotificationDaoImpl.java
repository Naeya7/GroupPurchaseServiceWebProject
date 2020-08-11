package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.PageInfoDto;
import dao.inter.NotificationDao;
import dto.NotificationDto;
import dto.join.ManagerNotificationListDto;
import mybatis.SqlMapClient;

public class NotificationDaoImpl implements NotificationDao {
	//세션 가져오기
	SqlSession session = SqlMapClient.getSession();
	
	//알림 전체 개수
	public int getNotificationCount() {
		return session.selectOne("manager.getNotificationCount");
	}
	
	//전체 알림 리스트 가져오기(범위)
	public List<ManagerNotificationListDto> getNotificationRangeList(PageInfoDto info) {
		return session.selectList("manager.getNotificationRangeList", info);
	}

	//ID로 알림 삭제
	public int deleteNotification(int notify_id) {
		return session.delete("manager.deleteNotification", notify_id);
	}
	
	//ID에 해당하는 멤버에게 알림 보내기
	public int insertNotificationByMemberId(NotificationDto notification) {
		return session.insert("manager.insertNotificationByMemberId", notification);
	}
	
	//POST를 신청한 멤버에게 알림 보내기
	public int insertAllNotificationByMemberId(NotificationDto notification) {
		return session.insert("manager.insertAllNotificationByMemberId", notification);
	}
	
	//알림 가져오기
	public List<NotificationDto> getNotificationById(int member_id) {
		return session.selectList("user.getNotificationById", member_id);
	}

	//유저 알림 전부 지우기
	public int deleteAllNotification(int member_id) {
		return session.delete("user.deleteAllNotification", member_id);
	}
	
	//알림 읽음으로 바꾸기
	public int updateReadStatus(int member_id) {
		return session.update("user.updateReadStatus", member_id);
	}
	
	//읽지 않은 알림 체크하기
	public int checkNotificationNotRead(int member_id) {
		return session.selectOne("user.checkNotificationNotRead", member_id);
	}
	
	
}
