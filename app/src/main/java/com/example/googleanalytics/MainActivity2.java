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
import android.widget.TextView;

import com.example.googleanalytics.Adapters.CategoryAdapter;
import com.example.googleanalytics.Adapters.NotesCategory;
import com.example.googleanalytics.Models.Notes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity  implements NotesCategory.ItemClickListener, NotesCategory.ItemClickListener2 {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
  //  private FirebaseAnalytics mFirebaseAnalytics;
  private FirebaseAnalytics mFirebaseAnalytics;

    ArrayList<Notes> items;
    NotesCategory[] myListData;
    NotesCategory adapter;
   String name;
  private   LinearLayoutManager layoutManager ;
    RecyclerView recycler;
    private TextView msgTV;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recycler = findViewById(R.id.recyclerview2);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        items = new ArrayList<Notes>();
        LinearLayoutManager layoutManager   = new LinearLayoutManager(this);
        adapter =new NotesCategory(this,items,this,this);
       String categoryname= getIntent().getStringExtra("cat");
        msgTV = findViewById(R.id.catname);
        msgTV.setText(getIntent().getStringExtra("cat"));
        getNote(categoryname);
    }
    public  void getNote(String catname){
        db.collection("Notes").whereEqualTo("categoryname",catname).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d("drn", "onSuccess: LIST EMPTY");
                        } else {
                            for (DocumentSnapshot documentSnapshot : documentSnapshots) {
                                if (documentSnapshot.exists()) {
                                    String id = documentSnapshot.getId();
                                    String title = documentSnapshot.getString("title");


                                    Notes notes = new Notes(title,id);
                                    items.add(notes);

                                    recycler.setLayoutManager( new GridLayoutManager(MainActivity2.this,2));
                                    recycler.setHasFixedSize(true);
                                    recycler.setAdapter(adapter);
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
    public void onItemClick(int position, String id) {

    }


    @Override
    public void onItemClick2(int position, String id) {
        btnEvent("notes","notes","CardView");
        Intent intent= new Intent(this,MainActivity3.class);
       intent.putExtra("title", items.get(position).getTitle());
        startActivity(intent);
    }
    public  void btnEvent(String id,String name,String content){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, content);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }
}