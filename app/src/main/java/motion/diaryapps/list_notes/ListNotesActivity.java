package motion.diaryapps.list_notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import motion.diaryapps.R;
import motion.diaryapps.create_notes.CreateNotesActivity;
import motion.diaryapps.utils.Tools;

/**
 * Activity Untuk Menampilkan List Notes yang ada
 */
public class ListNotesActivity extends AppCompatActivity {

    private List<ListNotesModel> mLists = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    /**
     * Method ini digunakan untuk menjalankan aksi ketika activity dibuat
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);

        initToolbar();

        // TODO: 4/12/19 panggil initRecyclerView() disini
        initRecyclerView();
        // TODO: 4/12/19 panggil initDummy() disini
        initDummy();
    }

    /**
     * method ini digunakan untuk membuat toolbar
     */
    public void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Diary Apps");
    }

    /**
     * Method ini digunakan untuk membuat recyclerview.
     */
    public void initRecyclerView() {
        // TODO: 4/12/19 -> ganti null dengan component RecyclerView pada activity_list_notes
        // hint: gunakan findViewById(R.id.xxxxx);
        mRecyclerView = findViewById(R.id.rvListNotes);

        // TODO: 4/12/19 -> ganti null dengan objek ListNotesAdapter
        mAdapter = new ListNotesAdapter(mLists,this);

        // TODO: 4/12/19 -> ganti null dengan objek LinearLayoutManager
        mLayoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL,false);


        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Method ini digunakan untuk membuat data dummy pada list
     */
    public void initDummy() {
        // TODO: 4/12/19 -> lengkapi parameter untuk ListNotesModel disini
        // hint: lakukan setelah menambahkan constructor pada ListNotesModel
        // untuk date gunakan Tools.getCurrentDateISO8601()
        mLists.add(new ListNotesModel("1","https://ik.imagekit.io/hj8sm3kk7/medium/gallery/exterior/14/1995/honda-brio-front-angle-low-view-753743.jpg","Mobil","1 Februari 2017"));
        mLists.add(new ListNotesModel("2","https://cdn.moladin.com/motor/yamaha/Yamaha_Aerox_155_2061_77787_thumbnail.jpg","Motor","1 Januari 2017"));
        mLists.add(new ListNotesModel("3","https://www.torro-shop.de/media/image/product/4343/lg/1-16-rc-panzer-iv-g-ir-camouflage.jpg","Tank","1 Maret 2017"));
        mLists.add(new ListNotesModel("4","https://media.guideku.com/thumbs/2018/08/08/92141-ilustrasi-pesawat/745x489-img-92141-ilustrasi-pesawat.jpg","Pesawat","1 Mei 2017"));

        mAdapter.notifyDataSetChanged();
    }


    /**
     * Method ini digunakan untuk menambahkan options menu dengan men-inflate
     * layout menu pada {@code R.menu.menu_list_notes} ke {@code menu}
     *
     * @param menu menu bawaan pada toolbar
     * @return true - mengganti menu bawaan
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_notes, menu);
        return true;
    }

    /**
     * method ini digunakan untuk mengatur ketika item pada menu di klik
     *
     * @param item MenuItem Bawaan
     * @return jika item yang di klik merupakan menuAdd maka panggil
     * method {@code startActivity(context)} pada class {@link CreateNotesActivity}
     *
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuAdd) {
            CreateNotesActivity.startActivity(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
