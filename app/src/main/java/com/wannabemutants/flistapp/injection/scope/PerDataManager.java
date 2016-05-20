package com.wannabemutants.flistapp.injection.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by cheglader on 5/14/16.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerDataManager {
}