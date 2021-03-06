package com.foxy.bookmanager.project.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;


import com.foxy.bookmanager.project.sqlitedao.StatisticsDAO;
import com.foxy.bookmanager.project.R;
import com.foxy.bookmanager.project.sqlite.Constant;
import com.foxy.bookmanager.project.sqlite.DatabaseHelper;

public class ActivtiyStatistics extends AppCompatActivity implements Constant {
    Toolbar toolbarStatistic;
    private TextView tvDoanhThuNgay;
    private TextView tvDoanhThuThang;
    private TextView tvDoanhThuNam;

    private DatabaseHelper databaseHelper;

    private StatisticsDAO statisticsDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        initView();
        databaseHelper = new DatabaseHelper(this);
        statisticsDAO = new StatisticsDAO(databaseHelper);
        String totalDay=String.valueOf(statisticsDAO.totalBillD(Constant.D_DAY))+" Đ";
        String totalMon=statisticsDAO.totalBillM()+" Đ";
        String totalYeah=statisticsDAO.totalBillY()+" Đ";
        tvDoanhThuNgay.setText(totalDay);
        tvDoanhThuThang.setText(totalMon);
        tvDoanhThuNam.setText(totalYeah);



    }


    private void initView() {
        toolbarStatistic = findViewById(R.id.toolbarThongKe);
        setSupportActionBar(toolbarStatistic);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarStatistic.setTitleTextColor(Color.WHITE);
        toolbarStatistic.setTitle("Thống kê");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvDoanhThuNgay = findViewById(R.id.tvDoanhThuNgay);
        tvDoanhThuThang = findViewById(R.id.tvDoanhThuThang);
        tvDoanhThuNam = findViewById(R.id.tvDoanhThuNam);

    }
}
