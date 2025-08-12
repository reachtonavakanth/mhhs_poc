package azure;


import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusReceiverClient;
import com.azure.messaging.servicebus.administration.ServiceBusAdministrationClient;
import com.azure.messaging.servicebus.administration.ServiceBusAdministrationClientBuilder;
import com.azure.messaging.servicebus.administration.models.QueueRuntimeProperties;

public final class ServiceBusUtils {

    public String peekMessage(String connectionString, String queueName) {
        // Build a receiver client in "peek‑lock" mode (default)
        ServiceBusReceiverClient receiver =
                new ServiceBusClientBuilder()
                        .connectionString(connectionString)
                        .receiver()
                        .queueName(queueName)
                        .buildClient();
        try {
            ServiceBusReceivedMessage msg = receiver.peekMessage();
            return (msg != null) ? msg.getBody().toString() : null;
        } finally {
            receiver.close();
        }
    }



    public void getCounts(String connectionString, String queueName){
        ServiceBusAdministrationClient admin =
                new ServiceBusAdministrationClientBuilder()
                        .connectionString(connectionString)
                        .buildClient();

        QueueRuntimeProperties stats = admin.getQueueRuntimeProperties(queueName);
        System.out.println("Active messages   : " + stats.getActiveMessageCount());
        System.out.println("Dead‑letter count : " + stats.getDeadLetterMessageCount());
    }



}
