package com.example.piso.logapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.common.UserRecoverableException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InfoActivity extends AppCompatActivity {

  //  private Firebase mDatabase;
  FirebaseDatabase database = FirebaseDatabase.getInstance();

    TextView first,last  ;
    DatabaseReference myRef ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);Firebase.setAndroidContext(this);
       // mDatabase = new Firebase("https://logapp-f4b65.firebaseio.com/") ;
        String Id = "ID";
        if(getIntent().getExtras().containsKey("Email"))
        {
            Id = (String) getIntent().getExtras().get("Email");
        }
        myRef = database.getReference("User");
        if(Id!=null)
        {
            myRef.child(Id).child("username").setValue("p");
            myRef.child(Id).child("email").setValue("mustafa");
        }

        first = (TextView) findViewById(R.id.firstname);
        last  = (TextView) findViewById(R.id.lastname);

    }


    public void commit(View view) {
        String  firstName=  first.getText().toString();
        String  lastName=  last.getText().toString();
   //    Firebase firebase  = mDatabase.child("Name");
     //    firebase.setValue("PisoDone");

        Toast.makeText(this,"Done",Toast.LENGTH_LONG).show();
       // PutNewUser("idDone",firstName,lastName);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.v( "Value is: " , value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.", "failed");
            }
        });
    }

    public class User {

        public String firstname;
        public String lastname;

        public User() {}

        public User(String firstname, String lastname) {
            this.firstname = firstname;
            this.lastname = lastname;
        }


    }
    private void PutNewUser(String userId, String name, String email) {
        User user = new User(name, email);

       // mDatabase.child("users").child(userId).setValue(user);
    }
}
