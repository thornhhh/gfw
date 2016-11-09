package cn.call110.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import java.util.ArrayList;
import java.util.List;
import cn.call110.R;
import cn.call110.adapter.HomeAdapter;
import cn.call110.model.HomeMenuItem;
import cn.call110.utils.IntentUtils;


public class HomeActivity extends AppCompatActivity {
    private GridView mGridView;
    private HomeAdapter mAdapter;
    private List<HomeMenuItem> list;

    private int[] icons = {R.mipmap.phone, R.mipmap.sms, R.mipmap.search, R.mipmap.laba, R.mipmap.qi, R.mipmap.email};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        mGridView = (GridView) findViewById(R.id.grid);
        list = new ArrayList();
        String[] menuTitles = {getString(R.string.home_menu1), getString(R.string.home_menu2), getString(R.string.home_menu3), getString(R.string.home_menu4), getString(R.string.home_menu5), getString(R.string.home_menu6)};
        for(int i = 0; i < menuTitles.length; i ++) {
            list.add(new HomeMenuItem().setIcon(icons[i]).setTitle(menuTitles[i]));
        }
        mAdapter = new HomeAdapter(this, list);
        mGridView.setAdapter(mAdapter);
        toolbar.setOnMenuItemClickListener(e -> {
            switch (e.getItemId()) {
                case R.id.setting:
                    IntentUtils.launch(this, SettingActivity.class);
                    break;
            }
            return true;
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 為了讓 Toolbar 的 Menu 有作用，這邊的程式不可以拿掉
        getMenuInflater().inflate(R.menu.home_toolbar_menu, menu);
        MenuItem item =  (MenuItem) menu.findItem(R.id.setting);
        item.setIcon(new IconicsDrawable(this).icon(FontAwesome.Icon.faw_cog).color(ContextCompat.getColor(this, R.color.theme_window_background)).sizeDp(20));
        return true;
    }

    public static void launch(Activity activity) {
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeCustomAnimation(activity,
                        R.anim.head_in, R.anim.head_out);
        Intent intent = new Intent(activity, SettingActivity.class);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

//    138.128.204.17:8081/compass/p/blackphone?username=admin&password=123321

}
