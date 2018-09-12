package space.chekanov.accountcalculators;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.View;

public class CalcListFragment extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_calc_list, container, false);

        CardView ndsCalc = view.findViewById(R.id.btn_nds_calc);
        ndsCalc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewCalcActivity.class);
                intent.putExtra(AccountCalcConstant.FRAGMENT_ID, AccountCalcConstant.NDS_FRAGMENT_ID);
                startActivity(intent);
            }
        });

        return view;
    }

}
