package com.zplh.dtp;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * Created by yong hao zeng on 2018/7/17.
 */
public class PopUp  {
    Context context;
    PopupWindow popupWindow;
    OnClickListener onClickListener;

    public PopUp(Context context,OnClickListener onClickListener) {

        this.context = context;
        this.onClickListener = onClickListener;
    }

    public void showBottom(View view){
        View inflate = LayoutInflater.from(context).inflate(R.layout.permission_layout, null,false);
        inflate.findViewById(R.id.bt_success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onSuccess(popupWindow);
            }
        });

        inflate.findViewById(R.id.bt_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onError();
            }
        });
        popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                ,true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);

    }

    public interface OnClickListener {
        void onError();
        void onSuccess(PopupWindow popupWindow);

    }


}
