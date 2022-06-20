package com.stedile.appcompras;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private final Context context;
    Activity activity;
    private final ArrayList tv_id, tv_produto, tv_descricao, tv_preco, tv_quantidade, tv_total_2;
    Float to = 0.0f;
    public CustomAdapter(Activity activity, Context context, ArrayList tv_id, ArrayList tv_produto,
                         ArrayList tv_descricao, ArrayList tv_preco, ArrayList tv_quantidade,ArrayList tv_total_2) {
        this.activity = activity;
        this.context = context;
        this.tv_id = tv_id;
        this.tv_produto = tv_produto;
        this.tv_descricao = tv_descricao;
        this.tv_preco = tv_preco;
        this.tv_quantidade = tv_quantidade;
        this.tv_total_2 = tv_total_2;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent, false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.tv_id.setText(String.valueOf(tv_id.get(position)));
        holder.tv_produto.setText(String.valueOf(tv_produto.get(position)));
        holder.tv_descricao.setText(String.valueOf(tv_descricao.get(position)));
        holder.tv_preco.setText(String.valueOf("Preço R$ "+tv_preco.get(position)));
        holder.tv_quantidade.setText(String.valueOf("Quant: "+tv_quantidade.get(position)));
        holder.tv_total.setText(String.valueOf(tv_total_2.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("id",String.valueOf(tv_id.get(position)));
                intent.putExtra("produto",String.valueOf(tv_produto.get(position)));
                intent.putExtra("descricao",String.valueOf(tv_descricao.get(position)));
                intent.putExtra("preco",String.valueOf(tv_preco.get(position)));
                intent.putExtra("quantidade",String.valueOf(tv_quantidade.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tv_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_id, tv_produto, tv_descricao, tv_preco, tv_quantidade, tv_total;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_produto = itemView.findViewById(R.id.tv_produto);
            tv_descricao = itemView.findViewById(R.id.tv_descricao);
            tv_preco = itemView.findViewById(R.id.tv_preco);
            tv_quantidade = itemView.findViewById(R.id.tv_quantidade);
            tv_total = itemView.findViewById(R.id.tv_total_2);
            mainLayout = itemView.findViewById(R.id.layout_secundario);

        }

    }
}
