package kr.ac.kmu.ncs.restview.DetailView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.ac.kmu.ncs.restview.MyConstatns;
import kr.ac.kmu.ncs.restview.R;

/**
 * Created by Eddie Sangwon Kim on 2016-11-05.
 */
public class DetailViewController {
//    static private DetailViewController instance = new DetailViewController();

//    private DetailViewController() {
//    }
//
//    ;
//
//    static public DetailViewController getInstance() {
//        return instance;
//    }

    private View view = null;

    private ListView listView;
    private ListViewAdapter listViewAdapter;
//    private ArrayAdapter<String> listItems;

    public DetailViewController(final View view) {
        this.view = view;
        initUI();
        listThread();

    }

    private void initUI() {
        listView = (ListView) view.findViewById(R.id.lv_detail);
        listViewAdapter = new ListViewAdapter(view.getContext());
        listView.setAdapter(listViewAdapter);

        for (int i = 0; i < 10; i++) {
            listViewAdapter.addItem(view.getResources().getDrawable(R.drawable.img_vacant), "1", "1");
        }
    }

    private void listThread() {
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                    try {
                        Thread.sleep(MyConstatns.REQUEST_TIME_INTERVAL);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    class ViewHolder {
        public TextView tvLabel;
        public ImageView ivState;
        public TextView tvState;
    }

    private class ListViewAdapter extends BaseAdapter {
        private Context ctx;
        private ArrayList<ListData> listDatas = new ArrayList<>();

        public ListViewAdapter(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        public int getCount() {
            return listDatas.size();
        }

        @Override
        public Object getItem(int i) {
            return listDatas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                holder = new ViewHolder();

                LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.listview_item, null);

                holder.tvLabel = (TextView) view.findViewById(R.id.tv_label);
                holder.ivState = (ImageView) view.findViewById(R.id.iv_state);
                holder.tvState = (TextView) view.findViewById(R.id.tv_state);

                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            ListData mData = listDatas.get(i);

            holder.ivState.setImageDrawable(mData.mPIRState);
            holder.tvLabel.setText(i + "번째 칸");
            holder.tvState.setText(mData.mIR);

            return view;
        }

        public void addItem(Drawable pirState, String pir, String ir) {
            ListData addInfo = new ListData();
            addInfo.mPIRState = pirState;
            addInfo.mPIR = pir;
            addInfo.mIR = ir;

            listDatas.add(addInfo);
            dataChange();
        }

        public void remove(int position) {
            listDatas.remove(position);
            dataChange();
        }

        public void dataChange() {
            listViewAdapter.notifyDataSetChanged();
        }
    }
}
