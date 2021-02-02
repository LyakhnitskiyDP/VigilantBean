package com.ldp.vigilantBean.exception.exceptionHandler;

import com.ldp.vigilantBean.exception.InvalidVerificationToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@ControllerAdvice
public class VerificationTokenExceptionHandler {

    private static final Logger log =
            LogManager.getLogger(ItemNotFoundExceptionHandler.class.getName());

    private MessageSource messageSource;

    public VerificationTokenExceptionHandler(
            @Autowired
            MessageSource messageSource) {

        this.messageSource = messageSource;
    }

    @ExceptionHandler
    public ModelAndView handleVerificationTokenException(
            InvalidVerificationToken exception,
            HttpServletRequest request) {

        Locale locale = request.getLocale();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("exceptions/invalidVerificationToken");

        InvalidVerificationToken.Reason reason = exception.getReason();

        switch (reason) {
            case EXPIRED:
                return handleExpiredVerificationToken(modelAndView, locale);

            case NO_SUCH_TOKEN:
                return handleNonexistingVerificationToken(modelAndView, locale);

            default:
                throw new IllegalStateException(
                        "No reason specified for InvalidVerificationToken exception"
                );
        }

    }

    private ModelAndView handleExpiredVerificationToken(
            ModelAndView modelAndView,
            Locale locale) {

        initExceptionModel(modelAndView,
                "expiredVerificationToken",
                locale);

        return modelAndView;
    }

    private ModelAndView handleNonexistingVerificationToken(
            ModelAndView modelAndView,
            Locale locale) {

        initExceptionModel(modelAndView,
                "nonexistingVerificationToken",
                locale);

        return modelAndView;
    }

    private void initExceptionModel(
            ModelAndView modelAndView,
            String exceptionName,
            Locale locale) {

        String message = messageSource.getMessage("view.exception." + exceptionName + ".message",
                null, locale);

        String title = messageSource.getMessage("view.exception." + exceptionName + ".title",
                null, locale);

        modelAndView.addObject("exceptionDescription", message);
        modelAndView.addObject("exceptionTitle", title);
        modelAndView.addObject("iconName", exceptionName + ".svg");
    }

}
