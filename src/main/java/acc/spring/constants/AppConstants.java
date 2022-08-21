package acc.spring.constants;

import java.util.Arrays;
import java.util.List;

public final class AppConstants {
    public static final List<String> ALLOWED_ACCOUNT_TYPES = Arrays.asList("AHORROS", "CORRIENTE");

    public static final List<String> ALLOWED_MOVEMENT_TYPES = Arrays.asList("DEBITO", "CREDITO");

    public static final Long MINIMUN_ACCOUNT_INITIAL_VALUE = 0L;

    public static final Long MINIMUN_MOVEMENT_VALUE = 1L;

    public static final String REPORT_URL_NAME = "./reporte.pdf";

    }
