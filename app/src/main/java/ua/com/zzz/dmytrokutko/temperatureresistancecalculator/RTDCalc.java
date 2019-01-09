package ua.com.zzz.dmytrokutko.temperatureresistancecalculator;

class RTDCalc {

    private final double arr_A[] = {
            3.9083 * Math.pow(10, -3),
            3.969 * Math.pow(10, -3)
    };

    private final double arr_B[] = {
            -5.775 * Math.pow(10, -7),
            -5.841 * Math.pow(10, -7)
    };

    private final double arr_D1[] = {
            255.819d,
            9.1455d,
            -2.92363d,
            1.7909d
    };

    private final double arr_D2[] = {
            251.903d,
            8.80035d,
            -2.91506d,
            1.67611d
    };

    strictfp double calculatePlatinum(double R1, int R0, String alpha) {

        double A;
        double B;
        double D[];

        if (alpha.equals("0.00385 ℃")) {
            D = arr_D1;
            A = arr_A[0];
            B = arr_B[0];
        } else {
            D = arr_D2;
            A = arr_A[1];
            B = arr_B[1];
        }
        double result = 0d;

        if (R1 / R0 < 1) {
            for (int i = 1; i <= 4; i++) {
                result += D[i - 1] * Math.pow((R1 / R0 - 1.0), i);
            }
        } else if (R1 / R0 >= 1) {
            result = (Math.sqrt((A * A) - 4.0 * B * (1 - (R1 / R0))) - A) / (2 * B);
        }

        return result;
    }
}
