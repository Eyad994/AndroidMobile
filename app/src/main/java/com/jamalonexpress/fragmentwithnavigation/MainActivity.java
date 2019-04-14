package com.jamalonexpress.fragmentwithnavigation;

import android.app.SearchManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jamalonexpress.fragmentwithnavigation.Fragments.Fragment1;
import com.jamalonexpress.fragmentwithnavigation.Fragments.Fragment2;
import com.jamalonexpress.fragmentwithnavigation.Fragments.Fragment3;
import com.jamalonexpress.fragmentwithnavigation.Fragments.Fragment4;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,AddorRemoveCallbacks{

    public String sku;
    public String jsonData;
    private static final String TAG = "MainActivity";
    private SectionStatePageAdapter mSectionStatePageAdapter;
    private ViewPager mViewPager;
    public ArrayList<Book> bookItems;
    private static int cart_count= 0;
    private final SectionStatePageAdapter adapter = new SectionStatePageAdapter(getSupportFragmentManager());

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();

        mSectionStatePageAdapter = new SectionStatePageAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container);

        adapter.addFragment(new Fragment1(),"Fragment1");
        adapter.addFragment(new Fragment4(),"Fragment4");
       // mSectionStatePageAdapter.notifyDataSetChanged();
        mViewPager.setAdapter(adapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze);
//        for(Book b : bookItems){
//                Log.d(TAG, "onForLoop: True "+b.getSku() + " "+sku);
//        }
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

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("cartItems",MODE_PRIVATE);
        Gson gson = new Gson();
        jsonData = sharedPreferences.getString("cartItem",null);
        Type type = new TypeToken<ArrayList<Book>>() {}.getType();
        bookItems = gson.fromJson(jsonData,type);
        Log.d(TAG, "loadData: "+ jsonData);
        if(bookItems == null){
            bookItems = new ArrayList<>();
        }
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
        menuItem.setIcon(Converter.convertLayoutToImage(MainActivity.this,bookItems.size(),R.drawable.ic_shopping_cart));
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        final SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        EditText searchEditText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.colorPrimary));
        searchEditText.setHintTextColor(getResources().getColor(R.color.colorPrimary));
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
                Toast.makeText(MainActivity.this, "onTextSubmit: "+query, Toast.LENGTH_SHORT).show();
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
            setViewPager(1);
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

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onAddProduct(String sku,String title,String image,String price,String author) {
        invalidateOptionsMenu();
        bookItems.add(new Book(sku,title,image,"12","00",author));
        saveData();
        Snackbar.make(findViewById(R.id.parentlayout), "Added to cart successfully", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    @Override
    public void onRemoveProduct() {
        invalidateOptionsMenu();
        Snackbar.make(findViewById(R.id.parentlayout), "Removed from cart !", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void changeFragment(final Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.parentlayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
