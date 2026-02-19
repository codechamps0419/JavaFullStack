/*
@Author: Dynamic Duo Learners

REAL WORLD EXAMPLE:
API Request Template containing a nested Header object.

The main object contains:

ApiRequest
   |
   +--> endpoint
   |
   +--> Header
          |
          +--> authorization


SHALLOW COPY:
Only the parent object is copied.
Nested objects are still SHARED between original and copy.

Original Request ------+
                       |
                       v
                    Header
                       ^
                       |
Shallow Copy -----------+

So, if the cloned object's Header is modified,
the original object's Header also changes.


DEEP COPY:
Both the parent object and nested mutable objects are copied.

Original Request
      |
      v
   Header-1


Deep Copy
      |
      v
   Header-2

Now both objects have independent Header objects.

So, changing the cloned object's Header does NOT affect
the original object.


MAIN DIFFERENCE:

SHALLOW COPY
"New parent object, but nested objects are shared."

DEEP COPY
"New parent object and new copies of nested objects."


IN THIS EXAMPLE:

Shallow Copy:
original.header == shallowCopy.header
        -> SAME Header object

Deep Copy:
original.header != deepCopy.header
        -> DIFFERENT Header objects


WHEN TO CARE:
Deep copy is important when nested objects are mutable and the cloned
object must be changed independently from the original.

*/

public class PrototypeShallowDeepCopyDemo {

    public static void main(String[] args) {

        System.out.println("===== SHALLOW COPY =====");

        Header header1 = new Header("Bearer-123");

        ApiRequestShallow originalShallow =
                new ApiRequestShallow("/users", header1);

        ApiRequestShallow shallowCopy =
                originalShallow.clone();

        // Modify cloned object's header
        shallowCopy.getHeader()
                .setAuthorization("Bearer-999");

        System.out.println("Original : " + originalShallow);
        System.out.println("Copy     : " + shallowCopy);

        System.out.println("\n===== DEEP COPY =====");

        Header header2 = new Header("Bearer-123");

        ApiRequestDeep originalDeep =
                new ApiRequestDeep("/users", header2);

        ApiRequestDeep deepCopy =
                originalDeep.clone();

        // Modify cloned object's header
        deepCopy.getHeader()
                .setAuthorization("Bearer-999");

        System.out.println("Original : " + originalDeep);
        System.out.println("Copy     : " + deepCopy);
    }
}


class Header {

    private String authorization;

    public Header(String authorization) {
        this.authorization = authorization;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    @Override
    public String toString() {
        return authorization;
    }
}

//SHALLOW COPY: Copies parent object only. Nested objects are shared.

class ApiRequestShallow implements Cloneable {

    private String endpoint;
    private Header header;

    public ApiRequestShallow(String endpoint,
                             Header header) {

        this.endpoint = endpoint;
        this.header = header;
    }

    @Override
    public ApiRequestShallow clone() {

        try {
            return (ApiRequestShallow) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Header getHeader() {
        return header;
    }

    @Override
    public String toString() {

        return "ApiRequestShallow{" +
                "endpoint='" + endpoint + '\'' +
                ", header=" + header +
                '}';
    }
}

//DEEP COPY: Copies parent object and nested objects.

class ApiRequestDeep implements Cloneable {

    private String endpoint;
    private Header header;

    public ApiRequestDeep(String endpoint,
                          Header header) {

        this.endpoint = endpoint;
        this.header = header;
    }

    @Override
    public ApiRequestDeep clone() {

        Header copiedHeader =
                new Header(
                        this.header.getAuthorization()
                );

        return new ApiRequestDeep(
                this.endpoint,
                copiedHeader
        );
    }

    public Header getHeader() {
        return header;
    }

    @Override
    public String toString() {

        return "ApiRequestDeep{" +
                "endpoint='" + endpoint + '\'' +
                ", header=" + header +
                '}';
    }
}


/*

Output:

H:\Design Patterns\Creational>java PrototypeShallowDeepCopyDemo
===== SHALLOW COPY =====
Original : ApiRequestShallow{endpoint='/users', header=Bearer-999}
Copy     : ApiRequestShallow{endpoint='/users', header=Bearer-999}

===== DEEP COPY =====
Original : ApiRequestDeep{endpoint='/users', header=Bearer-123}
Copy     : ApiRequestDeep{endpoint='/users', header=Bearer-999}

*/