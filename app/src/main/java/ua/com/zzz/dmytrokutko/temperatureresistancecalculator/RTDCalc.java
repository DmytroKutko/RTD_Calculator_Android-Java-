package ua.com.zzz.dmytrokutko.temperatureresistancecalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

class RTDCalc {

    /**
     * Platinum ------------------------------------------------------------------------------------
     */
    private final double PT_ARR_A[] = {
            3.9083d * Math.pow(10, -3),
            3.969d * Math.pow(10, -3)
    };

    private final double PT_ARR_B[] = {
            -5.775d * Math.pow(10, -7),
            -5.841d * Math.pow(10, -7)
    };

    private final double PT_ARR_D1[] = {
            255.819d,
            9.1455d,
            -2.92363d,
            1.7909d
    };

    private final double PT_ARR_D2[] = {
            251.903d,
            8.80035d,
            -2.91506d,
            1.67611d
    };

    strictfp double calculatePlatinum(double R1, int R0, String alpha, String spinnerValue) {

        double A;
        double B;
        double D[];

        if (alpha.equals("α = 0.00385 ℃")) {
            D = PT_ARR_D1;
            A = PT_ARR_A[0];
            B = PT_ARR_B[0];
        } else {
            D = PT_ARR_D2;
            A = PT_ARR_A[1];
            B = PT_ARR_B[1];
        }
        double result = 0d;

        if (R1 / R0 < 1) {
            for (int i = 1; i <= 4; i++) {
                result += D[i - 1] * Math.pow((R1 / R0 - 1.0d), i);
            }
        } else if (R1 / R0 >= 1) {
            result = (Math.sqrt((A * A) - 4.0d * B * (1 - (R1 / R0))) - A) / (2 * B);
        }

        result = new BigDecimal(result).setScale(Integer.valueOf(spinnerValue), RoundingMode.UP).doubleValue();

        return result;
    }

    /**
     * Copper --------------------------------------------------------------------------------------
     */
    private final double COP_ARR_A[] = {
            4.28d * Math.pow(10, -3),
            4.26d * Math.pow(10, -3)
    };

    private final double COP_ARR_D[] = {
            233.87d,
            7.937d,
            -2.0062d,
            -0.3953d
    };

    strictfp double calculateCopper(double R1, int R0, String alpha, String spinnerValue) {
        double A;
        double D[] = COP_ARR_D;

        if (alpha.equals("α = 0.00428 ℃")) {
            A = COP_ARR_A[0];
        } else {
            A = COP_ARR_A[1];
        }

        double result = 0d;

        if (R1 / R0 < 1) {
            for (int i = 1; i <= 4; i++) {
                result += D[i - 1] * Math.pow(((R1 / R0) - 1.0d), i);
            }
        } else if (R1 / R0 >= 1) {
            result = ((R1 / R0) - 1) / A;
        }

        result = new BigDecimal(result).setScale(Integer.valueOf(spinnerValue), RoundingMode.UP).doubleValue();

        return result;
    }

    /**
     * Nickel --------------------------------------------------------------------------------------
     */
    private final double NIC_A = 3.969d * Math.pow(10, -3);

    private final double NIC_B = 3.969d * Math.pow(10, -6);

    private final double NIC_ARR_D[] = {
            144.096d,
            -25.502d,
            4.4876d
    };

    strictfp double calculateNickel(double R1, int R0, String spinnerValue) {
        double A = NIC_A;
        double B = NIC_B;
        double D[] = NIC_ARR_D;

        double result = 0d;

        if (R1 / R0 < 1) {
            result = (Math.sqrt((A * A) - 4.0d * B * (1 - (R1 / R0))) - A) / (2 * B);
        } else if (R1 / R0 >= 1) {
            for (int i = 1; i <= 3; i++) {
                result += D[i - 1] * Math.pow(((R1 / R0) - 1.6172d), i);
            }
        }

        result = new BigDecimal(result).setScale(Integer.valueOf(spinnerValue), RoundingMode.UP).doubleValue();

        return result;
    }
}
