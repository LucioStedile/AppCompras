package com.stedile.appcompras;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText produto_input, descricao_input, preco_input, quantidade_input, total_input;
    Float pr = 0.00f, qt = 0.00f, to = 0.00f;
    Button btn_salvar_input, btn_delete_input;
    String id, produto, descricao, preco, quantidade, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        produto_input = findViewById(R.id.produto_input);
        descricao_input = findViewById(R.id.descricao_input);
        preco_input = findViewById(R.id.preco_input);
        quantidade_input = findViewById(R.id.quantidade_input);
        total_input = findViewById(R.id.total_input);

        getAndSetIntentData();

        btn_salvar_input = findViewById(R.id.btn_salvar_input);

        btn_salvar_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pr = Float.parseFloat(preco_input.getText().toString());
                qt = Float.parseFloat(quantidade_input.getText().toString());
                to = pr * qt;
                total_input.setText(String.valueOf("R$:" + to));

                MyDataBaseHelper myDB = new MyDataBaseHelper(UpdateActivity.this);
                produto = produto_input.getText().toString().trim();
                descricao = descricao_input.getText().toString().trim();
                preco = preco_input.getText().toString().trim();
                quantidade = quantidade_input.getText().toString().trim();
                total = total_input.getText().toString().trim();
                myDB.updateData(id, produto, descricao, preco, quantidade, total);
                //finish();
            }
        });

        btn_delete_input = findViewById(R.id.btn_delete_input);
        btn_delete_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });


        ActionBar ab = getSupportActionBar();
        ab.setTitle(produto);
        if (ab != null){
            ab.setTitle(produto);
        }

    }

    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("produto") &&
            getIntent().hasExtra("descricao") && getIntent().hasExtra("preco")
            && getIntent().hasExtra("quantidade")){

            id = getIntent().getStringExtra("id");
            produto = getIntent().getStringExtra("produto");
            descricao = getIntent().getStringExtra("descricao");
            preco = getIntent().getStringExtra("preco");
            quantidade = getIntent().getStringExtra("quantidade");
            total = getIntent().getStringExtra("total");

            produto_input.setText(produto);
            descricao_input.setText(descricao);
            preco_input.setText(preco);
            quantidade_input.setText(quantidade);
            total_input.setText(total);

        }else{
            Toast.makeText(this, "Sem Dados", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + produto + " ?");
        builder.setMessage("Tem certeza que deseja excluir " + produto + " ?");
        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDataBaseHelper myDB = new MyDataBaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

}