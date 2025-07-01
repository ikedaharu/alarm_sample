package com.alarmclock.network;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncHttpMin extends AsyncTask<URL, Void, String> {

    private CallBackTask3 callbacktask3;

    AsyncHttpMin() {
    }

    /**
     *
     */
    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }


    /**
     * 非同期処理で天気情報を取得する.
     * @param urls 接続先のURL
     * @return 取得した天気情報
     */
    @Override
    protected String doInBackground(URL... urls) {

        final URL url = urls[0];
        HttpURLConnection con = null;

        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            // リダイレクトを自動で許可しない設定
            con.setInstanceFollowRedirects(false);
            con.connect();

            final int statusCode = con.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                System.err.println("正常に接続できていません。statusCode:" + statusCode);
                return null;
            }

            // レスポンス(JSON文字列)を読み込む準備
            final InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            if(null == encoding){
                encoding = "UTF-8";
            }
            final InputStreamReader inReader = new InputStreamReader(in, encoding);
            final BufferedReader bufReader = new BufferedReader(inReader);
            StringBuilder response = new StringBuilder();
            String line;

            // 1行ずつ読み込む
            while((line = bufReader.readLine()) != null) {
                response.append(line);
            }

            bufReader.close();
            inReader.close();
            in.close();

            // 受け取ったJSON文字列をパース
            JSONObject jsonObject = new JSONObject(response.toString());
            int TODAY_FORCAST_INDEX = 0;
            JSONObject todayForcasts = jsonObject.getJSONArray("forecasts").getJSONObject(TODAY_FORCAST_INDEX);

            return "最低気温は " + todayForcasts.getJSONObject("temperature").getJSONObject("min").getString("celsius")+"度です。";
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

    /**
     * 非同期処理が終わった後の処理.
     * @param result 非同期処理の結果得られる文字列
     */
    @Override
    public void onPostExecute(String result) {
        super.onPostExecute(result);
        callbacktask3.CallBack3(result);
    }

    /**
     *
     * @param _cbj
     */
    void setOnCallBack3(CallBackTask3 _cbj){
        callbacktask3 = _cbj;
    }

    /**
     * コールバック用のstaticなclass
     */
    public  static class CallBackTask3{
        public void CallBack3(String result){

        }
    }
}



