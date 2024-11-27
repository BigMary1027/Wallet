package cn.smxy.wallet.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.hhmedic.android.sdk.okhttputils.okhttp.OkHttpUtils;
import com.hhmedic.android.sdk.okhttputils.okhttp.callback.StringCallback;

import java.util.List;

import cn.smxy.wallet.Adapter.SliceAdapter;
import cn.smxy.wallet.R;
import cn.smxy.wallet.entity.Card;
import cn.smxy.wallet.entity.CardResponse;
import okhttp3.Call;


public class CliceFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private int id;
    private SliceAdapter sliceAdapter;
    public void setType(int id){
        this.id = id;
    }
    public CliceFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_clice, container, false);

        recyclerView = view.findViewById(R.id.faslice_list_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        getCardList();

        return view;
    }
    private void getCardList(){
        String url = "http://192.168.158.1:8089/flowerServer/card/findByCardId";
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("TAG", "onError: " + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("TAG", "onResponse: " + response);
                CardResponse cardResponse = new Gson().fromJson(response,CardResponse.class);
                if (cardResponse != null && cardResponse.getCode() == 2000){
                    List<Card> cardList = cardResponse.getDataobject();
                    sliceAdapter = new SliceAdapter(getContext(),cardList);
                    recyclerView.setAdapter(sliceAdapter);
                }
            }
        });
    }
}