package io.github.hashmaparraylist.conversion;

import java.beans.PropertyEditor;

/**
 * {@link PropertyEditor} 示例
 *
 * @author
 * @date 2020/10/15
 *
 * @see PropertyEditor
 */
public class PropertyEditorDemo {
    public static void main(String[] args) {
        // 模拟 Spring Framework 操作
        // 有一段文本 name=PropertyEditorDemo测试
        String text = "name=PropertyEditorDemo测试";

        PropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();
        // 传递 String 内容
        propertyEditor.setAsText(text);

        System.out.println(propertyEditor.getValue());
        System.out.println(propertyEditor.getAsText());
    }
}
