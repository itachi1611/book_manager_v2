package com.foxy.bookmanager.project.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.foxy.bookmanager.project.fragment.FragmentIntroduce;
import com.foxy.bookmanager.project.fragment.MenuFragment;
import com.foxy.bookmanager.project.R;


public class ActivityHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentIntroduce fragmentIntroduce;
    private MenuFragment menuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // bbbbgit

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toolbar.setTitleTextColor(Color.WHITE);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentIntroduce = new FragmentIntroduce();
        menuFragment = new MenuFragment();
        showFragmentMenu();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_menu) {
            showFragmentMenu();
        } else if (id == R.id.nav_gioithieu) {
            showFragmentGioiThieu();

        } else if (id == R.id.nav_back) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Bạn có muốn thoát không?");
            alertDialogBuilder
                    .setMessage("Click để thoát!")
                    .setCancelable(false)
                    .setPositiveButton("Có",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    moveTaskToBack(true);
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                    System.exit(1);
                                }
                            })

                    .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        else if(id==R.id.nav_change_password){
            showDialogChangePassword();
        }
        else if(id==R.id.nav_dangxuat){
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void showFragmentMenu(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        if (menuFragment.isAdded()) {
            ft.show(menuFragment);
        } else {
            ft.add(R.id.container1, menuFragment);
        }
        if (fragmentIntroduce.isAdded()) {
            ft.hide(fragmentIntroduce);
        }
        ft.commit();
    }
    public void showFragmentGioiThieu(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        if (fragmentIntroduce.isAdded()) {
            ft.show(fragmentIntroduce);
        } else {
            ft.add(R.id.container1, fragmentIntroduce);
        }
        if (menuFragment.isAdded()) {
            ft.hide(menuFragment);
        }
        ft.commit();
    }
  public void showDialogChangePassword() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_change_password, null);
        dialog.setView(dialogView);
        final Dialog dialog1 = dialog.show();
        Button doi = dialogView.findViewById(R.id.btn_edit);
        Button huy = dialogView.findViewById(R.id.btn_cancel);
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
      doi.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Toast.makeText(ActivityHome.this, "Đổi thành công", Toast.LENGTH_SHORT).show();
              dialog1.dismiss();
          }
      });
    }


}
