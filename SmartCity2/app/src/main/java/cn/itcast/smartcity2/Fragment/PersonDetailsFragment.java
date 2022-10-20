package cn.itcast.smartcity2.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.itcast.smartcity2.Activity.GeRenZX.DingDanActivity;
import cn.itcast.smartcity2.Activity.GeRenZX.GeRenXinXiActivity;
import cn.itcast.smartcity2.Activity.GeRenZX.QianBaoActivity;
import cn.itcast.smartcity2.Activity.GeRenZX.XiuGaiMiMaActivity;
import cn.itcast.smartcity2.Activity.GeRenZX.YiJianActivity;
import cn.itcast.smartcity2.Activity.MainActivity;
import cn.itcast.smartcity2.Adapter.PersonDetailsAdapter;
import cn.itcast.smartcity2.R;
import cn.itcast.smartcity2.Tools.SaveData;
import cn.itcast.smartcity2.Tools.SendRequest;

public class PersonDetailsFragment extends Fragment {
    private View view;
    private TextView details_userCard;
    private RecyclerView details_recyclerview;
    private PersonDetailsAdapter adapter;
    private MainFragment mainFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_details,container,false);
        initView();
        return view;
    }

    public void initView(){
        details_userCard = view.findViewById(R.id.details_userCard);
        details_recyclerview = view.findViewById(R.id.details_recyclerview);

        String token = SaveData.get_token(getActivity());
        SendRequest.get_request_token("/prod-api/api/common/user/getInfo",0,mHandler,token);


        details_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),1));
        details_recyclerview.setNestedScrollingEnabled(false);
        adapter = new PersonDetailsAdapter(getActivity(), new PersonDetailsAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                switch (pos){
                    case 0:
                        Intent intent = new Intent(getActivity(), GeRenXinXiActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getActivity(), QianBaoActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(getActivity(), DingDanActivity.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3= new Intent(getActivity(), XiuGaiMiMaActivity.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4= new Intent(getActivity(), YiJianActivity.class);
                        startActivity(intent4);
                        break;
                    case 5:
                        Map<String, Object> map = new HashMap<>();
                        SendRequest.post_request("/logout",1,mHandler,map);
                        break;
                }
            }
        });
        details_recyclerview.setAdapter(adapter);
    }

    private final Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                String data = (String) msg.obj;
                JSONObject jsonObject = JSONObject.parseObject(data);
                String user = jsonObject.getString("user");
                JSONObject userData =JSONObject.parseObject(user);
                String code = jsonObject.getString("code");
                String msgs = jsonObject.getString("msg");
                if (code.equals("200")){
                    details_userCard.setText(userData.getString("userName"));
                }else {
                    Toast.makeText(getActivity(), msgs, Toast.LENGTH_SHORT).show();
                }
            }else if (msg.what == 1){
                    Intent intent5 = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent5);
                    getActivity().finish();
                    SaveData.save_login(getActivity(),false);
            }

        }
    };

    @Override
    public void onResume() {
        super.onResume();
        if (SaveData.get_login(getActivity())){
            initView();
        }else {
            Intent intent = new Intent(getActivity(),MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }
}