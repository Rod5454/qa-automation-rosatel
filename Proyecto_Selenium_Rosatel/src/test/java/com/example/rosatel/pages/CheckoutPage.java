package com.example.rosatel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By checkboxTerminos = By.id("terms_conds");
    private By botonContinuar = By.id("custom-checkout-button");
    private By inputNombre = By.id("client-first-name");
    private By inputApellidos = By.id("client-last-name");
    private By selectTipoDoc = By.id("client-document-type");
    private By inputDocumento = By.id("client-document");
    private By inputTelefono = By.id("client-phone");
    private By botonContinuarDatos = By.id("btn-submit-clientData");

    private By opcionMotivo = By.xpath("//label[contains(@class, 'motivo')]");
    private By botonGuardarDedicatoria = By.xpath("//div[contains(@class, 'modal-footer')]//button[contains(text(), 'Guardar')]");
    private By botonContinuarSinDedicatoria = By.id("continuar-sin-dedicatoria");

    private By tabRetiroTienda = By.id("shipping-option-pickup-in-point");
    private By inputDestinatario = By.id("ship-nameReceiver");
    private By inputTelefonoDestinatario = By.id("ship-phone");
    private By botonElegirFecha = By.xpath("//button[contains(@class, 'shp-datepicker-button')]");

    private By botonIrAlPago = By.id("btn-go-to-payment");

    private By tabTarjetaDebito = By.id("payment-group-debitCardPaymentGroup");
    private By inputNumeroTarjeta = By.id("creditCardpayment-card-0Number");
    private By inputNombreTarjeta = By.id("creditCardpayment-card-0Name");
    private By selectMesTarjeta = By.id("creditCardpayment-card-0Month");
    private By selectAnioTarjeta = By.id("creditCardpayment-card-0Year");
    private By inputCvvTarjeta = By.id("creditCardpayment-card-0Code");

    private By selectDepartamento = By.id("payment-billing-address-state-0");
    private By selectProvincia = By.id("payment-billing-address-city-0");
    private By selectDistrito = By.id("payment-billing-address-neighborhood-0");
    private By inputCalleFact = By.id("payment-billing-address-street-0");
    private By inputNumeroFact = By.id("payment-billing-address-number-0");

    private By botonFinalizarCompra = By.id("btn-finalizar-compra");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void validarIngresoAlCheckout() {
        try {
            System.out.println("8. Validando ingreso a la pasarela de pago...");
            wait.until(ExpectedConditions.urlContains("checkout"));
            System.out.println("¡Página de Checkout cargada con éxito!");
        } catch (Exception e) {
            System.out.println("Error al cargar el checkout: " + e.getMessage());
        }
    }

    public void aceptarTerminosYContinuar() {
        try {
            System.out.println("9. Aceptando Términos y Condiciones...");
            Thread.sleep(4000);
            WebElement chkTerminos = wait.until(ExpectedConditions.presenceOfElementLocated(checkboxTerminos));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", chkTerminos);
            Thread.sleep(1000);
            js.executeScript("arguments[0].click();", chkTerminos);
            Thread.sleep(1000);
            WebElement btnContinuar = wait.until(ExpectedConditions.elementToBeClickable(botonContinuar));
            js.executeScript("arguments[0].click();", btnContinuar);
        } catch (Exception e) {
            System.out.println("Error en términos: " + e.getMessage());
        }
    }

    public void manejarPopUpDedicatoria() {
        try {
            System.out.println("11. Manejando Pop-up de Dedicatoria...");
            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(8));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            WebElement motivo = longWait.until(ExpectedConditions.elementToBeClickable(opcionMotivo));
            js.executeScript("arguments[0].click();", motivo);
            Thread.sleep(1000);

            WebElement btnGuardar = longWait.until(ExpectedConditions.elementToBeClickable(botonGuardarDedicatoria));
            js.executeScript("arguments[0].click();", btnGuardar);

            WebElement btnSinDedicatoria = longWait.until(ExpectedConditions.elementToBeClickable(botonContinuarSinDedicatoria));
            js.executeScript("arguments[0].click();", btnSinDedicatoria);
            Thread.sleep(4000);
        } catch (Exception e) {
            System.out.println(" No apareció el popup de dedicatoria, continuando...");
        }
    }

    public void llenarDatosPersonales(String nombre, String apellidos, String documento, String telefono) {
        try {
            System.out.println("12. Llenando Datos Personales...");
            WebElement txtNombre = wait.until(ExpectedConditions.elementToBeClickable(inputNombre));
            txtNombre.clear();
            txtNombre.sendKeys(nombre);

            driver.findElement(inputApellidos).sendKeys(apellidos);
            new Select(driver.findElement(selectTipoDoc)).selectByVisibleText("DNI");
            driver.findElement(inputDocumento).sendKeys(documento);
            driver.findElement(inputTelefono).sendKeys(telefono);

            WebElement btnContinuar = wait.until(ExpectedConditions.elementToBeClickable(botonContinuarDatos));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnContinuar);
        } catch (Exception e) {
            System.out.println("Error en datos personales: " + e.getMessage());
        }
    }

    public void seleccionarRetiroEnTienda(String destinatario, String telefono) {
        try {
            System.out.println("14. Cambiando a modalidad 'Retirar en Tienda'...");
            Thread.sleep(5000);
            JavascriptExecutor js = (JavascriptExecutor) driver;

            WebElement tabRetiro = wait.until(ExpectedConditions.elementToBeClickable(tabRetiroTienda));
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", tabRetiro);
            Thread.sleep(1000);
            tabRetiro.click();
            Thread.sleep(2000);

            WebElement txtDestinatario = wait.until(ExpectedConditions.visibilityOfElementLocated(inputDestinatario));
            txtDestinatario.clear();
            txtDestinatario.sendKeys(destinatario);

            WebElement txtTel = driver.findElement(inputTelefonoDestinatario);
            txtTel.clear();
            txtTel.sendKeys(telefono);

            System.out.println("Abriendo el calendario...");
            WebElement btnFecha = wait.until(ExpectedConditions.elementToBeClickable(botonElegirFecha));
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btnFecha);
            Thread.sleep(1000);
            btnFecha.click();
            Thread.sleep(2000);

            System.out.println("Buscando todos los días válidos...");
            List<WebElement> diasValidos = driver.findElements(By.cssSelector("div.react-datepicker__day:not(.react-datepicker__day--disabled):not(.react-datepicker__day--outside-month)"));

            for (WebElement dia : diasValidos) {
                if (dia.isDisplayed()) {
                    try {
                        System.out.println("¡Día " + dia.getText() + " encontrado! Intentando seleccionar...");
                        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", dia);
                        Thread.sleep(500);

                        dia.click();

                        System.out.println(" Fecha seleccionada. Esperando que el calendario se cierre...");
                        Thread.sleep(3000);
                        break;

                    } catch (Exception clickError) {
                        System.out.println("Ese día estaba bloqueado visualmente, buscando el correcto...");
                    }
                }
            }

            System.out.println("15. Dando clic en el botón 'Ir al Pago'...");
            WebElement btnPago = wait.until(ExpectedConditions.elementToBeClickable(botonIrAlPago));
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btnPago);
            Thread.sleep(1000);

            try {
                btnPago.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", btnPago);
            }

            System.out.println("Validando que la página haya aceptado el botón rojo...");
            wait.until(ExpectedConditions.presenceOfElementLocated(tabTarjetaDebito));
            Thread.sleep(2000);
            System.out.println(" ¡Paso al área de pagos confirmado!");

        } catch (Exception e) {
            System.out.println("Error en retiro en tienda: " + e.getMessage());
        }
    }

    public void llenarPagoConDatosDummyYFinalizar() {
        try {
            System.out.println("16. Iniciando proceso de Pago con Tarjeta de Débito...");
            Thread.sleep(5000);
            JavascriptExecutor js = (JavascriptExecutor) driver;

            System.out.println("Dando clic en la pestaña Tarjeta de Débito...");
            Thread.sleep(4000);

            WebElement tabDebito = wait.until(ExpectedConditions.presenceOfElementLocated(tabTarjetaDebito));
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", tabDebito);
            Thread.sleep(1000);

            WebElement tabDebitoFresco = driver.findElement(tabTarjetaDebito);
            tabDebitoFresco.click();

            Thread.sleep(4000);

            System.out.println(" Detectando el Iframe de seguridad...");
            WebElement iframePago = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#payment-data iframe")));
            driver.switchTo().frame(iframePago);

            System.out.println(" Ingresando datos de la tarjeta...");
            WebElement txtNumTarjeta = wait.until(ExpectedConditions.elementToBeClickable(inputNumeroTarjeta));
            txtNumTarjeta.clear();
            txtNumTarjeta.sendKeys("4533333333333333");

            WebElement txtNombreTarjeta = driver.findElement(inputNombreTarjeta);
            txtNombreTarjeta.clear();
            txtNombreTarjeta.sendKeys("Rodolfo Gonzales");

            new Select(driver.findElement(selectMesTarjeta)).selectByVisibleText("03");
            new Select(driver.findElement(selectAnioTarjeta)).selectByVisibleText("28");

            WebElement txtCvv = driver.findElement(inputCvvTarjeta);
            txtCvv.clear();
            txtCvv.sendKeys("432");

            System.out.println(" Llenando dirección de facturación...");
            new Select(driver.findElement(selectDepartamento)).selectByVisibleText("Lima");
            Thread.sleep(2000);
            new Select(driver.findElement(selectProvincia)).selectByVisibleText("Lima");
            Thread.sleep(2000);
            new Select(driver.findElement(selectDistrito)).selectByVisibleText("Villa El Salvador");

            WebElement txtCalle = driver.findElement(inputCalleFact);
            txtCalle.clear();
            txtCalle.sendKeys("Calle Automatizacion 123");

            WebElement txtNumCasa = driver.findElement(inputNumeroFact);
            txtNumCasa.clear();
            txtNumCasa.sendKeys("999");

            driver.switchTo().defaultContent();

            System.out.println("17. Dando clic en FINALIZAR COMPRA...");
            WebElement btnFinalizar = wait.until(ExpectedConditions.presenceOfElementLocated(botonFinalizarCompra));
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btnFinalizar);
            Thread.sleep(1000);
            js.executeScript("arguments[0].click();", btnFinalizar);

            System.out.println(" Clic enviado. Esperando validación...");

        } catch (Exception e) {
            System.out.println(" Error en el proceso de pago: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
