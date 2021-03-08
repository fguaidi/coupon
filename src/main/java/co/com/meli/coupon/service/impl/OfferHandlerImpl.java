package co.com.meli.coupon.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.com.meli.coupon.model.Item;
import co.com.meli.coupon.service.IOfferHandler;

@Service
public class OfferHandlerImpl implements IOfferHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(OfferHandlerImpl.class);

	@Override
	public List<String> calculate(Map<String, Float> items, Float maxAmount) {
		List<String> result = new ArrayList<>();
		
		items.forEach((id, price) -> LOGGER.info("key:{} value:{} ", id, price));
		LOGGER.info("Amount:{} ", maxAmount);

		ArrayList<Item> arrayItems = new ArrayList<>();
		items.forEach((id, price) -> {
			if (price <= maxAmount) {
				arrayItems.add(new Item(id, price));
			}
		});

		int[] offer = new int[arrayItems.size()];
		int[] finalOffer = new int[arrayItems.size()];

		if (!arrayItems.isEmpty()) {
			calculateOfferV2(offer, 0, arrayItems, finalOffer, maxAmount);
			result = getItemList(finalOffer, arrayItems);
		}

		return result;
	}

	private List<String> getItemList(int[] finalOffer, ArrayList<Item> arrayItems) {
		List<String> result = new ArrayList<>();
		for (int i = 0; i < finalOffer.length; i++) {
			if (finalOffer[i] == 1)
				result.add(arrayItems.get(i).getId());
		}
		return result;
	}

	public void calculateOfferV2(int[] offer, int stage, ArrayList<Item> items, int[] finalOffer, float maxAmount) {

		int i = 0;

		do {
			offer[stage] = i;

			if (isValid(offer, stage, items, maxAmount)) {

				if (stage == items.size() - 1) { // Evalua si se llegó al final de las etapas
					updateSolution(offer, items, finalOffer);
				} else {
					calculateOfferV2(offer, stage + 1, items, finalOffer, maxAmount);
				}
			}
			i++;

		} while (offer[stage] != 1);

		offer[stage] = -1;
	}

	private void updateSolution(int[] offer, ArrayList<Item> items, int[] finalOffer) {

		float totalPrice = getOfferPrice(offer, items);
		float finalPrice = getOfferPrice(finalOffer, items);

		if (totalPrice > finalPrice) {
			for (int i = 0; i < offer.length; i++) {
				finalOffer[i] = offer[i];
			}
		}
	}

	private float getOfferPrice(int[] offer, ArrayList<Item> items) {
		float price = 0;
		for (int i = 0; i < offer.length; i++) {
			if (offer[i] == 1)
				price += items.get(i).getPrice();

		}
		return price;
	}

	private boolean isValid(int[] offer, int stage, ArrayList<Item> items, float maxAmount) {
		float amount = 0;
		for (int i = 0; i <= stage; i++) {
			if (offer[i] == 1) // Si el vector solucion tienen en cuenta el item lo sumo y evaluo
				amount += items.get(i).getPrice();

		}
		return amount <= maxAmount;
	}

}
