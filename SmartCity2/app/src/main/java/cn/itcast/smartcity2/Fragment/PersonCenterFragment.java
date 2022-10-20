package cn.itcast.smartcity2.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.itcast.smartcity2.Activity.GeRenZX.RegisterActivity;
import cn.itcast.smartcity2.R;
import cn.itcast.smartcity2.Tools.SaveData;
import cn.itcast.smartcity2.Tools.SendRequest;

public class PersonCenterFragment extends Fragment implements View.OnClickListener {
    private View view;
    private EditText login_userCard;
    private EditText login_passwd;
    private Button login_btn;
    private TextView login_register;
    private PersonDetailsFragment personDetailsFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_center,container,false);
        initView();
        return view;
    }

    public void initView(){
        login_userCard = view.findViewById(R.id.login_userCard);
        login_passwd = view.findViewById(R.id.login_passwd);
        login_btn = view.findViewById(R.id.login_btn);
        login_register = view.findViewById(R.id.login_register);

        login_register.setOnClickListener(this);
        login_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //跳转注册页面
            case R.id.login_register:
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
                break;
            //跳转个人详情页面
            case R.id.login_btn:
                String userCard = login_userCard.getText().toString();
                String passwd = login_passwd.getText().toString();
                if (TextUtils.isEmpty(userCard)){
                    Toast.makeText(getActivity(), "请输入账号", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(passwd)){
                    Toast.makeText(getActivity(), "请输入密码", Toast.LENGTH_SHORT).show();
                }else {
                    Map<String, Object> map = new HashMap<>();
                    map.put("username",userCard);
                    map.put("password",passwd);
                    SendRequest.post_request("/prod-api/api/login",0,mHandler,map);
                }
                break;
        }
    }
    private final Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                String data = (String) msg.obj;
                JSONObject jsonObject = JSONObject.parseObject(data);
                String code = jsonObject.getString("code");
                String msgs = jsonObject.getString("msg");
                String token = jsonObject.getString("token");
                SaveData.save_token(getActivity(),token);
                if (code.equals("200")){
                    if (personDetailsFragment == null){
                        personDetailsFragment = new PersonDetailsFragment();
                    }
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_FrameLayout,personDetailsFragment)
                            .commitAllowingStateLoss();
                    SaveData.save_login(getActivity(),true);
                }
                Toast.makeText(getActivity(), msgs, Toast.LENGTH_SHORT).show();
            }
        }
    };
}