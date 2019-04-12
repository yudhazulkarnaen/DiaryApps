package motion.diaryapps.create_notes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import motion.diaryapps.R;
import motion.diaryapps.dao.DiaryDao;
import motion.diaryapps.list_notes.ListNotesModel;
import motion.diaryapps.utils.Tools;

public class CreateNotesActivity extends AppCompatActivity {

    //  static Variable
    public static final int TYPE_ADD = 0;
    public static final int TYPE_EDIT = 1;
    public static final String KEY_TYPE = "type";
    public static final String KEY_DATA = "data";

    //  layout component
    private TextView mTvToolbarTitle;
    private EditText mEtCreateNotesTitle;
    private EditText mEtCreateNotesDescription;
    private LinearLayout mLlCreateNotesCover;
    private RelativeLayout mRlCreateNotesCover;
    private ImageView mIvCreateNotesEdit;
    private Button mBtnCreateNotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notes);

        int type = getIntent().getIntExtra(KEY_TYPE, 0);

        initToolbar();
        initComponent();
        initListener(type);
        changeState(type);
    }

    /**
     * Method ini digunakan untuk inisialisasi Objek
     */
    public void initComponent() {
        mTvToolbarTitle = findViewById(R.id.tvCreateNotesCover);
        mEtCreateNotesTitle = findViewById(R.id.etCreateNotesTitle);
        mEtCreateNotesDescription = findViewById(R.id.etCreateNotesDescription);
        mLlCreateNotesCover = findViewById(R.id.llCreateNotesCover);
        mRlCreateNotesCover = findViewById(R.id.rlCreateNotesCover);
        mIvCreateNotesEdit = findViewById(R.id.ivCreateNotesEdit);
        mBtnCreateNotes = findViewById(R.id.btnCreateNotes);
    }

    /**
     * method ini digunakan untuk mengganti tipe layout menjadi Edit atau Add
     * @param type Type jenis layout
     */
    public void changeState(int type) {
        switch (type) {
            case TYPE_EDIT:
                mTvToolbarTitle.setText("Edit Diary");
                initData((DiaryDao) getIntent().getSerializableExtra(KEY_DATA));
                mLlCreateNotesCover.setVisibility(View.GONE);
                mRlCreateNotesCover.setVisibility(View.VISIBLE);
                break;
            default:
                mTvToolbarTitle.setText("Create Diary");
                mLlCreateNotesCover.setVisibility(View.VISIBLE);
                mRlCreateNotesCover.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * Method ini digunakan untuk membuat toolbar
     */
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

    /**
     * Method ini digunakan untuk memasukkan data ke component
     * @param data data hasil {@link DiaryDao} yang akan ditampilkan
     */
    public void initData(DiaryDao data) {
        mEtCreateNotesTitle.setText(data.getTitle());
        mEtCreateNotesDescription.setText(data.getDescription());
        Tools.setImage(mIvCreateNotesEdit, data.getUrl_cover());
    }

    /**
     * Method ini digunakan untuk mengatur aksi yang ada di aktivitas
     * @param type tipe layout ( Edit / Create )
     */
    public void initListener(final int type) {
        mBtnCreateNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == TYPE_EDIT)
                    Toast.makeText(CreateNotesActivity.this, "button update clicked", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(CreateNotesActivity.this,"button create clicked ",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    /**
     * Method ini digunakan untuk melakukan perpindahan aktivitas
     * @param context Context dari Activity Sebelumnya
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CreateNotesActivity.class);
        intent.putExtra(KEY_TYPE, TYPE_ADD);
        context.startActivity(intent);
    }

    /**
     * Method ini digunakan untuk melakukan perpindahan aktivitas dengan mengirim data
     * @param context Context dari Activity sebelumnya
     * @param data data uang akan dikirim
     */
    public static void startActivity(Context context, DiaryDao data) {
        Intent intent = new Intent(context, CreateNotesActivity.class);
        intent.putExtra(KEY_TYPE, TYPE_EDIT);
        intent.putExtra(KEY_DATA, data);
        context.startActivity(intent);
    }
}
