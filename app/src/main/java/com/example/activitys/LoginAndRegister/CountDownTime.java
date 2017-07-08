package com.example.activitys.LoginAndRegister;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * Created by TurenK on 2017/7/6.
 */
class CountDownTime extends CountDownTimer {
    private Button sendMsg = null;

    //构造函数  第一个参数代表总的计时时长  第二个参数代表计时间隔  单位都是毫秒
    public CountDownTime(long millisInFuture, long countDownInterval, Button sendMsg) {
        super(millisInFuture, countDownInterval);
        this.sendMsg = sendMsg;
    }

    @Override
    public void onTick(long millisUntilFinished) { //每计时一次回调一次该方法
        sendMsg.setClickable(false);
        sendMsg.setText(""+millisUntilFinished/1000 + "s后重发");
    }

    @Override
    public void onFinish() { //计时结束回调该方法
        sendMsg.setClickable(true);
        sendMsg.setText("重获验证码");
    }
}