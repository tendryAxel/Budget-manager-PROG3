package java.com.example.walletprog3.utils.annotations;

import java.com.example.walletprog3.utils.ColumnType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GetColumn {
    ColumnType type();
}
