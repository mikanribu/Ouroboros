package epf.domethic.ouroboros.activity;

import java.util.ArrayList;

public class TitreListeActivity {

	private String name;
	private ArrayList<DetailListeActivity> productList = new ArrayList<DetailListeActivity>();;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<DetailListeActivity> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<DetailListeActivity> productList) {
		this.productList = productList;
	}

}
