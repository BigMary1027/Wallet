package cn.smxy.wallet.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import cn.smxy.wallet.Activity.LinkYourCardActivity;
import cn.smxy.wallet.Activity.UserActivity;
import cn.smxy.wallet.Activity.allCardActivity;
import cn.smxy.wallet.Activity.qRCodeActivity;
import cn.smxy.wallet.FragmentAdapter.MyWalletAdapter;
import cn.smxy.wallet.R;
import cn.smxy.wallet.entity.AllCard;

public class MyWalletsFragment extends Fragment {
    private View view;
    private ImageView allCard,search;
    private View scanGo,qRCode,linkCards,addCard,tranferHistory;
    private RecyclerView myCardView;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private List<Fragment> fragmentList;
    public MyWalletsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_wallets, container, false);
        initView();
        setOnclick();
        getCardList();
        return view;
    }
    private void getCardList(){
        myCardView = view.findViewById(R.id.myCardView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        myCardView.setLayoutManager(layoutManager);

        ArrayList<AllCard> allCardArrayList = new ArrayList<>();
        allCardArrayList.add(new AllCard("v","12543757",252.13));
        allCardArrayList.add(new AllCard("ic_mastercard","12586347",77.13));
        allCardArrayList.add(new AllCard("v","12574375",340.73));
        allCardArrayList.add(new AllCard("ic_mastercard","45867254",140.73));
        myCardView.setAdapter(new MyWalletAdapter(allCardArrayList));
    }
    private void initView(){
//        tabLayout=view.findViewById(R.id.tablayout);
//        viewPager2=view.findViewById(R.id.viewpager2);

        scanGo = view.findViewById(R.id.scan_go);
        qRCode = view.findViewById(R.id.qr_code);
        linkCards = view.findViewById(R.id.link_cards);
        addCard = view.findViewById(R.id.add_cards);
        allCard = view.findViewById(R.id.all_card);
//        tranferHistory = view.findViewById(R.id.tranfer_history);
        search = view.findViewById(R.id.my_wallet_search);
    }
    private void setOnclick(){
        //        二维码
        scanGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Initialize intent integrator
                IntentIntegrator intentIntegrator = new IntentIntegrator(getActivity());
//                Set prompt
                intentIntegrator.setPrompt("For flash use volume up key");
                // Set beep
                intentIntegrator.setBeepEnabled(true);
                // Locked orientation
                intentIntegrator.setOrientationLocked(true);
                // Set capture activity
                intentIntegrator.setCaptureActivity(Capture.class);
                // Initiate scan
                intentIntegrator.initiateScan();
            }
        });
        //扫描二维码
        qRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), qRCodeActivity.class);
                startActivity(intent);
            }
        });
//        添加卡
        linkCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LinkYourCardActivity.class);
                startActivity(intent);
            }
        });
        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LinkYourCardActivity.class);
                startActivity(intent);
            }
        });
        allCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), allCardActivity.class);
                startActivity(intent);
            }
        });
//        tranferHistory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), ChartActivity.class);
//                startActivity(intent);
//            }
//        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UserActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Initialize intent result
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        //Check condition
        if ((intentResult.getContents() != null)){
            //When result content is not null
            //Initialize alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            //Set title
            builder.setTitle("Result");
            //Ser message
            builder.setMessage(intentResult.getContents());
            //Set positive button
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Dismiss dialog
                    dialog.dismiss();
                }
            });
            //Show alert dialog
            builder.show();
        }else {
            //When result content is null
            Toast.makeText(getActivity().getApplicationContext(), "OOPS..You did not scan anything",Toast.LENGTH_SHORT).show();
        }
    }
}