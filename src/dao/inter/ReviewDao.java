package dao.inter;

import java.util.List;
import java.util.Map;

import common.PageInfoDto;
import dto.LikeDto;
import dto.ReviewDto;
import dto.join.ManagerReviewListDto;
import dto.join.ReviewContentDto;

public interface ReviewDao {	
	public List<ManagerReviewListDto> getReviewRangeList(PageInfoDto info);
	public ReviewDto getReviewById(int review_num);
	public int deleteReview(int review_num);
	public int getCheckReviewByPaymentID(int payment_id);
	public int getReviewCount();
	public List<ReviewDto> getReviewFromContent(int product_id);
	public List<ReviewDto> getReviewListFromMypage(int member_id);
	public List<ReviewContentDto> getReviewListFromBoard(PageInfoDto info);
	public int getSearchReviewCount(Map<String, String> map);
	public List<ReviewContentDto> getSearchReviewListFromBoard(PageInfoDto info);
	public ReviewContentDto getReviewContent(int review_num);
	public int addCountFromReview(int review_num);
	public int uploadReview(ReviewDto reviewDto);
	public int modifyReview(ReviewDto reviewDto);
	public int insertReviewintoDeletedReview(ReviewDto reviewDto);
	public int updateReplycountFromReview(int review_num);
	public int deleteupdateReplycountFromReview(int review_num);
	public int check(Map<String, Integer> like);
	public int likeinsert(LikeDto likeDto);
	public int addlike(int review_num);
	
	//20_0728
	public List<ReviewContentDto> getReviewListFromBoardByKeyword(PageInfoDto info);
	
	
	
}
