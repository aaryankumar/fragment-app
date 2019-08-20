package com.example.fragmentapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter adapter;
    //ArrayList<ModelApp> itemLists;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragement_home, container, false);
        recyclerView = view.findViewById(R.id.rv);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("event");
        mDatabase.keepSynced(true);
        /*itemLists=new ArrayList<>();

        itemLists.add(new ModelApp(R.drawable.biryani,"gsnbyujuyjvgukrj","tshrhjkbtcrtjulihtyvukltiulvbyktvuvtukyvbkyj6ctiulh","eybyhr","tyybj","ybyt"));
        itemLists.add(new ModelApp(R.drawable.biryani,"uy.kbk,hgu","kvttuilvtykuilkhrtrecttvi;vbrtcjyjtgvlrrtvrtkuykrtv5ey7ct6u7vrt","vvtyvt","jhfvttr","ybyt"));
        itemLists.add(new ModelApp(R.drawable.biryani,"tyv,jhkbttbutbk7ty","rtkuk,utc5tucy6756rc","kuytd","zsryk","ybyt"));
        itemLists.add(new ModelApp(R.drawable.biryani,"tyfcecwccuy;8vty","rt5 kbuio;ojhut7t67fvr5yrk7vt6l687t78kuyui6rc","kuytd","zsryk","ybyt"));
        itemLists.add(new ModelApp(R.drawable.biryani,"tyvrcukluyvty","rt56uvy5rv5,jhvwscy54ascwt8l7te l,iit77847y689l9vyirc","kuytd","zsryk","ybyt"));
        itemLists.add(new ModelApp(R.drawable.biryani,"hvbgbrt6cr4i67hgg","jt6reughcawexcttyolcv9fyuvykiufuf78","jjkjhn","zsryk","ybyt"));
        itemLists.add(new ModelApp(R.drawable.biryani,"hjfguilbiuy7vbli9jfkl","7678juimk;jd4ukuguytyvtlibol6fg69pvb89ds4wdce57ikcfbyty87vt9yhbik","kl;jjh","zsryk","ybyt"));
        itemLists.add(new ModelApp(R.drawable.biryani,"essuk,tvuykuyiyoo","bvcjbvrcy6cryuulkduy","klljiouuo","zsryk","ybyt"));
        itemLists.add(new ModelApp(R.drawable.biryani,"gvghy","jnhgukh,ybtvu7i7y7ukiluihfrtuo","hjgyu","zsryk","ybyt"));

*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager rvLiLayoutManager = layoutManager;
        recyclerView.setLayoutManager(rvLiLayoutManager);

        // AppAdapter adapter=new AppAdapter(getContext(),itemLists);


        //recyclerView.setAdapter(adapter);

        fetch();

        return view;
    }


    public class recycleViewHolder extends RecyclerView.ViewHolder {

        View mview;

        public recycleViewHolder(View itemView) {
            super(itemView);
            mview = itemView;
        }

        public void setHead(String head) {

            TextView head1 = mview.findViewById(R.id.head);
            head1.setText(head);
        }

        public void setDetail(String detail) {
            TextView detail1 = mview.findViewById(R.id.det);
            detail1.setText(detail);
        }

        public void setDate(String date) {

            TextView date1 = mview.findViewById(R.id.dat);
            date1.setText(date);
        }

        public void setVenue(String venue) {
            TextView venue1 = mview.findViewById(R.id.ven);
            venue1.setText(venue);
        }

        public void setLink(String link) {
            TextView link1 = mview.findViewById(R.id.lin);
            link1.setText(link);
        }

        public void setImage(String img) {

            ImageView img1 = mview.findViewById(R.id.img);
            Picasso.get().load(img).into(img1);
        }


    }





    private void fetch() {

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("event");

        FirebaseRecyclerOptions<ModelApp> options =
                new FirebaseRecyclerOptions.Builder<ModelApp>()
                        .setQuery(query, new SnapshotParser<ModelApp>() {
                            @NonNull
                            @Override
                            public ModelApp parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new ModelApp(snapshot.child("img").getValue().toString(),
                                        snapshot.child("head").getValue().toString(),
                                        snapshot.child("detail").getValue().toString(),
                                        snapshot.child("date").getValue().toString(),
                                        snapshot.child("venue").getValue().toString(),
                                        snapshot.child("link").getValue().toString()


                                );
                            }
                        })
                        .build();

        adapter = new FirebaseRecyclerAdapter<ModelApp, recycleViewHolder>(options) {
            @Override
            public recycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.rvappitems, parent, false);

                return new recycleViewHolder(view);
            }


            @Override
            protected void onBindViewHolder(recycleViewHolder holder, final int position, ModelApp model) {
                holder.setHead(model.getHead());
                holder.setDetail(model.getDetail());
                holder.setDate(model.getDate());
                holder.setVenue(model.getVenue());
                holder.setLink(model.getLink());
                holder.setImage(model.getImg());
            }

        };
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}