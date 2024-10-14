package com.bhd.ibp.pages.pagostransferencias.accesos.beneficiarios;

import accion.AccionMetodos;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class POBeneficiarioInternacionalPage extends AccionMetodos {
    public POBeneficiarioInternacionalPage(WebDriver webDriver) {
        super(webDriver);
    }

    private By checkUsarBancoIntermediario = by("//p-checkbox[@name='useInterBank']/div/div[@data-p-highlight]");
    private By checkAddTransaccionFavorita = by("//p-checkbox[@name='transactionFavorite']/div/div[@data-p-highlight]");
    private By radioButtonFechaHoy = by("//div[label[contains(.,'Hoy')]]/p-radiobutton[@name='date']/div/div/input[@aria-checked]  "); // false or true
    private By radioButtonFechaFutura = by("//div[label[contains(.,'Fecha futura')]]/p-radiobutton[@name='date']/div/div/input[@aria-checked]");
    private By radioButtonTransaccionRecurrente = by("//div[label[contains(.,'Programar transacción recurrente')]]/p-radiobutton[@name='date']/div/div/input[@aria-checked]");





}
