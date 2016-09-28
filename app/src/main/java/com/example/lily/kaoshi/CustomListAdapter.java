package com.example.lily.kaoshi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/21.
 */
public class CustomListAdapter extends BaseAdapter{
    LayoutInflater inflater;
    boolean flag[];
    ArrayList<Model> list;
    HashMap<Integer,Integer> hashMap;
    ListenerResult listener;
    StringBuffer str;
    Context context;
    public CustomListAdapter(Context context, ArrayList<Model> data) {
        // TODO Auto-generated constructor stub
        inflater = LayoutInflater.from(context);
        this.list = data;
        hashMap=new HashMap<>();
        str=new StringBuffer();
        this.context=context;
        flag=new boolean[list.size()];
        for (int i = 0; i < flag.length; i++) {
            flag[i]=true;
        }
    }
   //设置回调的监听
    public void setListener(ListenerResult listener){
        this.listener=listener;
        this.listener.sendResult(hashMap);
        Iterator iterator=hashMap.entrySet().iterator();
        while(iterator.hasNext())
        {
            Map.Entry<Integer,Integer> entry= (Map.Entry<Integer, Integer>) iterator.next();
            int id=entry.getKey();
            int answer=entry.getValue();
            if(answer==0)
            {
                str.append(id+".A|");
            }else if(answer==1)
            {
                str.append(id+".B|");
            }else if(answer==2)
            {
                str.append(id+".C|");
            }else if(answer==3)
            {
                str.append(id+".D|");
            }
        }
        Log.i("AAAAAAAAAAA", "=====Adapter====="+str.toString());
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v = convertView;
        ViewHolder holder = null;

        if (v == null) {
            v = inflater.inflate(R.layout.detail, parent, false);
            flag[position]=true;
            holder = new ViewHolder(v);
            v.setTag(holder);
            holder.group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    Integer pos = (Integer) group.getTag();
                    Model element = list.get(pos);
                    switch (checkedId) { // set the Model to hold the
                        // answer the user picked
                        case R.id.answer0:
                            element.current = Model.ANSWER_ONE_SELECTED;
                          //  Toast.makeText(context,"选择了A",Toast.LENGTH_LONG).show();
                            break;
                        case R.id.answer1:
                            element.current = Model.ANSWER_TWO_SELECTED;
                          //  Toast.makeText(context,"选择了B",Toast.LENGTH_LONG).show();
                            break;
                        case R.id.answer2:
                            element.current = Model.ANSWER_THREE_SELECTED;
                          //  Toast.makeText(context,"选择了C",Toast.LENGTH_LONG).show();
                            break;
                        case R.id.answer3:
                            element.current = Model.ANSWER_FOUR_SELECTED;

                             //   Toast.makeText(context,"选择了D",Toast.LENGTH_LONG).show();


                            break;
                        default:
                            element.current = Model.NONE; // Something was
                            // wrong set to
                            // the default
                    }
                }
            });
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.group.setTag(new Integer(position)); // I passed the current
        // position as a tag

        holder.t.setText(list.get(position).question); // Set the question body

        if (list.get(position).current != Model.NONE) {
            RadioButton r = (RadioButton) holder.group.getChildAt(list.get(position).current);
            r.setChecked(true);
            hashMap.put(position,list.get(position).current);
          //  Toast.makeText(context,"选择了",Toast.LENGTH_LONG).show();
        } else {
            holder.group.clearCheck(); // This is required because although the
            flag[position]=false;
            // Model could have the current
            // position to NONE you could be dealing
            // with a previous row where
            // the user already picked an answer.
        //   hashMap.put(position,list.get(position).current);
       //    Toast.makeText(context,"没有选择",Toast.LENGTH_LONG).show();

        }

        return v;
    }
    class ViewHolder {
        TextView t = null;
        RadioGroup group;

        ViewHolder(View v) {  //在viewHolder中初始化控件
            t = (TextView) v.findViewById(R.id.textView1);
            group = (RadioGroup) v.findViewById( R.id.group_me);
        }

    }
    public interface  ListenerResult{
        public void sendResult(HashMap<Integer,Integer> hashMap);
    }

}
