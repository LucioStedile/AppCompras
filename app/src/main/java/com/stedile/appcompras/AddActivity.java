package com.stedile.appcompras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddActivity extends AppCompatActivity {

    EditText et_produto, et_descricao, et_preco, et_quantidade, et_total;
    Float pr = 0.00f, qt = 0.00f, to = 0.00f;
    Button salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        et_produto = findViewById(R.id.et_produto);
        et_descricao = findViewById(R.id.et_descricao);
        et_preco = findViewById(R.id.et_preco);
        et_quantidade = findViewById(R.id.et_quantidade);
        et_total = findViewById(R.id.et_total);

        salvar = findViewById(R.id.btn_salvar);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pr = Float.parseFloat(et_preco.getText().toString());
                qt = Float.parseFloat(et_quantidade.getText().toString());
                to = pr * qt;
                et_total.setText(String.valueOf("R$:" + to));


                MyDataBaseHelper myDB = new MyDataBaseHelper(AddActivity.this);
                myDB.addProduto(et_produto.getText().toString().trim(),
                        et_descricao.getText().toString().trim(),
                        et_preco.getText().toString().trim(),
                        et_quantidade.getText().toString().trim(),
                        et_total.getText().toString().trim());
            }
        });
    }
}