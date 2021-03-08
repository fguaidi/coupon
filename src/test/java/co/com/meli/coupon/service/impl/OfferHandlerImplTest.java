package co.com.meli.coupon.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class OfferHandlerImplTest {

	private OfferHandlerImpl offerHandlerImpl;
	private Float maxAmount;
	private Map<String, Float> items;
	private List<String> itemIdsResult;
	
	@Before
    public void setUp() {
		items = new HashMap<>();
		items.put("MCO453165628", 3599000.0f);
		items.put("MCO561290822", 44900.0f);
		items.put("MCO594954126", 2999900.0f);
		items.put("MCO602224685", 1949900.0f);
		items.put("MCO599620447", 31000.0f);

		itemIdsResult = new ArrayList<>();
		itemIdsResult.add("MCO561290822");
		
		offerHandlerImpl = new OfferHandlerImpl();
		maxAmount = 50000f;
       
    }
	
	@Test
	public void testCalculate() {
		
		List<String> expected = offerHandlerImpl.calculate(items, maxAmount);
		assertEquals(expected, itemIdsResult);
	}
	
	@Test
	public void testCalculateEmpty() {
		Map<String, Float> itemsEmpty = new HashMap<>();
		List<String> expected = offerHandlerImpl.calculate(itemsEmpty, maxAmount);
		assertEquals(expected, new ArrayList<>());
	}

}
