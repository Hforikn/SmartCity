package cn.itcast.smartcity2.Tools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import cn.itcast.smartcity2.Activity.Bus.BSDetailsActivity;
import cn.itcast.smartcity2.Activity.NewsDetailsActivity;
import cn.itcast.smartcity2.Activity.TingN.CarListDetailsActivity;
import cn.itcast.smartcity2.Activity.WaiMai.DianPuDetailsActivity;
import cn.itcast.smartcity2.GetSet.BS;
import cn.itcast.smartcity2.GetSet.BannerImg;
import cn.itcast.smartcity2.GetSet.Car;
import cn.itcast.smartcity2.GetSet.Icon;
import cn.itcast.smartcity2.GetSet.MingXi;
import cn.itcast.smartcity2.GetSet.News;
import cn.itcast.smartcity2.GetSet.News_FenLei;
import cn.itcast.smartcity2.GetSet.WaiMai.Wai_CaiPin;
import cn.itcast.smartcity2.GetSet.WaiMai.Wai_DianPu;
import cn.itcast.smartcity2.GetSet.WaiMai.Wai_FenLei;

public class JsonParse {
    public static List<BannerImg> getBanner(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<BannerImg>>(){}.getType();
        List<BannerImg> list = gson.fromJson(json,type);
        return list;
    }

    public static List<Icon> getIcon(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<Icon>>(){}.getType();
        List<Icon> list = gson.fromJson(json,type);
        return list;
    }

    public static List<News> getNews(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<News>>(){}.getType();
        List<News> list = gson.fromJson(json,type);
        return list;
    }

    public static List<News_FenLei> getNews_FenLei(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<News_FenLei>>(){}.getType();
        List<News_FenLei> list = gson.fromJson(json,type);
        return list;
    }

    public static List<MingXi> getMingXi(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<MingXi>>(){}.getType();
        List<MingXi> list = gson.fromJson(json,type);
        return list;
    }

    public static List<Car> getCar(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<Car>>(){}.getType();
        List<Car> list = gson.fromJson(json,type);
        return list;
    }

    public static List<Wai_FenLei> getWai_FenLei(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<Wai_FenLei>>(){}.getType();
        List<Wai_FenLei> list = gson.fromJson(json,type);
        return list;
    }

    public static List<Wai_DianPu> getWai_DainPu(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<Wai_DianPu>>(){}.getType();
        List<Wai_DianPu> list = gson.fromJson(json,type);
        return list;
    }
    public static List<Wai_CaiPin> getWai_CaiPinXQ(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<Wai_CaiPin>>(){}.getType();
        List<Wai_CaiPin> list = gson.fromJson(json,type);
        return list;
    }

    public static List<BS> getBS(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<List<BS>>(){}.getType();
        List<BS> list = gson.fromJson(json,type);
        return list;
    }


    public static Intent chNewsData(Context context, int pos, List<News> list){
        Intent intent = new Intent(context, NewsDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title",list.get(pos).getTitle());
        bundle.putString("img",list.get(pos).getCover());
        bundle.putString("content",list.get(pos).getContent());
        bundle.putString("pinglun", String.valueOf(list.get(pos).getCommentNum()));
        bundle.putString("time",list.get(pos).getPublishDate());
        intent.putExtras(bundle);
        return intent;
    }
    public static Intent chCarData(Context context, int pos, List<Car> list){
        Intent intent = new Intent(context, CarListDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("img",list.get(pos).getImgUrl());
        bundle.putString("title",list.get(pos).getParkName());
        bundle.putString("address",list.get(pos).getAddress());
        bundle.putString("juli",list.get(pos).getDistance());
        bundle.putString("open", String.valueOf(list.get(pos).getOpen()));
        bundle.putString("allPark",list.get(pos).getAllPark());
        bundle.putString("kongwei",list.get(pos).getVacancy());
        bundle.putString("money",list.get(pos).getRates());
        intent.putExtras(bundle);
        return intent;
    }

    public static Intent chDianPuData(Context context, int pos, List<Wai_DianPu> list){
        Intent intent = new Intent(context, DianPuDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("img",list.get(pos).getImgUrl());
        bundle.putString("title",list.get(pos).getName());
        bundle.putString("money","人均消费: " + list.get(pos).getAvgCost());
        bundle.putString("pingfen","评分: " + list.get(pos).getScore());
        bundle.putString("yue","月售: " + list.get(pos).getSaleQuantity());
        bundle.putInt("categoryId",list.get(pos).getThemeId());
        bundle.putInt("sellerId",+ list.get(pos).getId());
        intent.putExtras(bundle);
        return intent;
    }

    public static Intent chBS(Context context, int pos, List<BS> list){
        Intent intent = new Intent(context, BSDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title",list.get(pos).getName());
        bundle.putString("address",list.get(pos).getFirst() +"-------->" + list.get(pos).getEnd());
        bundle.putString("licheng","里程:" + list.get(pos).getMileage()+"里");
        bundle.putString("time", list.get(pos).getStartTime()+"至"+list.get(pos).getEndTime());
        bundle.putString("money","￥" + list.get(pos).getPrice());
        intent.putExtras(bundle);
        return intent;
    }
}
