package com.example.meteats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.meteats.Database.Database;
import com.example.meteats.ViewHolder.Order;
import com.example.meteats.data.model.category;
import com.example.meteats.ui.data.model.Food;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.security.PKCS12Attribute;

import static android.widget.Toast.makeText;

public class FoodDetail extends AppCompatActivity {

    TextView food_name,food_price,food_description;
    ImageView food_image;
    String foodId="";
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    FirebaseDatabase database;
    DatabaseReference foods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        //Firebase

        database = FirebaseDatabase.getInstance();
        foods= database.getReference("Food");
        final Food currentFood = new Food();
        //Init View
        numberButton =(ElegantNumberButton)findViewById(R.id.number_button);
        btnCart = (FloatingActionButton)findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
           new Database(getBaseContext()).addtoCart(new Order(
                foodId,
                   currentFood.getName(),
                   numberButton.getNumber(),
                   currentFood.getPrice()

           ));
                Toast.makeText(FoodDetail.this,"Added To Cart",Toast.LENGTH_SHORT).show();
            }
        });

        //food_description = (TextView)findViewById(R.id.food_description);
        food_name = (TextView)findViewById(R.id.food_name);
        food_price = (TextView)findViewById(R.id.food_price);
        food_image =(ImageView)findViewById(R.id.img_food );

        collapsingToolbarLayout =(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);


        if(getIntent()!=null) {
          foodId=getIntent().getStringExtra("FoodId");
        }
        if(!foodId.isEmpty()) {

            getDetailFood(foodId);
        }

    }

    private void getDetailFood(String foodId) {


                foods.child(foodId).addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
               category food = dataSnapshot.getValue(category.class);

                //Set Image
                Picasso.with(getBaseContext()).load(food.getImage()).into(food_image);
                collapsingToolbarLayout.setTitle(food.getName());

                food_price.setText(food.getPrice());
                food_name.setText(food.getName());

                System.out.println(food.getName());
                //food_description.setText(food.getDescription());

            }

            @Override

            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




}
