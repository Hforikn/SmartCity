package cn.itcast.smartcity2.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.itcast.smartcity2.Activity.Bus.BSActivity;
import cn.itcast.smartcity2.Activity.FEnXi2Activity;
import cn.itcast.smartcity2.Activity.FenXiActivity;
import cn.itcast.smartcity2.Activity.TingN.TingNActivity;
import cn.itcast.smartcity2.Activity.WaiMai.WaiMaiActivity;
import cn.itcast.smartcity2.Adapter.RightServiceAdapter;
import cn.itcast.smartcity2.GetSet.Icon;
import cn.itcast.smartcity2.R;

public class RightServiceFragment extends Fragment {
    private View view;
    private RecyclerView right_service_recyclerview;
    private List<Icon> iconList;
    private RightServiceAdapter rightServiceAdapter;

    public RightServiceFragment(List<Icon> list){
        this.iconList = list;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_right_service,container,false);
        initView();
        return view;
    }

    public void initView(){
        right_service_recyclerview = view.findViewById(R.id.right_service_recyclerview);
        right_service_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),4));
        rightServiceAdapter = new RightServiceAdapter(getActivity(), iconList, new RightServiceAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                switch (pos){
                    case 0:
                        if (iconList.get(pos).getServiceName().equals("停哪儿")){
                            Intent intent = new Intent(getActivity(), TingNActivity.class);
                            startActivity(intent);
                        }else if (iconList.get(pos).getServiceName().equals("智慧巴士")){
                            Intent intent = new Intent(getActivity(), BSActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case 2:
                        if (iconList.get(pos).getServiceName().equals("外卖订餐")){
                            Intent intent = new Intent(getActivity(), WaiMaiActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case 4:
                        if (iconList.get(pos).getServiceName().equals("数据分析")){
                            Intent intent = new Intent(getActivity(), FenXiActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case 11:
                        if (iconList.get(pos).getServiceName().equals("数据分析")){
                            Intent intent = new Intent(getActivity(), FEnXi2Activity.class);
                            startActivity(intent);
                        }
                        break;
                }
//                Toast.makeText(getActivity(), "点击" + pos +"号图标成功", Toast.LENGTH_SHORT).show();
            }
        });
        right_service_recyclerview.setAdapter(rightServiceAdapter);
    }
}