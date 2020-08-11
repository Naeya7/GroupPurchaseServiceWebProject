package dao.inter;

import java.util.List;

import common.PageInfoDto;
import dto.PaymentDto;

public interface PaymentDao {
	public int getPaymentCount();
	public List<PaymentDto> getPaymentRangeList(PageInfoDto info);	
	public int deletePaymentById(int payment_id);
	public int getCheckPaymentByApplicationId(int application_id);
	public int getPaymentCount(int member_id);
	
	public int updateWritableFromReview(int payment_id);
	public int registerPaymentFromPayment(PaymentDto paymentDto);
}
