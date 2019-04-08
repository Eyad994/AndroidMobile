package com.jamalonexpress.fragmentwithnavigation;

import android.app.SearchManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,AddorRemoveCallbacks{

    public String sku;
    private static final String TAG = "MainActivity";
    private SectionStatePageAdapter mSectionStatePageAdapter;
    private ViewPager mViewPager;
     ArrayList<Book> bookItems;
    private static int cart_count= 0;
    SectionStatePageAdapter adapter = new SectionStatePageAdapter(getSupportFragmentManager());
    SharedPreferences.Editor editor;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();

        editor = getSharedPreferences("Test", MODE_PRIVATE).edit();
        prefs = getSharedPreferences("Test", MODE_PRIVATE);

        mSectionStatePageAdapter = new SectionStatePageAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container);

        adapter.addFragment(new Fragment1(),"Fragment1");
        mViewPager.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public void saveData(){

        SharedPreferences sharedPreferences = getSharedPreferences("cartItems",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(bookItems);
        editor.putString("cartItem",json);
        Log.d(TAG, "saveData: "+ json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("cartItems",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("cartItem",null);
        Type type = new TypeToken<ArrayList<Book>>() {}.getType();
        bookItems = gson.fromJson(json,type);
        Log.d(TAG, "loadData: "+ json);
        if(bookItems == null){
            bookItems = new ArrayList<>();
        }
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

    private void setupViewPager(ViewPager viewPager){

            adapter.addFragment(new Fragment2(),"Fragment2");
            adapter.addFragment(new Fragment3(),"Fragment3");
            viewPager.setAdapter(adapter);
    }

    public void onClicl(String sku){
        this.sku = sku;
        setupViewPager(mViewPager);
    }

    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.cart_action);
        menuItem.setIcon(Converter.convertLayoutToImage(MainActivity.this,bookItems.size(),R.drawable.ic_shopping_cart_white_24dp));
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        final SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                if(adapter.getCount() == 1){
//                    adapter.addFragment(new Fragment4(),"Fragment4");
//                    mViewPager.setAdapter(adapter);
//                    setViewPager(2);
//                }else if (adapter.getCount() == 3){
//                    adapter.addFragment(new Fragment4(), "Fragment4");
//                    mViewPager.setAdapter(adapter);
//                    setViewPager(4);
//                }
                Toast.makeText(MainActivity.this, "onTextSubmit: "+query+" adapter: "+adapter.getCount(), Toast.LENGTH_SHORT).show();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(MainActivity.this, "onTextChange: "+newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.d(TAG, "onOptionsItemSelected: created");
        int id = item.getItemId();

        if (id == R.id.action_search) {
            return true;
        }
        if(id == R.id.cart_action){
           return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            setViewPager(0);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onAddProduct(String sku) {
        cart_count++;
        invalidateOptionsMenu();
        Book book = new Book();
        bookItems.add(new Book("123","www","asd","12","fdds","dss"));
        saveData();
        Snackbar.make((CoordinatorLayout)findViewById(R.id.parentlayout), "Added to cart successfully", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    @Override
    public void onRemoveProduct() {
        cart_count--;
        invalidateOptionsMenu();
        Snackbar.make((CoordinatorLayout)findViewById(R.id.parentlayout), "Removed from cart !", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

}
