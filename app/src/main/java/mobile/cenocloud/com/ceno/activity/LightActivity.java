package mobile.cenocloud.com.ceno.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import mobile.cenocloud.com.ceno.R;
import mobile.cenocloud.com.ceno.mqtt.RandomString;
import mobile.cenocloud.com.ceno.mqtt.TwtMqtt;

/**
 * Created by neroyang on 2017/11/26.
 */

public class LightActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView state;
    private TextView msgs;
    private String clientid;


    private TwtMqtt client;
    private Handler mHandler;

    private String _key = "ZyQg4+dgiwDwGkG0GW2JCg==";
    private String _secret = "T39ifrtfusXaAXTNEEYkzQ==";


    private Button btnBlink;
    private Button btnRed;
    private Button btnGreen;
    private Button btnBlue;


    private ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        this.btn_back = (ImageView) findViewById(R.id.top_back);
        this.btn_back.setOnClickListener(this);

        this.state = (TextView) findViewById(R.id.state);
        this.msgs = (TextView) findViewById(R.id.msg);

        this.btnBlink = (Button) findViewById(R.id.btnBlink);
        this.btnRed =(Button) findViewById(R.id.btnRed);
        this.btnGreen=(Button) findViewById(R.id.btnGreen);
        this.btnBlue=(Button) findViewById(R.id.btnBlue);


        this.btnBlink.setOnClickListener(this);
        this.btnRed.setOnClickListener(this);
        this.btnGreen.setOnClickListener(this);
        this.btnBlue.setOnClickListener(this);



        String cc = RandomString.getRandomString(6);
        SharedPreferences pref = LightActivity.this.getSharedPreferences("data",MODE_PRIVATE);


        this.clientid = pref.getString("clientid","");//第二个参数为默认值
        if(this.clientid.equals("")){
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("clientid",cc);
            editor.commit();
            this.clientid = cc;
        }




        this.mHandler = new Handler() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void handleMessage(Message msg) {
                //操作界面
                switch (msg.what) {
                    case 1:
                        state.setText(msg.obj.toString());
                        state.setText("连接成功");
                        state.setTextSize(15);
                        state.setTextSize(15);
                        state.setText("连接成功");
                        break;
                    case 2:
                        state.setTextSize(15);
                        state.setText("连接成功···");
                        System.out.println(msg.obj.toString());
                        msgs.setText(msg.obj.toString());//对于javabean直接给出class实例
                        break;
                    case 3:
                        state.setText(msg.obj.toString());
                        break;
                    default:
                        state.setText("连接失败");
                        break;
                }

                super.handleMessage(msg);
            }
        };

        new Thread(new Runnable() {

            @Override
            public void run() {
                client = new TwtMqtt();
                client.setBroker("tcp://47.94.251.146:1883");
                client.setTopic(clientid);
                client.setUserName(_key);
                client.setPassword(_secret);
                client.setQos(2);
                client.setClientId(clientid);

                client.setHandler(mHandler);

                client.init();
                client.listen(clientid);
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.top_back:
                Intent myIntent = new Intent();
                myIntent = new Intent(LightActivity.this, MainActivity.class);
                startActivity(myIntent);
                this.finish();
                break;
            case R.id.btnBlink:
                client.send("ZyQg4_dgiwDwGkG0GW2JCg/tony","1");
                break;
            case R.id.btnRed:
                client.send("ZyQg4_dgiwDwGkG0GW2JCg/tony","4");
                break;
            case R.id.btnGreen:
                client.send("ZyQg4_dgiwDwGkG0GW2JCg/tony","3");
                break;
            case R.id.btnBlue:
                client.send("ZyQg4_dgiwDwGkG0GW2JCg/tony","2");
                break;
        }
    }
}
