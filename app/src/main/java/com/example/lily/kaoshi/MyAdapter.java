package com.example.lily.kaoshi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/9/21.
 */
public class MyAdapter extends BaseAdapter {
    Context context;
    List<String> list;
    int flag[];
    LayoutInflater inflater;
    String str;
    int answer[];
    listenerResult listener;


    public MyAdapter(Context context,List<String> list) {
        this.context = context;
        this.list=list;
        flag=new int[list.size()];//用来表示listview复用时的混乱现象
        inflater=LayoutInflater.from(context);
        answer=new int[list.size()];
    }
    public void setListener(listenerResult listener)
    {
        this.listener=listener;
        listener.callResult(flag);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;

        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.content,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.textView= (TextView) convertView.findViewById(R.id.tv);
            viewHolder.radioGroup= (RadioGroup) convertView.findViewById(R.id.group);
            viewHolder.radioButton_01= (RadioButton) convertView.findViewById(R.id.button_01);
            viewHolder.radioButton_02= (RadioButton) convertView.findViewById(R.id.button_02);
            viewHolder.radioButton_03= (RadioButton) convertView.findViewById(R.id.button_03);
            viewHolder.radioButton_04= (RadioButton) convertView.findViewById(R.id.button_04);
            convertView.setTag(viewHolder);
        }else {
           viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(list.get(position));
        viewHolder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.button_01)
                {
                    flag[position]=1;
                    answer[position]=1;
                }else if(checkedId==R.id.button_02)
                {
                    flag[position]=2;
                    answer[position]=2;
                }else if(checkedId==R.id.button_03)
                {
                    flag[position]=3;
                    answer[position]=3;
                }else if(checkedId==R.id.button_04)
                {
                    flag[position]=4;
                    answer[position]=4;
                }
            }

        });
        if(flag[position]==0)//证明还没有选择
        {
            viewHolder.radioButton_01.setChecked(false);
            viewHolder.radioButton_02.setChecked(false);
            viewHolder.radioButton_03.setChecked(false);
            viewHolder.radioButton_04.setChecked(false);

        }else {  //已经选择
            viewHolder.radioGroup.clearCheck();
            if(answer[position]==1)
            {
                viewHolder.radioButton_01.setChecked(true);

            }else if(answer[position]==2)
            {
                viewHolder.radioButton_02.setChecked(true);
            }else if(answer[position]==3)
            {
                viewHolder.radioButton_03.setChecked(true);
            }else if(answer[position]==4)
            {
                viewHolder.radioButton_04.setChecked(true);
            }
            return convertView;
        }

        return convertView;
    }
    public class ViewHolder{
        RadioGroup radioGroup;
        RadioButton radioButton_01,radioButton_02,radioButton_03,radioButton_04;
        TextView textView;
    }
    public interface listenerResult{
        public void callResult(int[] result);
    }
}
