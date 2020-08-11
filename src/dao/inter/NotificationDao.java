package dao.inter;

import java.util.List;

import common.PageInfoDto;
import dto.NotificationDto;
import dto.join.ManagerNotificationListDto;

public interface NotificationDao {
	public int getNotificationCount();
	public List<ManagerNotificationListDto> getNotificationRangeList(PageInfoDto info);
	public int deleteNotification(int notify_id);
	public int insertNotificationByMemberId(NotificationDto notification);
	public int insertAllNotificationByMemberId(NotificationDto notification);
	public List<NotificationDto> getNotificationById(int member_id);
	public int deleteAllNotification(int member_id);
	public int updateReadStatus(int member_id);
	public int checkNotificationNotRead(int member_id);
}
