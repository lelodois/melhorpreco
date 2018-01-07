package br.com.lelo.melhorpreco.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.transaction.Transactional;

@Transactional(rollbackOn = Exception.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MelhorPrecoTransaction {

}
