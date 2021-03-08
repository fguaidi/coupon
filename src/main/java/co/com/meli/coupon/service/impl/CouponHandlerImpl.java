package co.com.meli.coupon.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import co.com.meli.coupon.dto.CouponReqDto;
import co.com.meli.coupon.dto.CouponRsDto;
import co.com.meli.coupon.model.Item;
import co.com.meli.coupon.repository.ItemDao;
import co.com.meli.coupon.service.ICouponHandler;

@Service
public class CouponHandlerImpl implements ICouponHandler {


	@Override
	public CouponRsDto calculateCoupon(CouponReqDto couponRequest) {

		Map<String, Float> items = getApiItems(couponRequest.getItemIds());
		OfferHandlerImpl offerHandler = new OfferHandlerImpl();
		List<String> offer = offerHandler .calculate(items, couponRequest.getAmount());
		CouponRsDto couponResponse = new CouponRsDto();
		couponResponse.setItemIds(offer);
		couponResponse.setTotal(calculateCouponAmount(offer, items));
		return couponResponse;
	}

	private Float calculateCouponAmount(List<String> offer, Map<String, Float> items) {
		float amount = 0f;
		for (String item : offer) {
			amount += items.get(item);
		}
		return amount;
	}

	private Map<String, Float> getApiItems(List<String> itemIds) {

		ItemDao dao = new ItemDao();
		Map<String, Float> itemMap = new HashMap<>();

		for (String itemId : itemIds) {
			Item item = dao.getItemById(itemId);
			itemMap.put(item.getId(), item.getPrice());
		}

		return itemMap;
	}

}
