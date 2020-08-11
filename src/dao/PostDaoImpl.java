package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import common.PageInfoDto;
import dao.inter.PostDao;
import dto.PostDto;
import dto.join.HitPostDto;
import dto.join.MainPagePostDto;
import dto.join.ManagerModifyPostDto;
import dto.join.ManagerPostListDto;
import dto.join.PayablePostDto;
import dto.join.PayedPostDto;
import dto.join.PostContentDto;
import mybatis.SqlMapClient;

public class PostDaoImpl implements PostDao {
	
	//세션 가져오기
	private SqlSession session = SqlMapClient.getSession();

	//마감 임박 게시글 정보 가져오기
	public List<HitPostDto> getHitPostFromMain(){
		return session.selectList("user.getHitPostFromMain");
	}
	
	//전체 포스트 개수
	public int getPostCount() {
		return session.selectOne("manager.getPostCount");
	}
	
	//진행중인 포스트 개수
	public int getActivedPostCount() {
		return session.selectOne("manager.getActivedPostCount");
	}
	
	//종료된 포스트 개수
	public int getFinishedPostCount() {
		return session.selectOne("manager.getFinishedPostCount");
	}
		
	//종료된 전체 포스트 리스트
	public List<ManagerPostListDto> getFinishedPostRangeList(PageInfoDto info) {
		return session.selectList("manager.getFinishedPostRangeList", info);
	}
	
	//진행중인 전체 포스트 리스트
	public List<ManagerPostListDto> getActivedPostRangeList(PageInfoDto info) {
		return session.selectList("manager.getActivedPostRangeList", info);
	}

	//상태 변경하기
	public int updatePostStatus(Map<String, String> map){
		return session.update("manager.updatePostStatus", map);
	}

	//제품 id로 게시글 숫자 가져오기
	public int getPostCountByProductId(int product_id) {
		return session.selectOne("manager.getPostCountByProductId", product_id);
	}
	
	//제품 ID로 협상가격 입력하기
	public int updatePostFixed_price(PostDto post) {
		return session.update("manager.updatePostFixed_price", post);
	}
	
	//게시글 등록하기
	public int insertPost(PostDto post) {
		return session.insert("manager.insertPost", post);
	}
	
	//진행중인 포스트 지우기
	public int deleteActivePost(int post_id) {
		return session.delete("manager.deleteActivePost", post_id);
	}
	
	//게시글 수정에 사용될 정보 가져오기
	public ManagerModifyPostDto getModifyPostByPostId(int post_id) {
		return session.selectOne("manager.getModifyPostByPostId", post_id);
	}
	
	//게시글 수정하기
	public int updatePost(PostDto post) {
		return session.update("manager.updatePost", post);
	}
	
	// post 리스트 뿌리기
	public List<HitPostDto> getPostFromPostList(PageInfoDto info){
		return session.selectList("user.getPostFromPostList", info);
	}
	
	// 카테고리별 post 개수
	public int getCatePostCount(int category_id) {
		return session.selectOne("user.getCatePostCount", category_id);
	}
	
	// 카테고리별 검색조건에 따른 post 개수
	public int getSearchCatePostCount(Map<String,Object> map) {
		return session.selectOne("user.getSearchCatePostCount", map);
	}
	
	// 카테고리별 post 리스트 뿌리기
	public List<HitPostDto> getCatePostFromPostList(Map<String, Object>  map){
		return session.selectList("user.getCatePostFromPostList", map);
	}
	
	// 카테고리별 검색조건에 따른 post 리스트 뿌리기
	public List<HitPostDto> getSearchCatePostFromPostList(Map<String, Object>  map){
		return session.selectList("user.getSearchCatePostFromPostList", map);
	}

	//종료된 포스트 검색조건에 따른 개수
	public int getSearchFinishedPostCount(Map<String, String> map) {
		return session.selectOne("user.getSearchFinishedPostCount",map);
	}
	
	//종료된 검색조건에 따른 전체 포스트 리스트
	public List<ManagerPostListDto> getSearchFinishedPostRangeList(PageInfoDto info) {
		return session.selectList("user.getSearchFinishedPostRangeList", info);
	}	
	
	// 게시글 콘텐트 보기
	public PostContentDto getPostContentFromContent(int post_id){
		return session.selectOne("user.getPostContentFromContent", post_id);
	}	
	
	// 현재 신청 수량
	public int getCurrentamountFromApply(int post_id) {
		return session.selectOne("user.getCurrentamountFromApply", post_id);
	}
	
	// 모집수량
	public int getMinamountFromApply(int post_id) {
		return session.selectOne("user.getMinamountFromApply", post_id);
	}
	
	//결제 가능한 포스트 정보 가쟈오기
	public List<PayablePostDto> getPayablePostFromMypage(int member_id) {		
		return session.selectList("user.getPayablePostFromMypage", member_id);
	}
	
	//진행중인 포스트 정보 가져오기
	public List<PayablePostDto> getNotPayablePostFromMypage(int member_id) {		
		return session.selectList("user.getNotPayablePostFromMypage", member_id);
	}
	
	//결제한 포스트 정보 가져오기
	public List<PayedPostDto> getPayedPostListFromMypage(Map<String, Integer> map){
		return session.selectList("user.getPayedPostListFromMypage", map);
	}
	
	// reviewContent 에서 post 정보
	public PayedPostDto getPayedPostFromReview(int payment_id) {
		return session.selectOne("user.getPayedPostFromReview", payment_id);
	}	
	
	//최근 게시물 가져오기(6개)
	public List<MainPagePostDto> getRecentlyPostFromMain() {
		return session.selectList("user.getRecentlyPostFromMain");
	}	
	
}


