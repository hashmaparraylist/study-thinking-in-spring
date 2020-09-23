package io.github.hashmaparraylist.i18n;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * {@Link MessageFormat} 示例
 *
 * @author
 * @date 2020/9/23
 * @see MessageFormat
 */
public class MessageFormatDemo {
    public static void main(String[] args) {
        int planet = 7;
        String event = "a disturbance in the Force";
        String messageFormatPattern = "At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.";
        MessageFormat mf = new MessageFormat(messageFormatPattern);
        String result = mf.format(new Object[]{planet, new Date(), event});
        System.out.println(result);
        // 重置 MessageFormat Pattern
        // apply
        messageFormatPattern = "This a text : {0}, {1}, {2}";
        mf.applyPattern(messageFormatPattern);
        result = mf.format(new Object[]{"Hello, world!", "666"});
        System.out.println(result);

        // 重置 Locale
        mf.setLocale(Locale.ENGLISH);
        messageFormatPattern = "At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.";
        mf.applyPattern(messageFormatPattern);
        result = mf.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        // 重置 Format
        // 根据参数索引来设置 Pattern
        mf.setFormat(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        result = mf.format(new Object[]{planet, new Date(), event});
        System.out.println(result);
    }
}
