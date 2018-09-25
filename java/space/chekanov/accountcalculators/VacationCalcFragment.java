package space.chekanov.accountcalculators;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class VacationCalcFragment extends Fragment {

    CardView vacationCard;
    CardView compensationCard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.fragment_calc_vacation, container, false);

        vacationCard = view.findViewById(R.id.btn_vacation_vac);
        compensationCard = view.findViewById(R.id.btn_vacation_comp);

        View.OnClickListener cardOnclickListener = (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewCalcActivity.class);
                intent.putExtra(AccountCalcConstant.FRAGMENT_ID, AccountCalcConstant.FRAGMENT_VACATION_VAC_ID);
                switch (v.getId()) {
                    case R.id.btn_vacation_vac:
                        intent.putExtra(AccountCalcConstant.EXTRA_VACATION_TYPE, AccountCalcConstant.VACATION_ID);
                        startActivity(intent);
                        break;
                    case R.id.btn_vacation_comp:
                        intent.putExtra(AccountCalcConstant.EXTRA_VACATION_TYPE, AccountCalcConstant.COMPENSATION_ID);
                        startActivity(intent);
                        break;
                    default:
                        Toast toast = Toast.makeText(getActivity(), "Выберите значение", Toast.LENGTH_LONG);
                        break;
                }
            }

        });

        vacationCard.setOnClickListener(cardOnclickListener);
        compensationCard.setOnClickListener(cardOnclickListener);

        return view;
    }
}
