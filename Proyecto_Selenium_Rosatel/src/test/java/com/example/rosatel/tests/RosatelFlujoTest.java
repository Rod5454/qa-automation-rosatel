package com.example.rosatel.tests;

import com.example.rosatel.base.BaseTest;
import com.example.rosatel.pages.LoginPage;
import com.example.rosatel.pages.RegistroPage; 
import com.example.rosatel.pages.CartPage; 
import com.example.rosatel.pages.CheckoutPage; 

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class RosatelFlujoTest extends BaseTest {

    @Test
    public void test01_LoginNegativo() throws InterruptedException {
        System.out.println("--- INICIANDO PRUEBA 1: CASO NEGATIVO (LOGIN FALSO) ---");
        driver.get("https://www.rosatel.pe/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.intentarLogin("gmailfake@gmail.com", "fake123");
        Thread.sleep(5000); 
    }

    @Test
    public void test02_FlujoDeRegistro() throws InterruptedException {
        System.out.println("--- INICIANDO PRUEBA 2: FLUJO DE REGISTRO DE NUEVO USUARIO ---");
        driver.get("https://www.rosatel.pe/");
        
        RegistroPage registroPage = new RegistroPage(driver);
        registroPage.navegarYRegistrarCorreo("gmailprueba@gmail.com");
        
    }
}
