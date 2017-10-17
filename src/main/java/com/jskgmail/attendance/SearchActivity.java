package com.jskgmail.attendance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
static ListView list,friendlist;
    ListViewAdaptersea adapter;
    ListViewAdapteraddfri fradapter;
    SearchView search;
    String[] names;
     int i;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<String> arrayList1=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        names=new String[]{"ab","dbd","ffddf","gfgf"
        };
        list=(ListView)findViewById(R.id.listsearch);
         friendlist=(ListView)findViewById(R.id.friendlist);

        String percent=  MainActivity.percentagesending;
        TextView t=(TextView)findViewById(R.id.textView55);
        t.setText("My overall % : "+percent+" %");
        TextView tt=(TextView)findViewById(R.id.textView42);
tt.setText("Logged in as \n"+ConnectActivity.mynaam+"\n("+ConnectActivity.usernamee+")");

        search= (SearchView) findViewById(R.id.searchView);
        search.setOnQueryTextListener(this);



    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }
    int kk=0;
    @Override
    public boolean onQueryTextChange(final String newText) {
        String text=newText;
        Log.v("sosos",text);





        final ArrayList<String> arrayList2=new ArrayList<>();
        final ArrayList<String> arrayList22=new ArrayList<>();


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("user");



        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    if ((( dataSnapshot1.getKey()).toLowerCase().contains(newText)) || ((String.valueOf(dataSnapshot1.child("name").getValue()).toLowerCase().contains(newText))))
                    {

                        Log.d("soso",  dataSnapshot1.getKey());
                        Log.d("sosooo", "" + dataSnapshot1.child("name").getValue());
                        arrayList2.add("" + dataSnapshot1.getKey());
                        arrayList22.add(" : " + dataSnapshot1.child("name").getValue());

                    }

                }
                adapter=new ListViewAdaptersea(SearchActivity.this,arrayList2,arrayList22);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.d("sowhat",String.valueOf(arrayList2.get(position)));

                    }
                });
                list.setAdapter(adapter);


            } @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
















        return false;
    }







   void goo(final String s)
    {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("user");



        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                 if( dataSnapshot1.getKey().equals(s)) {
                     Log.d("soso", dataSnapshot1.getKey());
                     Log.d("sosooo", "" + dataSnapshot1.child("name").getValue());
                     Log.d("sosooperc", "" + dataSnapshot1.child("percent").getValue());

                 }
                }


            } @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
























    }


  static void addfriend(String friendusername, String friendname)
    {

    }






}
