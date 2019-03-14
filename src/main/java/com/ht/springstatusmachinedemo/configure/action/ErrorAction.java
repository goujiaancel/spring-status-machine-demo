package com.ht.springstatusmachinedemo.configure.action;

import lombok.extern.java.Log;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Component
@Log
public class ErrorAction implements Action<String,String> {

    @Override
    public void execute(StateContext<String, String> context) {
        String message =  context.getException().getMessage();
        log.info("errAction error message : "+message);
    }
}
