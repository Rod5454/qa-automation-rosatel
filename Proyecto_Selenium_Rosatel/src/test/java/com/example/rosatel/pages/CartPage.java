package com.example.rosatel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By barraBusqueda = By.xpath("//input[@placeholder='Buscar']");
    private By botonesAnadirCarrito = By.xpath("//button[contains(., 'Añadir al carrito')]");
    
    private By inputCiudad = By.xpath("//input[@placeholder='Elige una opción']");
    private By opcionMenuLima = By.xpath("//div[contains(@style, 'cursor: pointer') and contains(., 'Lima')]");
    private By botonConfirmar = By.xpath("//button[contains(text(), 'Confirmar')]");
    
    private By iconoCarrito = By.cssSelector(".vtex-minicart-2-x-minicartIconContainer");
    private By botonCheckout = By.id("proceed-to-checkout"); 

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void buscarYAgregarProducto(String producto) {
        try {
            System.out.println("1. Buscando producto: " + producto);
            WebElement inputBusqueda = wait.until(ExpectedConditions.elementToBeClickable(barraBusqueda));
            inputBusqueda.click();
            inputBusqueda.sendKeys(producto);
            inputBusqueda.sendKeys(Keys.ENTER);

            System.out.println("Esperando que carguen los productos...");
            Thread.sleep(6000); 

            System.out.println("2. Buscando el botón de 'Añadir al carrito'...");
            wait.until(ExpectedConditions.presenceOfElementLocated(botonesAnadirCarrito));
            List<WebElement> todosLosBotones = driver.findElements(botonesAnadirCarrito);
            
            WebElement botonReal = null;
            for (WebElement btn : todosLosBotones) {
                if (btn.isDisplayed()) { 
                    botonReal = btn;
                    break; 
                }
            }

            if (botonReal != null) {
                System.out.println("3. Botón encontrado. Haciendo scroll y dando clic...");
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", botonReal);
                Thread.sleep(1500); 
                js.executeScript("arguments[0].click();", botonReal);
                
                try {
                    WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(8));
                    
                    System.out.println("Pop-up detectado. Escribiendo 'Lima'...");
                    WebElement cajaCiudad = shortWait.until(ExpectedConditions.visibilityOfElementLocated(inputCiudad));
                    cajaCiudad.click(); 
                    cajaCiudad.clear();
                    cajaCiudad.sendKeys("Lima");
                    
                    System.out.println("Esperando la lista...");
                    WebElement itemLima = shortWait.until(ExpectedConditions.presenceOfElementLocated(opcionMenuLima));
                    Thread.sleep(1000); 
                    
                    System.out.println("Dando clic a 'Lima'...");
                    js.executeScript("arguments[0].click();", itemLima);
                    
                    System.out.println("Dando clic en Confirmar...");
                    WebElement btnConfirma = shortWait.until(ExpectedConditions.elementToBeClickable(botonConfirmar));
                    js.executeScript("arguments[0].click();", btnConfirma);
                    
                    System.out.println("Esperando que Rosatel procese...");
                    Thread.sleep(5000); 
                    
                    System.out.println("5. Re-escaneando para el clic definitivo...");
                    List<WebElement> botonesNuevos = driver.findElements(botonesAnadirCarrito);
                    for (WebElement btn : botonesNuevos) {
                        if (btn.isDisplayed()) {
                            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btn);
                            Thread.sleep(1000);
                            js.executeScript("arguments[0].click();", btn);
                            break;
                        }
                    }
                    
                } catch (Exception popUpException) {
                    System.out.println("No hubo pop-up, continuando...");
                }

                Thread.sleep(4000); 
                System.out.println("6. Abriendo la bolsa de compras...");
                WebElement btnCarrito = wait.until(ExpectedConditions.presenceOfElementLocated(iconoCarrito));
                js.executeScript("arguments[0].click();", btnCarrito);
                
                System.out.println("¡Producto en el carrito!");
                
            } else {
                System.out.println("No se encontró ningún botón visible.");
            }

        } catch (Exception e) {
            System.out.println("Error en el proceso de carrito: " + e.getMessage());
        }
    }

    public void irAlCheckout() {
        try {
            System.out.println("7. Dando clic en 'Ir al checkout'...");
            Thread.sleep(2000); 
            
            WebElement btnCheckout = wait.until(ExpectedConditions.elementToBeClickable(botonCheckout));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", btnCheckout);
            
        } catch (Exception e) {
            System.out.println("Error al ir al checkout: " + e.getMessage());
        }
    }
}
