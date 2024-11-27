package cn.smxy.wallet.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import cn.smxy.wallet.R;
import cn.smxy.wallet.fragment.HistoryFragment;
import cn.smxy.wallet.fragment.MyWalletsFragment;
import cn.smxy.wallet.fragment.NotificationFragment;
import cn.smxy.wallet.fragment.SmartInvestFragment;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private HistoryFragment coinMarketFragment;
    private MyWalletsFragment myWalletsFragment;
    private NotificationFragment notificationFragment;
    private SmartInvestFragment smartInvestFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        setListener();
        changeFragment(myWalletsFragment);
    }
    private void initView(){
        bottomNavigationView = findViewById(R.id.user_main_bottom_navigation);
        coinMarketFragment = new HistoryFragment();
        myWalletsFragment = new MyWalletsFragment();
        notificationFragment = new NotificationFragment();
        smartInvestFragment = new SmartInvestFragment();
    }
    private void setListener(){
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.my_wallets){
                    changeFragment(myWalletsFragment);
                }else if (itemId == R.id.smart_invest){
                    changeFragment(smartInvestFragment);
                }else if (itemId == R.id.coin_market){
                    changeFragment(coinMarketFragment);
                }else if (itemId == R.id.notification){
                    changeFragment(notificationFragment);
                }
                return true;
            }
        });
    }
    private void changeFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.user_main_content,fragment);
        transaction.commit();
    }
}