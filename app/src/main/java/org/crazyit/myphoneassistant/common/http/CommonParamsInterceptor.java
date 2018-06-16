package org.crazyit.myphoneassistant.common.http;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.crazyit.myphoneassistant.common.Constant;
import org.crazyit.myphoneassistant.common.util.ACache;
import org.crazyit.myphoneassistant.common.util.DensityUtil;
import org.crazyit.myphoneassistant.common.util.DeviceUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by Administrator on 2018/6/13.
 */

public class CommonParamsInterceptor implements Interceptor {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    private Gson mGson;
    private Context mContext;
    public  CommonParamsInterceptor(Context context,Gson gson){
        this.mGson=gson;
        this.mContext=context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {



        //我们先获取到最原始的额request
        Request request=chain.request();//112.124.22.238:8081/course_api/cniaoplay/featured?p={'page':0}

        try {

            HashMap<String, Object> commonParamsMap = new HashMap<>();
            commonParamsMap.put(Constant.IMEI, DeviceUtils.getIMEI(mContext));
            commonParamsMap.put(Constant.MODEL, DeviceUtils.getModel());
            commonParamsMap.put(Constant.LANGUAGE, DeviceUtils.getLanguage());
            commonParamsMap.put(Constant.os, DeviceUtils.getBuildVersionIncremental());
            commonParamsMap.put(Constant.RESOLUTION, DensityUtil.getScreenW(mContext) + "*" + DensityUtil.getScreenH(mContext));
            commonParamsMap.put(Constant.SDK, DeviceUtils.getBuildVersionSDK() + "");
            commonParamsMap.put(Constant.DENSITY_SCALE_FACTOR, mContext.getResources().getDisplayMetrics().density + "");

            //有些api是不需要token的
            String token=ACache.get(mContext).getAsString(Constant.TOKEN);
            commonParamsMap.put(Constant.TOKEN,token==null?"":token);


            //我们做一个判断是get还是post请求
            String method = request.method();
            if (method.equals("GET")) {
                HttpUrl httpUrl = request.url();

                //将参数转换成map对象
                HashMap<String, Object> rootMap = new HashMap<>();

                //通过httpurl拿到所有参数的名字
                Set<String> paramNames = httpUrl.queryParameterNames();
                for (String key : paramNames) {
                    //p={}&page=&ppa
                    if (Constant.PARAM.equals(key)) {
                        String oldParamJson = httpUrl.queryParameter(Constant.PARAM);
                        //这个地方需要判断是否是空指针异常,因为如果请求不需要参数p的时候
                        //那么你这个地方请求p参数那么便会请求为空,请求为空的话便是空指针异常
                        if (oldParamJson != null) {
                            HashMap<String, Object> p = mGson.fromJson(oldParamJson, HashMap.class);//原始参数

                            if (p != null) {
                                for (Map.Entry<String, Object> entry : p.entrySet()) {
                                    rootMap.put(entry.getKey(), entry.getValue());
                                }
                            }
                        }
                    } else {
                        rootMap.put(key, httpUrl.queryParameter(key));
                    }
                }

                rootMap.put("publicParams", commonParamsMap);//新组装好的参数
                String newJsonParams = mGson.toJson(rootMap);//{"page":0,"publicParams":{"imei":'xxxx',"sdk":14,......}}

                //重新去拼接Url
                String url = httpUrl.toString();

                int index = url.indexOf("?");
                if (index > 0) {
                    url = url.substring(0, index);
                }
                url = url + "?" + Constant.PARAM + "=" + newJsonParams;//112.124.22.238:8081/course_api/cniaoplay/featured?p={'page':0,"publicParams":{"imei":'xxxx',"sdk":14,......}}

                request = request.newBuilder().url(url).build();


            } else if (method.equals("POST")) {
                RequestBody body = request.body();


                HashMap<String,Object> rootMap = new HashMap<>();
                if(body instanceof FormBody){ // form 表单

                    for (int i=0;i<((FormBody) body).size();i++){

                        rootMap.put(((FormBody) body).encodedName(i),((FormBody) body).encodedValue(i));
                    }

                }
                else{

                    //因为数据量不大所以我们可以这么写如果数据量大的话我们便用那个缓存流来写
                    Buffer buffer = new Buffer();

                    body.writeTo(buffer);

                    String oldJsonParams =  buffer.readUtf8();

                    rootMap = mGson.fromJson(oldJsonParams,HashMap.class); // 原始参数
                    rootMap.put("publicParams",commonParamsMap); // 重新组装
                    String newJsonParams = mGson.toJson(rootMap); // {"page":0,"publicParams":{"imei":'xxxxx',"sdk":14,.....}}


                    request = request.newBuilder().post(RequestBody.create(JSON, newJsonParams)).build();

                }

            }
        }catch (JsonSyntaxException e){
            e.printStackTrace();
        }
        return chain.proceed(request);
    }
}
