package space.chekanov.accountcalculators;

import android.content.Intent;
import android.support.v4.app.Fragment;

public class NewCalcActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        Intent intent = getIntent();
        Fragment fragment = new Fragment();
        switch (intent.getIntExtra(AccountCalcConstant.FRAGMENT_ID, AccountCalcConstant.DEFAULT_FRAGMENT_ID)){
            case AccountCalcConstant.DEFAULT_FRAGMENT_ID:
                 fragment = new CalcListFragment();
                break;

             case AccountCalcConstant.NDS_FRAGMENT_ID:
                 fragment = new NDSCalcFragment();
                 break;

             default:
                fragment = new CalcListFragment();
                break;
        }
        return fragment;
    }
}
