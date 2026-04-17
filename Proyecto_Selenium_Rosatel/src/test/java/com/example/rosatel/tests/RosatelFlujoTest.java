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

    @Test
    public void test03_FlujoCompletoDeCompra() throws InterruptedException {
        System.out.println("--- INICIANDO PRUEBA 3: FLUJO COMPLETO DE COMPRA ---");
        driver.get("https://www.rosatel.pe/");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.intentarLogin("gonzalesrodolfo100@gmail.com", "Rodolfo5454");
        Thread.sleep(3000); 
        
        System.out.println("Iniciando proceso de carrito...");
        CartPage cartPage = new CartPage(driver);
        cartPage.buscarYAgregarProducto("rosas");
        
        cartPage.irAlCheckout();
        
        System.out.println("Entregando el control a la página de Checkout...");
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.validarIngresoAlCheckout();

        checkoutPage.aceptarTerminosYContinuar();
        
        checkoutPage.manejarPopUpDedicatoria();

        checkoutPage.llenarDatosPersonales("Rodolfo", "Gonzales Castillo", "75869160", "970615796");
        
        checkoutPage.seleccionarRetiroEnTienda("Rodolfo", "999999999");

        checkoutPage.llenarPagoConDatosDummyYFinalizar();

        System.out.println("PRUEBA FINALIZADA CORRECTAMENTE.");
        Thread.sleep(20000); 
    }
}
