package br.com.teste.processadora.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.integration.transformer.ObjectToStringTransformer;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
public class TcpSocketConfiguration {

	@Value("${application.socket.tcp.port}")
	private Integer socketTcpPortListen;
	
	@Bean
    public TcpNetServerConnectionFactory cf() {
        return new TcpNetServerConnectionFactory(socketTcpPortListen);
    }

    @Bean
    public TcpReceivingChannelAdapter inbound(AbstractServerConnectionFactory cf) {
        TcpReceivingChannelAdapter adapter = new TcpReceivingChannelAdapter();
        adapter.setConnectionFactory(cf);
        adapter.setOutputChannel(tcpIn());
        return adapter;
    }

    @Bean
    public MessageChannel tcpIn() {
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "tcpIn", outputChannel = "serviceChannel")
    public ObjectToStringTransformer transformer() {
        return new ObjectToStringTransformer();
    }
}
