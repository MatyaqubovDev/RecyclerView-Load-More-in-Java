package dev.matyaqubov.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;

import dev.matyaqubov.myapplication.R;
import dev.matyaqubov.myapplication.adapter.MyRecyclerViewAdapter;
import dev.matyaqubov.myapplication.listener.OnBottomReachedListener;
import dev.matyaqubov.myapplication.model.Member;

public class MainActivity extends AppCompatActivity {
    ArrayList<Member> members;
    Context context;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    RecyclerView rvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void initViews() {
        context=this;
        members=new ArrayList<>();
        prepareMembers();
        rvMain=findViewById(R.id.rv_mian);
        rvMain.setLayoutManager(new GridLayoutManager(context,1));
        adapterRefresh(members);

    }

    public void adapterRefresh(ArrayList<Member> members) {
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(members);

        rvMain.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                GridLayoutManager gridLayoutManager= (GridLayoutManager) rvMain.getLayoutManager();
                int totalItem=gridLayoutManager.getItemCount();
                int lastVisible=gridLayoutManager.findLastVisibleItemPosition();
                boolean endHasBeenReached=lastVisible+5>=totalItem;
                if (totalItem>0 && endHasBeenReached){
                    updateMembers(members);
                    //myRecyclerViewAdapter.notifyDataSetChanged();
                    rvMain.setAdapter(myRecyclerViewAdapter);
                }

            }
        });

        rvMain.setAdapter(myRecyclerViewAdapter);


    }


    private void prepareMembers() {

        for (int i = 0; i < 20; i++) {
            members.add(new Member(R.drawable.icon_person,"Matyaqubov"+i,"+9989423444332"));
        }
    }
    private void updateMembers(ArrayList<Member> memb) {

        for (int i = 0; i < 20; i++) {
            members.add(new Member(R.drawable.icon_person,"Matyaqubov"+(memb.size()+1),"+9989423444332"));
        }
    }
}