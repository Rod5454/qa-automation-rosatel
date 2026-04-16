package com.example.rosatel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistroPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By iconoPerfil = By.xpath("//div[contains(@class, 'vtex-login-2-x-container')]//button");
    private By opcionEmailClave = By.xpath("//button[.//span[contains(text(), 'e-mail y contraseña')]]");
    
    private By linkRegistrese = By.xpath("//a[contains(@class, 'vtex-login-2-x-dontHaveAccount')]");
    
    private By inputEmailRegistro = By.xpath("//input[@name='email']");
    
    private By botonEnviar = By.xpath("//div[contains(@class, 'vtex-login-2-x-sendButton')]//button");

    public RegistroPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void navegarYRegistrarCorreo(String nuevoCorreo) {
        try {
            System.out.println("--- INICIANDO FLUJO DE REGISTRO ---");
            
            System.out.println("1. Abriendo el menú de perfil...");
            WebElement btnPerfil = wait.until(ExpectedConditions.elementToBeClickable(iconoPerfil));
            btnPerfil.click();
            Thread.sleep(2000); 

            System.out.println("2. Seleccionando 'Entrar con e-mail y contraseña'...");
            WebElement btnOpcion = wait.until(ExpectedConditions.elementToBeClickable(opcionEmailClave));
            btnOpcion.click();
            Thread.sleep(2000); 

            System.out.println("3. Haciendo clic en '¿No tiene una cuenta? Regístrese'...");
            WebElement btnRegistro = wait.until(ExpectedConditions.elementToBeClickable(linkRegistrese));
            btnRegistro.click();
            Thread.sleep(2000); 

            System.out.println("4. Ingresando el correo para el nuevo registro...");
            WebElement txtCorreo = wait.until(ExpectedConditions.visibilityOfElementLocated(inputEmailRegistro));
            txtCorreo.clear();
            txtCorreo.sendKeys(nuevoCorreo);

            System.out.println("5. Haciendo clic en Enviar para recibir el código...");
            WebElement btnEnvio = wait.until(ExpectedConditions.elementToBeClickable(botonEnviar));
            btnEnvio.click();

            System.out.println(" Correo enviado a Rosatel.");
            System.out.println(" PAUSA DE 15 SEGUNDOS: VTEX está pidiendo el código de validación.");
            System.out.println("Si estás en vivo, escribe el código de 6 dígitos que llegó a tu correo ahora mismo...");
            
            
            Thread.sleep(15000); 

        } catch (Exception e) {
            System.out.println(" Error en el flujo de registro: " + e.getMessage());
        }
    }
}