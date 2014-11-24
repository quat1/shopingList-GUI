package dolphine.designs.shoppinglist;

import android.app.Application;

public class Smartphone {

	public String name, description;
	public int price;
	public boolean isSelected = false;

	public Smartphone(String name, String description, int price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
