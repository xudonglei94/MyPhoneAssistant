package org.crazyit.myphoneassistant.common.rx;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonParseException;

import org.crazyit.myphoneassistant.common.exception.ApiException;
import org.crazyit.myphoneassistant.common.exception.BaseException;
import org.crazyit.myphoneassistant.common.exception.ErrorMessageFactory;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;


/**
 * Created by Administrator on 2018/6/11.
 */

public class  RxErrorHandler {

    private Context mContext;
    public  RxErrorHandler(Context context){
        this.mContext=context;

    }
    public  BaseException handleError( Throwable e){
        BaseException exception=new BaseException();
        if (e instanceof ApiException){
            exception.setCode(((ApiException)e).getCode());

        }else if (e instanceof SocketException){
            exception.setCode(BaseException.SOCKET_ERROR);

        }else  if (e instanceof HttpException){
            exception.setCode(BaseException.HTTP_ERROR);

        }else if(e instanceof SocketTimeoutException){
            exception.setCode(BaseException.SOCKET_TIMEOUT_ERROR);
        }else if (e instanceof JsonParseException){
            exception.setCode(BaseException.JSON_ERROR);
        }else{
            exception.setCode(BaseException.UNKNOWN_ERROR);
        }
        exception.setDisplayMessage(ErrorMessageFactory.create(mContext,exception.getCode()));
        return exception;
    }
    public void  showErrorMessage(BaseException e){
        Toast.makeText(mContext,e.getDisplayMessage(),Toast.LENGTH_LONG).show();
    }

}
