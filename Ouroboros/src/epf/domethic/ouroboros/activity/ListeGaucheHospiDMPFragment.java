package epf.domethic.ouroboros.activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.actionbarsherlock.app.SherlockFragment;

import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.data.DetailListe;
import epf.domethic.ouroboros.data.TitreListe;
import epf.domethic.ouroboros.adapter.MyListAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
	 
public class ListeGaucheHospiDMPFragment extends SherlockFragment implements OnClickListener{
	 
	 private LinkedHashMap<String, TitreListe> myDepartments = new LinkedHashMap<String, TitreListe>();
	 private ArrayList<TitreListe> deptList = new ArrayList<TitreListe>();
	 
	 private MyListAdapter listAdapter;
	 private ExpandableListView myList;
	 
	 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
			View view = inflater.inflate(R.layout.fragment_dmp_hospi_menu_gauche,container, false);
			
	  //Just add some data to start with
	  loadData();
	 
	  //get reference to the ExpandableListView
	  myList = (ExpandableListView)view.findViewById(R.id.menu_gauche_hospi);
	  //create the adapter by passing your ArrayList data
	  listAdapter = new MyListAdapter(getActivity(), deptList);
	  //attach the adapter to the list
	  myList.setAdapter(listAdapter);
	 
	  //expand all Groups
	 // expandAll();
	 
	  //listener for child row click
	  myList.setOnChildClickListener(myListItemClicked);
	  //listener for group heading click
	        myList.setOnGroupClickListener(myListGroupClicked);
	         
	        return view;
	 }
	 
	 //method to expand all groups
	 private void expandAll() {
		 int count = listAdapter.getGroupCount();
		 for (int i = 0; i < count; i++){
				  myList.expandGroup(i);
			  }
		 }
	  
	 //method to collapse all groups
	 private void collapseAll() {
	  int count = listAdapter.getGroupCount();
	  for (int i = 0; i < count; i++){
	   myList.collapseGroup(i);
	  }
	 }
	 
	 //load some initial data into out list
	 private void loadData(){
	 
	  addProduct("Données Patient","Activewear");
	  addProduct("Apparel","Jackets");
	  addProduct("Apparel","Shorts");
	 
	  addProduct("Beauty","Fragrances");
	  addProduct("Beauty","Makeup");
	 
	 }
	  
	 //our child listener
	 private OnChildClickListener myListItemClicked =  new OnChildClickListener() {
	 
	  public boolean onChildClick(ExpandableListView parent, View v,
	    int groupPosition, int childPosition, long id) {
	    
	   //get the group header
		  TitreListe headerInfo = deptList.get(groupPosition);
	   //get the child info
	   DetailListe detailInfo =  headerInfo.getProductList().get(childPosition);
	  
	   return false;
	  }
	   
	 };
	  
	 //our group listener
	 private OnGroupClickListener myListGroupClicked =  new OnGroupClickListener() {
	 
	  public boolean onGroupClick(ExpandableListView parent, View v,
	    int groupPosition, long id) {
	     
	   return false;
	  }
	   
	 };
	 
	 //here we maintain our products in various departments
	 private int addProduct(String department, String product){
	 
	  int groupPosition = 0;
	   
	  //check the hash map if the group already exists
	  TitreListe headerInfo = myDepartments.get(department);
	  //add the group if doesn't exists
	  if(headerInfo == null){
	   headerInfo = new TitreListe();
	   headerInfo.setName(department);
	   myDepartments.put(department, headerInfo);
	   deptList.add(headerInfo);
	  }
	 
	  //get the children for the group
	  ArrayList<DetailListe> productList = headerInfo.getProductList();
	  //size of the children list
	  int listSize = productList.size();
	  //add to the counter
	  listSize++;
	 
	  //create a new child and add that to the group
	  DetailListe detailInfo = new DetailListe();
	  detailInfo.setSequence(String.valueOf(listSize));
	  detailInfo.setName(product);
	  productList.add(detailInfo);
	  headerInfo.setProductList(productList);
	 
	  //find the group position inside the list
	  groupPosition = deptList.indexOf(headerInfo);
	  return groupPosition;
	 }
	 

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
