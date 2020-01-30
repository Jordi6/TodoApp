import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
	public static void main(String[] args) {
	     BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	     String encoded = encoder.encode(args[1]);
	     System.out.println(args[0]+ " "+ args[1]);
	     System.out.println(encoded);
	 }
}
