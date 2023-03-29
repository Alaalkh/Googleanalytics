package com.example.googleanalytics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.googleanalytics.Adapters.CategoryAdapter;
import com.example.googleanalytics.Adapters.DetailsAdapter;
import com.example.googleanalytics.Models.Categories;
import com.example.googleanalytics.Models.Details;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity  implements DetailsAdapter.ItemClickListener, DetailsAdapter.ItemClickListener2 {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAnalytics mFirebaseAnalytics;

    ArrayList<Details> items;
    DetailsAdapter[] myListData;
    DetailsAdapter adapter;

    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        rv = findViewById(R.id.recyclerview3);
        items = new ArrayList<Details>();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        adapter =new DetailsAdapter(this,items,this,this);
        String title= getIntent().getStringExtra("title");
        getNote(title);
    }
    public  void getNote(String title){
        db.collection("Details").whereEqualTo("note",title).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d("drn", "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            for (DocumentSnapshot documentSnapshot : documentSnapshots) {
                                if (documentSnapshot.exists()) {
                                    String id = documentSnapshot.getId();
                                    String details = documentSnapshot.getString("details");
                                    String date = documentSnapshot.getString("date");
                                    String image = documentSnapshot.getString("image");


                                    Details details1 = new Details(details,id, date ,image);
                                    items.add(details1);

                                    rv.setLayoutManager(layoutManager);
                                    rv.setHasFixedSize(true);
                                    rv.setAdapter(adapter);
                                    ;
                                    adapter.notifyDataSetChanged();
                                    Log.e("LogDATA", items.toString());

                                }
                            }
                        }


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("LogDATA", "get failed with ");


                    }
                });
    }


    @Override
    public void onItemClick2(int position, String id) {
        btnEvent("details","Note details","CardView");
    }
    public  void btnEvent(String id,String name,String content){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, content);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    @Override
    public void onItemClick(int position, String id) {

    }
}