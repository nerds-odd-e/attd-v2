package com.odde.atddv2;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("anotherTaskSwitch")
@Primary
public class AnotherPausedTaskSwitch extends PausedTaskSwitch {
}
