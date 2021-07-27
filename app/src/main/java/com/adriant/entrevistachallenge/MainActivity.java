package com.adriant.entrevistachallenge;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.adriant.entrevistachallenge.Paging.CharacterViewModel;
import com.adriant.entrevistachallenge.Paging.ItemAdapter;
import com.adriant.entrevistachallenge.Utils.InternetCheck;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkInternet();

        btRetry = findViewById(R.id.bt_retry);
        btRetry.setOnClickListener(v -> checkInternet());

    }

    public void initList(){

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        CharacterViewModel characterViewModel = new ViewModelProvider(MainActivity.this).get(CharacterViewModel.class);
        final ItemAdapter adapter = new ItemAdapter(MainActivity.this);

        characterViewModel.characterPagedList.observe(this, adapter::submitList);

        recyclerView.setAdapter(adapter);

    }

    private void checkInternet(){
        new InternetCheck(internet -> {
            if(internet){
                initList();
                btRetry.setVisibility(View.GONE);
            } else{
                Toast.makeText(MainActivity.this, "Problemas de conexión, intente nuevamente", Toast.LENGTH_SHORT).show();
            }
        });
    }

}