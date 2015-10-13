package rsantillanc.linea1.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import rsantillanc.linea1.R;
import rsantillanc.linea1.ui.fragment.AccountFragment;
import rsantillanc.linea1.ui.fragment.HomeFragment;

public class Linea1Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Views
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavView;
    private ActionBarDrawerToggle mDrawerToggle;

    //Runtime
    private static Activity mActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linea1);
        mActivity = this;

        initViews();
        setUps();
    }

    private void setUps() {
        setUpActionBar();
        setUpNavView();
        setUpDrawerToggle();
        displayFragment(new HomeFragment());
    }

    private void setUpNavView() {
        mNavView.setNavigationItemSelectedListener(this);
    }


    private void setUpActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        mNavView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    private void setUpDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_linea1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else {
            //close menu list
            mDrawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }

    private void displayFragment(Fragment ui) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragments_content, ui);
        transaction.commit();
    }


    /**
     * Actualiza el título en el actionBar.
     *
     * @param sequence titulo a mostrar.
     */
    public static void updateTitle(CharSequence sequence) {
        mActivity.setTitle(sequence);
    }

    private void showToast(CharSequence s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
        } else
            super.onBackPressed();
    }


    //------------------------ CallBacks ------------------------

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        boolean isTransaction = false;
        Fragment ui = null;
        CharSequence title = null;


        switch (menuItem.getItemId()) {
            case R.id.nav_main_account:
                ui = AccountFragment.newInstance();
                title = getString(R.string.title_account);
                isTransaction = true;
                break;

            case R.id.nav_main_lines:
                showToast("Lines!");
                break;

            case R.id.nav_main_news:
                showToast("News!");
                break;

            case R.id.nav_main_guides:
                showToast("Guides!");
                break;

            default:
                ui = new HomeFragment();
                isTransaction = true;
                break;
        }


        if (isTransaction) {

            //Show indicate fragment
            displayFragment(ui);

            //Display a title
            getSupportActionBar().setTitle(title);

            //Active row
            menuItem.setChecked(true);

            //Close menu list
            mDrawerLayout.closeDrawers();
        }

        return true;
    }


}
