package rsantillanc.linea1.ui.fragment;


import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rsantillanc.linea1.R;
import rsantillanc.linea1.ui.activity.Linea1Activity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment
        implements View.OnClickListener {

    private FloatingActionButton mFloatButton;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(v);
        return v;
    }

    private void initViews(View view) {
        mFloatButton = (FloatingActionButton) view.findViewById(R.id.fab_home);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mFloatButton.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.whitesmoke)));
        }
        mFloatButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        loadFragment(AccountFragment.newInstance());
    }

    private void loadFragment(Fragment ui) {
        FragmentManager mng = getActivity().getSupportFragmentManager();
        FragmentTransaction tst = mng.beginTransaction();
        tst.replace(R.id.fragments_content, ui);
        tst.commit();

        Linea1Activity.updateTitle(getActivity().getString(R.string.title_account));
    }
}
