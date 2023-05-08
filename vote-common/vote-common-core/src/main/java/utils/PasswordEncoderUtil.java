package utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
    private static final int STRENGTH = 10; // 默认成本因素

    /**
     * 加密密码
     *
     * @param rawPassword 原始密码
     * @return 加密后的密码
     */
    public static String encode(String rawPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(STRENGTH);
        return encoder.encode(rawPassword);
    }

    /**
     * 验证密码
     *
     * @param rawPassword    原始密码
     * @param encodedPassword 加密后的密码
     * @return 如果密码匹配，返回true；否则返回false
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(STRENGTH);
        return encoder.matches(rawPassword, encodedPassword);
    }
}
