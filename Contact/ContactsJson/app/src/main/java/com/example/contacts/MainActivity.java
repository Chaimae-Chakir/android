package com.example.contacts;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button call;
    private ListView lv;
    private User user;
    private static ObjectMapper mapper = new ObjectMapper();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<User> userList = new ArrayList<>();
        try {
            List<User> contacts = Arrays.asList(mapper.readValue(getResources().openRawResource(R.raw.data), User[].class));
            for(User user1: contacts)
                userList.add(user1);
            monAdaptateur myAdapter = new monAdaptateur(userList);
            lv = findViewById(R.id.listview);
            lv.setAdapter(myAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MainActivity.this,UserActivity.class);
                    //User user = (User) lv.getSelectedItem();
                    user= (User) lv.getItemAtPosition(i);
                    String name = user.getName();
                    String profession = user.getProfession();
                    String phone_profile = user.getPhone_profile();
                    String email =user.getEmail();
                    String image=user.getImage();

                    Bundle bundle = new Bundle();
                    bundle.putString("name",name);
                    bundle.putString("phone_profile",phone_profile);
                    bundle.putString("profession",profession);
                    bundle.putString("email",email);
                    bundle.putString("profile_image",image);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void Call(View view) {
        if (user != null){
        call = findViewById(R.id.call);
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+user.getPhone_profile()));
        startActivity(intent);
        }
    }

    class monAdaptateur extends BaseAdapter {
        ArrayList<User> usersList = new ArrayList<User>();
        public monAdaptateur(ArrayList<User> users) {
            this.usersList = users;
        }
        public int getCount() {
            return usersList.size();
        }
        public User getItem(int i) {
            return usersList.get(i);
        }
        public long getItemId(int i) {
            return i;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater linflater = getLayoutInflater();
            View view1 = linflater.inflate(R.layout.list_item, null);
            TextView name = view1.findViewById(R.id.personName);
            TextView phone_profile = view1.findViewById(R.id.phone_profile);
            ImageView imageView=view1.findViewById(R.id.profile_pic);
            name.setText(usersList.get(i).getName());
            phone_profile.setText(usersList.get(i).getPhone_profile());
            String imageName = usersList.get(i).getImage();
            int imageResource = getResources().getIdentifier(imageName, "drawable", getPackageName());
            imageView.setImageResource(imageResource);

            return view1;
        }
    }
}
