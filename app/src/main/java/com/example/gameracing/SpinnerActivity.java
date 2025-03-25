package com.example.gameracing;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.CharArrayReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SpinnerActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<String> adapter;

    ArrayList<String> monHoc = new ArrayList<>();

    Button btnThem;
    EditText edtMH;
    int chon = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_spiner);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        Nhap();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, monHoc);

        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chon = position;
                //monHoc.remove(position);
                //Toast.makeText(SpinnerActivity.this, "Đã xoá vị trí" + position, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    private void AnhXa() {
        spinner = findViewById(R.id.spinner1);
        btnThem = findViewById(R.id.addSubject);
        edtMH = findViewById(R.id.editMonhoc);

    }

    private void Nhap(){
        monHoc.add("Lap trinh PHP");
        monHoc.add("Lap trinh IOS");
        monHoc.add("Lap trinh HTML");
    }

    public void MyClick(View view) {

        //Thêm
        //monHoc.add(edtMH.getText() + "");
        //adapter.notifyDataSetChanged();

        //Xoá
        //if (chon != -1) {
        //    monHoc.remove(chon);
        //    Toast.makeText(this, "Bạn đã xoá tại vị trí" + chon, Toast.LENGTH_SHORT).show();
        //    chon = -1;
        //}

        //Sửa
        monHoc.set(chon, edtMH.getText() + "");
    }
}