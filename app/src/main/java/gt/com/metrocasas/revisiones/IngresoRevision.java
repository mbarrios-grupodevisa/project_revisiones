package gt.com.metrocasas.revisiones;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

public class IngresoRevision extends AsyncTask<String, Integer, String> {

    Context context;
    String revisionid;
    List<Revision> listRevision;

    public IngresoRevision(Context context, List<Revision> listRevision) {
        this.context = context;
        this.listRevision = listRevision;
    }

    @Override
    protected String doInBackground(String... params) {
        try
        {
            String userid = params[0];
            String title = params[1];

            String link = "http://atreveteacrecer.metrocasas.com.gt/insertRevision.php";
            String data = URLEncoder.encode("userid", "UTF-8") + "=" + URLEncoder.encode(userid, "UTF-8")
                    + "&" + URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(title, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String line;
            if((line = reader.readLine()) != null)
            {
                sb.append(line);
            }
            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("project");
            if(jsonArray.length() > 0)
            {
                JSONObject objecto = jsonArray.getJSONObject(0);
                revisionid = objecto.getString("id");
            }
            for (Revision i:listRevision) {
                link = "http://atreveteacrecer.metrocasas.com.gt/insertModels.php";
                data = URLEncoder.encode("projectid", "UTF-8") + "=" + URLEncoder.encode(revisionid, "UTF-8");

                url = new URL(link);
                conn = url.openConnection();
                conn.setDoOutput(true);

                wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);
                wr.flush();

                reader = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                sb = new StringBuilder();
                if((line = reader.readLine()) != null)
                {
                    sb.append(line);
                }

            }
            //listRevision.clear();
            return "success";
        }
        catch(Exception e)  {
            return "error";
        }
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {

    }

    @Override
    protected void onPostExecute(String result) {

    }

}
