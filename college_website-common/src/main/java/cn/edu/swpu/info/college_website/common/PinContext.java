package cn.edu.swpu.info.college_website.common;


import cn.edu.swpu.info.college_website.domain.OpsFunction;

import java.util.List;

public class PinContext {

    private static ThreadLocal<String> local = new ThreadLocal<String>();
    private static ThreadLocal<String> roleLocal = new ThreadLocal<String>();
    private static ThreadLocal<List<OpsFunction>> functionData = new ThreadLocal<List<OpsFunction>>();

    public static String getPin() {
        String pin = local.get();
        return pin == null ? "" : pin;
    }

    public static void setPin(String pin) {
        local.set(pin);
    }

    public static String getRole() {
        String role = roleLocal.get();
        return role == null ? "" : role;
    }

    public static void setRole(String role) {
        roleLocal.set(role);
    }

    public static void remove() {
        local.remove();
        roleLocal.remove();
        functionData.remove();
    }



    public static List<OpsFunction> getFunctionData() {
        return functionData.get();
    }

    public static void setFunctionIds(List<OpsFunction> functionIds) {
        functionData.set(functionIds);
    }



}

