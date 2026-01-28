/* @Author: Dynamic Duo Learners */

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexDemo {

    /*
     * 1. PRE-COMPILING REGEX PATTERNS (Performance Best Practice)
     * * In Java, using String.matches() compiles the regex string into a Pattern object 
     * every single time it's called. By declaring them as 'static final' constants, 
     * the compilation happens only ONCE when the class loads, saving CPU cycles.
     * * Note: Backslashes (\) are escaped as (\\) in Java strings.
     */

    // Explanation of Phone Pattern:
    // ^                   : Start of string
    // (\\+\\d{1,3}[- ]?)? : Optional country code (e.g., +1 or +91) followed by an optional dash or space
    // \\(?\\d{3}\\)?      : Area code - 3 digits, optionally enclosed in parentheses (e.g., 123 or (123))
    // [- ]?               : Optional separator (dash or space)
    // \\d{3}              : 3 digits (exchange code)
    // [- ]?               : Another optional separator
    // \\d{4}              : Last 4 digits
    // $                   : End of string
    private static final Pattern PHONE_PATTERN = 
        Pattern.compile("^(\\+\\d{1,3}[- ]?)?\\(?\\d{3}\\)?[- ]?\\d{3}[- ]?\\d{4}$");

    // Explanation of Email Pattern:
    // ^                   : Start of string
    // [a-zA-Z0-9._%+-]+   : Local part - matches one or more alphanumeric characters or symbols like ., _, %, +, -
    // @                   : Must contain an literal '@' symbol
    // [a-zA-Z0-9.-]+      : Domain name - matches letters, numbers, dots, or hyphens
    // \\.                 : Must contain a literal dot (.) before the top-level domain
    // [a-zA-Z]{2,6}       : Top-level domain (TLD) - must be 2 to 6 letters long (e.g., .com, .org, .co.in)
    // $                   : End of string
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");

    // Explanation of Password Pattern (Uses Lookaheads to enforce rules):
    // ^                   : Start of string
    // (?=.*[a-z])         : Positive lookahead - ensures at least one lowercase letter exists anywhere
    // (?=.*[A-Z])         : Positive lookahead - ensures at least one uppercase letter exists anywhere
    // (?=.*\\d)           : Positive lookahead - ensures at least one digit exists anywhere
    // (?=.*[@$!%*?&])     : Positive lookahead - ensures at least one special character from this set exists
    // [A-Za-z\\d@$!%*?&]{8,} : Matches any valid combinations of allowed characters, requiring a MINIMUM length of 8
    // $                   : End of string
    private static final Pattern PASSWORD_PATTERN = 
        Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

    // Explanation of UUID Pattern:
    // Matches the hex format of an 8-4-4-4-12 character layout (Standard Java UUID layout)
    // [0-9a-fA-F]{X}      : Expects exactly 'X' amount of hexadecimal characters (case-insensitive) separated by dashes (-)
    private static final Pattern UUID_PATTERN = 
        Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

    // Explanation of IPv4 Pattern:
    // Matches 4 blocks of numbers separated by dots (e.g., X.X.X.X), where each X is between 0 and 255.
    // (25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?) : Validates a single block to make sure it doesn't exceed 255.
    // \\.                 : Escaped literal dot separating the IP blocks
    // {3}                 : Repeats the block structure exactly 3 times, ending with a final 4th block evaluation.
    private static final Pattern IP_V4_PATTERN = 
        Pattern.compile("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");


    /*
     * 2. VALIDATION HELPER METHODS
     * * These methods accept an input string, check for null safety, 
     * bind the string to the compiled Pattern using .matcher(), 
     * and call .matches() to execute the regex search.
     */

    public static boolean isValidPhone(String phone) {
        // Returns true only if phone is not null AND completely fits the phone pattern layout
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isStrongPassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

    public static boolean isValidUUID(String uuid) {
        return uuid != null && UUID_PATTERN.matcher(uuid).matches();
    }

    public static boolean isValidIPv4(String ip) {
        return ip != null && IP_V4_PATTERN.matcher(ip).matches();
    }


    /*
     * 3. MAIN RUNTIME TEST SUITE
     */
    public static void main(String[] args) {
        System.out.println("=== JAVA REGEX VALIDATION DEMO ===\n");

        // --- Email Validation Checks ---
        String[] emails = {"developer.dynamicduo@company.com", "invalid-email@com", "user_0419@domain.co.in", "appu04011999@gmail.com"};
        System.out.println("--- Email Validations ---");
        for (String email : emails) {
            // printf formats output: %s placeholder for String, %b for boolean, %n for new line
            System.out.printf("Is '%s' valid? -> %b%n", email, isValidEmail(email));
        }

        // --- Phone Number Validation Checks ---
        String[] phones = {"123-456-7890", "+1 (123) 456-7890", "555-XYZ-1234", "9075359303"};
        System.out.println("\n--- Phone Number Validations ---");
        for (String phone : phones) {
            System.out.printf("Is '%s' valid? -> %b%n", phone, isValidPhone(phone));
        }

        // --- Password Security Validation Checks ---
        String[] passwords = {"Password123!", "weakpass", "ONLYUPPER123!", "lowercase123!"};
        System.out.println("\n--- Password Strength Validations (Min 8 chars, 1 Upper, 1 Lower, 1 Digit, 1 Special) ---");
        for (String password : passwords) {
            System.out.printf("Is '%s' strong? -> %b%n", password, isStrongPassword(password));
        }

        // --- Network IPv4 Validation Checks ---
        String[] ips = {"192.168.1.1", "256.100.0.1", "10.0.0.254"};
        System.out.println("\n--- IPv4 Validations ---");
        for (String ip : ips) {
            System.out.printf("Is '%s' a valid IP? -> %b%n", ip, isValidIPv4(ip));
        }

        // --- Database/System UUID Validation Checks ---
        String[] uuids = {"4f8b92d1-7c5e-4b2a-a1f3-8e9d0c2b1a4f", "not-a-valid-uuid-string"};
        System.out.println("\n--- UUID Validations ---");
        for (String uuid : uuids) {
            System.out.printf("Is '%s' a valid UUID? -> %b%n", uuid, isValidUUID(uuid));
        }
    }
}

/*

Output:

=== JAVA REGEX VALIDATION DEMO ===

--- Email Validations ---
Is 'developer.dynamicduo@company.com' valid? -> true
Is 'invalid-email@com' valid? -> false
Is 'user_0419@domain.co.in' valid? -> true
Is 'appu04011999@gmail.com' valid? -> true

--- Phone Number Validations ---
Is '123-456-7890' valid? -> true
Is '+1 (123) 456-7890' valid? -> true
Is '555-XYZ-1234' valid? -> false
Is '9075359303' valid? -> true

--- Password Strength Validations (Min 8 chars, 1 Upper, 1 Lower, 1 Digit, 1 Special) ---
Is 'Password123!' strong? -> true
Is 'weakpass' strong? -> false
Is 'ONLYUPPER123!' strong? -> false
Is 'lowercase123!' strong? -> false

--- IPv4 Validations ---
Is '192.168.1.1' a valid IP? -> true
Is '256.100.0.1' a valid IP? -> false
Is '10.0.0.254' a valid IP? -> true

--- UUID Validations ---
Is '4f8b92d1-7c5e-4b2a-a1f3-8e9d0c2b1a4f' a valid UUID? -> true
Is 'not-a-valid-uuid-string' a valid UUID? -> false

*/