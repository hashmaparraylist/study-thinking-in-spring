package io.github.hashmaparraylist.aop.features.event;

public class Executor {

    public void execute() { // MethodMatcher: Join Point 方法 (需要 PointCut 来匹配)
        System.out.println("Executing...");
    }
}
