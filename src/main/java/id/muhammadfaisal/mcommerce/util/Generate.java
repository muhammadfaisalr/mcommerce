package id.muhammadfaisal.mcommerce.util;

import java.text.SimpleDateFormat;

public class Generate {
    public static String invoiceNumber(Long orderId) {
        //generate with format "INV-YYYYMMDD-orderId
        //get current yyyyMmdd
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDateFormat.format(System.currentTimeMillis());
        return "INV-" + date + "-" + orderId;
    }
}
