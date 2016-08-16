package fiftyfive.and_testfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductsListActivity extends AppCompatActivity {

    ListView availableProducts ;
    private FirebaseAnalytics mFirebaseAnalytics;
    ArrayList<Item> produitsDispo  = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //Création & remplissage de la liste de produits proposés

        produitsDispo = fillCatalogue(produitsDispo);

        //envoi du tag e-commerce "viewList"
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "console");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM_LIST, bundle);
        Log.d("TAG: ", "VIEW_ITEM_LIST sent.");

        //Récupération de la listview créée dans le fichier activity_products_list.xml
        availableProducts = (ListView) findViewById(R.id.listviewproducts);

        //Création de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

        //On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;

        for (Integer i=0; i<produitsDispo.size(); i++){
            //Création d'une HashMap pour insérer les informations du premier item de notre listView
            map = new HashMap<String, String>();
            //on insère un élément titre que l'on récupérera dans le textView titre créé dans le fichier affichageitem.xml
            map.put("name", produitsDispo.get(i).name);
            //on insère un élément description que l'on récupérera dans le textView description créé dans le fichier affichageitem.xml
            map.put("price", produitsDispo.get(i).price.toString()+ "€");
            //on insère la référence à l'image (convertit en String car normalement c'est un int) que l'on récupérera dans l'imageView créé dans le fichier affichageitem.xml
            //map.put("img", String.valueOf(R.drawable.word));
            //enfin on ajoute cette hashMap dans la arrayList
            listItem.add(map);
        }

        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.affichageitem,
                new String[] {"name", "price"}, new int[] {R.id.name, R.id.price});

        //On attribut à notre listView l'adapter que l'on vient de créer
        availableProducts.setAdapter(mSchedule);

        //Enfin on met un écouteur d'évènement sur notre listView
        final ArrayList<Item> finalProduitsDispo = produitsDispo;
        availableProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                //on récupère la HashMap contenant les infos de notre item (titre, description, img)
                HashMap<String, String> map = (HashMap<String, String>) availableProducts.getItemAtPosition(position);
                Intent zeIntent = new Intent(ProductsListActivity.this, ProductDetail.class);
                Bundle zeBundle = produitsDispo.get(position).itemToBundle();
                zeIntent.putExtra("item", zeBundle); // pour passer le data layer
                startActivityForResult(zeIntent, 0);

            }
        });

    }

    public ArrayList<Item> fillCatalogue(ArrayList<Item> catalogue){
        catalogue.add(new Item("123456", "Playstation 4", "Console de salon", "Sony Corporation", "Uncharted edition", 399.99));
        catalogue.add(new Item("098765", "Xbox One", "Console de salon", "Microsoft", "30 Go", 290.00));
        catalogue.add(new Item("135791", "PSP Street", "Console portable", "Sony Corporation", "PSP Street Fifa 16 Edition", 99.90));
        return catalogue;
    }
}
