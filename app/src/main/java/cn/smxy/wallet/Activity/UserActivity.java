package cn.smxy.wallet.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cn.smxy.wallet.Adapter.ContactsAdapter;
import cn.smxy.wallet.Adapter.RecentlyPaidAdapter;
import cn.smxy.wallet.FragmentAdapter.CrtproAdapter;
import cn.smxy.wallet.R;
import cn.smxy.wallet.entity.Domain;
import cn.smxy.wallet.entity.UserDemo;

public class UserActivity extends AppCompatActivity {
    private RecyclerView contactsView,recentlyPaidView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        contacts();
        recent();
    }

    private void recent() {
        recentlyPaidView = findViewById(R.id.recently_paid_view);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(UserActivity.this,RecyclerView.HORIZONTAL,false);
        recentlyPaidView.setLayoutManager(layoutManager2);

        ArrayList<UserDemo> userDemoArrayList2 = new ArrayList<>();
        userDemoArrayList2.add(new UserDemo("heading","Chakrika Joyanto","Lexington"));
        userDemoArrayList2.add(new UserDemo("heading","Miglena Tadic","Durban"));
        userDemoArrayList2.add(new UserDemo("heading","Bob Kerton","contacts"));
        userDemoArrayList2.add(new UserDemo("heading","Chakrika Joyanto","Lexington"));
        userDemoArrayList2.add(new UserDemo("heading","Miglena Tadic","Durban"));
        userDemoArrayList2.add(new UserDemo("heading","Bob Kerton","contacts"));
        recentlyPaidView.setAdapter(new RecentlyPaidAdapter(userDemoArrayList2));
    }

    private void contacts() {
        contactsView = findViewById(R.id.contact_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(UserActivity.this,RecyclerView.VERTICAL,false);
        contactsView.setLayoutManager(layoutManager);

        ArrayList<UserDemo> userDemoArrayList = new ArrayList<>();
        userDemoArrayList.add(new UserDemo("heading","Chakrika Joyanto","Lexington"));
        userDemoArrayList.add(new UserDemo("heading","Miglena Tadic","Durban"));
        userDemoArrayList.add(new UserDemo("heading","Bob Kerton","contacts"));
        userDemoArrayList.add(new UserDemo("heading","Chakrika Joyanto","Lexington"));
        userDemoArrayList.add(new UserDemo("heading","Miglena Tadic","Durban"));
        userDemoArrayList.add(new UserDemo("heading","Bob Kerton","contacts"));
        contactsView.setAdapter(new ContactsAdapter(userDemoArrayList));


    }
}