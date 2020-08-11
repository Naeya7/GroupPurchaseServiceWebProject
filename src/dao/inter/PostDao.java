package dao.inter;

import java.util.List;
import java.util.Map;

import common.PageInfoDto;
import dto.PostDto;
import dto.join.HitPostDto;
import dto.join.MainPagePostDto;
import dto.join.ManagerModifyPostDto;
import dto.join.ManagerPostListDto;
import dto.join.PayablePostDto;
import dto.join.PayedPostDto;
import dto.join.PostContentDto;

public interface PostDao {
	public List<HitPostDto> getHitPostFromMain();
	public List<PayablePostDto> getNotPayablePostFromMypage(int member_id);
	public List<ManagerPostListDto> getFinishedPostRangeList(PageInfoDto info);
	public List<ManagerPostListDto> getActivedPostRangeList(PageInfoDto info);
	public List<HitPostDto> getPostFromPostList(PageInfoDto info);
	public ManagerModifyPostDto getModifyPostByPostId(int post_id);
	public int updatePostStatus(Map<String, String> map);
	public int getPostCountByProductId(int product_id);
	public int updatePostFixed_price(PostDto post);
	public int insertPost(PostDto post);
	public int updatePost(PostDto post);
	public int deleteActivePost(int post_id);
	public int getPostCount();
	public int getFinishedPostCount();
	public int getActivedPostCount();
	public int getCatePostCount(int category_id);
	public int getSearchCatePostCount(Map<String,Object> map);
	public List<HitPostDto> getCatePostFromPostList(Map<String, Object>  map);
	public List<HitPostDto> getSearchCatePostFromPostList(Map<String, Object>  map);	
	public int getSearchFinishedPostCount(Map<String, String> map);
	public List<ManagerPostListDto> getSearchFinishedPostRangeList(PageInfoDto info);
	public PostContentDto getPostContentFromContent(int post_id);
	public int getCurrentamountFromApply(int post_id);
	public int getMinamountFromApply(int post_id);
	public List<PayablePostDto> getPayablePostFromMypage(int member_id);
	public List<PayedPostDto> getPayedPostListFromMypage(Map<String, Integer> map);	
	
	public PayedPostDto getPayedPostFromReview(int payment_id);	
	public List<MainPagePostDto> getRecentlyPostFromMain();
	
}
