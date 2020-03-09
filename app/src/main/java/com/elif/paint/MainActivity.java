package com.elif.paint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ColorAdapter.ColorSelected {

    Button rectangleButton;
    Button squareButton;
    Button circleButton;
    Button lineButton;
    FloatingActionButton deleteButton;
    FloatingActionButton moveButton;
    PaintView paintView;
    RecyclerView colorRecyclerView;
    ColorAdapter colorAdapter;
    ArrayList<Integer> colorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rectangleButton = findViewById(R.id.rect_button);
        squareButton = findViewById(R.id.square_button);
        circleButton = findViewById(R.id.circle_button);
        lineButton = findViewById(R.id.line_button);
        deleteButton = findViewById(R.id.delete_button);
        moveButton = findViewById(R.id.move_button);
        paintView = findViewById(R.id.paint_view);

        rectangleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.setCategory(PaintView.Category.RECTANGLE);
            }
        });
        squareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.setCategory(PaintView.Category.SQUARE);
            }
        });
        circleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.setCategory(PaintView.Category.CIRCLE);
            }
        });
        lineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.setCategory(PaintView.Category.LINE);
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.setCategory(PaintView.Category.DELETE);
            }
        });
        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.setCategory(PaintView.Category.MOVE);
            }
        });

        colorRecyclerView = findViewById(R.id.color_recyclerView);
        colorList = new ArrayList<>();
        colorList.add(R.color.pink);
        colorList.add(R.color.blue);
        colorList.add(R.color.yellow);
        colorList.add(R.color.red);
        colorList.add(R.color.green);
        colorList.add(R.color.orange);
        colorList.add(R.color.darkBlue);
        colorList.add(R.color.purple);
        colorAdapter = new ColorAdapter(colorList, MainActivity.this);
        colorRecyclerView.setAdapter(colorAdapter);

        paintView.setColor(ContextCompat.getColor(MainActivity.this, colorList.get(0)));    }

    @Override
    public void onColorSelect(int position) {
        colorAdapter.setSelectedIndex(position);
        paintView.setColor(ContextCompat.getColor(MainActivity.this, colorList.get(position)));
    }
}
