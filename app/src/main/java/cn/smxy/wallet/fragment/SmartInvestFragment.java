package cn.smxy.wallet.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import cn.smxy.wallet.FragmentAdapter.CrtproAdapter;
import cn.smxy.wallet.FragmentAdapter.StockAdapter;
import cn.smxy.wallet.R;
import cn.smxy.wallet.entity.Domain;

public class SmartInvestFragment extends Fragment {
    private View view;
    private RecyclerView cryptoView,stockView;
    ArrayList<Integer> lineData1 = new ArrayList<>();
    ArrayList<Integer> lineData2 = new ArrayList<>();
    ArrayList<Integer> lineData3 = new ArrayList<>();
    public SmartInvestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_smart_invest, container, false);
        setData();

        recyclerViewCrypto();
        recyclerViewStock();
        return view;
    }

    private void recyclerViewCrypto() {
        cryptoView = view.findViewById(R.id.cryptoView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        cryptoView.setLayoutManager(layoutManager);

        ArrayList<Domain> domainArrayList = new ArrayList<>();
        domainArrayList.add(new Domain("bitcoin","BTX",1244.58,2.13,lineData1,1244.58,0.1234));
        domainArrayList.add(new Domain("etherium","ETH",5842.27,-1.13,lineData2,5842.27,0.12345));
        domainArrayList.add(new Domain("trox","ROX",8524.25,0.73,lineData3,8524.25,0.0241));
        cryptoView.setAdapter(new CrtproAdapter(domainArrayList));
    }
    private void recyclerViewStock() {
        stockView = view.findViewById(R.id.stockView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        stockView.setLayoutManager(layoutManager);

        ArrayList<Domain> domainArrayList2 = new ArrayList<>();
        domainArrayList2.add(new Domain("NASDAQ100","BTX",1244.58,2.13,lineData1,1244.58,0.1234));
        domainArrayList2.add(new Domain("S&P 500","ETH",5842.27,-1.13,lineData2,5842.27,0.12345));
        domainArrayList2.add(new Domain("Dow Jones","ROX",8524.25,0.73,lineData3,8524.25,0.0241));
        stockView.setAdapter(new StockAdapter(domainArrayList2));
    }

    private void setData() {
        lineData1.add(1000);
        lineData1.add(1100);
        lineData1.add(1200);
        lineData1.add(1100);

        lineData2.add(2100);
        lineData2.add(2000);
        lineData2.add(1900);
        lineData2.add(2000);

        lineData3.add(900);
        lineData3.add(1100);
        lineData3.add(1200);
        lineData3.add(1000);
    }
}