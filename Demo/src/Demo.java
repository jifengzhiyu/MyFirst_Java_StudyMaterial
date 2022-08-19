/**
 * @author jifengzhiyu
 * @create 2022-08-19 16:23
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 *@ClassName demo
 *@Description TODO
 *@Author kaixin
 *@Date 2022/8/19 16:23
 *@Version 1.0
 */
public class demo {

    public static void main(String[] args) {
        String lastDayOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.print(lastDayOfMonth);
    }
}
