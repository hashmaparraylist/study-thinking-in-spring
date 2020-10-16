package io.github.hashmaparraylist.conversion;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;

/**
 * String -> Properties {@link PropertyEditor}
 *
 * @author
 * @date 2020/10/15
 */
public class StringToPropertiesPropertyEditor extends PropertyEditorSupport implements PropertyEditor {
    // 1. 实现 setAsText 方法
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        // 2. 将 String 类型转换成 Properties 类型
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(text));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3. 临时存储 Properties 对象
        setValue(properties);

        // next 通过 getValue 方法获取 Properties 对象
    }

    @Override
    public String getAsText() {
        Properties properties = (Properties) getValue();

        StringBuilder text = new StringBuilder();
        
        for(Map.Entry<Object, Object> entry : properties.entrySet()) {
            text.append(entry.getKey()).append("=").append(entry.getValue()).append(System.getProperty("line.separator"));
        }

        return text.toString();
    }
}

