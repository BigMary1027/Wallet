package cn.smxy.wallet.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.smxy.wallet.FragmentAdapter.CrtproAdapter;
import cn.smxy.wallet.FragmentAdapter.HistoryAdapter;
import cn.smxy.wallet.R;
import cn.smxy.wallet.entity.Domain;
import cn.smxy.wallet.entity.PersonWallet;

public class HistoryFragment extends Fragment {
    private View view;
    private RecyclerView itemList;
    ArrayList<Integer> lineData1 = new ArrayList<>();
    ArrayList<Integer> lineData2 = new ArrayList<>();
    ArrayList<Integer> lineData3 = new ArrayList<>();
    public HistoryFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_history, container, false);
        initView();
        return view;
    }

    private void initView() {
        itemList = view.findViewById(R.id.item_list_rec);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        itemList.setLayoutManager(layoutManager);

        ArrayList<PersonWallet> personWalletArrayList = new ArrayList<>();
        personWalletArrayList.add(new PersonWallet("Jack Bob","heading","12543757",252.13));
        personWalletArrayList.add(new PersonWallet("Mcrch Mary","heading","12586347",-177.13));
        personWalletArrayList.add(new PersonWallet("Job Big","heading","12574375",340.73));
        personWalletArrayList.add(new PersonWallet("Marliay Big","heading","45867254",340.73));
        itemList.setAdapter(new HistoryAdapter(personWalletArrayList));
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