package dev.matyaqubov.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.matyaqubov.myapplication.R;
import dev.matyaqubov.myapplication.listener.OnBottomReachedListener;
import dev.matyaqubov.myapplication.model.Member;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    ArrayList<Member> members;
    OnBottomReachedListener listener;


    public void setMembers(ArrayList<Member> members) {
        this.members=(members);
    }

    public MyRecyclerViewAdapter(ArrayList<Member> members ) {
        this.members = members;
    }

//    public MyRecyclerViewAdapter(ArrayList<Member> members ,OnBottomReachedListener listener) {
//        this.members = members;
//        this.listener=listener;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact,parent,false);
        return new ViewHolder(view);

    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (position==members.size()-1){
            listener.onBottomReached(position);
        }

        Member member=members.get(position);
        holder.nameTextView.setText(member.getName());
        holder.phoneTextView.setText(member.getPhoneNumber());
        holder.personImageView.setImageResource(member.getImage());
        holder.phoneImageView.setImageResource(member.getPhoneImage());

    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView nameTextView, phoneTextView;
        ImageView personImageView, phoneImageView;

        public ViewHolder(@NonNull View view) {
            super(view);

            nameTextView = view.findViewById(R.id.tv_name);
            phoneTextView = view.findViewById(R.id.tv_phone_number);
            personImageView = view.findViewById(R.id.img_photo);
            phoneImageView = view.findViewById(R.id.img_phone);
            this.view = view;
        }
    }
}
