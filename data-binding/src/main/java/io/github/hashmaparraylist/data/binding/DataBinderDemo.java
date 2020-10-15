package io.github.hashmaparraylist.data.binding;

import io.github.hashmaparraylist.ioc.overview.domain.Company;
import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link DataBinder} 示例
 *
 * @author
 * @date 2020/10/14
 * @see DataBinder
 */
public class DataBinderDemo {
    public static void main(String[] args) {

        // 创建空白对象
        User user = new User();
        // 1. 创建 DataBinder
        DataBinder binder = new DataBinder(user, "user");

        // 2. 创建 PropertyValues
        Map<String, Object> source = new HashMap<>();
        source.put("id", 1);
        source.put("name", "DataBinder测试");

        // a. PropertyValues 中存在 User 中不存在的属性
        // DataBinder 忽略未知的属性
        source.put("age", 18);

        // b. PropertyValues 存在一个嵌套属性
        // DataBinder 支持嵌套属性
        source.put("company", new Company());
        source.put("company.name", "Geekbang");

        PropertyValues propertyValues = new MutablePropertyValues(source);

        // 1. 调整 IgnoreUnknownFields : true(Default) -> false (抛出异常 age 字段不存在与 user 中)
        // binder.setIgnoreUnknownFields(false);

        // 2. 调整自动增加嵌套路径小 true(Default) -> false
        binder.setAutoGrowNestedPaths(false);

        // 3. 调整 IgnoreInvalidFields : false(Default) -> true (默认情况调整不变化, 需要调整 AutoGrowNestedPaths 为 false)
        binder.setIgnoreInvalidFields(true);

        binder.setRequiredFields("id", "name", "city");

        binder.bind(propertyValues);

        // 3. 输出 User 内容
        System.out.println(user);

        // 4. 获取绑定结果(结果包括错误文案code, 不会抛出异常   )
        BindingResult result = binder.getBindingResult();
        System.out.println(result);
    }
}
