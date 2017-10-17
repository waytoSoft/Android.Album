package com.jg.album;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jg.album.model.data.PictureEntity;
import com.jg.album.view.PullToRefreshRecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    PullToRefreshRecyclerView pullToRefresRecyclerView;

    private ShowImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pullToRefresRecyclerView = (PullToRefreshRecyclerView) findViewById(R.id.MainActivity_RecyclerView);

        initRecyclerView();
        findViewById(R.id.Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SelectImageActivity.class);
                startActivityForResult(intent, 653);
            }
        });
    }

    private void initRecyclerView() {
        adapter = new ShowImageAdapter(this);

        pullToRefresRecyclerView.setRecyclerViewAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SelectImageActivity.SELECT_PICTURE_RESULT_CODE) {
            List<PictureEntity> imgs = (List<PictureEntity>) data.getSerializableExtra("imgs");
            adapter.addItems(imgs);
        }
    }
}
