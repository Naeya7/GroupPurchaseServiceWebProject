package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dao.inter.ReplyDao;
import dto.ReplyDto;
import dto.join.ReplyContentDto;
import mybatis.SqlMapClient;

public class ReplyDaoImpl implements ReplyDao {
	
	//세션 가져오기
	private SqlSession session = SqlMapClient.getSession();
	
	//ID로 내가쓴 댓글 가져오기
	public List<ReplyDto> getReplyListFromMypage(int member_id){
		return session.selectList("user.getReplyListFromMypage", member_id);
	}
	
	// ReviewContent 에서 reply 가져오기
	public List<ReplyContentDto> getReplyContentFromReview(int review_num){
		return session.selectList("user.getReplyContentFromReview", review_num);
	}	
	
	//댓글 등록하기
	public int uploadReplyFromReview(ReplyDto replyDto) {
		return session.insert("user.uploadReplyFromReview", replyDto);
	}	
	
	//댓글 삭제하기
	public int deleteReplyFromReview(int reply_num) {
		return session.delete("user.deleteReplyFromReview", reply_num);
	}
}
