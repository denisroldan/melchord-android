package com.aiculabs.melchord.test.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import com.aiculabs.melchord.injection.component.ApplicationComponent;
import com.aiculabs.melchord.test.common.injection.module.ApplicationTestModule;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
