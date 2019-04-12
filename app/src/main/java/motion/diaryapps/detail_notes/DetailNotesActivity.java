package motion.diaryapps.detail_notes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import motion.diaryapps.R;
import motion.diaryapps.create_notes.CreateNotesActivity;
import motion.diaryapps.dao.DiaryDao;
import motion.diaryapps.utils.Tools;

public class DetailNotesActivity extends AppCompatActivity {

//    constant value
    private static final String KEY_ID = "id";

//    layout component
    private ImageView mIvDetailNotes;
    private TextView mTvDetailNotesTitle;
    private TextView mTvDetailNotesDescription;

//    attribute
    private DiaryDao mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notes);

        mData = setDummy();

        initToolbar();
        initComponent();
        setData(mData);
    }

    public DiaryDao setDummy(){
        return new DiaryDao(
                "1",
                "Lost Paradise",
                "Lorem ipsum simdolor amet",
                "https://t-ec.bstatic.com/images/hotel/max1024x768/136/136201154.jpg",
                Tools.getCurrentDateISO8601());
    }

    public void setData(DiaryDao mData){
        mTvDetailNotesTitle.setText(mData.getTitle());
        mTvDetailNotesDescription.setText(mData.getDescription());
        getSupportActionBar().setTitle(Tools.getTitleDate(mData.getDate()));
        Tools.setImage(mIvDetailNotes,mData.getUrl_cover());
    }

    public void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void initComponent(){
        mIvDetailNotes = findViewById(R.id.ivDetailNotes);
        mTvDetailNotesTitle = findViewById(R.id.tvDetailNotesTitle);
        mTvDetailNotesDescription = findViewById(R.id.tvDetailNotesDescription);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menuDelete:
                showDialog();
                return true;
            case R.id.menuEdit:
                CreateNotesActivity.startActivity(this,mData);
                return true;
            case R.id.menuShare:
                Toast.makeText(this,"menu share di klik",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure want delete this diary?");
        builder.setTitle("Delete Diary");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DetailNotesActivity.this,"delete diary di click",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }

}
