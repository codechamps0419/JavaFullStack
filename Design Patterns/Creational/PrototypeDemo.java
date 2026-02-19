/*
@Author: Dynamic Duo Learners

REAL WORLD EXAMPLE:
API Request Template.

Many API requests may share the same common configuration:

- Authorization Token
- Content Type
- Client ID
- Timeout

Only some values, such as the endpoint, may change.


WITHOUT PROTOTYPE PATTERN:
Client creates a new ApiRequestTemplate object every time and repeatedly
sets the same common configuration.

Problems:
- Repeated object initialization.
- Duplicate setup/configuration code.
- Creating complex or expensive objects again and again can be costly.
- Changes to common configuration may need updates in multiple places.


WITH PROTOTYPE PATTERN:
Create one fully configured original object and clone it whenever
a similar object is required.

After cloning, only the required fields are changed.

Example:

Original Template
      |
      +--> clone() -> User API Request
      |
      +--> clone() -> Order API Request


MAIN IDEA:
"Create new objects by copying an existing configured object
 instead of creating and initializing them from scratch."


STEPS:
1. Create Prototype Class
2. Implement Cloneable
3. Override clone()
4. Create Original Object
5. Clone Existing Object
6. Modify Copy if required
7. Use Cloned Object


STRUCTURE:

Client
  |
  v
Original ApiRequestTemplate
  |
  +--> clone()
  |      |
  |      v
  |   User API Request
  |
  +--> clone()
         |
         v
      Order API Request
*/

public class PrototypeDemo {

    public static void main(String[] args) {

        ApiRequestTemplate template =
                new ApiRequestTemplate(
                        "Bearer xyz123",
                        "application/json",
                        "CLIENT-001",
                        30
                );

        ApiRequestTemplate userApi = template.clone();
        userApi.setEndpoint("/users");

        ApiRequestTemplate orderApi = template.clone();
        orderApi.setEndpoint("/orders");

        System.out.println(userApi);
        System.out.println(orderApi);
    }
}

class ApiRequestTemplate implements Cloneable {

    private String token;
    private String contentType;
    private String clientId;
    private int timeout;

    private String endpoint;

    public ApiRequestTemplate(
            String token,
            String contentType,
            String clientId,
            int timeout) {

        this.token = token;
        this.contentType = contentType;
        this.clientId = clientId;
        this.timeout = timeout;
    }

    @Override
    public ApiRequestTemplate clone() {

        try {
            return (ApiRequestTemplate) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String toString() {
        return "ApiRequestTemplate{" +
                "endpoint='" + endpoint + '\'' +
                ", token='" + token + '\'' +
                ", contentType='" + contentType + '\'' +
                ", clientId='" + clientId + '\'' +
                ", timeout=" + timeout +
                '}';
    }
}


/*

Output:

H:\Design Patterns\Creational>java PrototypeDemo
ApiRequestTemplate{endpoint='/users', token='Bearer xyz123', contentType='application/json', clientId='CLIENT-001', timeout=30}
ApiRequestTemplate{endpoint='/orders', token='Bearer xyz123', contentType='application/json', clientId='CLIENT-001', timeout=30}

*/