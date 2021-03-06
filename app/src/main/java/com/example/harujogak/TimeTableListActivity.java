package com.example.harujogak;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harujogak.user.User;
import com.example.harujogak.timetable.*;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Iterator;

public class TimeTableListActivity extends AppCompatActivity {
    private TableAdapter tableAdapter;
    ArrayList<MyTimeTable> usertable;

    //테스트용 임시 시간표
    private MyTimeTable exT = new MyTimeTable();
    private MyTimeTable exT1 = new MyTimeTable();
    private MyTimeTable exT2 = new MyTimeTable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable_list);

        //title bar 제거하기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        GridView gv = (GridView) findViewById(R.id.gridView1);
        RecyclerView recyclerView = findViewById(R.id.recycler1);

        //setdata2(); // 유저 정보 연결되면 삭제될 부분
        addToWeekList();
        addToDateList(this);

        // 그리드뷰 어댑터, 리스너
        // TODO
//        gv.setAdapter(tableAdapter);
//        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(TimeTableListActivity.this, TableByDateEditActivity.class);
//                if (position == user.getDateTable().size())
//                    intent.putExtra("byDate", -1);
//                else
//                    intent.putExtra("byDate", position);
//                startActivity(intent);
//            }
//        });
//
//        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
//        SimpleTextAdapter adapter = new SimpleTextAdapter(user.getWeekTable());
//        recyclerView.setAdapter(adapter);

    } // end of onCreate

    public void addToWeekList() {
//        user.getWeekTable();
    }

    public void addToDateList(Context c) {
            //        MyTimeTable empty = new MyTimeTable("새로 만들기");
            //        tableAdapter = new TableAdapter(c);
            //        for (int i = 0; i < user.getDateTable().size(); i++)
            //            tableAdapter.addItem(user.getDateTable().get(i));
            //        tableAdapter.addItem(empty);
        MyTimeTable empty = new MyTimeTable("새로 만들기");
        tableAdapter = new TableAdapter(c);
        Iterator it = usertable.iterator();
        while(it.hasNext()){
            MyTimeTable table = (MyTimeTable) it.next();
            System.out.println("herehere "+table.getDate());
            tableAdapter.addItem(table);
        }
        tableAdapter.addItem(empty);
    }

    // 그리드뷰 어댑터
    class TableAdapter extends BaseAdapter {
        Context context;
        ArrayList<MyTimeTable> items = new ArrayList<MyTimeTable>();

        public TableAdapter(Context c) {
            context = c;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(MyTimeTable item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            com.example.harujogak.TableItemView view;
            if (convertView == null) {
                view = new com.example.harujogak.TableItemView(context);
                view.setLayoutParams(new GridView.LayoutParams(320, 360));
                view.setPadding(1, 1, 1, 1);
            } else {
                view = (com.example.harujogak.TableItemView) convertView;
            }
            MyTimeTable item = items.get(position);

            view.setDate(item.getDate());
            view.setPieChart(item.getPieData()).setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                @Override
                public void onValueSelected(Entry e, Highlight h) {
                    Intent intent = new Intent(TimeTableListActivity.this, TableByDateEditActivity.class);
                    intent.putExtra("byDate", position);
                    startActivity(intent);
                }

                @Override
                public void onNothingSelected() {
                }
            });

            return view;
        }
    }

    // 리사이클러뷰 어댑터
    class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.ViewHolder> {
        private ArrayList<MyTimeTable> weekSchedule = null;

        // 아이템 뷰를 저장하는 뷰홀더 클래스.
        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView1;
            PieChart pieChart;

            ViewHolder(View itemView) {
                super(itemView);
                // 뷰 객체에 대한 참조. (hold strong reference)
                textView1 = itemView.findViewById(R.id.dateView);
                pieChart = itemView.findViewById(R.id.pieChartView);

                pieChart.setUsePercentValues(false);
                pieChart.setRotationEnabled(false);
                pieChart.getLegend().setEnabled(false);
                pieChart.getDescription().setEnabled(false);
                pieChart.setDrawHoleEnabled(false);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
//                            MyTimeTable item = (MyTimeTable) user.getWeekTable().get(position);
//                            Toast.makeText(getApplicationContext(), item.getDate(), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(TimeTableListActivity.this, TableByDayEditActivity.class);
                            intent.putExtra("byDay", position);
                            startActivity(intent);
                        }
                    }
                });
                pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                    @Override
                    public void onValueSelected(Entry e, Highlight h) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
//                            MyTimeTable item = (MyTimeTable) user.getWeekTable().get(position);
//                            Toast.makeText(getApplicationContext(), item.getDate(), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), TableByDayEditActivity.class);
                            intent.putExtra("byDay", position);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onNothingSelected() {
                    }
                });
            }
        }

        // 생성자에서 데이터 리스트 객체를 전달받음.
        SimpleTextAdapter(ArrayList<MyTimeTable> list) {
            weekSchedule = list;
        }

        // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
        @Override
        public SimpleTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.table_item, parent, false);
            SimpleTextAdapter.ViewHolder vh = new SimpleTextAdapter.ViewHolder(view);

            return vh;
        }

        // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
        @Override
        public void onBindViewHolder(SimpleTextAdapter.ViewHolder holder, int position) {
            MyTimeTable data = weekSchedule.get(position);
            holder.textView1.setText(data.getDate());
            PieData piedata;
            piedata = data.getPieData();
            holder.pieChart.setData(piedata);
        }

        // getItemCount() - 전체 데이터 갯수 리턴.
        @Override
        public int getItemCount() {
            return weekSchedule.size();
        }
    }// end of class

}