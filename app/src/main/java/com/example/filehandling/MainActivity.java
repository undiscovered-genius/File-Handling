package com.example.filehandling;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText fileName, fileData;
    Button read, write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileName = findViewById(R.id.fileName);
        fileData = findViewById(R.id.fileData);
        read = findViewById(R.id.read);
        write = findViewById(R.id.write);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fileName.getText().toString();
                String data = fileData.getText().toString();
                StringBuffer stringBuffer = new StringBuffer();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput(name)));
                    String readString;
                    while ((readString = bufferedReader.readLine()) != null){
                        stringBuffer.append(readString + "\n");
                        fileData.setText(stringBuffer.toString());
                    }
                }catch (IOException e){
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,"ERROR!",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(),"Displaying  " + name ,Toast.LENGTH_LONG).show();
            }
        });

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fileName.getText().toString();
                String data = fileData.getText().toString();
                FileOutputStream fileOutputStream;
                try {
                    fileOutputStream = openFileOutput(name, Context.MODE_PRIVATE);
                    fileOutputStream.write(data.getBytes());
                    fileOutputStream.close();
                    Toast.makeText(getApplicationContext(),name + "  Saved !",Toast.LENGTH_LONG).show();
                    fileData.setText("");
                    fileName.setText("");
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}