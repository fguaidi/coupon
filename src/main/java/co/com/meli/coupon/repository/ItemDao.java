package co.com.meli.coupon.repository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import co.com.meli.coupon.config.CacheConfig;
import co.com.meli.coupon.model.Item;
import co.com.meli.coupon.service.impl.CouponHandlerImpl;


@Repository
public class ItemDao {
	
	public static final String BASE_URL = "https://api.mercadolibre.com/items/";
	public static final String PRICE = "price";
	public static final String ID = "id";

	public static final String HASH_KEY = "Item";
	

	private static final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2)
			.connectTimeout(Duration.ofSeconds(10)).build();

	private static final Logger LOGGER = LoggerFactory.getLogger(CouponHandlerImpl.class);

	@Cacheable(cacheNames = CacheConfig.ITEM_CACHE, unless = "#result == null")
    public Item getItemById(String itemId){
    	LOGGER.info("Get item MELI API - itemId: {}", itemId);
    	HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(BASE_URL.concat(itemId))).build();

		CompletableFuture<HttpResponse<String>> response = httpClient.sendAsync(request,
				HttpResponse.BodyHandlers.ofString());

		String result;

		try {
			result = response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);

			Gson gson = new Gson();
			JsonObject jsonObj = gson.fromJson(result, JsonObject.class);

			if (jsonObj.has(ID) && jsonObj.has(PRICE))
				return new Item(jsonObj.get(ID).getAsString(), jsonObj.get(PRICE).getAsFloat());

		} catch (InterruptedException e) {
			LOGGER.error("InterruptedException: {}", e.getMessage());
		} catch (ExecutionException e) {
			LOGGER.error("ExecutionException: {}", e.getMessage());
		} catch (TimeoutException e) {
			LOGGER.error("TimeoutException: {}", e.getMessage());
		}
		return null;
    }

    
    
}
