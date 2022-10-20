package cn.itcast.smartcity2.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.smartcity2.Adapter.LeftServiceAdapter;
import cn.itcast.smartcity2.GetSet.Icon;
import cn.itcast.smartcity2.R;

public class AllServiceFragment extends Fragment {

    private View view;
    private RecyclerView left_recyclerview;
    private FrameLayout right_frameLayout;
    private LeftServiceAdapter adapter;
    private RightServiceFragment rightServiceFragment;
    private List<Icon> iconList;
    public AllServiceFragment(List<Icon> list){
        this.iconList = list;
    }

    private List<Icon> list1;
    private List<Icon> list2;
    private List<Icon> list3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_all_service,container,false);
        initView();
        return view;
    }

    public void initView(){
        left_recyclerview = view.findViewById(R.id.service_left_recyclerview);
        right_frameLayout = view.findViewById(R.id.service_right_frameLayout);

        left_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),1));
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        for (int i = 0; i < iconList.size(); i++) {
            if (iconList.get(i).getServiceType().equals("车主服务")){
                list1.add(iconList.get(i));
            }else if (iconList.get(i).getServiceType().equals("生活服务")){
                list2.add(iconList.get(i));
            }else {
                list3.add(iconList.get(i));
            }
        }

        rightServiceFragment = new RightServiceFragment(list1);
        getFragmentManager()
                .beginTransaction()
                .add(R.id.service_right_frameLayout,rightServiceFragment)
                .commitAllowingStateLoss();

        adapter = new LeftServiceAdapter(getActivity(), new LeftServiceAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                switch (pos){
                    case 0:
                        rightServiceFragment = new RightServiceFragment(list1);
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.service_right_frameLayout,rightServiceFragment)
                                .commitAllowingStateLoss();
                        break;
                    case 1:
                        rightServiceFragment = new RightServiceFragment(list2);
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.service_right_frameLayout,rightServiceFragment)
                                .commitAllowingStateLoss();
                        break;
                    case 2:
                        rightServiceFragment = new RightServiceFragment(list3);
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.service_right_frameLayout,rightServiceFragment)
                                .commitAllowingStateLoss();
                        break;
                    case 3:
                        rightServiceFragment = new RightServiceFragment(iconList);
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.service_right_frameLayout,rightServiceFragment)
                                .commitAllowingStateLoss();
                        break;
                }
                //Toast.makeText(getActivity(), "点击成功", Toast.LENGTH_SHORT).show();
                adapter.setmPosition(pos);
                adapter.notifyDataSetChanged();
            }
        });
        left_recyclerview.setAdapter(adapter);
    }
}