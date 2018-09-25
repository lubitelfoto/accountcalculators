package space.chekanov.accountcalculators;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;

final class NDSCalc {

  private static NDSCalc instance;

    private NDSCalc(){
    }

    public static NDSCalc getInstance(){
        if(instance == null){
            instance = new NDSCalc();
        }
        return instance;
    }

    private BigDecimal taxNDS = new BigDecimal(AccountCalcConstant.NDS_TAX);
    private BigDecimal sumNDS = new BigDecimal(0);
    private BigDecimal sumWithNDS = new BigDecimal(0);
    private BigDecimal sumWithoutNDS = new BigDecimal(0);

    public void setNDS(BigDecimal taxNDS, int sourceNDS){
        switch (sourceNDS){
            case R.id.NDS_calc_sum:
                sumWithoutNDS = PercentCalc.getSumWithoutPercent(sumWithNDS, taxNDS);
                sumNDS = PercentCalc.getPercentValue(sumWithNDS, sumWithoutNDS);
                break;
            case R.id.NDS_calc_sum_no_tax:
                sumWithNDS = PercentCalc.getSumWithPercent(sumWithoutNDS, taxNDS);
                sumNDS = PercentCalc.getPercentValue(sumWithNDS, sumWithoutNDS);
                break;
            default:
                break;
        }
    }

    public void setNDSSum(BigDecimal sumNDS, int sourceNDS){
            sumWithNDS = PercentCalc.getSumWithPercentFromPercentValue(sumNDS, taxNDS);
            sumWithoutNDS = PercentCalc.getSumWithoutPercentFromPercentValue(sumNDS, taxNDS);
    }

    public void setSumWithNDS(BigDecimal sumWithNDS, int sourceNDS){
        sumWithoutNDS = PercentCalc.getSumWithoutPercent(sumWithNDS, taxNDS);
        sumNDS = PercentCalc.getPercentValue(sumWithNDS, sumWithoutNDS);
    }

    public void setSumWithoutNDS(BigDecimal sumWithoutNDS, int sourceNDS){
        sumWithNDS = PercentCalc.getSumWithPercent(sumWithoutNDS, taxNDS);
        sumNDS = PercentCalc.getPercentValue(sumWithNDS, sumWithoutNDS);
    }

    public BigDecimal getSumNDS() {
        return sumNDS;
    }

    public BigDecimal getTaxNDS() {
        return taxNDS;
    }

    public BigDecimal getSumWithNDS() {
        return sumWithNDS;
    }

    public BigDecimal getSumWithoutNDS() {
        return sumWithoutNDS;
    }

    public static void noEmptyFocus(EditText text, View textOnFocus, Toast toast) {
        if (text.getText().length() <= 0) {
            toast.show();
            text.setText("1");
            textOnFocus.clearFocus();
            text.requestFocus();
        }
    }

}