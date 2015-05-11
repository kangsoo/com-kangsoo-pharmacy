package com.kangsoo.pharmacy.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.kangsoo.pharmacy.util.SettingsUtil;
import com.liferay.mobile.android.service.JSONObjectWrapper;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.task.callback.UploadProgressAsyncTaskCallback;
import com.liferay.mobile.android.v62.wikipage.WikiPageService;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by bsnc on 2015-05-05.
 */
public class WikiUploadAsyncTask extends AsyncTask<String, String, Integer> {

    private String _CLASS_NAME = WikiUploadAsyncTask.class.getName();

    private ProgressDialog mDlg;
    private Context mContext;
    private float totalSize;
    private int sendCount = 0;

    public WikiUploadAsyncTask(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected void onPreExecute() {

        mDlg = new ProgressDialog(mContext);
        mDlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mDlg.setMessage("전송...");
        mDlg.show();

        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(String... params) {

        String photoPath = params[0];
        totalSize = Float.parseFloat(params[1]);

        Session session = SettingsUtil.getSession();
        session.setCallback(_getCallback());

        WikiPageService service = new WikiPageService(session);

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("image", "image");

            String mimeType = "image/jpeg";

            File f = new File(photoPath);
            String filename = f.getName();

            InputStream is = new FileInputStream(f);
            InputStreamBody imageFile = new InputStreamBody(is, ContentType.DEFAULT_BINARY, filename);

            publishProgress("Max", Integer.toString(100));

            try {
                service.addPage(21478, filename, "Contents kangsookim {{" + filename + "}}", "", false, new JSONObjectWrapper(jsonObject));
                service.addPageAttachment(21478, filename, filename, imageFile, mimeType);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(String... progress) {

        if (progress[0].equals("progress")) {
            mDlg.setProgress(Integer.parseInt(progress[1]));
            mDlg.setMessage(progress[2]);
        } else if (progress[0].equals("Max")) {
            mDlg.setMax(Integer.parseInt(progress[1]));
        }
    }

    @Override
    protected void onPostExecute(Integer result) {
/*        if(sendCount != 0){
            mDlg.dismiss();
            Toast.makeText(mContext, "upload 작업 완료", Toast.LENGTH_SHORT).show();
        }
        sendCount = 1;*/
    }

    private UploadProgressAsyncTaskCallback<JSONObject> _getCallback() {
        return new UploadProgressAsyncTaskCallback<JSONObject>() {

            @Override
            public void onFailure(Exception exception) {
                Log.d(_CLASS_NAME, "exception = " + exception);
                Toast.makeText(mContext, "네트워크가 불안정 합니다. 재활영 후 전송해 주세요.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProgress(int totalBytes) {
                int progressValue = (int) ((totalBytes / totalSize) * 100);
                if(progressValue == 100){
                    publishProgress("progress", Integer.toString(progressValue), "전송완료. 잠시만 기다려 주세요.... ");
                }else{
                    publishProgress("progress", Integer.toString(progressValue), "전송 중... " + Float.toString(totalBytes) + " byte");
                }
                Log.d(_CLASS_NAME, "progress value : " + progressValue + ",  totalBytes kangsoo kim = " + totalBytes);
            }

            @Override
            public void onSuccess(JSONObject result) {
                if(result.toString().startsWith("{}")){
                    mDlg.dismiss();
                    Toast.makeText(mContext, "upload 작업 완료", Toast.LENGTH_SHORT).show();
                }
                Log.d(_CLASS_NAME, "UploadProgressAsyncTaskCallback result = " + result);
            }

            @Override
            public JSONObject transform(Object obj) throws Exception {
                Log.d(_CLASS_NAME, "transform : ===> " + obj.toString());
                return (JSONObject) obj;
            }

        };
    }
}
