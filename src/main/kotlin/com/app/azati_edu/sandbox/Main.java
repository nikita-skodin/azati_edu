package com.app.azati_edu.sandbox;

public class Main {
    public static void main(String[] args) {
        MainSandbox.staticMethod();
        MainSandbox.Companion.staticMethod();  // Без @JvmStatic

        MainSandbox sandbox = new MainSandbox();
        sandbox.defParamsMethod("Name", 0);
        sandbox.defParamsMethod("Name");
        sandbox.defParamsMethod();
    }
}
