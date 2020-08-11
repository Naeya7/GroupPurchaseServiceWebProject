package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import common.PageInfoDto;
import dao.inter.ReviewDao;
import dto.LikeDto;
import dto.ReviewDto;
import dto.join.ManagerReviewListDto;
import dto.join.ReviewContentDto;
import mybatis.SqlMapClient;

public class ReviewDaoImpl implements ReviewDao{
	
	//세션 가져오기
	SqlSession session = SqlMapClient.getSession();
	
	//리뷰리스트 가져오기
	public List<ManagerReviewListDto> getReviewRangeList(PageInfoDto info) {
		return session.selectList("manager.getReviewRangeList", info);
	}
	
	//리뷰 전체 개수
	public int getReviewCount() {
		return session.selectOne("manager.getReviewCount");
	}
	
	//ID로 리뷰 지우기
	public int deleteReview(int review_num) {
		return session.delete("manager.deleteReview", review_num);
	}
	
	//ID로 해당 리뷰 찾기
	public ReviewDto getReviewById(int review_num) {
		return session.selectOne("manager.getReviewById", review_num);
	}
	
	//payment_id로 작성된 리뷰 체크하기
	public int getCheckReviewByPaymentID(int payment_id) {
		return session.selectOne("manager.getReviewCountByPaymentID", payment_id);
	}
	
	// post Content 에 들어갈 탑리뷰 3개
	public List<ReviewDto> getReviewFromContent(int product_id) {
		return session.selectList("user.getReviewFromContent", product_id);
	}
	
	public List<ReviewDto> getReviewListFromMypage(int member_id){
		return session.selectList("user.getReviewListFromMypage", member_id);
	}
	
	// 리뷰 리스트 뽑기
	public List<ReviewContentDto> getReviewListFromBoard(PageInfoDto info){
		return session.selectList("user.getReviewListFromBoard", info);
	}
	
	// 검색조건에 맞는 리뷰 갯수 뽑기
	public int getSearchReviewCount(Map<String, String> map) {
		return session.selectOne("user.getSearchReviewCount", map);
	}
	
	// 검색조건에 맞는 리뷰 리스트 뽑기
	public List<ReviewContentDto> getSearchReviewListFromBoard(PageInfoDto info){
		return session.selectList("user.getSearchReviewListFromBoard", info);
	}
	
	// 리뷰 컨텐트
	public ReviewContentDto getReviewContent(int review_num) {
		return session.selectOne("user.getReviewContent", review_num);
	}	
	
	// 조회수 올리기
	public int addCountFromReview(int review_num) {
		return session.update("user.addCountFromReview", review_num);
	}
	
	// 리뷰 작성
	public int uploadReview(ReviewDto reviewDto) {
		return session.insert("user.uploadReview", reviewDto);
	}
	
	// 리뷰 수정
	public int modifyReview(ReviewDto reviewDto) {
		return session.update("user.modifyReview", reviewDto);
	}
	
	// 리뷰 삭제하기 전 삭제테이블로 옮기기(매니저꺼 사용)
	public int insertReviewintoDeletedReview(ReviewDto reviewDto) {
		return session.insert("manager.insertDeletedReview", reviewDto);
	}
	
	// 댓글 작성 시 replycount update +1
	public int updateReplycountFromReview(int review_num) {
		return session.update("user.updateReplycountFromReview", review_num);
	}	
	// 댓글 삭제 시 replycount update -1
	public int deleteupdateReplycountFromReview(int review_num) {
		return session.update("user.deleteupdateReplycountFromReview", review_num);
	}
	
	// like 중복체크
	public int check(Map<String, Integer> like) {
		return session.selectOne("user.check", like);
	}
	
	 //like insert
		public int likeinsert(LikeDto likeDto) {
			return session.insert("user.insertReview", likeDto);
	}	
		
	// 클릭시  like + 1
	public int addlike(int review_num) {
		return session.update("user.addlike",review_num);
	}	
	
	//키워드로  리뷰 리스트 뽑기
	public List<ReviewContentDto> getReviewListFromBoardByKeyword(PageInfoDto info) {
		return session.selectList("user.getReviewListFromBoardByKeyword", info);
	}
	
}





