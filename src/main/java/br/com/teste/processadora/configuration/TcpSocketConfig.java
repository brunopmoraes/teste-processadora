package br.com.teste.processadora.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.ip.tcp.TcpInboundGateway;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.integration.transformer.ObjectToStringTransformer;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
public class TcpSocketConfig {

	@Value("${application.socket.tcp.port}")
	private Integer socketTcpPortListen;
	
	@Bean
    public TcpNetServerConnectionFactory cf() {
        return new TcpNetServerConnectionFactory(socketTcpPortListen);
    }

    @Bean
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }


    @Bean
    public MessageChannel outputChannel() {
    	return new DirectChannel();
    }

    @Bean
    public TcpInboundGateway tcpInGate(AbstractServerConnectionFactory cf)  {
        TcpInboundGateway inGateway = new TcpInboundGateway();
        inGateway.setConnectionFactory(cf());
        inGateway.setRequestChannel(inputChannel());
        inGateway.setReplyChannel(outputChannel());
        return inGateway;
    }
    
    @Bean
    @Transformer(inputChannel = "inputChannel", outputChannel = "serviceChannel")
    public ObjectToStringTransformer transformer() {
        return new ObjectToStringTransformer();
    }
}
