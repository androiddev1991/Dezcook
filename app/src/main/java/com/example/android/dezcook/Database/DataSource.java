package com.example.android.dezcook.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.example.android.dezcook.Activities.Food;


/**
 * Created by Android on 6/28/2016.
 */
public class DataSource {

    private DbHelper dbHelper;
    private Context mContext;
    private SQLiteDatabase sqLiteDatabase;
    public DataSource(Context context)
    {
        this.mContext=context;
        dbHelper=new DbHelper(mContext);

    }

    public List<Food> GetListOfFav()
    {
        int picId;
        List<Food> lstFood=new ArrayList<>();
        try {
            dbHelper.createDatabase();
            dbHelper.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqLiteDatabase=dbHelper.getMyDataBase();
        String sql="SELECT * FROM food WHERE fav==1";
        Cursor cursor=sqLiteDatabase.rawQuery(sql,null);
        if(cursor!=null && cursor.getCount()>0)
        {
            if(cursor.moveToFirst())
            {
                while (!cursor.isAfterLast())
                {
                    Food food=new Food();
                    food.setTxtName(cursor.getString(cursor.getColumnIndex("food_name")));
                    food.setTxtImage(cursor.getString(cursor.getColumnIndex("image")));
                    food.setTxtid(cursor.getInt(cursor.getColumnIndex("food_id")));
                    picId = mContext.getResources().getIdentifier(food.getTxtImage(), "drawable",
                            mContext.getApplicationContext().getPackageName());
                    food.setConverted_image(picId);
                    Log.d("ERROR:","Image Id: "+ Integer.toString(food.getConverted_image()));
                    Log.d("ERROR:","Image name: "+ (food.getTxtImage()));
                    String items;

                    items=this.get_items(food.getTxtid());

                    food.setItems(items);

                    lstFood.add(food);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        dbHelper.close();
        return lstFood;
    }
    public List<Food> GetListOfAll()
    {
        int picId;
        List<Food> lstFood=new ArrayList<>();
        try {
            dbHelper.createDatabase();
            dbHelper.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String sql="SELECT * FROM food";
        sqLiteDatabase=dbHelper.getMyDataBase();
        Cursor cursor=sqLiteDatabase.rawQuery(sql,null);
        if(cursor!=null && cursor.getCount()>0)
        {
            if(cursor.moveToFirst())
            {
                while (!cursor.isAfterLast())
                {
                    Food food=new Food();
                    food.setTxtName(cursor.getString(cursor.getColumnIndex("food_name")));
                    food.setTxtImage(cursor.getString(cursor.getColumnIndex("image")));
                    food.setTxtid(cursor.getInt(cursor.getColumnIndex("food_id")));
                    picId = mContext.getResources().getIdentifier(food.getTxtImage(), "drawable",
                            mContext.getApplicationContext().getPackageName());
                    food.setConverted_image(picId);
                    Log.d("ERROR:","Image Id: "+ Integer.toString(food.getConverted_image()));
                    Log.d("ERROR:","Image name: "+ (food.getTxtImage()));
                    String items;

                    items=this.get_items(food.getTxtid());

                    food.setItems(items);

                    lstFood.add(food);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        dbHelper.close();
        return lstFood;
    }
    public Food RetunFood(int id)
    {
        if(!sqLiteDatabase.isOpen())
            dbHelper.openDataBase();
        sqLiteDatabase=dbHelper.getMyDataBase();
        Cursor itemCursor=sqLiteDatabase.rawQuery("SELECT * FROM food WHERE food_id == "+id,null);
        Log.d("Column count: ",Integer.toString(itemCursor.getColumnCount()));
        itemCursor.moveToFirst();
        Food food=new Food();
        food.setTxtid(id);
        food.setItems(get_items(id));
        food.setTxtImage(itemCursor.getString(4).toString());
        food.setTxtDesc(itemCursor.getString(2));
        food.setTxtName(itemCursor.getString(1));
        food.setTxtFav(Boolean.parseBoolean(itemCursor.getString(3)));
        int picId = mContext.getResources().getIdentifier(food.getTxtImage(), "drawable",
                mContext.getApplicationContext().getPackageName());
        food.setConverted_image(picId);
        return food;
    }
    public String get_items(int Txtid)
    {
        if(!sqLiteDatabase.isOpen())
            dbHelper.openDataBase();
        sqLiteDatabase=dbHelper.getMyDataBase();
        Cursor itemCursor=sqLiteDatabase.rawQuery("SELECT item FROM item WHERE food_id == "+Integer.toString(Txtid),null);
        String items="";
        int counter=0;
        if(itemCursor.getCount()>0){
            if(itemCursor.moveToFirst()){
                do{
                    counter++;
                    if(counter<itemCursor.getCount())
                        items=items+itemCursor.getString(0)+" ، ";
                    else
                        items=items+itemCursor.getString(0);
                }while (itemCursor.moveToNext());
            }
        }
        return items;
    }

    public int UpdateDb(int id,String favStatus)
    {
        dbHelper = new DbHelper(mContext);
        ContentValues values=new ContentValues();
        values.put("fav",favStatus);

        dbHelper.openDataBase();
        sqLiteDatabase=dbHelper.getMyDataBase();

        int part= sqLiteDatabase.update("food",values,"food_id="+id,null);
        dbHelper.close();
        Log.i("update","successful"+part);
        return  part;
    }
    public void closeDatabase()
    {
        dbHelper.close();
    }

}

