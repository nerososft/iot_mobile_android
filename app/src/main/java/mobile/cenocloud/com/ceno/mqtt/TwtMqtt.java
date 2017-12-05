package mobile.cenocloud.com.ceno.mqtt;

import android.os.Handler;
import android.os.Message;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by nero on 16-7-4.
 */
public class TwtMqtt {
    private String broker;
    private String userName;
    private String password;
    private String topic;
    private String msg;
    private Integer qos;
    private String clientId;
    private String content;
    private boolean isConnected = false;

    private Handler mhandler;

    public Handler getHandler() {
        return mhandler;
    }

    public void setHandler(Handler handler) {
        this.mhandler = handler;
    }

    private MemoryPersistence persistence;//
    private MqttClient mqttClient;
    private MqttConnectOptions mqttConnectOptions;
    private MqttMessage message;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getQos() {
        return qos;
    }

    public void setQos(Integer qos) {
        this.qos = qos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }


    public void init() {
        persistence = new MemoryPersistence();
        mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(false);
        mqttConnectOptions.setKeepAliveInterval(18330);
        mqttConnectOptions.setUserName(userName);
        mqttConnectOptions.setPassword(password.toCharArray());
        try {
            mqttClient = new MqttClient(broker, clientId, persistence);
            mqttClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {
                    isConnected = false;
                    System.out.println("lost connecion");
                    Message msg = new Message();
                    msg.what = 3;
                    msg.obj = "连接断开 !";
                    mhandler.sendMessage(msg);
                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    System.out.println("message arrived:"+mqttMessage.toString());
                    Message msg = new Message();
                    msg.what = 2;
                    msg.obj = mqttMessage.toString();
                    mhandler.sendMessage(msg);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    System.out.println(iMqttDeliveryToken.toString());
                }
            });
            if(!isConnected){
                mqttClient.connect(mqttConnectOptions);
                Message msg = new Message();
                msg.what = 1;
                System.out.println("connect to broker: "+broker);
                msg.obj = "连接到代理节点: "+broker.substring(6);
                mhandler.sendMessage(msg);

            }
            isConnected = true;
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);

        }
    }

    void closeConnection(){
        try{
            mqttClient.disconnect();
            mqttClient.close();
            if (!mqttClient.isConnected()){
                Message msg = new Message();
                msg.what = 3;
                msg.obj = "连接断开 !";
                mhandler.sendMessage(msg);
            }else{
                Message msg = new Message();
                msg.what = 3;
                msg.obj = "连接断开失败 !";
                mhandler.sendMessage(msg);
            }
        }catch (Exception eee){
            Message msg = new Message();
            msg.what = 3;
            msg.obj = eee.getMessage();
            mhandler.sendMessage(msg);
        }
    }

    public void listen(String topp){

        try {
            mqttClient.subscribe("/Hiw9Bo");
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);

        }
    }
    public void send(String topp,String ss){
        message = new MqttMessage(ss.getBytes());
        message.setQos(qos);
        try {
            mqttClient.publish("/"+topp, message);
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);

        }
    }
}
