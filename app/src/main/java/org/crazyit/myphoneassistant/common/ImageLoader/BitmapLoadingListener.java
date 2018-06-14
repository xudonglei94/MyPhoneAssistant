package org.crazyit.myphoneassistant.common.ImageLoader;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2018/6/13.
 */

public interface BitmapLoadingListener {

    void onSuccess(Bitmap b);

    void onError();
}
