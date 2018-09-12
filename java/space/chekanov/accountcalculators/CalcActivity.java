package space.chekanov.accountcalculators;

import android.support.v4.app.Fragment;

public class CalcActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new CalcListFragment();
    }
}
