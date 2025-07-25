package com.devsuperior.dscommerce.security.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PostAuthorize;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PostAuthorize("hasRole('ADMIN') or returnObject.body.client.id == authentication.principal.id")
public @interface AdminOrSelf {
}
