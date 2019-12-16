package com.cdf.factory.streaminterface;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Barista {
	String OUTPUT_CHANNEL = "output_channel"; 
	
	String INPUT_CHANNEL = "input_channel";  
	
	
    //注解@Output声明了它是一个输出类型的通道，名字是output_channel。这一名字与app1中通道名一致，表明注入了一个名字为output_channel的通道，类型是output，发布的主题名为mydest。  
    @Output(Barista.OUTPUT_CHANNEL)
    MessageChannel logoutput();  
    
    @Input(Barista.INPUT_CHANNEL)  
    SubscribableChannel loginput();
}
