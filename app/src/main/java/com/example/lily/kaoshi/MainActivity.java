package com.example.lily.kaoshi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Model> dataList;
    CustomListAdapter cadapter;
    StringBuffer str=new StringBuffer();
    private String[] result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataList = new ArrayList<Model>();
        //得到数据源
        setData();
        listView = (ListView)findViewById(R.id.ListView01);
         cadapter = new CustomListAdapter(MainActivity.this, dataList);
        listView.setAdapter(cadapter);
        Log.i("AAAAAAAA", "修改了的");
        Log.i("BBBBB", "真的忽略完成了吗？");

    }


    private void setData(){
        for (int i = 0; i < 100; i++) {
            Model model=new Model("这是第"+i+"题");
            dataList.add(model);
        }
       result=new String[dataList.size()];
    }


    public void MyOnclick(View view) {

       cadapter.setListener(new CustomListAdapter.ListenerResult() {
           @Override
           public void sendResult(HashMap<Integer, Integer> hashMap) {

               Iterator iterator=hashMap.entrySet().iterator();
               while(iterator.hasNext())
               {
                   Map.Entry<Integer,Integer> entry= (Map.Entry<Integer, Integer>) iterator.next();
                   int id=entry.getKey();
                   int answer=entry.getValue();
                   if(answer==0)
                   {
                      // str.append(id+".A|");
                       result[id]="A";
                   }else if(answer==1)
                   {
                     //  str.append(id+".B|");
                       result[id]="B";
                   }else if(answer==2)
                   {
                      // str.append(id+".C|");
                       result[id]="C";
                   }else if(answer==3)
                   {
                     //  str.append(id+".D|");
                       result[id]="D";
                   }
               }
           }
       });
 //       Log.i("AAAAAAAAAAA", "=========="+result.toString());
//        String aaa=str.toString();
//        Log.i("AAAAAAAAAAA", "=========="+aaa);
//
//        String ss[]=aaa.split("\\|");
//
        for (int i = 0; i < result.length; i++) {
            if(result[i]!=null)
            {
                Log.i("========", "========== "+result[i].toString());
            }

        }
    }
}
