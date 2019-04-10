package com.creaginetech.xpreshoesadmin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.creaginetech.xpreshoesadmin.Common.Common;
import com.creaginetech.xpreshoesadmin.Interface.ItemClickListener;
import com.creaginetech.xpreshoesadmin.Model.Shop;
import com.creaginetech.xpreshoesadmin.ViewHolder.ShopViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ShopListActivity extends AppCompatActivity {

    //var search bar
    private TextView txtSearchBar;
    private Button btnAddShop;

    //var recycler
    RecyclerView recyclerView;

    //firebase recycler adapter
    FirebaseRecyclerAdapter<Shop, ShopViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        //init search bar
        txtSearchBar = findViewById(R.id.search_field);
        btnAddShop = findViewById(R.id.buttonAddShop);

        //init recycler shop
        recyclerView = findViewById(R.id.recycler_shop);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(Common.isConnectedToInternet(getBaseContext())) //cp15 check intrnt

            //load list shop
            loadShop();

        else {

            //check internet connnection
            Toast.makeText(getBaseContext(), "Please Check your connection", Toast.LENGTH_SHORT).show();

        }

        txtSearchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ShopListActivity.this, SearchActivity.class);
                startActivity(intent);

            }
        });

        btnAddShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ShopListActivity.this, AddShopActivity.class);
                startActivity(intent);

            }
        });

    }

    //method load shop
    private void loadShop() {

        //firebase recycler, model Shop
        FirebaseRecyclerOptions<Shop> options = new FirebaseRecyclerOptions.Builder<Shop>()
                .setQuery(FirebaseDatabase.getInstance()
                                .getReference()
                                .child("Shop")
                        ,Shop.class)
                .build();

        //recycler adapter shop - ShopViewHolder
        adapter = new FirebaseRecyclerAdapter<Shop, ShopViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ShopViewHolder viewHolder, int position, @NonNull Shop model) {

                viewHolder.txt_shop_name.setText(model.getShopName());
                Picasso.with(getBaseContext()).load(model.getShopImage())
                        .into(viewHolder.img_shop);

                final Shop clickItem = model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        //Get CategoryId and send to new Activity
//                        Intent serviceList = new Intent(ShopListActivity.this, ServiceListActivity.class);
//
//                        //When user select shop, we will save shop id to select service of this shop
//                        Common.shopSelected = adapter.getRef(position).getKey();
//
//                        startActivity(serviceList);

                    }
                });
            }

            @Override
            public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.shop_item, parent, false);
                return new ShopViewHolder(itemView);
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        //show item in service list when click back from service detail
        if (adapter != null)
            adapter.startListening();
    }

}
