package space.chekanov.accountcalculators;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;

public class NDSCalcFragment extends Fragment {

    int sourceNDS; //ресурс заполненный пользователем, что б понять какое значение вычислять при изменении ндс
    int focusField; //поле в фокусе, нужно для понимания что изменяет пользователь а что программа
    EditText taxNDS;
    EditText sumNDS;
    EditText sumWithNDS;
    EditText sumWithoutNDS;


    Toast toast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_nds_calc, container, false);

        taxNDS = view.findViewById(R.id.NDS_calc_tax);
        sumNDS = view.findViewById(R.id.NDS_calc_tax_sum);
        sumWithNDS = view.findViewById(R.id.NDS_calc_sum);
        sumWithoutNDS = view.findViewById(R.id.NDS_calc_sum_no_tax);
        final NDSCalc ndsCalc = NDSCalc.getInstance();



        toast = Toast.makeText(getActivity(), "Значение НДС должно быть больше ноля", Toast.LENGTH_LONG);
        taxNDS.setText(AccountCalcConstant.NDS_TAX);

        View.OnFocusChangeListener ndsOnFocusListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){

                    switch (v.getId()){
                        case R.id.NDS_calc_sum:
                            focusField = R.id.NDS_calc_sum;
                            NDSCalc.noEmptyFocus(taxNDS, v, toast);
                            break;
                        case R.id.NDS_calc_sum_no_tax:
                            focusField = R.id.NDS_calc_sum_no_tax;
                            NDSCalc.noEmptyFocus(taxNDS, v, toast);
                            break;
                        case R.id.NDS_calc_tax:
                            focusField = R.id.NDS_calc_tax;
                            NDSCalc.noEmptyFocus(taxNDS, v, toast);
                            break;
                        case R.id.NDS_calc_tax_sum:
                            focusField = R.id.NDS_calc_tax_sum;
                            NDSCalc.noEmptyFocus(taxNDS, v, toast);
                            break;
                        default:
                            break;
                    }
                }
            }
        };

        taxNDS.setOnFocusChangeListener(ndsOnFocusListener);
        sumNDS.setOnFocusChangeListener(ndsOnFocusListener);
        sumWithNDS.setOnFocusChangeListener(ndsOnFocusListener);
        sumWithoutNDS.setOnFocusChangeListener(ndsOnFocusListener);

        taxNDS.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String value = s.toString();

                if (taxNDS.getId() == focusField && !value.isEmpty()){
                    BigDecimal valueBigDec = new BigDecimal(value);
                    ndsCalc.setNDS(valueBigDec, sourceNDS);
                    sumNDS.setText(ndsCalc.getSumNDS().toString());
                    sumWithNDS.setText(ndsCalc.getSumWithNDS().toString());
                    sumWithoutNDS.setText(ndsCalc.getSumWithoutNDS().toString());
                }
            }
        });

        sumNDS.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String value = s.toString();

                if (sumNDS.getId() == focusField && !value.isEmpty()){
                    BigDecimal valueBigDec = new BigDecimal(value);
                    ndsCalc.setNDSSum(valueBigDec, sourceNDS);
                    sumWithNDS.setText(ndsCalc.getSumWithNDS().toString());
                    sumWithoutNDS.setText(ndsCalc.getSumWithoutNDS().toString());
                    sourceNDS = sumNDS.getId();
                }
            }
        });

        sumWithNDS.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String value = s.toString();

                if (sumWithNDS.getId() == focusField && !value.isEmpty()){
                    sourceNDS = sumWithNDS.getId();
                    BigDecimal valueBigDec = new BigDecimal(value);
                    ndsCalc.setSumWithNDS(valueBigDec, sourceNDS);
                    sumNDS.setText(ndsCalc.getSumNDS().toString());
                    sumWithoutNDS.setText(ndsCalc.getSumWithoutNDS().toString());
                }
            }
        });

        sumWithoutNDS.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String value = s.toString();

                if (sumWithoutNDS.getId() == focusField && !value.isEmpty()){
                    sourceNDS = sumWithoutNDS.getId();
                    BigDecimal valueBigDec = new BigDecimal(value);
                    ndsCalc.setSumWithoutNDS(valueBigDec, sourceNDS);
                    sumNDS.setText(ndsCalc.getSumNDS().toString());
                    sumWithNDS.setText(ndsCalc.getSumWithNDS().toString());
                }

            }
        });

        return view;
    }

}