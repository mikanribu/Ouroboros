package epf.domethic.ouroboros.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
 
import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockFragment;

import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.adapter.MenuGaucheHospiDMPAdapter;
 
public class ListeGaucheHospiDMPFragment extends SherlockFragment {
    private ExpandableListView mExpandableList;
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
    	View view = inflater.inflate(R.layout.fragment_liste_hospi_dmp,container, false);
 
        mExpandableList = (ExpandableListView)view.findViewById(R.id.menu_gauche_hospi);
        
        //Méthode temporaire pour avoir un menu propre en attendant d'aller chercher dans la bdd
        ArrayList<String> arrayNomParents = new ArrayList<String>();
        arrayNomParents.add("Analyses");
        arrayNomParents.add("Examens");
        arrayNomParents.add("Compte-rendus");
        arrayNomParents.add("Radiographies");
        arrayNomParents.add("Fiches de suivi");
        arrayNomParents.add("Consultations"); 
        
        ArrayList<Parent> arrayParents = new ArrayList<Parent>();
        ArrayList<String> arrayChildren = new ArrayList<String>();
 
        //here we set the parents and the children
        for (int i = 0; i < arrayNomParents.size(); i++){
            //for each "i" create a new Parent object to set the title and the children
            Parent parent = new Parent();
            parent.setTitle(arrayNomParents.get(i));
             
            //Récupérer ici les enfants
            arrayChildren = new ArrayList<String>();
            for (int j = 0; j < 3; j++) {
                arrayChildren.add("Child " + j);
            }
            parent.setArrayChildren(arrayChildren);
 
            //in this array we add the Parent object. We will use the arrayParents at the setAdapter
            arrayParents.add(parent);
        }
 
        //sets the adapter that provides data to the list.
        mExpandableList.setAdapter(new MenuGaucheHospiDMPAdapter(getSherlockActivity(),arrayParents));
        
        return view;
 
    }
    
    public class Parent {
        private String mTitle;
        private ArrayList<String> mArrayChildren;
     
        public String getTitle() {
            return mTitle;
        }
     
        public void setTitle(String mTitle) {
            this.mTitle = mTitle;
        }
     
        public ArrayList<String> getArrayChildren() {
            return mArrayChildren;
        }
     
        public void setArrayChildren(ArrayList<String> mArrayChildren) {
            this.mArrayChildren = mArrayChildren;
        }
    }
}