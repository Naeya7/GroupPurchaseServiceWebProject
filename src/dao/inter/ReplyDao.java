package dao.inter;

import java.util.List;

import dto.ReplyDto;
import dto.join.ReplyContentDto;

public interface ReplyDao {	
	public List<ReplyDto> getReplyListFromMypage(int member_id);
	public List<ReplyContentDto> getReplyContentFromReview(int review_num);
	public int uploadReplyFromReview(ReplyDto replyDto);	
	public int deleteReplyFromReview(int reply_num);
	
}
