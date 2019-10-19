package id.ac.poliban.dts.roman.sqlitetesting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ac.poliban.dts.roman.sqlitetesting.dao.impl.FriendDaoImplSQLite;
import id.ac.poliban.dts.roman.sqlitetesting.domain.Friend;

public class MainActivity extends AppCompatActivity {
    private List<Friend> data = new ArrayList<>();
    {
        data.add(new Friend("Vita Susanti", "Dago Utara, Bandung", "0812223"));
        data.add(new Friend("Junaidi Abdi", "Paiton, Probolinggo", "0812226"));
        data.add(new Friend("Yusie Rizal", "HKSN, Banjarmasin", "0812228"));
        data.add(new Friend("M. Yusuf", "Telawang, Banjarmasin", "0812222"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar()!=null) getSupportActionBar().setTitle("SQLite I Demo");

        //views to objects
        EditText etId = findViewById(R.id.etID);

        Button btUpgrage = findViewById(R.id.btUpgrade);
        Button btInsert = findViewById(R.id.btInsert);
        Button btUpdate = findViewById(R.id.btUpdate);
        Button btDelete = findViewById(R.id.btDelete);
        Button btGetAFriend = findViewById(R.id.btGetAFriend);
        Button btGetAllFriends = findViewById(R.id.btGetAllFriend);

        FriendDaoImplSQLite db = new FriendDaoImplSQLite(this);

        //event handler
        btUpgrage.setOnClickListener(v -> {
            db.onUpgrade(db.getReadableDatabase(), 1, 2);
            Toast.makeText(this, "upgrade successed!", Toast.LENGTH_SHORT).show();
        });

        btInsert.setOnClickListener(v -> {
            data.forEach(o-> db.insert(o));
            Toast.makeText(this, "inserted ok", Toast.LENGTH_SHORT).show();
        });

        btGetAllFriends.setOnClickListener(v -> {
            db.getAllFriends().forEach(o-> System.out.println(o));
            Toast.makeText(this, "showing data ok, check in 'run' monitor!", Toast.LENGTH_SHORT).show();
        });

        btUpdate.setOnClickListener(v -> {
            int id = Integer.parseInt(etId.getText().toString());
            db.update(new Friend(id, "XXX", "XXX", "XXX"));
            Toast.makeText(this, "updated success!, check in run monitor!", Toast.LENGTH_SHORT).show();
        });

        btDelete.setOnClickListener(v -> {
            int id = Integer.parseInt(etId.getText().toString());
            db.delete(id);
            Toast.makeText(this, "delete success!, check in run monitor!", Toast.LENGTH_SHORT).show();
        });

        btGetAFriend.setOnClickListener(v -> {
            int id = Integer.parseInt(etId.getText().toString());
            Friend f = db.getAFriendById(id);
            Toast.makeText(this, "get a friend success!, check in run monitor!", Toast.LENGTH_SHORT).show();
            System.out.println(f);;
        });

    }
}
