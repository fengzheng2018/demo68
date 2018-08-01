package fun.wxy.www.demo68;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private BottomNavigationView mBottomNavigationView;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置顶部标题栏
        mToolbar = (Toolbar) findViewById(R.id.toolbar_top_container1);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        //底部导航栏
        mBottomNavigationView = findViewById(R.id.bottom_navigation_view_item);
        BottomNavigationViewHelper.disableShiftModle(mBottomNavigationView);

        //侧边滑动栏
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //滑动栏菜单项监听
        mNavigationView = (NavigationView) findViewById(R.id.navigation_left_container2);
        mNavigationView.setNavigationItemSelectedListener(this);

        //显示地图
        mapView = findViewById(R.id.mapView);
        ArcGISMap map =
                new ArcGISMap(Basemap.Type.TOPOGRAPHIC,
                        34.056295, -117.195800, 16);
        mapView.setMap(map);
    }

    //按返回键时隐藏侧边滑动栏
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //显示toolbar顶部菜单栏
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_top_menu1, menu);
        return true;
    }

    //监听顶部菜单栏点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.tooBar_top_menu_item1) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //监听侧边滑动栏点击事件
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.navigation_left_item_1) {

        } else if (id == R.id.navigation_left_item_2) {

        } else if (id == R.id.navigation_left_item_3) {

        } else if (id == R.id.navigation_left_item_4) {

        } else if (id == R.id.navigation_left_item_5) {

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onPause(){
        mapView.pause();
        super.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        mapView.resume();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mapView.dispose();
    }
}
