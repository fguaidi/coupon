package co.com.meli.coupon.service;

import java.util.List;
import java.util.Map;

public interface IOfferHandler {

	List<String> calculate(Map<String, Float> items, Float amount);
}
