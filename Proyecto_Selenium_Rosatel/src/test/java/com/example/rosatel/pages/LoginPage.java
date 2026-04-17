package com.example.rosatel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By iconoPerfil = By.xpath("//div[contains(@class, 'vtex-login-2-x-container')]//button");
    private By opcionEmailClave = By.xpath("//button[.//span[contains(text(), 'e-mail y contraseña')]]");

    private By correoInput = By.xpath("//input[@placeholder='Ej.: ejemplo@mail.com']");
    private By claveInput = By.xpath("//input[@type='password']");
    private By botonEntrar = By.xpath("//div[contains(@class, 'vtex-login-2-x-sendButton')]//button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void intentarLogin(String correo, String clave) {
        try {
            System.out.println("1. Dando clic al ícono de la personita...");
            WebElement btnPerfil = wait.until(ExpectedConditions.elementToBeClickable(iconoPerfil));
            btnPerfil.click();
            
            System.out.println(" Esperando la animación del menú...");
            Thread.sleep(2000); 

            System.out.println("2. Dando clic en 'Entrar con e-mail y contraseña'...");
            WebElement btnOpcion = wait.until(ExpectedConditions.elementToBeClickable(opcionEmailClave));
            btnOpcion.click(); 

            System.out.println(" Esperando que cambie el formulario...");
            Thread.sleep(2000);

            System.out.println("3. Escribiendo correo...");
            WebElement inputCorreo = wait.until(ExpectedConditions.visibilityOfElementLocated(correoInput));
            inputCorreo.clear();
            inputCorreo.sendKeys(correo);

            System.out.println("4. Escribiendo contraseña...");
            WebElement inputClave = driver.findElement(claveInput);
            inputClave.clear();
            inputClave.sendKeys(clave);

            System.out.println("5. Haciendo clic en Entrar...");
            WebElement btnEntrarFinal = driver.findElement(botonEntrar);
            btnEntrarFinal.click();
            
            System.out.println(" Login enviado correctamente. Esperando que cargue la cuenta...");
            Thread.sleep(3000);

        } catch (Exception e) {
            System.out.println(" Hubo un problema en el Login: " + e.getMessage());
        }
    }
}