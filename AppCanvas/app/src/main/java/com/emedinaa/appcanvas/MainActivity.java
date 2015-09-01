package com.emedinaa.appcanvas;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.emedinaa.appcanvas.components.RippleLayout;


public class MainActivity extends ActionBarActivity {

    private Button btnRipple;
    private RippleLayout rlayRipple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {

        rlayRipple= (RippleLayout)findViewById(R.id.rlayRipple);
        btnRipple= (Button)findViewById(R.id.btnRipple);

        btnRipple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rlayRipple.goAnimation();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return false;
    }
}
