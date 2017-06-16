package tetamba.androidlibrary;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import tetamba.androidlibrary.ButterKnife.ButterKnifeFragment;
import tetamba.androidlibrary.Glide.GlideFragment;
import tetamba.androidlibrary.Gson.GsonActivity;
import tetamba.androidlibrary.Gson.GsonFragment;
import tetamba.androidlibrary.Photoview.PhotoVIewFragment;
import tetamba.androidlibrary.Retrofit.RetrofitFragment;
import tetamba.androidlibrary.Saripaar.SaripaarFragment;
import tetamba.androidlibrary.Volley.VolleyFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        ButterKnifeFragment.OnFragmentInteractionListener,
        SaripaarFragment.OnFragmentInteractionListener,
        GsonFragment.OnFragmentInteractionListener,
        VolleyFragment.OnFragmentInteractionListener,
        RetrofitFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // Butterknife
        if (id == R.id.nav_butterknife) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_content, new ButterKnifeFragment())
                    .commit();
        }
        // Saripaar
        else if (id == R.id.nav_saripaar) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_content, new SaripaarFragment())
                    .commit();
        }
        // Glide
        else if (id == R.id.nav_glide) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_content, new GlideFragment())
                    .commit();
        }
        // Gson
        else if (id == R.id.nav_gson) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_content, new GsonFragment())
                    .commit();
        }
        // Volley
        else if (id == R.id.nav_volley) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_content, new VolleyFragment())
                    .commit();
        }
        // Retrofit
        else if (id == R.id.nav_retrofit) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_content, new RetrofitFragment())
                    .commit();
        }
        // Photoview
        else if (id == R.id.nav_photoview) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_content, new PhotoVIewFragment())
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this, "Listener library", Toast.LENGTH_SHORT).show();
    }
}
