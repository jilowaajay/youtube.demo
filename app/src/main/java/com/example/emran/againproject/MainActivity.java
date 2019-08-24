package com.example.emran.againproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by emran on 6/7/17.
 */

public class MainActivity extends Activity
{
    ImageButton ibMenu;
    private TextView tvNames;
    private GridView gvItems;

    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_main);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final int[] images = {R.mipmap.introduction, R.mipmap.history, R.mipmap.installation, R.mipmap.activitynew, R.mipmap.lifecycle, R.mipmap.layout1, R.mipmap.linear, R.mipmap.relative, R.mipmap.table, R.mipmap.grid, R.mipmap.frame, R.mipmap.constraint, R.mipmap.views, R.mipmap.viewgroup, R.mipmap.menifest, R.mipmap.orientation, R.mipmap.event, R.mipmap.toast, R.mipmap.intent, R.mipmap.adapter, R.mipmap.layoutinflater, R.mipmap.dialog, R.mipmap.fragment, R.mipmap.viewpager, R.mipmap.tab, R.mipmap.shared, R.mipmap.string, R.mipmap.drawable, R.mipmap.values, R.mipmap.additioal, R.mipmap.br};
        gvItems = (GridView) findViewById(R.id.gvItems);
        tvNames = (TextView) findViewById(R.id.tvNames);
        String[] arr = getResources().getStringArray(R.array.topicNames);
        final MyAdapter adapter = new MyAdapter(getApplicationContext(), arr, images);
        gvItems.setAdapter(adapter);
        gvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l)
            {
                TextView tvname = (TextView) view.findViewById(R.id.tvNames);
                String TOPICNAME = tvname.getText().toString();

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                final AlertDialog alertDialog = dialogBuilder.create();
                LayoutInflater inflater = getLayoutInflater();
                View v = inflater.inflate(R.layout.alert_label_editor, null);
                alertDialog.setView(v);
                TextView tvTopicName = (TextView) v.findViewById(R.id.tvItemName);
                tvTopicName.setText(TOPICNAME);

                WebView wv = (WebView) v.findViewById(R.id.wvInfo);
                WebSettings webSettings = wv.getSettings();
//                wv.setLayoutParams();
                wv.getSettings().setJavaScriptEnabled(true);
                webSettings.setBuiltInZoomControls(true);
                wv.setHorizontalScrollBarEnabled(false);
                wv.setWebViewClient(new WebViewClient());
                String[] websites = getResources().getStringArray(R.array.websites);
                wv.loadUrl(websites[position]);

                alertDialog.show();
                ImageButton cancel = (ImageButton) v.findViewById(R.id.ibCancelDialog);
                cancel.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        alertDialog.dismiss();
                    }
                });
                return true;
            }
        });
        gvItems.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent intent = new Intent(MainActivity.this, FunctionActivity.class);
                intent.putExtra("ClickedPosition", i);
                startActivity(intent);
            }
        });

        ibMenu = (ImageButton) findViewById(R.id.ibMenu);
        ibMenu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                PopupMenu popup = new PopupMenu(MainActivity.this, ibMenu);
                popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        if (R.id.one == item.getItemId())
                        {
//                            SharedPreferences sp = getSharedPreferences("UserName", 0);
//                            String name = sp.getString("getUserName", null);
                            Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                            startActivity(i);
                        } else if (R.id.two == item.getItemId())
                        {
                            startActivity(new Intent(MainActivity.this, AboutUs.class));
                        } else if (R.id.three == item.getItemId())
                        {
                            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                            final AlertDialog alertDialog = dialogBuilder.create();
                            LayoutInflater inflater = getLayoutInflater();
                            View v = inflater.inflate(R.layout.up_comingfeatures, null);
                            ImageButton cancel_upcoming = (ImageButton) v.findViewById(R.id.cancel_upcoming_features);
                            cancel_upcoming.setOnClickListener(new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View view)
                                {
                                    alertDialog.dismiss();
                                }
                            });
                            alertDialog.setView(v);
                            alertDialog.show();
                        } else if (R.id.four == item.getItemId())
                        {
                            SharedPreferences sp = getSharedPreferences("UserName", 0);
                            sp.edit().remove("getUserName").commit();
                            startActivity(new Intent(MainActivity.this, AfterFlash.class));
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public class MyAdapter extends BaseAdapter
    {
        int[] image;
        String[] names;
        Context context;

        public MyAdapter(Context c, String[] arr, int[] images)
        {
            names = arr;
            image = images;
            context = c;
        }

        @Override
        public int getCount()
        {
            return names.length;
        }

        @Override
        public Object getItem(int i)
        {
            return i;
        }

        @Override
        public long getItemId(int i)
        {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            view = LayoutInflater.from(context).inflate(R.layout.cutom_single_item, null);
            TextView tvName = (TextView) view.findViewById(R.id.tvNames);
            CircleImageView civ = (CircleImageView) view.findViewById(R.id.profile_image);
            tvName.setText(names[i]);
            civ.setImageResource(image[i]);
            return view;
        }
    }
}
