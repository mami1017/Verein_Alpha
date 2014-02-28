package de.juehvtech.verein.administration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import de.juehvtech.verein.R;

public class MenuActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administraion_main);

        // init Widgets
        menuList = (ListView) findViewById(R.id.adminMainMenuList);
        String[] mainMenu = {
                this.getResources().getString(R.string.menu_admin_training),
                this.getResources().getString(R.string.menu_admin_member)
                // this.getResources().getString(R.string.menu_admin_trainer)
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MenuActivity.this,
                android.R.layout.simple_list_item_1, mainMenu);
        menuList.setAdapter(adapter);
        menuList.setClickable(true);
        menuList.setOnItemClickListener(new MenuListListener());
    }

    // nested classes
    class MenuListListener implements OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arg0, View view, int position,
                                long index) {
            Intent intent;
            switch (position) {
//                case 0: // Training
//                    intent = new Intent(view.getContext(), EventActivity.class);
//                    startActivity(intent);
//                    break;
//                case 1: // Mitglieder
//                    intent = new Intent(view.getContext(),
//                            MemberActivity.class);
//                    startActivity(intent);
//                    break;
                // case 1: // Trainer
                // intent = new Intent(view.getContext(),
                // TrainerAdministrationActivity.class);
                // startActivity(intent);
                // break;
            }

        }

    }

    // atributes
    private ListView menuList;
}
