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

/**
 * Class ini digunakan untuk membuat semua method yang umum atau sering digunakan oleh applikasi
 */
public class Tools {
    private static String TIMESTAMP_PATTERN1 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static String TIMESTAMP_PATTERN2 = "MMMM dd, yyyy";
    private static String TIMESTAMP_PATTERN3 = "dd MMMM yyyy";

    /**
     * Method ini digunakan untuk mengganti format timestamp menjadi Date Pattern untuk title
     * format : MMMM dd, yyyy
     * ex. March 2, 2018
     * @param timestamp string timestamp menggunakan format ISO8601
     * @return string timestamp yang telah di format ulang
     */
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

    /**
     * Method ini digunakan untuk mengganti format timestamp menjadi Date Pattern pada Card
     * format : dd MMMM yyyy
     * ex. 2 March 2018
     * @param timestamp timestamp dengan format ISO8601
     * @return string hasil konversi
     */
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

    /**
     * Method ini digunakan untuk merubah timestamp menjadi class {@link Date}
     * @param timestamp timestamp dengan ISO8601 format
     * @return class {@link Date} hasil konversi
     */
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

    /**
     * Method ini digunakan untuk merubah {@link Date} ke {@link String} timestamp dengan ISO8601 format
     *
     * @param date class {@link Date} yang akan dirubah
     * @return hasil konversi ke ISO8601
     */
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

    /**
     * method ini digunakan untuk mengambil tanggal sekarang dan merubahnya ke format ISO8601
     * @return String hasil konversi dengan format ISO8601
     */
    @SuppressLint("SimpleDateFormat")
    public static String getCurrentDateISO8601(){
        TimeZone timezone= TimeZone.getTimeZone("UTC");
        SimpleDateFormat dateFormat = new SimpleDateFormat(TIMESTAMP_PATTERN1);
        dateFormat.setTimeZone(timezone);
        return dateFormat.format(new Date());
    }

    /**
     * digunakan untuk mengambil gambar dari website dengan menggunakan {@link Picasso}
     * @param view {@link ImageView} - view yang akan di masukkan gambar
     * @param url {@link String} - url gambar
     */
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
