package fun.wxy.www.demo68;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.LocationDisplay;
import com.esri.arcgisruntime.mapping.view.MapView;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private BottomNavigationView mBottomNavigationView;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private MapView mapView;
    private LocationDisplay mLocationDisplay;
    private Context mContext;
    private boolean internetStatus  = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;

        //设置顶部标题栏
        mToolbar = (Toolbar) findViewById(R.id.toolbar_top_container1);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        //底部导航栏
        mBottomNavigationView = findViewById(R.id.bottom_navigation_view_item);
        BottomNavigationViewHelper.disableShiftModle(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

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
        ArcGISMap arcGISMap = new ArcGISMap(Basemap.createImagery());
        mapView.setMap(arcGISMap);
        mLocationDisplay = mapView.getLocationDisplay();
        mLocationDisplay.setAutoPanMode(LocationDisplay.AutoPanMode.NAVIGATION);
        MainActivityPermissionsDispatcher.checkInternetWithPermissionCheck(MainActivity.this);
        MainActivityPermissionsDispatcher.getLocationProviderWithPermissionCheck(MainActivity.this);
    }


    //满足权限，显示地图
    @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION})
    public void getLocationProvider(){
        if(internetStatus){
            mLocationDisplay.setAutoPanMode(LocationDisplay.AutoPanMode.RECENTER);
            mLocationDisplay.startAsync();
        }else{
            Toast.makeText(mContext,"没有网络访问权限，请授予网络访问权限",Toast.LENGTH_LONG).show();
        }

    }


    //展示为什么需要定位权限
    @OnShowRationale({Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION})
    public void showRationaleDialog(final PermissionRequest request){
        new AlertDialog.Builder(mContext)
                .setMessage("在地图上显示您的位置需要定位权限，是否授予定位权限？")
                .setPositiveButton("允许", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false).show();
    }

    //获取到连接网络的权限时设置internetStatus的值为true
    @NeedsPermission(Manifest.permission.INTERNET)
    public void checkInternet(){
        internetStatus = true;
    }


    //拒绝授予定位权限时调用
    @OnPermissionDenied({Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION})
    public void localPermissionDenied(){
        Toast.makeText(mContext,"您拒绝授予定位权限，软件无法正常使用",Toast.LENGTH_SHORT).show();
    }

    //拒绝授予定位权限且点击了不再询问时调用
    @OnNeverAskAgain({Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION})
    public void localPermissionNeverAsk(){
        Toast.makeText(mContext,"软件无法正常使用，请到设置里同意此软件获取定位权限",Toast.LENGTH_SHORT).show();
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

    //监听底部导航栏的点击事件
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
          switch (item.getItemId()) {
              case R.id.bottom_dingwei:
                  MainActivityPermissionsDispatcher.checkInternetWithPermissionCheck(MainActivity.this);
                  MainActivityPermissionsDispatcher.getLocationProviderWithPermissionCheck(MainActivity.this);
                  Toast.makeText(mContext,"定位中...",Toast.LENGTH_SHORT).show();
                  return true;
              case R.id.bottom_tianjia:
                  return true;
              case R.id.bottom_sousuo:
                  return true;
              case R.id.bottom_renwu:
                  return true;
          }
          return false;
      }
    };


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(MainActivity.this,requestCode,grantResults);
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
