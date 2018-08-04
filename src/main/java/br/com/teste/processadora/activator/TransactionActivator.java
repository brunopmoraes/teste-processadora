package br.com.teste.processadora.activator;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
public class TransactionActivator {

	@ServiceActivator(inputChannel = "serviceChannel")
    public void service(String in) {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date()) + in);
    }
	
}
