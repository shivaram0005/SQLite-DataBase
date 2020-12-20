package com.example.sqldatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2, et3, et4;
    Button b1, b2, b3, b4;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        dataBase = new DataBase(this);
        addData();
        getAllData();
        updateData();
        deleteData();
    }

    public void addData() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = dataBase.insertData(et1.getText().toString(), et2.getText().toString(), et3.getText().toString());
                if (isInserted == true) {
                    Toast.makeText(MainActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void getAllData() {
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = dataBase.getData();
                if (cursor.getCount() == 0) {
                    showMessage("Erro", "NothingFound");
                    return;
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    while (cursor.moveToNext()) {
                        stringBuffer.append("ID :" + cursor.getString(0) + "\n");
                        stringBuffer.append("Name :" + cursor.getString(1) + "\n");
                        stringBuffer.append("Surname :" + cursor.getString(2) + "\n");
                        stringBuffer.append("Number :" + cursor.getString(3) + "\n");

                    }
                    showMessage("Data", stringBuffer.toString());
                }

            }
        });

    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
    public void updateData(){
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated=dataBase.updateData(et4.getText().toString(),et1.getText().toString(),
                        et2.getText().toString(),et3.getText().toString());
                if(isUpdated==true){
                    Toast.makeText(MainActivity.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Data not inserted successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void deleteData(){
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  Integer deleteRows=dataBase.daleteData(et4.getText().toString());
                  if(deleteRows>0)
                  {
                      Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                  }
                  else{
                      Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                  }
            }
        });
    }
}
