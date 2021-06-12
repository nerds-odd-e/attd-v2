package com.odde.atddv2;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("completeDoneTaskSwitch")
@Primary
public class CompleteDonePausedTaskSwitch extends PausedTaskSwitch {
}
