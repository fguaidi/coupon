package co.com.meli.coupon.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Item")
public class Item implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private String id;
    private Float price;

    public Item(String id, Float price) {
        this.id = id;
        this.price = price;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
    
}
