package co.com.meli.coupon.service.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import co.com.meli.coupon.dto.CouponReqDto;
import co.com.meli.coupon.model.Item;
import co.com.meli.coupon.repository.ItemDao;

public class CouponHandlerImplTest {
	

	private CouponHandlerImpl couponHandlerImpl;
	
	private ItemDao dao;
	
	
	
	private CouponReqDto couponRequest;
	private List<String> itemIds;
	

	@Before
    public void setUp() {
		MockitoAnnotations.initMocks(this);
		dao = mock(ItemDao.class);
		itemIds = new ArrayList<>();
		itemIds.add("MCO453165628");
		itemIds.add("MCO561290822");
		itemIds.add("MCO594954126");
		itemIds.add("MCO602224685");
		itemIds.add("MCO599620447");
		
		couponRequest = new CouponReqDto();
		couponHandlerImpl = new CouponHandlerImpl();
       
    }
	
	@Test
	public void testCalculateCoupon() {
		
		couponRequest.setItemIds(itemIds);
		couponRequest.setAmount(50000f);
		when(dao.getItemById("MCO453165628")).thenReturn(new Item("MCO453165628", 3599000.0f));
		when(dao.getItemById("MCO561290822")).thenReturn(new Item("MCO561290822", 44900.0f));
		when(dao.getItemById("MCO594954126")).thenReturn(new Item("MCO594954126", 2999900.0f));
		when(dao.getItemById("MCO602224685")).thenReturn(new Item("MCO602224685", 1949900.0f));
		when(dao.getItemById("MCO599620447")).thenReturn(new Item("MCO599620447", 31000.0f));
		
		couponHandlerImpl.calculateCoupon(couponRequest);
	}

}
