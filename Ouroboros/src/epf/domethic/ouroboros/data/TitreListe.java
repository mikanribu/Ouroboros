package epf.domethic.ouroboros.data;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

public class TitreListe {

	private String name;
	private ArrayList<DetailListe> productList = new ArrayList<DetailListe>();;	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<DetailListe> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<DetailListe> productList) {
		this.productList = productList;
	}

}
