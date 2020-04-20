# AOTTechnologies
Apache Kafka Test

Message definition :
 The  below message data is used for testing purpose. It includes both the orders : Book and Computer as asked in the requirement.
 Here ProductType :"Book" means the order is related to book.
 Similarly ProductType :"Computer" means the order is related to computer.
 [
  {
"ProductCode":1,
"ProductType": "Book",
    "ProductDescripton": "Harry Potter",
    "ProductQuantity" : 2,
    "ProductOrderStatus": ""
  },
  {
    "ProductCode":24,
    "ProductType": "Computer",
    "ProductDescripton": "HP",
    "ProductQuantity" : 10,
    "ProductOrderStatus": ""
  },
  {
    "ProductCode":12,
    "ProductType": "Computer",
    "ProductDescripton": "Lenovo",
    "ProductQuantity" : 2,
    "ProductOrderStatus": ""
  },
  {
    "ProductCode":212,
    "ProductType":"Book",
    "ProductDescripton": "SCJP",
    "ProductQuantity" : 4,
    "ProductOrderStatus": ""
  }
]

Description of various Java classes used :
1)AppProperties : It consist of all the common properties used by other classes.
2)OrderGenerator : This class is used to read the input json file .
3)JsonDeserializer : Used for deserialization purpose.
4)JsonSerializer :Used for serialization purpose.
5)OrderManagementApp :This class is used as a producer for topic:order-topic and in the end of the flow act as consumer for topic :orderStatus-topic.
6)OrderFulfilmentApp:This class is used as a consumer for topic:order-topic and this class calls : BookOrderFulfilment and ComputerOrderFulfilment class based on the order received.
7)BookOrderFulfilment : This class accepts the order as 'Book' and then act as producer for topic:orderStatus-topic.
8)ComputerOrderFulfilment: This class accepts the order as 'Computer' and then act as producer for topic:orderStatus-topic.

Tools used :
1) IntelliJ Idea 
2) Java
3) Apche Kafka
4) Docker 

Testing steps :
1) Run the docker-compose.yml by installing the docker.
2) Run the OrderManagementApp class from IDE.
3) Run the OrderFulfilmentApp class from IDE.

