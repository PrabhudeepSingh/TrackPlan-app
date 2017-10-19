package com.jskgmail.attendance;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConnectActivity extends AppCompatActivity {
  static   String mynaam="",usernamee="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);


        final EditText name=(EditText)findViewById(R.id.editText3);
        final EditText username=(EditText)findViewById(R.id.editText4);



        SharedPreferences sp1=this.getSharedPreferences("login",MODE_PRIVATE);
        String unm=sp1.getString("username","");
        String nam=sp1.getString("name","");
        if(!(unm.equals("")))
        {
            username.setText(unm);
            name.setText(nam);
        }







        Button button=(Button)findViewById(R.id.button7) ;
        final ProgressBar p=(ProgressBar)findViewById(R.id.progressBar2) ;
        p.setVisibility(View.INVISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int[] check = {2};
final String TAG="what";
                mynaam=name.getText().toString();
                usernamee=username.getText().toString();
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference("user");
                DatabaseReference myRef1 = database.getReference("user").child(usernamee);
                p.setVisibility(View.VISIBLE);
                p.setIndeterminate(true);
                myRef.addValueEventListener(new ValueEventListener() {



                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        int ch=0;
                        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                        {

                            if(!(dataSnapshot1.getKey().equals(usernamee)))
                            {ch++;
                                Log.d("noofchild", String.valueOf(dataSnapshot.getChildrenCount()));
                                if(ch==dataSnapshot.getChildrenCount())
                                {  p.setIndeterminate(false); p.setVisibility(View.INVISIBLE);






                                    if(MainsettingActivity.don.equals("1"))
                                    {
                                        Toast.makeText(getApplicationContext(),"Logged in as "+mynaam+" ("+usernamee+")",Toast.LENGTH_LONG).show();
                                        finish();
                                        MainsettingActivity.don="2";
                                    }
                                    else{go();}


                                    SharedPreferences sp1=getSharedPreferences("login",MODE_PRIVATE);
                                    final String unm=sp1.getString("username","");
                                    String nam=sp1.getString("name","");
                                    if(!(unm.equals(""))) {

                                        if(!((unm.equals(usernamee))&&(nam.equals(mynaam))))
                                        {



                                            final FirebaseDatabase database = FirebaseDatabase.getInstance();
                                            final DatabaseReference myRef = database.getReference("user");



                                            myRef.addListenerForSingleValueEvent(new ValueEventListener() {

                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    // This method is called once with the initial value and again
                                                    // whenever data at this location is updated.
                                                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                                      if( dataSnapshot1.getKey().equals(unm)) {
                                                          Log.d("soso", dataSnapshot1.getKey());
                                                          Log.d("sosooo", "" + dataSnapshot1.child("name").getValue());
                                                          Log.d("sosooperc", "" + dataSnapshot1.child("percent").getValue());
                                                          dataSnapshot1.getRef().removeValue();
                                                      }

                                                    }


                                                } @Override
                                                public void onCancelled(DatabaseError databaseError) {

                                                }
                                            });
































                                        }


                                    }





                                    SharedPreferences sp=getSharedPreferences("login",MODE_PRIVATE);
                                    SharedPreferences.Editor ed=sp.edit();
                                    ed.putString("username",usernamee);
                                    ed.putString("name",mynaam);
                                    ed.commit();









                                }

                            }else
                            {


                                {Log.d("plzzz", ""+dataSnapshot1.getKey());
                                    check[0] =1;
                                    p.setIndeterminate(false); p.setVisibility(View.INVISIBLE);
                                    gosetalert();

                                    break;
                                }









                            }

                        }

                    }



                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });



Log.e("plzz", String.valueOf(check[0]));

























                    }
        });











    }






    void go() {


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("user");
        DatabaseReference myRef1 = database.getReference("user").child(usernamee);



        myRef1.child("name").setValue(mynaam);
        myRef1.child("percent").setValue(MainActivity.percentagesending);






        Log.d("djfdjfdfjdfn",usernamee);

finish();
        Intent i=new Intent(ConnectActivity.this,SearchActivity.class);
        startActivity(i);






































    }


















    private void gosetalert() {









        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layouterror, null);






        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setTitle(" Error ");
        alert.setIcon(R.drawable.ic_error_outline_black_24dp);



        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {

dialog.cancel();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();


















    }
}
