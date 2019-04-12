package motion.diaryapps.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import motion.diaryapps.R;

public class Tools {
    private static String TIMESTAMP_PATTERN1 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static String TIMESTAMP_PATTERN2 = "MMMM dd, yyyy";
    private static String TIMESTAMP_PATTERN3 = "dd MMMM yyyy";

    @SuppressLint("SimpleDateFormat")
    public static String getTitleDate(String timestamp){
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat(TIMESTAMP_PATTERN1);
            Date date = dateFormat.parse(timestamp);
            SimpleDateFormat formatter = new SimpleDateFormat(TIMESTAMP_PATTERN2);
            return formatter.format(date);
        }catch (Exception e){
            Log.e("Tools", "getTitleDate: caused by "+e);
        }
        return timestamp;
    }

    @SuppressLint("SimpleDateFormat")
    public static String getNormalDate(String timestamp){
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat(TIMESTAMP_PATTERN1);
            Date date = dateFormat.parse(timestamp);
            SimpleDateFormat formatter = new SimpleDateFormat(TIMESTAMP_PATTERN3);
            return formatter.format(date);
        }catch (Exception e){
            Log.e("Tools", "getNormalDate: caused by "+e);
        }
        return timestamp;
    }

    @SuppressLint("SimpleDateFormat")
    public static Date getDateFromTimestamp(String timestamp){
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat(TIMESTAMP_PATTERN1);
            Date date = dateFormat.parse(timestamp);
            return date;
        }catch (Exception e){
            Log.e("Tools", "getNormalDate: caused by "+e);
        }
        return null;
    }

    @SuppressLint("SimpleDateFormat")
    public static String setTimestampFromDate(Date date){
        try{
            SimpleDateFormat formatter = new SimpleDateFormat(TIMESTAMP_PATTERN1);
            return formatter.format(date);
        }catch (Exception e){
            Log.e("Tools", "getNormalDate: caused by "+e);
        }
        return null;
    }

    @SuppressLint("SimpleDateFormat")
    public static String getCurrentDateISO8601(){
        TimeZone timezone= TimeZone.getTimeZone("UTC");
        SimpleDateFormat dateFormat = new SimpleDateFormat(TIMESTAMP_PATTERN1);
        dateFormat.setTimeZone(timezone);
        return dateFormat.format(new Date());
    }

    public static void setImage(ImageView view, String url){
        if(view != null){
            if(!TextUtils.isEmpty(url) && url.contains("https:")){
                Picasso.get()
                        .load(url)
                        .placeholder(R.color.colorGray)
                        .error(R.color.colorGray)
                        .fit()
                        .into(view);
            }else {
                view.setImageResource(R.color.colorGray);
            }
        }
    }
}
