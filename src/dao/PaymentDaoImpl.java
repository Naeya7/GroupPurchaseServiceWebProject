package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.PageInfoDto;
import dao.inter.PaymentDao;
import dto.PaymentDto;
import mybatis.SqlMapClient;

public class PaymentDaoImpl implements PaymentDao {
	
	//세션 가져오기
	private SqlSession session = SqlMapClient.getSession();
	
	//ID에 따른 결제 수
	public int getPaymentCount(int member_id) {
		return session.selectOne("user.getPaymentCount", member_id);
	}
	
	//매니저
	//결제리스트 전체 개수
	public int getPaymentCount() {
		return session.selectOne("manager.getPaymentCount");
	}
	
	//전체 결제 리스트 가져오기(범위)
	public List<PaymentDto> getPaymentRangeList(PageInfoDto info) {
		return session.selectList("manager.getPaymentRangeList", info);
	}
	
	//ID로 결제정보 지우기
	public int deletePaymentById(int payment_id) {
		return session.delete("manager.deletePaymentById", payment_id);
	}
	
	//Application ID로 결제여부 확인하기
	public int getCheckPaymentByApplicationId(int application_id) {
		return session.selectOne("manager.getCheckPaymentByApplicationId", application_id);
	}
	
	// 리뷰 작성 후 writable update
	public int updateWritableFromReview(int payment_id) {
		return session.update("user.updateWritableFromReview", payment_id);
	}	
	
	// 유저
	public int registerPaymentFromPayment(PaymentDto paymentDto) {
		return session.insert("user.registerPaymentFromPayment", paymentDto);
	}
	
	
}
