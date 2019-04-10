package com.creaginetech.xpreshoesadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnShop,btnShipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShop = findViewById(R.id.buttonShop);
        btnShipper = findViewById(R.id.buttonShipper);

        btnShop.setOnClickListener(this);
        btnShipper.setOnClickListener(this);

        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ShopListActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.buttonShop){
            Intent intent = new Intent(MainActivity.this, ShopListActivity.class);
            startActivity(intent);
        } else if (i == R.id.buttonShipper){
            Intent intent = new Intent(MainActivity.this, AddShipperActivity.class);
            startActivity(intent);
        }
    }
}
