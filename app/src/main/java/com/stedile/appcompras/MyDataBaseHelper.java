package com.stedile.appcompras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    private Context context;

    public static final String DATABASE_NAME = "Produto.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "compras";
    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_PRODUTO = "produto";
    public static final String COLUMN_DESCRICAO = "descricao";
    public static final String COLUMN_PRECO = "preco";
    public static final String COLUMN_QUANTIDADE = "quantidade";
    public static final String COLUMN_TOTAL = "total";

    public MyDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUTO + " TEXT," +
                COLUMN_DESCRICAO + " TEXT, " +
                COLUMN_PRECO + " TEXT, " +
                COLUMN_QUANTIDADE + " TEXT, " +
                COLUMN_TOTAL + " TEXT) ; ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addProduto(String produto, String descricao, String preco, String quantidade, String total){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PRODUTO,produto);
        cv.put(COLUMN_DESCRICAO,descricao);
        cv.put(COLUMN_PRECO,preco);
        cv.put(COLUMN_QUANTIDADE,quantidade);
        cv.put(COLUMN_TOTAL,total);

        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1){
            Toast.makeText(context, "Falha", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Salvo com éxito", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor lerTodosDados(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return  cursor;
    }

    void updateData(String row_id, String produto, String descricao, String preco, String quantidade, String total){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PRODUTO, produto);
        cv.put(COLUMN_DESCRICAO, descricao);
        cv.put(COLUMN_PRECO, preco);
        cv.put(COLUMN_QUANTIDADE, quantidade);
        cv.put(COLUMN_TOTAL, total);
        long result = db.update(TABLE_NAME, cv,"_id=?",new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Falha na Atualização", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Atualizado com Sucesso.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME,"_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Falha ao Deletar", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Deletado com Sucesso", Toast.LENGTH_SHORT).show();
        }
    }

    void deletarAllDate(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
