package com.example.asaf2work;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Friend> FriendList;
    FriendAdapter FA;
//    Button btn;
    Friend lastSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bitmap content = BitmapFactory.decodeResource(getResources(), R.drawable.images);
        Bitmap content2 = BitmapFactory.decodeResource(getResources(), R.drawable.images2);
//        btn = (Button) findViewById(R.id.add_action);
        Friend Friend1 = new Friend("2121485", "Bomj", content);
        Friend Friend2 = new Friend("2121487", "Shlepok", content2);
        FriendList = new ArrayList<Friend>();
        FriendList.add(Friend1);
        FriendList.add(Friend2);
        FA = new FriendAdapter(this, 0, FriendList);
        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(FA);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lastSelected = FA.getItem(position);
                Intent intent = new Intent(MainActivity.this, Edit.class);
                intent.putExtra("Name", lastSelected.getName());
                intent.putExtra("PhoneNumber", lastSelected.getPhonenumber());
                intent.putExtra("bitmap", Bitmap.createScaledBitmap(lastSelected.getPicture(),300,300,false));

                startActivityForResult(intent, 0);


            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                lastSelected = FA.getItem(position);
                FA.remove(lastSelected);
                FA.notifyDataSetChanged();
                return false;
            }
        });
    }


    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String Name = data.getExtras().getString("Name");
                String PhoneNumber = data.getExtras().getString("PhoneNumber");
                Bitmap bitmap = Helper.byteArrayToBitmap(data.getExtras().getByteArray("bitmap"));
                lastSelected.setName(Name);
                lastSelected.setPhonenumber(PhoneNumber);
                lastSelected.setPicture(bitmap);
                FA.notifyDataSetChanged();
                Toast.makeText(this, "data saved", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "user cancel", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == 1) {
            if(data!=null){
                if (resultCode == RESULT_OK) {
                    String lastName = data.getExtras().getString("Name");
                    String numOfPeople = data.getExtras().getString("PhoneNumber");
                    Bitmap bitmap = Helper.byteArrayToBitmap(data.getExtras().getByteArray("bitmap"));
                    Friend friend = new Friend(lastName, numOfPeople, bitmap);
                    FA.add(friend);
                    FA.notifyDataSetChanged();
                    Toast.makeText(this, "data saved", Toast.LENGTH_LONG).show();
            }

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "user cancel", Toast.LENGTH_LONG).show();
            }

        }


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.add_action)

        {
            Intent intent = new Intent(this,Edit.class);

            startActivityForResult(intent,1);

            return true;

        }

        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        for (int j = 0; j < menu.size(); j++) {
            MenuItem item = menu.getItem(j);
            item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
        return true;
    }
}