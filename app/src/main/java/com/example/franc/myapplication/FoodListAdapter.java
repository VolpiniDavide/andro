package com.example.franc.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class FoodListAdapter extends RecyclerView.Adapter {


    private LayoutInflater mInflater;
    private ArrayList<Food> data;

    public FoodListAdapter(Context context, ArrayList<Food> data) {
        this.data = data;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = mInflater.inflate(R.layout.row, viewGroup, false); // prendo un xml e lo asswgno ad un oggetto di tipo view
        return new FoodViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        FoodViewHolder foodViewHolder = (FoodViewHolder) viewHolder;
        foodViewHolder.productName.setText(data.get(i).getName());
        foodViewHolder.productPrice.setText(data.get(i).getPrezzo());
        foodViewHolder.productQuantity.setText((data.get(i).getQuantity()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView productName;
        public TextView productPrice;
        public TextView productQuantity;
        public Button plusBtn;
        public Button minusBtn;

        public void plusButton(){

            Integer newQuantity = (Integer.parseInt(productQuantity.getText().toString()));
            newQuantity += 1;
            productQuantity.setText(newQuantity.toString());

            Double newPrice = (Double.parseDouble(productPrice.getText().toString()));
            Double totEx = Double.parseDouble(WelcomeActivity.tot.getText().toString());
            totEx += newPrice;
            WelcomeActivity.tot.setText ( totEx.toString() );
        }

        public void minusButton(){

            Integer newQuantity = (Integer.parseInt(productQuantity.getText().toString()));
            if ( newQuantity >= 1){
            newQuantity -= 1;
            productQuantity.setText(newQuantity.toString());

            Double newPrice = (Double.parseDouble(productPrice.getText().toString()));
            Double totEx = Double.parseDouble(WelcomeActivity.tot.getText().toString());
            totEx -= newPrice;
            WelcomeActivity.tot.setText ( totEx.toString() );

        }

        }




        public FoodViewHolder(@NonNull View itemView) { // sono in una clase che non estende l'activity quindi non posso usare diretamente il
            super(itemView);                            // findViewById. ma nel costrutore passo una View, un modello ( la raw in questo caso ).
            // ora posso quindi dire "quellaView.findElementById".
            productName = itemView.findViewById(R.id.item_name);
            productPrice = itemView.findViewById(R.id.prezzo);
            productQuantity = itemView.findViewById(R.id.quantita);
            plusBtn = itemView.findViewById(R.id.piu);
            minusBtn = itemView.findViewById(R.id.meno);

            plusBtn.setOnClickListener(this);
            minusBtn.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            if (v.getId() == plusBtn.getId()) {
                plusButton();
            }

            if (v.getId() == minusBtn.getId()) {
                    minusButton();
            }
        }


    }
}
