package com.j4dream.gxstory;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.j4dream.gxstory.view.SlidingMenu;


public class MainActivity extends Activity {

    private LinearLayout menuList;
    private SlidingMenu mLeftMenu;
    private String[] menuName = {"Home Page", "Music", "Photo"};
    private int[] defaultIcon = {R.drawable.menu_home, R.drawable.menu_music, R.drawable.menu_photo};
    private int[] selectedIcon = {R.drawable.menu_home_selected, R.drawable.menu_music_selected, R.drawable.menu_photo_selected};
    private static int preSelectedPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);

        setMenuList();

        PicFragment picFragment = new PicFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add(R.id.home_fragemt, picFragment);
        beginTransaction.commit();

    }

    public void toggleMemu(View view) {
        mLeftMenu.toggle();
    }

    private void setMenuList() {
        int selectPost = 0;
        ListView menuList = (ListView) findViewById(R.id.list_view_menu);
        menuList.setAdapter(new MenuListAdapter(this));
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (preSelectedPosition != position) {
                    LinearLayout linearLayout = (LinearLayout) view;
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.white));
                    ImageView iv = (ImageView) linearLayout.getChildAt(0);
                    iv.setImageResource(selectedIcon[position]);
                    TextView tv = (TextView) linearLayout.getChildAt(1);
                    tv.setTextColor(getResources().getColor(R.color.deepskyblue));

                    LinearLayout preSelectedItem = (LinearLayout) parent.getChildAt(preSelectedPosition);
                    preSelectedItem.setBackgroundResource(0);
                    ImageView piv = (ImageView) preSelectedItem.getChildAt(0);
                    piv.setImageResource(defaultIcon[preSelectedPosition]);
                    TextView ptv = (TextView) preSelectedItem.getChildAt(1);
                    ptv.setTextColor(getResources().getColor(R.color.white));
                    preSelectedPosition = position;
                }
            }
        });
    }

    private class MenuListAdapter extends BaseAdapter {
        private LayoutInflater inflater;

        public MenuListAdapter(Context contex) {
            inflater = LayoutInflater.from(contex);
        }

        @Override
        public int getCount() {
            return menuName.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.menu_item, null);
            }

            LinearLayout linearLayout = (LinearLayout) convertView.getRootView();
            ImageView imageView = (ImageView) linearLayout.getChildAt(0);
            imageView.setImageResource(defaultIcon[position]);
            TextView textView = (TextView) linearLayout.getChildAt(1);
            textView.setText(menuName[position]);
            if (position == 0){
                linearLayout.setBackgroundColor(getResources().getColor(R.color.white));
                imageView.setImageResource(selectedIcon[position]);
                textView.setTextColor(getResources().getColor(R.color.deepskyblue));
            }
            return convertView;
        }


    }
}
