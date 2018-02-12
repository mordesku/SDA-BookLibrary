package pl.mordesku.sda.spring.hello;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created with notepad.exe.
 * Author: mprzy
 * Date: 12.02.2018
 * Time: 17:36
 */
@Order(2)
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
}
