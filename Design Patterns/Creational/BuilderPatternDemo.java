/*
@Author: Dynamic Duo Learners

REAL WORLD EXAMPLE:
User Registration where some fields are mandatory and some are optional.

A User may contain many fields such as:

- Name
- Email
- Phone
- City
- District
- Country

Some fields may be required while others are optional.


WITHOUT BUILDER PATTERN:
Client creates an object using a constructor with many parameters.

Example:

User user = new User(
        101,
        "ABC",
        "Java Developer",
        2000000,
	"ABC123@gmail.com",
        "Mumbai",
        "India",
        "9999999999",
        true,
        "OneNetwork"
);

Problems:
- Constructor becomes long and difficult to read.
- It is difficult to understand which value belongs to which field.
- Optional parameters make constructors complicated.
- Multiple constructor combinations can cause Telescoping Constructor problem.
- Adding new fields may require changing constructors and client code.


WITH BUILDER PATTERN:
Object is created step-by-step using clearly named methods.

Example:

User user = User.builder()
        .name("ABC")
        .email("ABC123@gmail.com")
        .city("Mumbai")
        .country("India")
        .build();


MAIN IDEA:
"Build a complex object step-by-step using readable named methods
 instead of a large constructor."


STRUCTURE:

Client
  |
  v
User.builder()
  |
  v
UserBuilder
  |
  +--> name(...)
  +--> email(...)
  +--> phone(...)
  +--> city(...)
  +--> district(...)
  +--> country(...)
  |
  v
build()
  |
  v
User Object
*/

public class BuilderPatternDemo {

    public static void main(String[] args) {

        User user = User.builder()
                .name("AP")
                .email("AP547@gmail.com")
                .phone("0401999625")
                .city("Bettiah")
		.district("W.Champaran")
                .country("India")
                .build();

        System.out.println(user);
    }
}

class User {

    private String name;
    private String email;
    private String phone;
    private String city;
    private String district;
    private String country;

    private User(UserBuilder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.phone = builder.phone;
        this.city = builder.city;
	this.district = builder.district;
        this.country = builder.country;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
		", district='" + district + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    
    //Builder
    static class UserBuilder {

        private String name;
        private String email;
        private String phone;
        private String city;
	private String district;
        private String country;
	
        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder city(String city) {
            this.city = city;
            return this;
        }

	public UserBuilder district(String district) {
            this.district = district;
            return this;
        }

        public UserBuilder country(String country) {
            this.country = country;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

/*

Output:
H:\Design Patterns\Creational>java BuilderPatternDemo
User{name='AP', email='AP547@gmail.com', phone='0401999625', city='Bettiah', district='W.Champaran', country='India'}

*/